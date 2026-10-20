/**
 * @author Kia
 */
public class Category {

    private int categoryId;
    private String categoryName;

    public Category() {
    }

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    // I override toString() so this object can be dropped straight into a
    // JComboBox and it will display the category name, not Category@1a2b3c.
    public String toString() {
        return categoryName;
    }
}