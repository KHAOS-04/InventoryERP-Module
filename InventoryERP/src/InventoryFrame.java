import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 * @author Kia
 */

public class InventoryFrame extends javax.swing.JFrame {

    private ItemService itemService = new ItemService();
    private CategoryService categoryService = new CategoryService();
    public InventoryFrame() {
        initComponents();

        CategoryDatabase.createTableIfNotExists();
        ItemDatabase.createTableIfNotExists();

        loadCategoryCombos();
        loadTableData();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        pnlHeader = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        lblSubtitle = new javax.swing.JLabel();
        pnlHeaderAccent = new javax.swing.JPanel();
        pnlCategory = new javax.swing.JPanel();
        lblCategoryTitle = new javax.swing.JLabel();
        lblSelectCategory = new javax.swing.JLabel();
        cmbDeleteCategory = new javax.swing.JComboBox<>();
        lblNewCategory = new javax.swing.JLabel();
        txtNewCategoryName = new javax.swing.JTextField();
        btnAddCategory = new javax.swing.JButton();
        btnDeleteCategory = new javax.swing.JButton();
        pnlItemInfo = new javax.swing.JPanel();
        lblItemInfoTitle = new javax.swing.JLabel();
        lblItemId = new javax.swing.JLabel();
        txtItemId = new javax.swing.JTextField();
        lblItemName = new javax.swing.JLabel();
        txtItemName = new javax.swing.JTextField();
        cmbCategory = new javax.swing.JComboBox<>();
        lblQuantity = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        lblUnitPrice = new javax.swing.JLabel();
        txtUnitPrice = new javax.swing.JTextField();
        lblCategory = new javax.swing.JLabel();
        lblSupplierName = new javax.swing.JLabel();
        txtSupplierName = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnViewAll = new javax.swing.JButton();
        pnlItemRecords = new javax.swing.JPanel();
        lblItemRecords = new javax.swing.JLabel();
        scrollPaneItems = new javax.swing.JScrollPane();
        tableItems = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inventory System");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(249, 247, 255));
        setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        setMinimumSize(new java.awt.Dimension(600, 1000));
        setName("frame1"); // NOI18N

        pnlMain.setBackground(new java.awt.Color(249, 247, 255));
        pnlMain.setForeground(new java.awt.Color(249, 247, 255));

        pnlHeader.setBackground(new java.awt.Color(255, 255, 255));
        pnlHeader.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 222, 236), 4));

        lblTitle.setBackground(new java.awt.Color(255, 255, 255));
        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(46, 42, 63));
        lblTitle.setText("ITEM / PRODUCT MASTER");
        lblTitle.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        lblSubtitle.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblSubtitle.setForeground(new java.awt.Color(122, 117, 143));
        lblSubtitle.setText("Manage your inventory items and categories");

        pnlHeaderAccent.setBackground(new java.awt.Color(120, 108, 246));

        javax.swing.GroupLayout pnlHeaderAccentLayout = new javax.swing.GroupLayout(pnlHeaderAccent);
        pnlHeaderAccent.setLayout(pnlHeaderAccentLayout);
        pnlHeaderAccentLayout.setHorizontalGroup(
            pnlHeaderAccentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        pnlHeaderAccentLayout.setVerticalGroup(
            pnlHeaderAccentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(pnlHeaderAccent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSubtitle)
                    .addComponent(lblTitle))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlHeaderAccent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlHeaderLayout.createSequentialGroup()
                        .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(lblSubtitle)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pnlCategory.setBackground(new java.awt.Color(255, 255, 255));
        pnlCategory.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(225, 222, 236), 4, true));

        lblCategoryTitle.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        lblCategoryTitle.setForeground(new java.awt.Color(60, 54, 82));
        lblCategoryTitle.setText("CATEGORIES");

        lblSelectCategory.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblSelectCategory.setForeground(new java.awt.Color(122, 117, 143));
        lblSelectCategory.setText("Select Category");

        cmbDeleteCategory.setBackground(new java.awt.Color(255, 255, 255));
        cmbDeleteCategory.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cmbDeleteCategory.setForeground(new java.awt.Color(46, 42, 63));
        cmbDeleteCategory.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(93, 82, 199), 1, true));
        cmbDeleteCategory.setPreferredSize(new java.awt.Dimension(104, 50));
        cmbDeleteCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            }
        });

        lblNewCategory.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblNewCategory.setForeground(new java.awt.Color(122, 117, 143));
        lblNewCategory.setText("New Category");

        txtNewCategoryName.setBackground(new java.awt.Color(255, 255, 255));
        txtNewCategoryName.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtNewCategoryName.setForeground(new java.awt.Color(46, 42, 63));
        txtNewCategoryName.setToolTipText("Enter Category Name here...");
        txtNewCategoryName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(93, 82, 199), 2, true));
        txtNewCategoryName.setPreferredSize(new java.awt.Dimension(4, 50));

        btnAddCategory.setBackground(new java.awt.Color(203, 198, 243));
        btnAddCategory.setFont(new java.awt.Font("Segoe UI", 0, 32)); // NOI18N
        btnAddCategory.setForeground(new java.awt.Color(60, 54, 82));
        btnAddCategory.setText("Add Category");
        btnAddCategory.setBorder(null);
        btnAddCategory.setFocusPainted(false);
        btnAddCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCategoryActionPerformed(evt);
            }
        });

        btnDeleteCategory.setBackground(new java.awt.Color(255, 204, 212));
        btnDeleteCategory.setFont(new java.awt.Font("Segoe UI", 0, 32)); // NOI18N
        btnDeleteCategory.setForeground(new java.awt.Color(60, 54, 82));
        btnDeleteCategory.setText("Delete Category");
        btnDeleteCategory.setBorder(null);
        btnDeleteCategory.setFocusPainted(false);
        btnDeleteCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteCategoryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCategoryLayout = new javax.swing.GroupLayout(pnlCategory);
        pnlCategory.setLayout(pnlCategoryLayout);
        pnlCategoryLayout.setHorizontalGroup(
            pnlCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCategoryLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pnlCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNewCategory)
                    .addComponent(lblSelectCategory)
                    .addComponent(lblCategoryTitle)
                    .addComponent(txtNewCategoryName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbDeleteCategory, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAddCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDeleteCategory, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        pnlCategoryLayout.setVerticalGroup(
            pnlCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCategoryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCategoryTitle)
                .addGap(18, 18, 18)
                .addComponent(lblSelectCategory)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbDeleteCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblNewCategory)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNewCategoryName, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnAddCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDeleteCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlItemInfo.setBackground(new java.awt.Color(255, 255, 255));
        pnlItemInfo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(225, 222, 236), 4, true));

        lblItemInfoTitle.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        lblItemInfoTitle.setForeground(new java.awt.Color(60, 54, 82));
        lblItemInfoTitle.setText("ITEM INFORMATION");

        lblItemId.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblItemId.setForeground(new java.awt.Color(122, 117, 143));
        lblItemId.setText("Item ID");

        txtItemId.setBackground(new java.awt.Color(243, 241, 248));
        txtItemId.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtItemId.setForeground(new java.awt.Color(46, 42, 63));
        txtItemId.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(46, 42, 63), 2, true));

        lblItemName.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblItemName.setForeground(new java.awt.Color(122, 117, 143));
        lblItemName.setText("Item Name");

        txtItemName.setBackground(new java.awt.Color(255, 255, 255));
        txtItemName.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtItemName.setForeground(new java.awt.Color(46, 42, 63));
        txtItemName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(46, 42, 63), 2, true));

        cmbCategory.setBackground(new java.awt.Color(255, 255, 255));
        cmbCategory.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cmbCategory.setForeground(new java.awt.Color(46, 42, 63));
        cmbCategory.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(93, 82, 199), 1, true));

        lblQuantity.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblQuantity.setForeground(new java.awt.Color(122, 117, 143));
        lblQuantity.setText("Quantity");

        txtQuantity.setBackground(new java.awt.Color(243, 241, 248));
        txtQuantity.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtQuantity.setForeground(new java.awt.Color(46, 42, 63));
        txtQuantity.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(46, 42, 63), 2, true));

        lblUnitPrice.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblUnitPrice.setForeground(new java.awt.Color(122, 117, 143));
        lblUnitPrice.setText("Unit Price");

        txtUnitPrice.setBackground(new java.awt.Color(243, 241, 248));
        txtUnitPrice.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtUnitPrice.setForeground(new java.awt.Color(46, 42, 63));
        txtUnitPrice.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(46, 42, 63), 2, true));

        lblCategory.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblCategory.setForeground(new java.awt.Color(122, 117, 143));
        lblCategory.setText("Category");

        lblSupplierName.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblSupplierName.setForeground(new java.awt.Color(122, 117, 143));
        lblSupplierName.setText("Supplier");

        txtSupplierName.setBackground(new java.awt.Color(255, 255, 255));
        txtSupplierName.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtSupplierName.setForeground(new java.awt.Color(46, 42, 63));

        btnAdd.setBackground(new java.awt.Color(140, 130, 241));
        btnAdd.setFont(new java.awt.Font("Segoe UI", 0, 28)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("ADD");
        btnAdd.setToolTipText("");
        btnAdd.setBorder(null);
        btnAdd.setFocusPainted(false);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(141, 141, 196));
        btnUpdate.setFont(new java.awt.Font("Segoe UI", 0, 28)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("UPDATE");
        btnUpdate.setToolTipText("");
        btnUpdate.setBorder(null);
        btnUpdate.setFocusPainted(false);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(255, 204, 212));
        btnDelete.setFont(new java.awt.Font("Segoe UI", 0, 28)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(60, 54, 82));
        btnDelete.setText("DELETE");
        btnDelete.setToolTipText("");
        btnDelete.setBorder(null);
        btnDelete.setFocusPainted(false);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnSearch.setBackground(new java.awt.Color(233, 230, 247));
        btnSearch.setFont(new java.awt.Font("Segoe UI", 0, 28)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(81, 74, 105));
        btnSearch.setText("SEARCH");
        btnSearch.setToolTipText("");
        btnSearch.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(216, 211, 233), 2, true));
        btnSearch.setFocusPainted(false);
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(233, 230, 247));
        btnClear.setFont(new java.awt.Font("Segoe UI", 0, 28)); // NOI18N
        btnClear.setForeground(new java.awt.Color(81, 74, 105));
        btnClear.setText("CLEAR");
        btnClear.setToolTipText("");
        btnClear.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(216, 211, 233), 2, true));
        btnClear.setFocusPainted(false);
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnViewAll.setBackground(new java.awt.Color(233, 230, 247));
        btnViewAll.setFont(new java.awt.Font("Segoe UI", 0, 28)); // NOI18N
        btnViewAll.setForeground(new java.awt.Color(81, 74, 105));
        btnViewAll.setText("VIEW ALL");
        btnViewAll.setToolTipText("");
        btnViewAll.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(216, 211, 233), 2, true));
        btnViewAll.setFocusPainted(false);
        btnViewAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlItemInfoLayout = new javax.swing.GroupLayout(pnlItemInfo);
        pnlItemInfo.setLayout(pnlItemInfoLayout);
        pnlItemInfoLayout.setHorizontalGroup(
            pnlItemInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlItemInfoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlItemInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblItemInfoTitle)
                    .addGroup(pnlItemInfoLayout.createSequentialGroup()
                        .addGroup(pnlItemInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtUnitPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(lblItemId)
                            .addComponent(txtItemId)
                            .addComponent(lblQuantity)
                            .addComponent(txtQuantity)
                            .addComponent(lblUnitPrice))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlItemInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblSupplierName)
                            .addComponent(lblCategory)
                            .addComponent(lblItemName)
                            .addComponent(txtItemName)
                            .addComponent(cmbCategory, 0, 395, Short.MAX_VALUE)
                            .addComponent(txtSupplierName))))
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlItemInfoLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(pnlItemInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlItemInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlItemInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnViewAll, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
        pnlItemInfoLayout.setVerticalGroup(
            pnlItemInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlItemInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlItemInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlItemInfoLayout.createSequentialGroup()
                        .addComponent(lblItemInfoTitle)
                        .addGap(18, 18, 18)
                        .addGroup(pnlItemInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblItemId)
                            .addComponent(lblItemName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlItemInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtItemName, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                            .addComponent(txtItemId))
                        .addGap(21, 21, 21)
                        .addComponent(lblCategory)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlItemInfoLayout.createSequentialGroup()
                        .addComponent(lblQuantity)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pnlItemInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUnitPrice)
                    .addComponent(lblSupplierName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlItemInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtUnitPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(txtSupplierName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(pnlItemInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlItemInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnViewAll, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );

        pnlItemRecords.setBackground(new java.awt.Color(255, 255, 255));
        pnlItemRecords.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(225, 222, 236), 4, true));

        lblItemRecords.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        lblItemRecords.setForeground(new java.awt.Color(60, 54, 82));
        lblItemRecords.setText("ITEM RECORDS");

        tableItems.setBackground(new java.awt.Color(255, 255, 255));
        tableItems.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        tableItems.setForeground(new java.awt.Color(46, 42, 63));
        tableItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Category", "Quantity", "Unit Price", "Supplier"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableItems.setRowHeight(32);
        tableItems.setShowVerticalLines(false);
        scrollPaneItems.setViewportView(tableItems);

        javax.swing.GroupLayout pnlItemRecordsLayout = new javax.swing.GroupLayout(pnlItemRecords);
        pnlItemRecords.setLayout(pnlItemRecordsLayout);
        pnlItemRecordsLayout.setHorizontalGroup(
            pnlItemRecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlItemRecordsLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblItemRecords)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlItemRecordsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneItems)
                .addContainerGap())
        );
        pnlItemRecordsLayout.setVerticalGroup(
            pnlItemRecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlItemRecordsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblItemRecords)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPaneItems, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(pnlCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlItemInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlItemRecords, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addComponent(pnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlItemInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlItemRecords, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void loadCategoryCombos() {
    cmbCategory.removeAllItems();
    cmbDeleteCategory.removeAllItems();

    ArrayList<Category> categories = categoryService.getAllCategories();
    for (int i = 0; i < categories.size(); i++) {
        cmbCategory.addItem(categories.get(i));
        cmbDeleteCategory.addItem(categories.get(i));
        }
    }

    private void loadTableData() {
        DefaultTableModel model = (DefaultTableModel) tableItems.getModel();
        model.setRowCount(0);

        ArrayList<Item> items = itemService.getAllItems();
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            Object[] row = {
                item.getItemId(),
                item.getItemName(),
                item.getCategoryName(),
                item.getQuantity(),
                item.getUnitPrice(),
                item.getSupplierName()
            };
            model.addRow(row);
        }
    }

    private void selectCategoryInCombo(javax.swing.JComboBox<Category> combo, int categoryId) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            Category category = combo.getItemAt(i);
            if (category.getCategoryId() == categoryId) {
                combo.setSelectedIndex(i);
                break;
            }
        }
    }

    private void clearFields() {
        txtItemId.setText("");
        txtItemName.setText("");
        txtQuantity.setText("");
        txtUnitPrice.setText("");
        txtSupplierName.setText("");
    }
    
    private void displayItemInFields(Item item) {
        txtItemId.setText(String.valueOf(item.getItemId()));
        txtItemName.setText(item.getItemName());
        selectCategoryInCombo(cmbCategory, item.getCategoryId());
        txtQuantity.setText(String.valueOf(item.getQuantity()));
        txtUnitPrice.setText(String.valueOf(item.getUnitPrice()));
        txtSupplierName.setText(item.getSupplierName());
    }

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        String itemName = txtItemName.getText();
    Category selectedCategory = (Category) cmbCategory.getSelectedItem();
    String quantityText = txtQuantity.getText();
    String unitPriceText = txtUnitPrice.getText();
    String supplierName = txtSupplierName.getText();

    try {
        itemService.addItem(itemName, selectedCategory, quantityText, unitPriceText, supplierName);
        JOptionPane.showMessageDialog(this, "Item added successfully.");
        clearFields();
        loadTableData();
    } catch (BusinessRuleException e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        String itemIdText = txtItemId.getText();
        String itemName = txtItemName.getText();
        Category selectedCategory = (Category) cmbCategory.getSelectedItem();
        String quantityText = txtQuantity.getText();
        String unitPriceText = txtUnitPrice.getText();
        String supplierName = txtSupplierName.getText();

        try {
            itemService.updateItem(itemIdText, itemName, selectedCategory, quantityText, unitPriceText, supplierName);
            JOptionPane.showMessageDialog(this, "Item updated successfully.");
            clearFields();
            loadTableData();
        } catch (BusinessRuleException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        String itemIdText = txtItemId.getText();

        int confirm = JOptionPane.showConfirmDialog(this, "Delete this item?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            itemService.deleteItem(itemIdText);
            JOptionPane.showMessageDialog(this, "Item deleted successfully.");
            clearFields();
            loadTableData();
        } catch (BusinessRuleException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String itemIdText = txtItemId.getText();

        try {
            Item foundItem = itemService.searchItem(itemIdText);
            displayItemInFields(foundItem);
        } catch (BusinessRuleException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clearFields();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnViewAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewAllActionPerformed
        loadTableData();
    }//GEN-LAST:event_btnViewAllActionPerformed

    private void btnAddCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCategoryActionPerformed
        String categoryName = txtNewCategoryName.getText();

        try {
            categoryService.addCategory(categoryName);
            JOptionPane.showMessageDialog(this, "Category added successfully.");
            txtNewCategoryName.setText("");
            loadCategoryCombos();
        } catch (BusinessRuleException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnAddCategoryActionPerformed

    private void btnDeleteCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteCategoryActionPerformed
        Category selectedCategory = (Category) cmbDeleteCategory.getSelectedItem();

        int confirm = JOptionPane.showConfirmDialog(this, "Delete this category?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            categoryService.deleteCategory(selectedCategory);
            JOptionPane.showMessageDialog(this, "Category deleted successfully.");
            loadCategoryCombos();
        } catch (BusinessRuleException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnDeleteCategoryActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InventoryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InventoryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InventoryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InventoryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InventoryFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddCategory;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDeleteCategory;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnViewAll;
    private javax.swing.JComboBox<Category> cmbCategory;
    private javax.swing.JComboBox<Category> cmbDeleteCategory;
    private javax.swing.JLabel lblCategory;
    private javax.swing.JLabel lblCategoryTitle;
    private javax.swing.JLabel lblItemId;
    private javax.swing.JLabel lblItemInfoTitle;
    private javax.swing.JLabel lblItemName;
    private javax.swing.JLabel lblItemRecords;
    private javax.swing.JLabel lblNewCategory;
    private javax.swing.JLabel lblQuantity;
    private javax.swing.JLabel lblSelectCategory;
    private javax.swing.JLabel lblSubtitle;
    private javax.swing.JLabel lblSupplierName;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUnitPrice;
    private javax.swing.JPanel pnlCategory;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlHeaderAccent;
    private javax.swing.JPanel pnlItemInfo;
    private javax.swing.JPanel pnlItemRecords;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JScrollPane scrollPaneItems;
    private javax.swing.JTable tableItems;
    private javax.swing.JTextField txtItemId;
    private javax.swing.JTextField txtItemName;
    private javax.swing.JTextField txtNewCategoryName;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtSupplierName;
    private javax.swing.JTextField txtUnitPrice;
    // End of variables declaration//GEN-END:variables
}
