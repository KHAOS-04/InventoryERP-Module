import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author Kia
 */

public class CategoryDatabase {

    public static void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS categories ("
                + "category_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "category_name TEXT NOT NULL UNIQUE"
                + ")";

        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Something went wrong creating the categories table: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error while closing: " + e.getMessage());
            }
        }
    }

    public static boolean addCategory(Category category) {
        String sql = "INSERT INTO categories (category_name) VALUES (?)";

        boolean success = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DatabaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, category.getCategoryName());

            int rowsChanged = pstmt.executeUpdate();
            if (rowsChanged > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error while adding category: " + e.getMessage());
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

    public static ArrayList<Category> getAllCategories() {
        ArrayList<Category> categoryList = new ArrayList<Category>();
        String sql = "SELECT category_id, category_name FROM categories ORDER BY category_name";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("category_id");
                String name = rs.getString("category_name");
                categoryList.add(new Category(id, name));
            }
        } catch (SQLException e) {
            System.out.println("Error while getting categories: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error while closing: " + e.getMessage());
            }
        }
        return categoryList;
    }

    public static Category getCategoryById(int categoryId) {
        Category category = null;
        String sql = "SELECT category_id, category_name FROM categories WHERE category_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, categoryId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("category_id");
                String name = rs.getString("category_name");
                category = new Category(id, name);
            }
        } catch (SQLException e) {
            System.out.println("Error while getting the category: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error while closing: " + e.getMessage());
            }
        }
        return category;
    }

    public static Category getCategoryByName(String categoryName) {
        Category category = null;
        String sql = "SELECT category_id, category_name FROM categories WHERE category_name = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, categoryName);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("category_id");
                String name = rs.getString("category_name");
                category = new Category(id, name);
            }
        } catch (SQLException e) {
            System.out.println("Error while getting the category: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error while closing: " + e.getMessage());
            }
        }
        return category;
    }

    public static boolean deleteCategory(int categoryId) {
        String sql = "DELETE FROM categories WHERE category_id = ?";

        boolean success = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DatabaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, categoryId);

            int rowsChanged = pstmt.executeUpdate();
            if (rowsChanged > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error while deleting category: " + e.getMessage());
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
}