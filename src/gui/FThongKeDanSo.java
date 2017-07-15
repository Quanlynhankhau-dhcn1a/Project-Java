/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import sql.qlnk.MyConnectUnit;
import sql.qlnk.MySQLConnect;
import sql.qlnk.kiemtra.dulieu;
import java.awt.print.PrinterException;
import java.sql.ResultSet;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class FThongKeDanSo extends javax.swing.JInternalFrame {

    MySQLConnect con = new MySQLConnect(dulieu.getHost(), dulieu.getId(), dulieu.getPassword(), dulieu.getCsdl());
    MyConnectUnit con1 = new MyConnectUnit(dulieu.getHost(), dulieu.getId(), dulieu.getPassword(), dulieu.getCsdl());
    private ResultSet rs;

    /**
     * Creates new form FThongKeDanSo
     */
    public FThongKeDanSo() {
        initComponents();
    }

    private void loadDk(String dk) {
        String sql = null;
        switch (jComboBox1.getSelectedIndex()) {
            case 0:
                switch (jComboBox2.getSelectedIndex()) {
                    case 0:
                        switch (jComboBox3.getSelectedIndex()) {
                            case 0:
                                sql = "SELECT * FROM nguoidan WHERE (Socmnd LIKE '%" + dk + "%') or (mahd LIKE '%" + dk + "%')or (hoten LIKE '%" + dk + "%')";
                                break;
                            case 1:
                                sql = "SELECT * FROM nguoidan WHERE ((Socmnd LIKE '%" + dk + "%') or (mahd LIKE '%" + dk + "%')or (hoten LIKE '%" + dk + "%')) and (gioitinh = 'Nam')";
                                break;
                            case 2:
                                sql = "SELECT * FROM nguoidan WHERE ((Socmnd LIKE '%" + dk + "%') or (mahd LIKE '%" + dk + "%')or (hoten LIKE '%" + dk + "%')) and (gioitinh = 'Nử')";
                                break;
                        }
                        break;
                    case 1:
                        switch (jComboBox3.getSelectedIndex()) {
                            case 0:
                                sql = "SELECT * FROM nguoidan WHERE (hoten LIKE '%" + dk + "%')";
                                break;
                            case 1:
                                sql = "SELECT * FROM nguoidan WHERE ((hoten LIKE '%" + dk + "%')) and (gioitinh = 'Nam')";
                                break;
                            case 2:
                                sql = "SELECT * FROM nguoidan WHERE ((hoten LIKE '%" + dk + "%')) and (gioitinh = 'Nử')";
                                break;
                        }
                        break;
                    case 2:
                        switch (jComboBox3.getSelectedIndex()) {
                            case 0:
                                sql = "SELECT * FROM nguoidan WHERE (Socmnd LIKE '%" + dk + "%')";
                                break;
                            case 1:
                                sql = "SELECT * FROM nguoidan WHERE ((Socmnd LIKE '%" + dk + "%')) and (gioitinh = 'Nam')";
                                break;
                            case 2:
                                sql = "SELECT * FROM nguoidan WHERE ((Socmnd LIKE '%" + dk + "%')) and (gioitinh = 'Nử')";
                                break;
                        }
                        break;
                    case 3:
                        switch (jComboBox3.getSelectedIndex()) {
                            case 0:
                                sql = "SELECT * FROM nguoidan WHERE (mahd LIKE '%" + dk + "%')";
                                break;
                            case 1:
                                sql = "SELECT * FROM nguoidan WHERE (mahd LIKE '%" + dk + "%') and (gioitinh = 'Nam')";
                                break;
                            case 2:
                                sql = "SELECT * FROM nguoidan WHERE (mahd LIKE '%" + dk + "%') and (gioitinh = 'Nử')";
                                break;
                        }
                        break;
                }
                break;
            case 1:
                switch (jComboBox2.getSelectedIndex()) {
                    case 0:
                        sql = "SELECT * FROM hokhau WHERE (socmnd LIKE '%" + dk + "%') or (MaHD LIKE '%" + dk + "%')or (tenchuho LIKE '%" + dk + "%')";
                        break;
                    case 1:
                        sql = "SELECT * FROM hokhau WHERE (tenchuho LIKE '%" + dk + "%')";
                        break;
                    case 2:
                        sql = "SELECT * FROM hokhau WHERE (socmnd LIKE '%" + dk + "%')";
                        break;
                    case 3:
                        sql = "SELECT * FROM hokhau WHERE (mahd LIKE '%" + dk + "%')";
                        break;
                }
                break;
            case 2:
                switch (jComboBox2.getSelectedIndex()) {
                    case 0:
                        switch (jComboBox3.getSelectedIndex()) {
                            case 0:
                                sql = "SELECT * FROM tamvang WHERE (socmnd LIKE '%" + dk + "%') or (ten LIKE '%" + dk + "%')";
                                break;
                            case 1:
                                sql = "SELECT * FROM tamvang WHERE ((socmnd LIKE '%" + dk + "%') or (ten LIKE '%" + dk + "%')) and (gioitinh = 'Nam')";
                                break;
                            case 2:
                                sql = "SELECT * FROM tamvang WHERE ((socmnd LIKE '%" + dk + "%') or (ten LIKE '%" + dk + "%')) and (gioitinh = 'Nử')";
                                break;
                        }
                        break;
                    case 1:
                        switch (jComboBox3.getSelectedIndex()) {
                            case 0:
                                sql = "SELECT * FROM tamvang WHERE (ten LIKE '%" + dk + "%')";
                                break;
                            case 1:
                                sql = "SELECT * FROM tamvang WHERE ((ten LIKE '%" + dk + "%')) and (gioitinh = 'Nam')";
                                break;
                            case 2:
                                sql = "SELECT * FROM tamvangWHERE ((ten LIKE '%" + dk + "%')) and (gioitinh = 'Nử')";
                                break;
                        }
                        break;
                    case 2:
                        switch (jComboBox3.getSelectedIndex()) {
                            case 0:
                                sql = "SELECT * FROM tamvang WHERE (socmnd LIKE '%" + dk + "%')";
                                break;
                            case 1:
                                sql = "SELECT * FROM tamvang WHERE ((socmnd LIKE '%" + dk + "%')) and (gioitinh = 'Nam')";
                                break;
                            case 2:
                                sql = "SELECT * FROM tamvang WHERE ((socmnd LIKE '%" + dk + "%')) and (gioitinh = 'Nử')";
                                break;
                        }
                        break;
                    case 3:
                        switch (jComboBox3.getSelectedIndex()) {
                            case 0:
                                sql = "SELECT * FROM tamvang WHERE (ten LIKE '%" + dk + "%')";
                                break;
                            case 1:
                                sql = "SELECT * FROM tamvang WHERE ((ten LIKE '%" + dk + "%')) and (gioitinh = 'Nam')";
                                break;
                            case 2:
                                sql = "SELECT * FROM tamvang WHERE ((ten LIKE '%" + dk + "%')) and (gioitinh = 'Nử')";
                                break;
                        }
                        break;
                }
                break;
            case 3:
                switch (jComboBox2.getSelectedIndex()) {
                    case 0:
                        switch (jComboBox3.getSelectedIndex()) {
                            case 0:
                                sql = "SELECT * FROM tamtru WHERE (socmnd LIKE '%" + dk + "%') or (ten LIKE '%" + dk + "%')";
                                break;
                            case 1:
                                sql = "SELECT * FROM tamtru WHERE ((socmnd LIKE '%" + dk + "%') or (ten LIKE '%" + dk + "%')) and (gioitinh = 'Nam')";
                                break;
                            case 2:
                                sql = "SELECT * FROM tamtru WHERE ((socmnd LIKE '%" + dk + "%') or (ten LIKE '%" + dk + "%')) and (gioitinh = 'Nử')";
                                break;
                        }
                        break;
                    case 1:
                        switch (jComboBox3.getSelectedIndex()) {
                            case 0:
                                sql = "SELECT * FROM tamtru WHERE (ten LIKE '%" + dk + "%')";
                                break;
                            case 1:
                                sql = "SELECT * FROM tamtru WHERE ((ten LIKE '%" + dk + "%')) and (gioitinh = 'Nam')";
                                break;
                            case 2:
                                sql = "SELECT * FROM tamtru WHERE ((ten LIKE '%" + dk + "%')) and (gioitinh = 'Nử')";
                                break;
                        }
                        break;
                    case 2:
                        switch (jComboBox3.getSelectedIndex()) {
                            case 0:
                                sql = "SELECT * FROM tamtru WHERE (socmnd LIKE '%" + dk + "%')";
                                break;
                            case 1:
                                sql = "SELECT * FROM tamtru WHERE ((socmnd LIKE '%" + dk + "%')) and (gioitinh = 'Nam')";
                                break;
                            case 2:
                                sql = "SELECT * FROM tamtru WHERE ((socmnd LIKE '%" + dk + "%')) and (gioitinh = 'Nử')";
                                break;
                        }
                        break;
                    case 3:
                        switch (jComboBox3.getSelectedIndex()) {
                            case 0:
                                sql = "SELECT * FROM tamtru WHERE (ten LIKE '%" + dk + "%')";
                                break;
                            case 1:
                                sql = "SELECT * FROM tamtru WHERE ((ten LIKE '%" + dk + "%')) and (gioitinh = 'Nam')";
                                break;
                            case 2:
                                sql = "SELECT * FROM tamtru WHERE ((ten LIKE '%" + dk + "%')) and (gioitinh = 'Nử')";
                                break;
                        }
                        break;
                }
                break;
        }
        try {
            rs = con.excuteQuery(sql);
            DefaultTableModel tableMode = new DefaultTableModel();
            java.sql.ResultSetMetaData metadata = rs.getMetaData();
            int numberColunm = metadata.getColumnCount() - 1;
            Vector<String> arrayColunm = new Vector<String>();
            for (int i = 1; i <= numberColunm; i++) {
                arrayColunm.add(metadata.getColumnName(i));
            }
            tableMode.setColumnIdentifiers(arrayColunm.toArray());
            Vector<String> arrayRow = new Vector<String>();
            while (rs.next()) {
                for (int i = 1; i <= numberColunm; i++) {
                    arrayRow.add(rs.getString(i));
                }
                tableMode.addRow(arrayRow.toArray());
                arrayRow.clear();
            }
            jTable1.setModel(tableMode);
            rs.close();
            rs = con.excuteQuery(sql);
            int i = 0;
            while (rs.next()) {
                i++;
            }
            rs.close();
            jLabel7.setText("Hiện Tại có " + i + " Người (Theo Điều Kiện Tìm Kiếm Của bạn)");
        } catch (Exception ex) {
            Logger.getLogger(FThongKeDanSo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        tffind = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setForeground(java.awt.Color.white);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Tìm kiếm theo điều kiện");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 255)));
        jPanel2.setForeground(new java.awt.Color(153, 153, 255));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Hộ Dân", "Hộ Khẩu", "Tạm Vắng", "Tạm Trú" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất Cả", "Họ Tên", "Số CMND", "Mã" }));

        tffind.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tffindKeyReleased(evt);
            }
        });

        jLabel1.setText("Nội Dung Cần Tìm");

        jLabel2.setText("Giới Tính");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất Cả", "Nam", "Nử" }));

        jButton2.setText("Tìm");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setText("Chọn Bảng Cần Tìm");

        jLabel4.setText("Tìm theo");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(jLabel1)
                        .addGap(56, 56, 56)
                        .addComponent(tffind, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addGap(16, 16, 16)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(236, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tffind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2))
                    .addComponent(jLabel1))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        jButton1.setText("In Nội Dung Bên Dưới");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel6.setText("Thống kê:");

        jLabel7.setText("jLabel7");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            jTable1.print();
        } catch (PrinterException ex) {
            Logger.getLogger(FThongKeDanSo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tffindKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tffindKeyReleased
        loadDk(tffind.getText());
    }//GEN-LAST:event_tffindKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        loadDk(tffind.getText());
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FThongKeDanSo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FThongKeDanSo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FThongKeDanSo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FThongKeDanSo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new FThongKeDanSo().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField tffind;
    // End of variables declaration//GEN-END:variables
}
