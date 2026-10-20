/**
 *
 * @author Kia
 */

import java.util.ArrayList;

// Service/Control layer for categories. This is the only class allowed to
// decide whether a category operation is ALLOWED - CategoryDatabase just
// executes whatever SQL it is told to run, it does not know any rules.
public class CategoryService {

    public void addCategory(String categoryName) throws BusinessRuleException {
        if (categoryName == null || categoryName.trim().equals("")) {
            throw new BusinessRuleException("Category name cannot be empty.");
        }
        if (CategoryDatabase.getCategoryByName(categoryName.trim()) != null) {
            throw new BusinessRuleException("A category named \"" + categoryName.trim() + "\" already exists.");
        }
        boolean success = CategoryDatabase.addCategory(new Category(categoryName.trim()));
        if (!success) {
            throw new BusinessRuleException("Something went wrong, the category was not added.");
        }
    }
    
    public void deleteCategory(Category selectedCategory) throws BusinessRuleException {

        if (selectedCategory == null) {
            throw new BusinessRuleException("Please select a category to delete.");
        }

        int categoryId = selectedCategory.getCategoryId();

        int itemCount = ItemDatabase.countItemsByCategory(categoryId);
        if (itemCount > 0) {
            throw new BusinessRuleException("Cannot delete \"" + selectedCategory.getCategoryName()
                    + "\" because it still has " + itemCount + " item(s) assigned to it.");
        }

        CategoryDatabase.deleteCategory(categoryId);
    }

    public ArrayList<Category> getAllCategories() {
        return CategoryDatabase.getAllCategories();
    }
}