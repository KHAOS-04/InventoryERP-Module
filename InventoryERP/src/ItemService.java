import java.util.ArrayList;

/**
 * @author Kia
 */

// Service/Control layer for items. ALL validation and business rules live
// here. ItemDatabase just runs SQL; InventoryFrame just calls these methods
// and shows whatever message comes back (success, or a BusinessRuleException).
public class ItemService {

    public void addItem(String itemName, Category selectedCategory, String quantityText, String unitPriceText, String supplierName)
        throws BusinessRuleException {

    if (selectedCategory == null) {
        throw new BusinessRuleException("Please select a category.");
    }
    int categoryId = selectedCategory.getCategoryId();

    validateCommonFields(itemName, categoryId);
    int quantity = parseQuantity(quantityText);
    double unitPrice = parseUnitPrice(unitPriceText);

    ArrayList<Item> existingItems = ItemDatabase.getItemsByCategory(categoryId);
    for (int i = 0; i < existingItems.size(); i++) {
        if (existingItems.get(i).getItemName().equalsIgnoreCase(itemName.trim())) {
            throw new BusinessRuleException("An item named \"" + itemName.trim()
                    + "\" already exists in this category.");
        }
    }

    Item newItem = new Item(itemName.trim(), categoryId, quantity, unitPrice, supplierName);
    boolean success = ItemDatabase.addItem(newItem);
    if (!success) {
        throw new BusinessRuleException("Something went wrong, the item was not added.");
    }
}

public void updateItem(String itemIdText, String itemName, Category selectedCategory, String quantityText, String unitPriceText, String supplierName)
        throws BusinessRuleException {

    int itemId = parseItemId(itemIdText);

    Item existingItem = ItemDatabase.getItemById(itemId);
    if (existingItem == null) {
        throw new BusinessRuleException("No item found with that ID.");
    }

    if (selectedCategory == null) {
        throw new BusinessRuleException("Please select a category.");
    }
    int categoryId = selectedCategory.getCategoryId();

    validateCommonFields(itemName, categoryId);
    int quantity = parseQuantity(quantityText);
    double unitPrice = parseUnitPrice(unitPriceText);

    Item updatedItem = new Item(itemId, itemName.trim(), categoryId, quantity, unitPrice, supplierName);
    boolean success = ItemDatabase.updateItem(updatedItem);
    if (!success) {
        throw new BusinessRuleException("Something went wrong, the item was not updated.");
    }
}

    public void deleteItem(String itemIdText) throws BusinessRuleException {
        int itemId = parseItemId(itemIdText);

        Item existingItem = ItemDatabase.getItemById(itemId);
        if (existingItem == null) {
            throw new BusinessRuleException("No item found with that ID.");
        }

        boolean success = ItemDatabase.deleteItem(itemId);
        if (!success) {
            throw new BusinessRuleException("Something went wrong, the item was not deleted.");
        }
    }

    public Item searchItem(String itemIdText) throws BusinessRuleException {
        int itemId = parseItemId(itemIdText);

        Item item = ItemDatabase.getItemById(itemId);
        if (item == null) {
            throw new BusinessRuleException("No item found with that ID.");
        }
        return item;
    }

    public ArrayList<Item> getAllItems() {
        return ItemDatabase.getAllItems();
    }

    private void validateCommonFields(String itemName, int categoryId) throws BusinessRuleException {
        if (itemName == null || itemName.trim().equals("")) {
            throw new BusinessRuleException("Item name cannot be empty.");
        }
        if (categoryId <= 0) {
            throw new BusinessRuleException("Please select a category.");
        }
        if (CategoryDatabase.getCategoryById(categoryId) == null) {
            throw new BusinessRuleException("That category no longer exists. Please pick another one.");
        }
    }

    private int parseItemId(String itemIdText) throws BusinessRuleException {
        try {
            return Integer.parseInt(itemIdText.trim());
        } catch (NumberFormatException e) {
            throw new BusinessRuleException("Please enter a valid item ID.");
        }
    }

    private int parseQuantity(String quantityText) throws BusinessRuleException {
        int quantity;
        try {
            quantity = Integer.parseInt(quantityText.trim());
        } catch (NumberFormatException e) {
            throw new BusinessRuleException("Quantity must be a whole number.");
        }
        if (quantity < 0) {
            throw new BusinessRuleException("Quantity cannot be negative.");
        }
        return quantity;
    }

    private double parseUnitPrice(String unitPriceText) throws BusinessRuleException {
        double unitPrice;
        try {
            unitPrice = Double.parseDouble(unitPriceText.trim());
        } catch (NumberFormatException e) {
            throw new BusinessRuleException("Unit price must be a number.");
        }
        if (unitPrice < 0) {
            throw new BusinessRuleException("Unit price cannot be negative.");
        }
        return unitPrice;
    }
    
    
}   