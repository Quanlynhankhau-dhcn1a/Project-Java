/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import sql.qlnk.MySQLConnect;
import sql.qlnk.*;
import sql.qlnk.kiemtra.dulieu;
import java.awt.print.PrinterException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public class FQuanLyCB extends javax.swing.JInternalFrame {

    MySQLConnect con = new MySQLConnect(dulieu.getHost(), dulieu.getId(), dulieu.getPassword(), dulieu.getCsdl());
    MyConnectUnit con1 = new MyConnectUnit(dulieu.getHost(), dulieu.getId(), dulieu.getPassword(), dulieu.getCsdl());
    private ResultSet rs;
    int mastt = 0, max = 0;

    public FQuanLyCB() throws Exception {
        initComponents();
        loadStt();
        loadTable();
        lList();
    }

    private void lList() throws Exception {
        rs = con1.Select("canbo");
        DefaultListModel a = new DefaultListModel();
        while (rs.next()) {
            a.addElement(rs.getString(2));
        }
        jList1.setModel(a);
        rs.close();
    }

    private void thongKe() throws Exception {
        rs = con.excuteQuery("select * from canbo where chucvu = '" + "Quản Lý" + "'");
        int i = 0;
        while (rs.next()) {
            i++;
        }
        jLabel8.setText("Hiện tại có " + i + " Quản Lý");
        rs.close();
        i = 0;
        rs = con.excuteQuery("select * from canbo where chucvu = '" + "Nhân Viên" + "'");
        while (rs.next()) {
            i++;
        }
        jLabel8.setText(jLabel8.getText() + " và " + i + " Nhân Viên");
        rs.close();
    }

    private void loadStt() throws Exception {
        rs = con.excuteQuery("select * from canbo");//dem them vào so thứ tự
        while (rs.next()) {
            max = Math.max(max, rs.getInt(8)) + 1;
        }

        rs.close();
    }

    ;

    private void loadTable() throws Exception {
        DefaultTableModel tableMode = new DefaultTableModel();
        rs = con.excuteQuery("select * from canbo");
        java.sql.ResultSetMetaData metadata = rs.getMetaData();
        int numberColunm = metadata.getColumnCount() - 1;
        String a[] = {"Mã Cán Bộ", "Tên Cán Bộ", "Ngày tháng Năm Sinh", "Quê Quán", "Chức Vụ", "Số CMNN"};
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
        thongKe();
    }

    private void lList(String dk) throws Exception {
        String sql = null;
        if (jComboBox1.getSelectedIndex() == 0) {
            sql = "SELECT * FROM canbo WHERE (socmnd LIKE '%" + dk + "%') or (tenCB LIKE '%" + dk + "%')or (MaCB LIKE '%" + dk + "%')";
        } else if (jComboBox1.getSelectedIndex() == 1) {
            sql = "SELECT * FROM canbo WHERE socmnd LIKE '%" + dk + "%'";
        } else if (jComboBox1.getSelectedIndex() == 2) {
            sql = "SELECT * FROM canbo WHERE macb LIKE '%" + dk + "%'";
        } else {
            sql = "SELECT * FROM canbo WHERE ten LIKE '%" + dk + "%'";
        }
        DefaultListModel a = new DefaultListModel();
        rs = con.excuteQuery(sql);
        while (rs.next()) {
            a.addElement(rs.getString(2));
        }
        jList1.setModel(a);
        rs.close();
    }

    public static String now(String dateFormat) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(cal.getTime());
    }

    private void txt() throws SQLException {
        tfmacb.setText(rs.getString(1));
        tfmacb.requestFocus();
        tfngaysinh.setText(rs.getString(3));
        tfquequan.setText(rs.getString(4));
        tfsocmnd.setText(rs.getString(6));
        tftencb.setText(rs.getString(2));
        tfpw.setText(rs.getString(7));
        mastt = rs.getInt(8);
        tfpw1.setText(tfpw.getText());
        DefaultComboBoxModel a = new DefaultComboBoxModel();
        a.addElement(rs.getString(5));
        a.addElement("Quản Lý");
        a.addElement("Nhân Viên");
        cbocv.setModel(a);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel3 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jPanel1 = new javax.swing.JPanel();
        tfmacb = new javax.swing.JTextField();
        tftencb = new javax.swing.JTextField();
        tfngaysinh = new javax.swing.JTextField();
        tfquequan = new javax.swing.JTextField();
        cbocv = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tfsocmnd = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        tfpw = new javax.swing.JPasswordField();
        tfpw1 = new javax.swing.JPasswordField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Quản lý cán bộ");

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));

        jLabel8.setText("jLabel8");

        jLabel11.setText("Thống Kê: ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jSplitPane1.setDividerLocation(160);

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất Cả", "Số CMND", "Mã Cán Bộ", "Tên Cán Bộ" }));

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
        jScrollPane1.setViewportView(jList1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboBox1, 0, 149, Short.MAX_VALUE)
                    .addComponent(jTextField1)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane1.setLeftComponent(jPanel3);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(771, 452));

        cbocv.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Quản Lý", "Nhân Viên" }));

        jLabel1.setText("Mã Cán Bộ");

        jLabel2.setText("Tên Cán Bộ");

        jLabel3.setText("Ngày Sinh");

        jLabel4.setText("Quê Quán");

        jLabel5.setText("Chức Vụ");

        jLabel6.setText("Mật Khẩu");

        jLabel7.setText("Nhập Lại :");

        jLabel9.setText("Sô CMNN: ");

        jButton5.setText("Tạo Mới");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Cập Nhật");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Xóa");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Lưu");
        jButton8.setEnabled(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jButton1.setText("Hủy");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tfmacb, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                            .addComponent(tftencb, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfngaysinh, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfquequan, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(tfpw))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(tfpw1, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(26, 26, 26)
                                .addComponent(cbocv, 0, 244, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfsocmnd, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton7)
                        .addGap(18, 18, 18)
                        .addComponent(jButton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfmacb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(tfpw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tftencb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(tfpw1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfngaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfquequan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton5)
                            .addComponent(jButton6)
                            .addComponent(jButton7)
                            .addComponent(jButton8)
                            .addComponent(jButton1)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(cbocv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfsocmnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane1.setRightComponent(jPanel1);

        jPanel4.setBackground(new java.awt.Color(0, 0, 102));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 857, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        tfmacb.setText("");
        tfmacb.requestFocus();
        tfngaysinh.setText("");
        tfquequan.setText("");
        tfsocmnd.setText("");
        tftencb.setText("");
        tfpw.setText("");
        tfpw1.setText("");
        jButton8.setEnabled(true);
        jButton5.setEnabled(false);
        jButton6.setEnabled(false);
        jButton7.setEnabled(false);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        String macb, tencb, ngaysinh, chucvu, quequan, pw, pw1;
        int socmnd;
        macb = tfmacb.getText();
        ngaysinh = tfngaysinh.getText();
        quequan = tfquequan.getText();
        socmnd = Integer.valueOf(tfsocmnd.getText());
        tencb = tftencb.getText();
        pw = tfpw.getText();
        pw1 = tfpw1.getText();
        chucvu = cbocv.getSelectedItem().toString();
        if (pw.equals(pw)) {
            try {
                rs = con.excuteQuery("select * from canbo where MaCb = '" + tfmacb.getText() + "'");
                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "mã cán bộ này đã tòn tài");
                } else {
                    int a = con.executeUpdate("insert into canbo values ('" + macb + "','" + tencb + "','" + ngaysinh + "'"
                            + ",'" + quequan + "','" + chucvu + "','" + socmnd + "','" + pw + "','" + max + "')");
                    max++;
                    rs.close();
                    loadTable();
                    int h = con.executeUpdate("insert into log values ('" + Main.jLabel1.getText() + "','Tạo 1 quản lý mới','" + now("dd/MM/yyyy") + "','" + now("hh:mmss") + "')");
                    JOptionPane.showMessageDialog(null, "Bạn đã Thêm Thành Công Mã Cán Bộ " + macb + " vào CSDL");
                    jButton8.setEnabled(false);
                    jButton5.setEnabled(true);
                    jButton6.setEnabled(true);
                    jButton7.setEnabled(true);
                    loadTable();
                    lList();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "bạn vui lòng kiểm tra lại thông tin cho chính xác");
                Logger.getLogger(FQuanLyCB.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            JOptionPane.showMessageDialog(this, "Bạn Nhập 2  mật khẩu không trùng nhau");
            tfpw.requestFocus();
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            rs = con.excuteQuery("select * from canbo where MaCb = '" + tfmacb.getText() + "'");
            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "mã cán bộ này đã tòn tài");
            } else {
                HashMap<String, Object> updateValues = new HashMap<String, Object>();
                updateValues.put("macb", tfmacb.getText());
                updateValues.put("tencb", tftencb.getText());
                updateValues.put("ngaysinh", tfngaysinh.getText());
                updateValues.put("quequan", tfquequan.getText());
                updateValues.put("chucvu", cbocv.getSelectedItem().toString());
                updateValues.put("socmnd", Integer.valueOf(tfsocmnd.getText()));
                updateValues.put("mk", tfpw.getText());
                try {
                    con1.Update("canbo", updateValues, "stt=" + mastt);
                    int h = con.executeUpdate("insert into log values ('" + Main.jLabel1.getText() + "','Cập Nhật 1 quản lý','" + now("dd/MM/yyyy") + "','" + now("hh:mm:ss") + "')");
                    JOptionPane.showMessageDialog(this, "cập nhật thành công");
                    loadTable();
                    lList();
                } catch (Exception ex) {
                    Logger.getLogger(FQuanLyCB.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "cập nhật thất bại");
                }
            }
            rs.close();
        } catch (Exception ex) {
            Logger.getLogger(FQuanLyCB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        int a = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa cán bộ này không", "Chú ý", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (a == 0) {
            try {
                con1.Delete("canbo", "stt =" + mastt);
                int h = con.executeUpdate("insert into log values ('" + Main.jLabel1.getText() + "','Xóa 1 quản lý','" + now("dd/MM/yyyy") + "','" + now("hh:mm:ss") + "')");
                JOptionPane.showMessageDialog(this, "Xóa Thành công");
                loadTable();
                lList();
                tfmacb.setText("");
                tfngaysinh.setText("");
                tfquequan.setText("");
                tfsocmnd.setText("");
                tftencb.setText("");
                tfpw.setText("");
                tfpw1.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi");

                Logger.getLogger(FQuanLyCB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void show(String dk) throws Exception {
        String sql = null;
        sql = ("SELECT * FROM canbo where TenCB = '" + dk + "'");
        rs = con.excuteQuery(sql);
        rs.next();
        txt();
        rs.close();

    }
    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        try {
            show(jList1.getSelectedValue().toString());
        } catch (Exception ex) {
            Logger.getLogger(FQuanLyCB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jList1ValueChanged

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        try {
            lList(jTextField1.getText());
        } catch (Exception ex) {
            Logger.getLogger(FQuanLyCB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        DefaultTableModel a = (DefaultTableModel) jTable1.getModel();
        int u = jTable1.getSelectedRow();
        System.out.println(a.getValueAt(u, 0));

    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jButton8.setEnabled(false);
        jButton5.setEnabled(true);
        jButton6.setEnabled(true);
        jButton7.setEnabled(true);
    }//GEN-LAST:event_jButton1ActionPerformed
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbocv;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField tfmacb;
    private javax.swing.JTextField tfngaysinh;
    private javax.swing.JPasswordField tfpw;
    private javax.swing.JPasswordField tfpw1;
    private javax.swing.JTextField tfquequan;
    private javax.swing.JTextField tfsocmnd;
    private javax.swing.JTextField tftencb;
    // End of variables declaration//GEN-END:variables
}
