# InventoryERP — Item / Product Master Sub-Module

**Course:** Midterm Project — Inventory Module
**Architecture:** UI → Service/Control → DAO → HikariCP → SQLite

This README walks through exactly how the project is wired together, file by file, so you can explain any part of it confidently during your presentation and answer follow-up questions without hesitating.

---

## 1. The Big Picture

The project has four layers, and each layer is only allowed to talk to the layer directly below it:

```
InventoryFrame.java  (UI)
        |
        v
ItemService.java / CategoryService.java   (Service/Control — all business rules live here)
        |
        v
ItemDatabase.java / CategoryDatabase.java  (DAO — only runs SQL, no decisions)
        |
        v
DatabaseConnection.java  (HikariCP connection pool)
        |
        v
inventory_database.db  (SQLite file on disk)
```

 **"InventoryFrame.java only calls the functionality, not contain the functionality"** — means every button in the UI does exactly three things: read raw values from the fields, call one Service method, and show whatever message comes back. It never validates anything, never touches SQL, and never decides whether an operation is allowed.

---

## 2. What Each File Does

### `InventoryFrame.java` — UI Layer
The Swing JFrame. Holds all the text fields, combo boxes, buttons, and the table. Every button's `ActionPerformed` method is intentionally short — it gathers input, calls a Service method inside a `try/catch`, and either shows a success message or shows `e.getMessage()` from a caught `BusinessRuleException`. It also has small UI-only helpers (`loadCategoryCombos`, `loadTableData`, `clearFields`, `displayItemInFields`, `selectCategoryInCombo`) that move data between Java objects and Swing components — these don't make decisions, they just display.

### `ItemService.java` and `CategoryService.java` — Service/Control Layer
This is where **every** validation rule and business rule lives. Nothing is allowed to reach the database unless it passes through here first. If a rule is broken, the method throws a `BusinessRuleException` with a human-readable message, which the UI catches and displays. Full list of rules in Section 5 below.

### `ItemDatabase.java` and `CategoryDatabase.java` — DAO Layer
"DAO" stands for Data Access Object. Each method here is a single SQL operation — one method = one `INSERT`, one `SELECT`, one `UPDATE`, or one `DELETE` — using `PreparedStatement` so values are parameterized (never string-concatenated into SQL, which protects against SQL injection). These classes have zero knowledge of business rules; they will run whatever they're told, correct or not. They don't even know what a "duplicate" is — that's the Service layer's job.

### `DatabaseConnection.java` — Connection Pool
Sets up one `HikariDataSource` using HikariCP, pointed at `jdbc:sqlite:inventory_database.db`. Every DAO method calls `DatabaseConnection.getConnection()` to borrow a connection from this pool and closes it in a `finally` block when done. It also runs `PRAGMA foreign_keys = ON;` on every new connection — without this one line, SQLite would silently ignore the `FOREIGN KEY` constraint on the `items` table.

### `Category.java` and `Item.java` — Model classes
Plain data holders (fields + getters/setters + constructors). `Category.toString()` is overridden to return just the category name, so a `Category` object can be dropped directly into a `JComboBox` and display correctly instead of showing `Category@1a2b3c`.

### `BusinessRuleException.java` — Custom Exception
A small checked exception. The Service layer throws it to reject an operation; the UI catches it in exactly one `catch` block per handler and shows the message in a `JOptionPane`. This is the single channel through which "no" travels from the Service layer back up to the user.

---

## 3. Database Schema (1-to-Many Relationship)

```sql
CREATE TABLE categories (
    category_id   INTEGER PRIMARY KEY AUTOINCREMENT,
    category_name TEXT NOT NULL UNIQUE
);

CREATE TABLE items (
    item_id       INTEGER PRIMARY KEY AUTOINCREMENT,
    item_name     TEXT NOT NULL,
    category_id   INTEGER NOT NULL,
    quantity      INTEGER,
    unit_price    REAL,
    supplier_name TEXT,
    FOREIGN KEY (category_id) REFERENCES categories(category_id)
);
```

One `Category` (the "1" side) can have many `Item` rows pointing back at it through `category_id` (the "many" side). This is enforced two ways:
1. **Structurally** — the `FOREIGN KEY` constraint, which only works because `DatabaseConnection` turns it on with `PRAGMA foreign_keys = ON;`.
2. **Behaviorally** — `CategoryService.deleteCategory()` checks `ItemDatabase.countItemsByCategory(categoryId)` before allowing a delete. If any items still reference that category, the delete is blocked with a message naming the category and how many items are attached. This is a deliberate design choice: the parent is protected rather than cascading the delete, so you never lose item data by accident.

The relationship is also demonstrated through a **related-record query**: `ItemDatabase.getItemsByCategory(categoryId)` returns every item belonging to one category — this is the "handling of related records" the rubric asks for. It's used internally by the duplicate-name check in `ItemService.addItem()`.

For *display*, `ItemDatabase` uses a JOIN so the item table on screen shows the category's name instead of just its numeric ID:

```sql
SELECT i.item_id, i.item_name, i.category_id, c.category_name,
       i.quantity, i.unit_price, i.supplier_name
FROM items i
JOIN categories c ON i.category_id = c.category_id
```

---

## 4. Full Walkthrough: What Happens When You Click "ADD"

This is the sequence you should be ready to narrate live, since it touches every layer:

1. **UI:** `btnAddActionPerformed` runs. It reads `txtItemName.getText()`, casts `cmbCategory.getSelectedItem()` to a `Category`, and reads the quantity/price/supplier fields as raw `String`s. No parsing, no validation happens here.
2. **UI → Service:** It calls `itemService.addItem(itemName, selectedCategory, quantityText, unitPriceText, supplierName)` inside a `try` block.
3. **Service:** `ItemService.addItem()` runs, in order:
   - Checks `selectedCategory != null` ("Please select a category.")
   - Calls `validateCommonFields()` — checks the item name isn't blank, and that the category still exists in the database.
   - Calls `parseQuantity()` and `parseUnitPrice()` — these both try `Integer.parseInt` / `Double.parseDouble` and catch `NumberFormatException`, converting it into a `BusinessRuleException` with a friendly message, and both reject negative numbers.
   - Calls `ItemDatabase.getItemsByCategory(categoryId)` and loops through the results to check for a duplicate name in that same category.
   - If everything passed, builds a new `Item` object and calls `ItemDatabase.addItem(newItem)`.
4. **Service → DAO:** `ItemDatabase.addItem()` opens a connection via `DatabaseConnection.getConnection()`, prepares an `INSERT` statement, binds the five values with `PreparedStatement.setString`/`setInt`/`setDouble`, executes it, and closes everything in `finally`.
5. **DAO → Database:** SQLite writes the row and auto-generates `item_id`.
6. **Back up the chain:** If any step 3 check failed, a `BusinessRuleException` propagates straight back up to the UI's `catch` block, and `JOptionPane.showMessageDialog` shows that exact message. If it succeeded, the UI shows "Item added successfully.", clears the fields, and calls `loadTableData()` to refresh the table from the database.

Every other button (Update, Delete, Search, Add Category, Delete Category) follows this same shape — UI reads raw values → one Service call → Service validates and calls the DAO → DAO runs one SQL statement → result/exception flows back to a single `catch` block in the UI.

---

## 5. Complete List of Business Rules (for Q&A)

| Rule | Where enforced | What happens if broken |
|---|---|---|
| Item name cannot be empty | `ItemService.validateCommonFields` | `BusinessRuleException("Item name cannot be empty.")` |
| A category must be selected | `ItemService.addItem` / `updateItem` | `BusinessRuleException("Please select a category.")` |
| Selected category must still exist | `ItemService.validateCommonFields` | `BusinessRuleException("That category no longer exists...")` |
| Quantity must be a whole number | `ItemService.parseQuantity` | `BusinessRuleException("Quantity must be a whole number.")` |
| Quantity cannot be negative | `ItemService.parseQuantity` | `BusinessRuleException("Quantity cannot be negative.")` |
| Unit price must be a number | `ItemService.parseUnitPrice` | `BusinessRuleException("Unit price must be a number.")` |
| Unit price cannot be negative | `ItemService.parseUnitPrice` | `BusinessRuleException("Unit price cannot be negative.")` |
| No duplicate item name within the same category | `ItemService.addItem` | `BusinessRuleException("An item named ... already exists in this category.")` |
| Item ID must be a valid number (search/update/delete) | `ItemService.parseItemId` | `BusinessRuleException("Please enter a valid item ID.")` |
| Item must exist (search/update/delete) | `ItemService` | `BusinessRuleException("No item found with that ID.")` |
| Category name cannot be empty | `CategoryService.addCategory` | `BusinessRuleException("Category name cannot be empty.")` |
| No duplicate category names | `CategoryService.addCategory` | `BusinessRuleException("A category named ... already exists.")` |
| Cannot delete a category that still has items | `CategoryService.deleteCategory` | `BusinessRuleException("Cannot delete ... because it still has N item(s) assigned to it.")` |

---

## 6. Why HikariCP Matters Here

Without a pool, every database operation would open a brand-new raw JDBC connection and close it — slow, and wasteful for an app that hits the database constantly (every Add, Update, Delete, Search, and View All). HikariCP keeps a small pool of ready-to-use connections open (`setMaximumPoolSize(1)` here, since SQLite is a single-file, single-writer database and a student desktop app doesn't need concurrent connections) and hands them out on request via `getConnection()`. It's also the single place where `PRAGMA foreign_keys = ON;` is guaranteed to run on every connection — that's what makes the FK constraint on `items.category_id` actually mean something instead of just being decorative SQL text.

## 7. Why the DAO Pattern Matters Here

Separating "what SQL to run" (DAO) from "whether this operation is allowed" (Service) means each class has exactly one job and one reason to change. If your instructor asked you to switch databases later (SQLite → MySQL, for example), only the DAO layer and `DatabaseConnection` would need to change — the Service layer's business rules and the UI wouldn't need to be touched at all, because they never knew SQL existed in the first place.

---

## 8. Likely Q&A Questions and How to Answer Them

**"Why does the UI only call one method per button?"**
Because the architecture requires `InventoryFrame.java` to call functionality, not contain it. All decision-making — validation, duplicate checks, business rules — lives in the Service layer so it can be tested and reused independently of the UI.

**"What happens if I try to delete a category that has items in it?"**
`CategoryService.deleteCategory()` calls `ItemDatabase.countItemsByCategory()` first. If the count is greater than zero, it throws a `BusinessRuleException` naming the category and how many items are blocking it, and the delete never reaches the database. This protects the "many" side of the relationship from being orphaned.

**"How is the one-to-many relationship actually enforced, not just designed?"**
Two ways: structurally, via the `FOREIGN KEY` constraint on `items.category_id` (enforced because `DatabaseConnection` turns on `PRAGMA foreign_keys = ON`), and behaviorally, via the delete-block business rule described above.

**"What's the difference between a DAO method and a Service method?"**
A DAO method runs exactly one SQL statement and returns a result — it has no opinion about whether that statement *should* run. A Service method decides whether an operation is allowed, and only calls the DAO once it's sure.

**"Why use PreparedStatement instead of building the SQL string directly?"**
`PreparedStatement` binds values as parameters instead of concatenating them into the SQL text, which prevents SQL injection and also handles type conversion safely.

**"What does HikariCP actually give you over a plain `DriverManager.getConnection()` call?"**
Connection reuse (avoiding the overhead of opening a fresh connection every time), and a single guaranteed place to configure connection-level settings like the foreign-key pragma.

**"Where would I add a new validation rule, like a maximum quantity limit?"**
Inside `ItemService`, in `parseQuantity()` or as a new check in `addItem`/`updateItem` — never in `InventoryFrame.java`.

**"What happens if two people try to add an item with the same name but different casing, like 'Coke' and 'coke'?"**
The duplicate check in `ItemService.addItem()` uses `.equalsIgnoreCase()`, so those are treated as the same name and the second one is blocked.

**"Why is `categoryId` passed around as a whole `Category` object in some places instead of just an int?"**
So the "category must be selected" check can happen in the Service layer (checking for `null`) instead of the UI needing to do that check itself — keeping that decision out of `InventoryFrame.java`.

---

## 9. Requirements Checklist

| # | Requirement | Status | Evidence |
|---|---|---|---|
| 1 | Layered architecture: UI → Service/Control → DAO → Database | **Met** | `InventoryFrame.java` and both Service classes contain zero SQL/JDBC code (`SQLException`, `PreparedStatement`, `getConnection()` only appear in `CategoryDatabase.java`, `ItemDatabase.java`, `DatabaseConnection.java`). Every button handler is a thin read-input → call-Service → show-result wrapper. |
| 2 | One-to-many relationship | **Met** | Real `FOREIGN KEY (category_id) REFERENCES categories(category_id)`, enforced via `PRAGMA foreign_keys = ON`; a related-record query (`getItemsByCategory`); and a business rule blocking deletion of a category with dependent items. Verified working in a live test run. |
| 3 | Necessary CRUD operations | **Met** | Items: full Add / Search / Update / Delete / View All, all confirmed working against a live database. Categories: Add / Delete, confirmed working. |
| 4 | HikariCP and DAO approach | **Met** | `DatabaseConnection.java` configures a real `HikariDataSource`; every DAO method borrows its connection from that pool via `getConnection()`. |
| 5 | Fully functional and integrated | **Met** | Ran a complete scripted pass through add → duplicate-block → search → update (including moving an item across categories) → delete → category-delete-blocked-by-dependents → cleanup, all against the actual compiled classes and a live SQLite database. Everything behaved correctly. |

All five requirements are satisfied by the code in `InventoryERP.zip` as it currently stands, verified by compiling and running it directly rather than just reading it.
