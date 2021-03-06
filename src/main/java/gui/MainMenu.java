package gui;

import dao.ProductDAOInterface;

/**
 *
 * @author peani371
 */
public class MainMenu extends javax.swing.JFrame {

    private ProductDAOInterface dao;

    /**
     * Creates new form MainMenu
     */
    public MainMenu(ProductDAOInterface dao) {
        initComponents();
        setTitle("Product Administration");
        this.dao = dao;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonViewProducts = new javax.swing.JButton();
        buttonExit = new javax.swing.JButton();
        buttonAddNewProduct = new javax.swing.JButton();
        labelMainMenuTitle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        buttonViewProducts.setText("View Products");
        buttonViewProducts.setName("buttonViewProducts"); // NOI18N
        buttonViewProducts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonViewProductsActionPerformed(evt);
            }
        });

        buttonExit.setText("Exit");
        buttonExit.setName("buttonExit"); // NOI18N
        buttonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExitActionPerformed(evt);
            }
        });

        buttonAddNewProduct.setText("Add a New Product");
        buttonAddNewProduct.setName("buttonAddNewProduct"); // NOI18N
        buttonAddNewProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddNewProductActionPerformed(evt);
            }
        });

        labelMainMenuTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMainMenuTitle.setText("Product Administration");
        labelMainMenuTitle.setName("labelMainMenuTitle"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelMainMenuTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonViewProducts, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                    .addComponent(buttonExit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                    .addComponent(buttonAddNewProduct, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelMainMenuTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonAddNewProduct)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonViewProducts)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonExit)
                .addGap(139, 139, 139))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
       System.exit(0);
   }//GEN-LAST:event_buttonExitActionPerformed

   private void buttonAddNewProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddNewProductActionPerformed
       ProductEditor addProduct = new ProductEditor(this, true, dao);
       addProduct.setLocationRelativeTo(this);
       addProduct.setVisible(true);
   }//GEN-LAST:event_buttonAddNewProductActionPerformed

   private void buttonViewProductsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonViewProductsActionPerformed
       ProductViewer viewProducts = new ProductViewer(this, true, dao);
       viewProducts.setLocationRelativeTo(this);
       viewProducts.setVisible(true);
   }//GEN-LAST:event_buttonViewProductsActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAddNewProduct;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonViewProducts;
    private javax.swing.JLabel labelMainMenuTitle;
    // End of variables declaration//GEN-END:variables
}
