package inventaris;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;

public class DetailTransaksi extends javax.swing.JFrame {

    private int id_transaksi = -1;
    public Integer totalHarga;
    public Integer hargaProduk;

    public void setProductId(int id) {
        this.id_transaksi = id;
        // Tambahkan kode untuk memuat data produk ke dalam form berdasarkan id
    }

    public DetailTransaksi() {
        initComponents();
    }

    public DetailTransaksi(Integer id_transaksi) {
        initComponents();
        if (id_transaksi != null) {
            this.id_transaksi = id_transaksi;
            loadTransactionData(id_transaksi);
        } else {
            loadTransactionData(null);
        }
    }

    private void loadTransactionData(Integer id_transaksi) {
        if (id_transaksi != null) {
            System.out.println(id_transaksi);

            String sql = "SELECT * FROM tb_transaksi WHERE id_transaksi = ?";

            try (Connection conn = ConnectionJava.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id_transaksi);
                var rs = pstmt.executeQuery();

                if (rs.next()) {
                    String tanggalDb = rs.getString("tanggal");

                    // Format tanggal
                    SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");

                    try {
                        Date date = originalFormat.parse(tanggalDb);
                        String formattedDate = targetFormat.format(date);
                        jTextField1.setText(formattedDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Failed to parse date. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    jTextField2.setText(rs.getString("nama_pembeli"));
                    jSpinner1.setValue(rs.getInt("jumlah"));
                    jLabel9.setText(String.format("Rp. %.2f", rs.getDouble("harga")));
                    jLabel10.setText(String.format("Rp. %.2f", rs.getDouble("total_harga")));

                    // Update jComboBox1 dengan ID produk dari transaksi
                    int productId = rs.getInt("id_produk");
                    updateComboBox(productId);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to load transaction data. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            updateComboBox(null);
        }
    }

    private void prepareForInsert() {
        updateComboBox(-1); // -1 berarti tidak ada produk yang dipilih
    }

    private void updateComboBox(Integer selectedProductId) {
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();

        try (Connection conn = ConnectionJava.getConnection()) {
            String sql = "SELECT id_produk, nama FROM tb_produk";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int productId = rs.getInt("id_produk");
                String namaProduk = rs.getString("nama");
                comboBoxModel.addElement(namaProduk);
            }

            jComboBox1.setModel(comboBoxModel);

            // Set item yang dipilih di jComboBox1 jika ada ID produk dari transaksi
            if (selectedProductId != null) {
                String productNameSql = "SELECT nama FROM tb_produk WHERE id_produk = ?";
                PreparedStatement pstProductName = conn.prepareStatement(productNameSql);
                pstProductName.setInt(1, selectedProductId);
                ResultSet rsProductName = pstProductName.executeQuery();

                if (rsProductName.next()) {
                    String selectedProductName = rsProductName.getString("nama");
                    jComboBox1.setSelectedItem(selectedProductName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to update combo box. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int getProductIdFromTransaction(int id_transaksi) {
        int productId = -1;
        String sql = "SELECT id_produk FROM tb_transaksi WHERE id_transaksi = ?";

        try (Connection conn = ConnectionJava.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id_transaksi);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                productId = rs.getInt("id_produk");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to get product ID from transaction. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return productId;
    }

    private int getSelectedProductId() {
        int selectedProductId = -1;
        try (Connection conn = ConnectionJava.getConnection()) {
            String selectedProductName = (String) jComboBox1.getSelectedItem();
            String sql = "SELECT id_produk FROM tb_produk WHERE nama = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, selectedProductName);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                selectedProductId = rs.getInt("id_produk");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to get selected product ID. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return selectedProductId;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton7.setText("Manajemen Produk");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Transaksi");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton6.setText("Laporan");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Informasi Transaksi");

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Tanggal                :");

        jLabel3.setText("Nama Pembeli     :");

        jLabel4.setText("Produk                  :");

        jLabel5.setText("Jumlah                  :");

        jLabel6.setText("Harga Produk       :");

        jLabel7.setText("Total Harga          :");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel9.setText(" ");

        jLabel10.setText(" ");

        jButton3.setText("Check");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(294, 294, 294)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField1)
                                    .addComponent(jTextField2)
                                    .addComponent(jComboBox1, 0, 213, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jSpinner1)))
                            .addComponent(jButton6)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(88, 88, 88)
                                .addComponent(jLabel2)))))
                .addContainerGap(261, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton8)
                    .addComponent(jButton7))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        ManajemenProduk produk = new ManajemenProduk();
        produk.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        Transaksi transaksi = new Transaksi();
        transaksi.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String tanggal = jTextField1.getText().trim();
        String namaPembeli = jTextField2.getText().trim();
        String selectedProductName = (String) jComboBox1.getSelectedItem();
        int jumlah = (Integer) jSpinner1.getValue();

        // Validation
        if (tanggal.isEmpty() || namaPembeli.isEmpty() || selectedProductName == null || jumlah <= 0) {
            JOptionPane.showMessageDialog(this, "Tanggal, Nama Pembeli, Produk, dan Jumlah harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int selectedProductId = getProductIdByName(selectedProductName);
        int currentStock = getProductStock(selectedProductId);
//        hargaProdukdouble productPrice = getProductPriceById(selectedProductId);

        if (jumlah > currentStock) {
            JOptionPane.showMessageDialog(this, "Jumlah yang anda masukkan melebihi stok! Stok saat ini adalah: " + currentStock, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Insert or update transaction
        if (id_transaksi == -1) {
            insertTransaction(tanggal, namaPembeli, selectedProductId, jumlah, currentStock, hargaProduk, totalHarga);
        } else {
            updateTransaction(id_transaksi, tanggal, namaPembeli, selectedProductId, jumlah, currentStock, hargaProduk, totalHarga);
        }

        // Open Transaksi and close DetailTransaksi
        Transaksi transaksi = new Transaksi();
        transaksi.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_jButton1ActionPerformed

    private int getProductIdByName(String productName) {
        int productId = -1;
        String sql = "SELECT id_produk FROM tb_produk WHERE nama = ?";

        try (Connection conn = ConnectionJava.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, productName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                productId = rs.getInt("id_produk");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to get product ID by name. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return productId;
    }

    private void insertTransaction(String tanggal, String namaPembeli, int productId, int jumlah, int currentStock, int hargaProduk, int totalHarga){
        String sqlInsert = "INSERT INTO tb_transaksi (tanggal, nama_pembeli, id_produk, jumlah, harga, total_harga) VALUES (?, ?, ?, ?, ?, ?)";
        String sqlUpdateStock = "UPDATE tb_produk SET stok = ? WHERE id_produk = ?";

        try (Connection conn = ConnectionJava.getConnection(); PreparedStatement pstmtInsert = conn.prepareStatement(sqlInsert); PreparedStatement pstmtUpdateStock = conn.prepareStatement(sqlUpdateStock)) {

            // Insert transaction
            pstmtInsert.setString(1, tanggal);
            pstmtInsert.setString(2, namaPembeli);
            pstmtInsert.setInt(3, productId);
            pstmtInsert.setInt(4, jumlah);
            pstmtInsert.setInt(5, hargaProduk);
            pstmtInsert.setInt(6, totalHarga);
            pstmtInsert.executeUpdate();

            // Update stock
            int newStock = currentStock - jumlah;
            pstmtUpdateStock.setInt(1, newStock);
            pstmtUpdateStock.setInt(2, productId);
            pstmtUpdateStock.executeUpdate();

            JOptionPane.showMessageDialog(this, "Transaction added and stock updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to add transaction. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateTransaction(int transactionId, String tanggal, String namaPembeli, int productId, int newQuantity, int currentStock, int hargaProduk, int totalHarga) {
    String sqlUpdateTransaction = "UPDATE tb_transaksi SET tanggal = ?, nama_pembeli = ?, id_produk = ?, jumlah = ?, harga = ?, total_harga = ? WHERE id_transaksi = ?";
    String sqlUpdateStock = "UPDATE tb_produk SET stok = ? WHERE id_produk = ?";
    String sqlGetOldQuantity = "SELECT jumlah FROM tb_transaksi WHERE id_transaksi = ?";

    try (Connection conn = ConnectionJava.getConnection(); 
         PreparedStatement pstmtUpdateTransaction = conn.prepareStatement(sqlUpdateTransaction);
         PreparedStatement pstmtUpdateStock = conn.prepareStatement(sqlUpdateStock);
         PreparedStatement pstmtGetOldQuantity = conn.prepareStatement(sqlGetOldQuantity)) {

        // Get old quantity from the transaction
        pstmtGetOldQuantity.setInt(1, transactionId);
        ResultSet rs = pstmtGetOldQuantity.executeQuery();
        int oldQuantity = 0;
        if (rs.next()) {
            oldQuantity = rs.getInt("jumlah");
        }

        // Update transaction
        pstmtUpdateTransaction.setString(1, tanggal);
        pstmtUpdateTransaction.setString(2, namaPembeli);
        pstmtUpdateTransaction.setInt(3, productId);
        pstmtUpdateTransaction.setInt(4, newQuantity);
        pstmtUpdateTransaction.setInt(5, hargaProduk);
        pstmtUpdateTransaction.setInt(6, totalHarga);
        pstmtUpdateTransaction.setInt(7, transactionId);
        pstmtUpdateTransaction.executeUpdate();

        // Update stock
        int stockAdjustment = currentStock + oldQuantity - newQuantity;
        pstmtUpdateStock.setInt(1, stockAdjustment);
        pstmtUpdateStock.setInt(2, productId);
        pstmtUpdateStock.executeUpdate();

        JOptionPane.showMessageDialog(this, "Transaction updated and stock adjusted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Failed to update transaction. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    private int getProductStock(int productId) {
        String sql = "SELECT stok FROM tb_produk WHERE id_produk = ?";
        try (Connection conn = ConnectionJava.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, productId);
            var rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("stok");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Default if product is not found
    }

    private double getProductPriceById(int productId) {
        double price = 0.0;
        String sql = "SELECT harga FROM tb_produk WHERE id_produk = ?";

        try (Connection conn = ConnectionJava.getConnection(); 
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, productId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                price = rs.getDouble("harga");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to get product price. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return price;
    }


    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Transaksi transaksi = new Transaksi();
        transaksi.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // Get selected product name and quantity
        String selectedProductName = (String) jComboBox1.getSelectedItem();
        int quantity = (Integer) jSpinner1.getValue();

        // Check if the selected product and quantity are valid
        if (selectedProductName == null || quantity <= 0) {
            JOptionPane.showMessageDialog(this, "Please select a product and enter a valid quantity.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Get the product ID based on the selected product name
        int selectedProductId = getProductIdByName(selectedProductName);
        if (selectedProductId == -1) {
            JOptionPane.showMessageDialog(this, "Failed to retrieve product ID. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Get the product price based on the product ID
        double productPrice = getProductPriceById(selectedProductId);
        hargaProduk = (int)(productPrice);

        // Calculate total price
        double totalPrice = productPrice * quantity;
        totalHarga = (int)(totalPrice);

        // Update the labels with price and total price
        jLabel9.setText(String.format("Rp. %.2f", productPrice));
        jLabel10.setText(String.format("Rp. %.2f", totalPrice));

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        Laporan transaksi = new Laporan();
        transaksi.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(DetailTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DetailTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DetailTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DetailTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DetailTransaksi(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
