import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Kia
 */

public class ItemDatabase {

    public static void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS items ("
                + "item_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "item_name TEXT NOT NULL, "
                + "category_id INTEGER NOT NULL, "
                + "quantity INTEGER, "
                + "unit_price REAL, "
                + "supplier_name TEXT, "
                + "FOREIGN KEY (category_id) REFERENCES categories(category_id)"
                + ")";

        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Something went wrong creating the items table: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error while closing: " + e.getMessage());
            }
        }
    }

    public static boolean addItem(Item item) {
        String sql = "INSERT INTO items (item_name, category_id, quantity, unit_price, supplier_name) VALUES (?, ?, ?, ?, ?)";

        boolean success = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DatabaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, item.getItemName());
            pstmt.setInt(2, item.getCategoryId());
            pstmt.setInt(3, item.getQuantity());
            pstmt.setDouble(4, item.getUnitPrice());
            pstmt.setString(5, item.getSupplierName());

            int rowsChanged = pstmt.executeUpdate();
            if (rowsChanged > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error while adding item: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error while closing: " + e.getMessage());
            }
        }
        return success;
    }

    // this join pulls the category_name along with the item row, purely
    // for display - it does not change what is stored, just what comes back.
    private static final String SELECT_WITH_CATEGORY =
            "SELECT i.item_id, i.item_name, i.category_id, c.category_name, "
            + "i.quantity, i.unit_price, i.supplier_name "
            + "FROM items i "
            + "JOIN categories c ON i.category_id = c.category_id";

    public static ArrayList<Item> getAllItems() {
        ArrayList<Item> itemList = new ArrayList<Item>();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SELECT_WITH_CATEGORY);

            while (rs.next()) {
                itemList.add(buildItemFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error while getting items: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error while closing: " + e.getMessage());
            }
        }
        return itemList;
    }

    public static Item getItemById(int itemId) {
        Item item = null;
        String sql = SELECT_WITH_CATEGORY + " WHERE i.item_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, itemId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                item = buildItemFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error while getting the item: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error while closing: " + e.getMessage());
            }
        }
        return item;
    }

    // related-record query: every item that belongs to one specific category
    public static ArrayList<Item> getItemsByCategory(int categoryId) {
        ArrayList<Item> itemList = new ArrayList<Item>();
        String sql = SELECT_WITH_CATEGORY + " WHERE i.category_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, categoryId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                itemList.add(buildItemFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error while getting items for that category: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error while closing: " + e.getMessage());
            }
        }
        return itemList;
    }

    // related-record count: used by CategoryService to decide whether a
    // category is allowed to be deleted or not.
    public static int countItemsByCategory(int categoryId) {
        int count = 0;
        String sql = "SELECT COUNT(*) AS total FROM items WHERE category_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, categoryId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (SQLException e) {
            System.out.println("Error while counting items: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error while closing: " + e.getMessage());
            }
        }
        return count;
    }

    public static boolean updateItem(Item item) {
        String sql = "UPDATE items SET item_name = ?, category_id = ?, quantity = ?, unit_price = ?, supplier_name = ? WHERE item_id = ?";

        boolean success = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DatabaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, item.getItemName());
            pstmt.setInt(2, item.getCategoryId());
            pstmt.setInt(3, item.getQuantity());
            pstmt.setDouble(4, item.getUnitPrice());
            pstmt.setString(5, item.getSupplierName());
            pstmt.setInt(6, item.getItemId());

            int rowsChanged = pstmt.executeUpdate();
            if (rowsChanged > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error while updating item: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error while closing: " + e.getMessage());
            }
        }
        return success;
    }

    public static boolean deleteItem(int itemId) {
        String sql = "DELETE FROM items WHERE item_id = ?";

        boolean success = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DatabaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, itemId);

            int rowsChanged = pstmt.executeUpdate();
            if (rowsChanged > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error while deleting item: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error while closing: " + e.getMessage());
            }
        }
        return success;
    }

    // small private helper so I don't repeat the same lines in
    // getAllItems, getItemById, and getItemsByCategory
    private static Item buildItemFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("item_id");
        String name = rs.getString("item_name");
        int categoryId = rs.getInt("category_id");
        String categoryName = rs.getString("category_name");
        int quantity = rs.getInt("quantity");
        double price = rs.getDouble("unit_price");
        String supplier = rs.getString("supplier_name");

        Item item = new Item(id, name, categoryId, quantity, price, supplier);
        item.setCategoryName(categoryName);
        return item;
    }
}