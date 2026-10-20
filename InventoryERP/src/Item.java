public class Item {

    private int itemId;
    private String itemName;
    private int categoryId;
    private String categoryName; // display-only, filled in by a JOIN in the DAO
    private int quantity;
    private double unitPrice;
    private String supplierName;

    public Item() {
    }

    // used when adding a new item - no itemId yet, the database assigns it
    public Item(String itemName, int categoryId, int quantity, double unitPrice, String supplierName) {
        this.itemName = itemName;
        this.categoryId = categoryId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.supplierName = supplierName;
    }

    // used when reading an item back from the database (id already exists)
    public Item(int itemId, String itemName, int categoryId, int quantity, double unitPrice, String supplierName) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.categoryId = categoryId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.supplierName = supplierName;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String toString() {
        String line = "";
        line = line + "ID: " + itemId;
        line = line + " | Name: " + itemName;
        line = line + " | Category: " + categoryName;
        line = line + " | Quantity: " + quantity;
        line = line + " | Unit Price: " + unitPrice;
        line = line + " | Supplier: " + supplierName;
        return line;
    }
}