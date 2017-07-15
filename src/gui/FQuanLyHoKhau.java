/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import sql.qlnk.MySQLConnect;
import sql.qlnk.*;
import sql.qlnk.kiemtra.dulieu;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class FQuanLyHoKhau extends javax.swing.JInternalFrame {

    int mastt, max;
    MySQLConnect con = new MySQLConnect(dulieu.getHost(), dulieu.getId(), dulieu.getPassword(), dulieu.getCsdl());
    MyConnectUnit con1 = new MyConnectUnit(dulieu.getHost(), dulieu.getId(), dulieu.getPassword(), dulieu.getCsdl());
    private ResultSet rs;

    /**
     * Creates new form FQuanLyHoKhau
     */
    public FQuanLyHoKhau() throws Exception {
        initComponents();
        lList();
        lThongke();
        loadStt();
        loadTable();
        DefaultComboBoxModel a = new DefaultComboBoxModel();
        for (int i = 1990; i < 2020; i++) {
            a.addElement(i);
        }
        cboyear.setModel(a);
    }

    public static String now(String dateFormat) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(cal.getTime());
    }

    private void loadStt() throws Exception {
        rs = con.excuteQuery("select * from hokhau");//dem them vào so thứ tự
        while (rs.next()) {
            max = Math.max(max, rs.getInt(7)) + 1;
        }

        rs.close();
    }

    private void lThongke() throws Exception {
        rs = con1.Select("hokhau");
        int i = 0;
        while (rs.next()) {
            i++;
        }
        jLabel12.setText("Hiện tại đang có " + i + " hộ khẩu");
        rs.close();
    }

    private void lList() throws Exception {
        rs = con1.Select("hokhau");
        DefaultListModel a = new DefaultListModel();
        while (rs.next()) {
            a.addElement(rs.getString(2));
        }
        jList1.setModel(a);
        rs.close();
    }

    private void loadTable() throws Exception {
        DefaultTableModel tableMode = new DefaultTableModel();
        rs = con.excuteQuery("select * from hokhau");
        java.sql.ResultSetMetaData metadata = rs.getMetaData();
        int numberColunm = metadata.getColumnCount() - 1;
        String a[] = {"Mã Hộ Khẩu", "Tên Chủ Hộ", "Số CMND", "Địa Chỉ", "Ngày Cấp", "Nơi Cấp"};
//        Vector<String> arrayColunm = new Vector<String>();
//        for (int i = 1; i <= numberColunm; i++) {
//            arrayColunm.add(metadata.getColumnName(i));
//        }
        tableMode.setColumnIdentifiers(a);
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
    }

    private void lList(String dk) throws Exception {
        String sql = null;
        if (jComboBox1.getSelectedIndex() == 0) {
            sql = "SELECT * FROM hokhau WHERE (socmnd LIKE '%" + dk + "%') or (MaHD LIKE '%" + dk + "%')or (tenchuho LIKE '%" + dk + "%')";
        } else if (jComboBox1.getSelectedIndex() == 1) {
            sql = "SELECT * FROM tamtru WHERE tenchuho LIKE '%" + dk + "%'";
        } else if (jComboBox1.getSelectedIndex() == 1) {
            sql = "SELECT * FROM tamtru WHERE socmnd LIKE '%" + dk + "%'";
        } else {
            sql = "SELECT * FROM tamtru WHERE MaHD LIKE '%" + dk + "%'";
        }
        DefaultListModel a = new DefaultListModel();
        rs = con.excuteQuery(sql);
        while (rs.next()) {
            a.addElement(rs.getString(2));
        }
        jList1.setModel(a);
        rs.close();
    }

    private void txt() throws SQLException {
        tfmahk.setText(rs.getString(1));
        tften.setText(rs.getString(2));
        tfscmnd.setText(rs.getString(3));
        tfdiachi.setText(rs.getString(4));
        tfnoicap.setText(rs.getString(6));
        mastt = rs.getInt(7);
        DefaultComboBoxModel n = new DefaultComboBoxModel();
        n.addElement(rs.getString(5).substring(8));
        for (int i = 0; i < 31; i++) {
            n.addElement(i);
        }

        cboday.setModel(n);
        DefaultComboBoxModel t = new DefaultComboBoxModel();
        t.addElement(rs.getString(5).substring(5, 7));
        for (int i = 0; i < 12; i++) {
            t.addElement(i);
        }

        cbomoth.setModel(t);
        cboday.setModel(n);
        DefaultComboBoxModel nam = new DefaultComboBoxModel();
        nam.addElement(rs.getString(5).substring(0, 4));
        for (int i = 1900; i < 2020; i++) {
            nam.addElement(i);
        }
        cboyear.setModel(nam);

    }

    private void show(String dk) throws Exception {
        String sql = null;
        sql = ("SELECT * FROM hokhau where Tenchuho = '" + dk + "'");
        rs = con.excuteQuery(sql);
        rs.next();
        txt();
        rs.close();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tfmahk = new javax.swing.JTextField();
        tften = new javax.swing.JTextField();
        tfscmnd = new javax.swing.JTextField();
        tfdiachi = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tfnoicap = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        cboday = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        cbomoth = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        cboyear = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        jTextField7 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Quản Lý Hộ Khẩu");

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jPanel2.setBackground(new java.awt.Color(0, 0, 102));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(153, 153, 255));

        jLabel11.setText("Thống Kê");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel1.setText("Mã Hộ Khẩu");

        jLabel2.setText("Tên Chủ Hộ");

        jLabel3.setText("Số CMND");

        jLabel4.setText("Địa Chỉ");

        jLabel5.setText("Ngày Cấp");

        jLabel6.setText("Nơi Cấp");

        jButton1.setText("Tạo Mới");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cập Nhật");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Xóa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Lưu");
        jButton4.setEnabled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

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

        jButton5.setText("Hủy");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        cboday.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jLabel7.setText("Tháng");

        cbomoth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        jLabel8.setText("Năm");

        cboyear.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(28, 28, 28)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfmahk, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                            .addComponent(tften)
                            .addComponent(tfscmnd))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfnoicap))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfdiachi))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cboday, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbomoth, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8)
                                .addGap(12, 12, 12)
                                .addComponent(cboyear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tfmahk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(tfdiachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(tften, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addComponent(jLabel3))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cboday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7)
                                .addComponent(cbomoth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8)
                                .addComponent(cboyear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tfscmnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6))
                            .addComponent(tfnoicap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE))
        );

        jSplitPane1.setRightComponent(jPanel3);

        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField7KeyReleased(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất Cả", "Tên Chủ Hộ", "Số CMND", "Mã Hộ Khẩu" }));

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jList1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jComboBox1, 0, 147, Short.MAX_VALUE)
                    .addComponent(jTextField7))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(jPanel5);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSplitPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane1)
                .addGap(21, 21, 21)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyReleased
        try {
            lList(jTextField7.getText());
        } catch (Exception ex) {
            Logger.getLogger(FTamTru.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTextField7KeyReleased

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        try {
            show(jList1.getSelectedValue().toString());
            jButton1.setEnabled(true);
            jButton2.setEnabled(true);
            jButton3.setEnabled(true);
            jButton4.setEnabled(false);
        } catch (Exception ex) {
            Logger.getLogger(FQuanLyHoKhau.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jList1ValueChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        tfmahk.setText("");
        tften.setText("");
        tfscmnd.setText("");
        tfdiachi.setText("");
        tfnoicap.setText("");
        tfmahk.requestFocus();
        jButton1.setEnabled(false);
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
        jButton4.setEnabled(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String mahk, ten, scmnd, diachi, ngaycap, noicap, n, t, nam;
        mahk = tfmahk.getText();
        ten = tften.getText();
        scmnd = tfscmnd.getText();
        diachi = tfdiachi.getText();
        ngaycap = cboyear.getSelectedItem().toString() + "-" + cbomoth.getSelectedItem().toString() + "-" + cboday.getSelectedItem().toString();
        noicap = tfnoicap.getText();
        try {
            rs = con.excuteQuery("select * from canbo where MaCb = '" + mahk + "'");
            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Mã Hộ Khẩu này đã tồn tại");
            } else {
                try {
                    int a = con.executeUpdate("insert into hokhau values ('" + mahk + "','" + ten + "','" + scmnd + "'"
                            + ",'" + diachi + "','" + ngaycap + "','" + noicap + "','" + max + "')");
                    max++;
                    jButton1.setEnabled(true);
                    jButton2.setEnabled(true);
                    jButton3.setEnabled(true);
                    jButton4.setEnabled(false);
                    int h = con.executeUpdate("insert into log values ('" + Main.jLabel1.getText() + "','Tạo 1 hộ khẩu mới','" + now("dd/MM/yyyy") + "','" + now("hh:mm:ss") + "')");
                    JOptionPane.showMessageDialog(null, "Bạn đã Thêm Thành Công Mã Hộ Khẩu " + tfmahk.getText() + " vào CSDL");
                    lList();
                    loadTable();
                } catch (Exception ex) {
                    Logger.getLogger(FQuanLyHoKhau.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            rs.close();
        } catch (Exception ex) {
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int a = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa Hộ khẩu này không này không", "Chú ý", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (a == 0) {
            try {
                con1.Delete("hokhau", "stt =" + mastt);
                JOptionPane.showMessageDialog(this, "Xóa Thành công");
                tfmahk.setText("");
                tften.setText("");
                tfscmnd.setText("");
                tfdiachi.setText("");
                tfnoicap.setText("");
                int h = con.executeUpdate("insert into log values ('" + Main.jLabel1.getText() + "','Xóa 1 hộ khẩu','" + now("dd/MM/yyyy") + "','" + now("hh:mm:ss") + "')");
                lList();
                loadTable();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi");

                Logger.getLogger(FQuanLyCB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String ngaycap;
        ngaycap = cboyear.getSelectedItem().toString() + "-" + cbomoth.getSelectedItem().toString() + "-" + cboday.getSelectedItem().toString();
        try {


            HashMap<String, Object> updateValues = new HashMap<String, Object>();
            updateValues.put("maHD", tfmahk.getText());
            updateValues.put("tenChuHo", tften.getText());
            updateValues.put("SoCMND", tfscmnd.getText());
            updateValues.put("Diachi", tfdiachi.getText());
            updateValues.put("ngaycap", ngaycap);
            updateValues.put("noicap", tfnoicap.getText());
            try {
                con1.Update("hokhau", updateValues, "stt=" + mastt);
                JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                int h = con.executeUpdate("insert into log values ('" + Main.jLabel1.getText() + "','Cập nhật 1 hộ khẩu','" + now("dd/MM/yyyy") + "','" + now("hh:mm:ss") + "')");
                lList();
                loadTable();
            } catch (Exception ex) {
                Logger.getLogger(FQuanLyCB.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "cập nhật thất bại");

            }
    }//GEN-LAST:event_jButton2ActionPerformed
 catch (Exception ex) {
            Logger.getLogger(FQuanLyHoKhau.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        jButton1.setEnabled(true);
        jButton2.setEnabled(true);
        jButton3.setEnabled(true);
        jButton4.setEnabled(false);
    }//GEN-LAST:event_jButton5ActionPerformed
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cboday;
    private javax.swing.JComboBox cbomoth;
    private javax.swing.JComboBox cboyear;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField tfdiachi;
    private javax.swing.JTextField tfmahk;
    private javax.swing.JTextField tfnoicap;
    private javax.swing.JTextField tfscmnd;
    private javax.swing.JTextField tften;
    // End of variables declaration//GEN-END:variables
}
