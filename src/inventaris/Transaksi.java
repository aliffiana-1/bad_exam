/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package inventaris;

import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author aliff_qdx6
 */
public class Transaksi extends javax.swing.JFrame {

    public Transaksi() {
        initComponents();
        loadData("");
        jTable1.getColumn("Harga").setCellRenderer(new CurrencyRenderer());
        jTable1.getColumn("Total Harga").setCellRenderer(new CurrencyRenderer());
    }

    private void loadData() {
        loadData(""); // Panggil loadData dengan string kosong
    }

// Metode loadData dengan parameter pencarian
    private void loadData(String searchQuery) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Reset table

        try (Connection conn = ConnectionJava.getConnection()) {
            // Update SQL query to match the table structure
            String sql = "SELECT t.id_transaksi, t.tanggal, t.nama_pembeli, p.nama AS nama_produk, t.jumlah, t.harga, t.total_harga "
                    + "FROM tb_transaksi t "
                    + "JOIN tb_produk p ON t.id_produk = p.id_produk "
                    + "WHERE t.nama_pembeli LIKE ? OR p.nama LIKE ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + searchQuery + "%");
            pst.setString(2, "%" + searchQuery + "%"); // parameter kedua untuk pencarian produk
            ResultSet rs = pst.executeQuery();

            int no = 1;
            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(no++); // Nomor otomatis
                row.add(rs.getString("tanggal")); // Tanggal
                row.add(rs.getString("nama_pembeli")); // Nama Pembeli
                row.add(rs.getString("nama_produk")); // Produk
                row.add(rs.getInt("jumlah")); // Jumlah
                row.add(rs.getDouble("harga")); // Harga
                row.add(rs.getDouble("total_harga")); // Total Harga
                row.add(""); // Placeholder untuk kolom action (akan diisi button)
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Manajemen Produk");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Transaksi");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Laporan");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Transaksi");

        jButton4.setText("Add");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton5.setText("Search");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No", "Tanggal", "Nama Pembeli", "Produk", "Jumlah", "Harga", "Total Harga", "Action"
            }
        ));
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.getColumn("Action").setCellRenderer(new ButtonRenderer());
        jTable1.getColumn("Action").setCellEditor(new ButtonEditor(new JCheckBox()));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 782, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5)))
                        .addGap(35, 35, 35))))
            .addGroup(layout.createSequentialGroup()
                .addGap(390, 390, 390)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(23, 23, 23)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ManajemenProduk produk = new ManajemenProduk();
        produk.setVisible(true);
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Transaksi transaksi = new Transaksi();
        transaksi.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Laporan transaksi = new Laporan();
        transaksi.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        DetailTransaksi aaaa = new DetailTransaksi(null);
        aaaa.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String searchText = jTextField1.getText();
        loadData(searchText);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    class ButtonRenderer extends JPanel implements TableCellRenderer {

        private JButton editButton = new JButton("Edit");
        private JButton deleteButton = new JButton("Delete");

        public ButtonRenderer() {
            setLayout(new java.awt.GridLayout(1, 2));
            add(editButton);
            add(deleteButton);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

// Custom editor for Action column to handle button actions
    class ButtonEditor extends AbstractCellEditor implements TableCellEditor {

        private JPanel panel = new JPanel(new java.awt.GridLayout(1, 2));
        private JButton editButton = new JButton("Edit");
        private JButton deleteButton = new JButton("Delete");

        public ButtonEditor(JCheckBox checkBox) {
            panel.add(editButton);
            panel.add(deleteButton);

            // Use an array to hold id_transaksi since arrays can be modified
            final int[] id_transaksi = new int[1];

            try {
                Connection conn = ConnectionJava.getConnection();

                editButton.addActionListener(e -> {
                    fireEditingStopped();
                    int row = jTable1.getSelectedRow();
                    if (row >= 0) {
                        try {
                            String getName = jTable1.getValueAt(row, 2).toString();
                            String getProduk = jTable1.getValueAt(row, 3).toString();
                            Integer getJumlah = (int) jTable1.getValueAt(row, 4);
                            String getIdTransSql = "SELECT id_transaksi FROM tb_transaksi WHERE nama_pembeli = ? AND jumlah = ? AND id_produk = (SELECT id_produk FROM tb_produk WHERE nama = ?)";
                            PreparedStatement pst = conn.prepareStatement(getIdTransSql);
                            pst.setString(1, getName);
                            pst.setInt(2, getJumlah);
                            pst.setString(3, getProduk);
                            ResultSet rs = pst.executeQuery();

                            if (rs.next()) {
                                id_transaksi[0] = rs.getInt("id_transaksi");
                            }
                            System.out.println(id_transaksi[0]);

                            DetailTransaksi detailProdukForm = new DetailTransaksi(id_transaksi[0]);
                            detailProdukForm.setVisible(true);
                            // Close the current form if needed
                            Transaksi.this.dispose();
                        } catch (SQLException ex) {
                            Logger.getLogger(Transaksi.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

                deleteButton.addActionListener(e -> {
                    fireEditingStopped();
                    int selectedRow = jTable1.getSelectedRow();
                    if (selectedRow >= 0) {
                        String getName = jTable1.getValueAt(selectedRow, 2).toString();
                        String getProduk = jTable1.getValueAt(selectedRow, 3).toString();
                        Integer getJumlah = (int) jTable1.getValueAt(selectedRow, 4);
                        System.out.println(getName);
                        String getIdTransSql = "SELECT id_transaksi FROM tb_transaksi WHERE nama_pembeli = ? AND jumlah = ? AND id_produk = (SELECT id_produk FROM tb_produk WHERE nama = ?)";
                        try (PreparedStatement pstt = conn.prepareStatement(getIdTransSql)) {
                            pstt.setString(1, getName);
                            pstt.setInt(2, getJumlah);
                            pstt.setString(3, getProduk);
                            ResultSet rs = pstt.executeQuery();

                            if (rs.next()) {
                                id_transaksi[0] = rs.getInt("id_transaksi");
                            }

                            // Konfirmasi penghapusan
                            int response = javax.swing.JOptionPane.showConfirmDialog(null,
                                    "Apakah Anda yakin ingin menghapus produk ini?",
                                    "Konfirmasi",
                                    javax.swing.JOptionPane.YES_NO_OPTION,
                                    javax.swing.JOptionPane.QUESTION_MESSAGE);

                            if (response == javax.swing.JOptionPane.YES_OPTION) {
                                String sql = "DELETE FROM tb_transaksi WHERE id_transaksi = ?";
                                try (PreparedStatement psttt = conn.prepareStatement(sql)) {
                                    psttt.setInt(1, id_transaksi[0]);
                                    psttt.executeUpdate();

                                    // Hapus baris dari tabel setelah berhasil dihapus dari database
                                    ((DefaultTableModel) jTable1.getModel()).removeRow(selectedRow);
                                    javax.swing.JOptionPane.showMessageDialog(null, "Produk berhasil dihapus.");
                                }
                            }
                            Transaksi transaksi = new Transaksi();
                            transaksi.setVisible(true);
                        } catch (SQLException ex) {
                            Logger.getLogger(Transaksi.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            return null;
        }
    }

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
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Transaksi().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
