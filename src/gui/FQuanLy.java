/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import sql.qlnk.MySQLConnect;
import sql.qlnk.*;
import java.sql.ResultSet;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import sql.qlnk.kiemtra.dulieu;
import gui.Main;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

/**
 *
 * @author user
 */
public class FQuanLy extends javax.swing.JInternalFrame {

    //  MySQLConnect con = new MySQLConnect(dulieu.getHost(), dulieu.getId(), dulieu.getPassword(), dulieu.getCsdl());
    MySQLConnect con = new MySQLConnect(dulieu.getHost(), dulieu.getId(), dulieu.getPassword(), dulieu.getCsdl());
    MyConnectUnit con1 = new MyConnectUnit(dulieu.getHost(), dulieu.getId(), dulieu.getPassword(), dulieu.getCsdl());
    private ResultSet rs;
    int max = 0;
    int mastt = 0;

    /**
     * Creates new form FQuanLy
     */
    public FQuanLy() throws Exception {
        initComponents();
        llist();
        loadTable();
        loadStt();
        DefaultComboBoxModel a = new DefaultComboBoxModel();
        for (int i = 1990; i < 2020; i++) {
            a.addElement(i);
        }
        cboyear.setModel(a);
    }

    private void llist() throws Exception {
        rs = con.excuteQuery("select * from nguoidan");
        DefaultListModel a = new DefaultListModel();
        while (rs.next()) {
            a.addElement(rs.getString(2));
        }
        jList1.setModel(a);
        rs.close();
    }

    private void loadStt() throws Exception {
        rs = con.excuteQuery("select * from nguoidan");//dem them vào so thứ tự
        while (rs.next()) {
            max = Math.max(max, rs.getInt(16)) + 1;
        }

        rs.close();
        try {
            rs = con.excuteQuery("select * from hokhau");

            DefaultComboBoxModel p = new DefaultComboBoxModel();
            while (rs.next()) {
                p.addElement(rs.getString(1));
            }
            jComboBox2.setModel(p);
        } catch (Exception ex) {
            Logger.getLogger(FQuanLy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void txt() {
        try {
            rs.next();
            jTextField2.setText(rs.getString(1));
            tfhten.setText(rs.getString(2));
            tfttg.setText(rs.getString(3));
            tfns.setText(rs.getString(4));
            tfscmnd.setText(rs.getString(5));
            tfnc.setText(rs.getString(6));
            tfq.setText(rs.getString(8));
            tfnghenhiep.setText(rs.getString(9));
            tfhocvan.setText(rs.getString(10));
            tftongiao.setText(rs.getString(11));
            tfquanhe.setText(rs.getString(12));
            tfghichu.setText(rs.getString(13));
            tfanh.setText(rs.getString(14));
            mastt = rs.getInt(16);
            lbanh.setIcon(new ImageIcon(tfanh.getText()));
            DefaultComboBoxModel a = new DefaultComboBoxModel();
            a.addElement(rs.getString(15));
            a.addElement("Nam");
            a.addElement("Nữ");
            cbogt.setModel(a);
            DefaultComboBoxModel n = new DefaultComboBoxModel();
            n.addElement(rs.getString(7).substring(8));
            for (int i = 0; i < 31; i++) {
                n.addElement(i);
            }
            cboday.setModel(n);

            DefaultComboBoxModel t = new DefaultComboBoxModel();
            t.addElement(rs.getString(7).substring(5, 7));
            for (int i = 0; i < 12; i++) {
                t.addElement(i);
            }
            cbomoth.setModel(t);

            cboday.setModel(n);
            DefaultComboBoxModel nam = new DefaultComboBoxModel();
            nam.addElement(rs.getString(7).substring(0, 4));
            for (int i = 1900; i < 2020; i++) {
                nam.addElement(i);
            }
            cboyear.setModel(nam);

            rs.close();
            String sql = null;
            sql = ("SELECT * FROM hokhau where Mahd = '" + jTextField2.getText() + "'");
            jTextField3.setText("");
            rs = con.excuteQuery(sql);
            rs.next();
            jTextField3.setText(rs.getString(2));
            rs.close();
        } catch (Exception e) {
        }
    }

    private void loadTable() throws Exception {
        DefaultTableModel tableMode = new DefaultTableModel();
        rs = con.excuteQuery("select * from nguoidan");
        java.sql.ResultSetMetaData metadata = rs.getMetaData();
        int numberColunm = metadata.getColumnCount() - 1;
        String a[] = {"Mã Hộ Khẩu", "Tên", "Tên Thường Gọi", "Năm Sinh",
            "socmnd", "Nơi Cấp", "Ngày cấp", "Quê Quán", "Nghề Nghiệp", "Học Vấn", "Tôn Giáo", "Quan hệ với chủ hộ",
            "Ghi chú", "Ảnh", "Giới Tính"};
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

    private void loadList(String dk) throws Exception {
        String sql = null;


        switch (jComboBox1.getSelectedIndex()) {
            case 4:
                sql = ("SELECT * FROM nguoidan where Socmnd like '%" + dk + "%'");
                break;
            case 1:
                sql = ("SELECT * FROM nguoidan where mahd like '%" + dk + "%'");
                break;
            case 2:
                sql = ("SELECT * FROM nguoidan where tenhd like '%" + dk + "%'");
                break;
            case 3:
                sql = ("SELECT * FROM nguoidan where ngaysinh like '%" + dk + "%'");
                break;
            default:
                sql = "SELECT * FROM nguoidan WHERE (Socmnd LIKE '%" + dk + "%') or (mahd LIKE '%" + dk + "%')or (hoten LIKE '%" + dk + "%')";
        }
        rs = con.excuteQuery(sql);
        DefaultListModel model = new DefaultListModel();
        while (rs.next()) {
            model.addElement(rs.getString(2));
        }
        jList1.setModel(model);
        rs.close();

        DefaultTableModel tableMode = new DefaultTableModel();
        rs = con.excuteQuery(sql);
        java.sql.ResultSetMetaData metadata = rs.getMetaData();
        int numberColunm = metadata.getColumnCount() - 1;
        String a[] = {"Mã Hộ Khẩu", "Tên", "Tên Thường Gọi", "Năm Sinh",
            "socmnd", "Nơi Cấp", "Ngày cấp", "Quê Quán", "Nghề Nghiệp", "Học Vấn", "Tôn Giáo", "Quan hệ với chủ hộ",
            "Ghi chú", "Ảnh", "Giới Tính"};
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jMenuItem3 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jPanel2 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        tfhten = new javax.swing.JTextField();
        tfscmnd = new javax.swing.JTextField();
        tfnc = new javax.swing.JTextField();
        tfttg = new javax.swing.JTextField();
        tfns = new javax.swing.JTextField();
        tfq = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jTextField2 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        cbomoth = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        cboyear = new javax.swing.JComboBox();
        cboday = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        tftongiao = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tfnghenhiep = new javax.swing.JTextField();
        tfquanhe = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tfghichu = new javax.swing.JTextArea();
        lbanh = new javax.swing.JLabel();
        tfanh = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        tfhocvan = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cbogt = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jButton1.setText("Tam trú");

        jMenuItem3.setText("jMenuItem3");

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Quản Lý Người Dân");

        jPanel1.setBackground(new java.awt.Color(0, 0, 102));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1105, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 62, Short.MAX_VALUE)
        );

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất cả", "Tìm Theo Mã Hộ Khẩu", "Tìm Theo Tên", "Tìm theo Số CMNN" }));

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

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 255)));

        jButton4.setText("Cập nhật");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Lưu");
        jButton5.setEnabled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Nhập mới");
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

        jLabel2.setText("Họ Tên");

        jLabel3.setText("Tên Thường gọi");

        jLabel4.setText("Năm Sinh");

        jLabel5.setText("Số CMND");

        jLabel6.setText("Nơi Cấp");

        jLabel8.setText("Quê Quán");

        jButton2.setText("Hủy");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel15.setText("Mã Hộ Khẩu");

        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });

        jLabel16.setText("Tên Chủ Hộ");

        jTextField3.setEnabled(false);

        jLabel17.setText("Tháng");

        cbomoth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        jLabel18.setText("Năm");

        cboyear.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboday.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jLabel19.setText("Ngày Cấp");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(jLabel5))
                                    .addComponent(jLabel15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(50, 50, 50))
                                    .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tfhten)
                                    .addComponent(tfttg)
                                    .addComponent(tfns)
                                    .addComponent(tfq)
                                    .addComponent(tfscmnd)
                                    .addComponent(tfnc)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton2)))))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboday, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbomoth, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel18)
                        .addGap(12, 12, 12)
                        .addComponent(cboyear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfhten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfttg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(tfscmnd, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfnc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17)
                        .addComponent(cbomoth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18)
                        .addComponent(cboyear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton6)
                        .addComponent(jButton4)
                        .addComponent(jButton7)
                        .addComponent(jButton5))
                    .addComponent(jButton2))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 255)));

        jLabel1.setText("Tôn Giáo");

        jLabel11.setText("Quan hệ Với Chủ hộ");

        jLabel14.setText("Ghi Chú");

        jLabel10.setText("Nghề Nghiệp");

        tfghichu.setColumns(20);
        tfghichu.setRows(5);
        jScrollPane1.setViewportView(tfghichu);

        lbanh.setBackground(new java.awt.Color(204, 255, 204));
        lbanh.setVerifyInputWhenFocusTarget(false);

        jButton8.setText("Chọn Ảnh");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel13.setText("Hình ảnh");

        jLabel9.setText("Học Vấn");

        cbogt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nam", "Nữ" }));

        jLabel12.setText("Giới Tình");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11))
                                .addGap(16, 16, 16)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfnghenhiep)
                                    .addComponent(tftongiao)
                                    .addComponent(tfquanhe)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(lbanh, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfhocvan, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfanh, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbogt, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton8))
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tftongiao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel10))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfnghenhiep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfquanhe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfhocvan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(tfanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13))
                    .addComponent(lbanh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbogt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(29, 29, 29))))
        );

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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane4)
                        .addGap(24, 24, 24))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                        .addGap(19, 19, 19))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        try {
            loadList(jTextField1.getText());
        } catch (Exception ex) {
            Logger.getLogger(FQuanLy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        JFileChooser a = new JFileChooser();
        a.setDialogTitle("Bạn vui lòng chọn 1 file ảnh");
        int b = a.showOpenDialog(null);
        if (b == JFileChooser.OPEN_DIALOG) {
            ImageIcon icon = new ImageIcon(a.getSelectedFile().toString());
            tfanh.setText(a.getSelectedFile().toString());
            lbanh.setIcon(icon);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged

        String sql = null;
        sql = ("SELECT * FROM nguoidan where hoten = '" + jList1.getSelectedValue().toString() + "'");
        try {
            rs = con.excuteQuery(sql);
            txt();
        } catch (Exception ex) {
            Logger.getLogger(FQuanLy.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jList1ValueChanged

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        DefaultTableModel a = (DefaultTableModel) jTable1.getModel();
        int u = jTable1.getSelectedRow();
        try {
            String sql = null;
            sql = "SELECT * FROM nguoidan WHERE socmnd LIKE '%" + a.getValueAt(u, 4).toString() + "%'";
            rs = con.excuteQuery(sql);
            txt();
        } catch (Exception ex) {
            Logger.getLogger(gui.FQuanLy.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String ngaycap;
        ngaycap = cboyear.getSelectedItem().toString() + "-" + cbomoth.getSelectedItem().toString() + "-" + cboday.getSelectedItem().toString();
        try {
            int a = con.executeUpdate("insert into nguoidan values ('" + jTextField2.getText() + "','" + tfhten.getText() + "','" + tfttg.getText() + "'"
                    + ",'" + tfns.getText() + "','" + tfscmnd.getText() + "','" + tfnc.getText() + "','" + ngaycap + "','" + tfq.getText() + "','" + tfnghenhiep.getText() + "'"
                    + ",'" + tfhocvan.getText() + "','" + tftongiao.getText() + "','" + tfquanhe.getText() + "','" + tfghichu.getText() + "','" + tfanh.getText() + "'"
                    + ",'" + cbogt.getSelectedItem().toString() + "','" + max + "')");
            max++;
            JOptionPane.showMessageDialog(this, "Thêm Thành công");
            int h = con.executeUpdate("insert into log values ('" + Main.jLabel1.getText() + "','Thêm 1 người dân','" + now("dd/MM/yyyy") + "','" + now("hh:mm:ss") + "')");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Người này đã tồn tại trong CSDL hoặc đã có lỗi khác xãy ra");
        }

        jButton6.setEnabled(false);
        jButton6.setEnabled(true);
        jButton4.setEnabled(true);
        jButton7.setEnabled(true);
        try {
            llist();
            loadTable();
        } catch (Exception ex) {
            Logger.getLogger(FQuanLy.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        jButton5.setEnabled(true);
        jButton6.setEnabled(false);
        jButton4.setEnabled(false);
        jButton7.setEnabled(false);
        tfhten.setText("");
        tfhten.requestFocus();
        tfttg.setText("");
        tfns.setText("");
        tfscmnd.setText("");
        tfnc.setText("");
        tfq.setText("");
        tfnghenhiep.setText("");
        tfhocvan.setText("");
        tftongiao.setText("");
        tfquanhe.setText("");
        tfghichu.setText("");
        tfanh.setText("");
        tfanh.setText("");
        lbanh.setIcon(new ImageIcon(tfanh.getText()));
        DefaultComboBoxModel a = new DefaultComboBoxModel();
        a.addElement("Nam");
        a.addElement("Nữ");
        cbogt.setModel(a);

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jButton6.setEnabled(false);
        jButton6.setEnabled(true);
        jButton4.setEnabled(true);
        jButton7.setEnabled(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        jTextField2.setText(jComboBox2.getSelectedItem().toString());
        String sql = null;
        sql = ("SELECT * FROM hokhau where Mahd = '" + jComboBox2.getSelectedItem().toString() + "'");
        try {
            rs = con.excuteQuery(sql);
            rs.next();
            jTextField3.setText(rs.getString(2));
            rs.close();
        } catch (Exception ex) {
            Logger.getLogger(FQuanLy.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jComboBox2ItemStateChanged
    public static String now(String dateFormat) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(cal.getTime());
    }
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        int a = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa cán bộ này không", "Chú ý", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (a == 0) {
            try {
                con1.Delete("nguoidan", "stt =" + mastt);
                int h = con.executeUpdate("insert into log values ('" + Main.jLabel1.getText() + "','Xóa 1 người dân','" + now("dd/MM/yyyy") + "','" + now("hh:mm:ss") + "')");
                JOptionPane.showMessageDialog(this, "Xóa Thành công");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi");
                Logger.getLogger(gui.FQuanLyCB.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                llist();
                loadTable();
            } catch (Exception ex) {
                Logger.getLogger(FQuanLy.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String ngaycap;
        ngaycap = cboyear.getSelectedItem().toString() + "-" + cbomoth.getSelectedItem().toString() + "-" + cboday.getSelectedItem().toString();
        HashMap<String, Object> updateValues = new HashMap<String, Object>();
        updateValues.put("mahd", jTextField2.getText());
        updateValues.put("hoten", tfhten.getText());
        updateValues.put("bidanh", tfttg.getText());
        updateValues.put("namsinh", tfns.getText());
        updateValues.put("socmnd", tfscmnd.getText());
        updateValues.put("ngaycap", ngaycap);
        updateValues.put("noicap", tfnc.getText());
        updateValues.put("quequan", tfq.getText());
        updateValues.put("nghenghiep", tfnghenhiep.getText());
        updateValues.put("hocvan", tfhocvan.getText());
        updateValues.put("tongiao", tftongiao.getText());
        updateValues.put("quanhe", tfquanhe.getText());
        updateValues.put("ghichu", tfghichu.getText());
        updateValues.put("anh", tfanh.getText());
        updateValues.put("gioitinh", cbogt.getSelectedItem().toString());
        try {
            con1.Update("nguoidan", updateValues, "stt=" + mastt);
            JOptionPane.showMessageDialog(this, "Cập nhật thành công");
            int h = con.executeUpdate("insert into log values ('" + Main.jLabel1.getText() + "','Cập nhật 1 người dân','" + now("dd/MM/yyyy") + "','" + now("hh:mm:ss") + "')");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Người này đã tồn tại trong CSDL hoặc đã có lỗi khác xãy ra");
            Logger.getLogger(FQuanLy.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            llist();
            loadTable();
        } catch (Exception ex) {
            Logger.getLogger(FQuanLy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed
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
            java.util.logging.Logger.getLogger(FQuanLy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FQuanLy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FQuanLy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FQuanLy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    new FQuanLy().setVisible(true);


                } catch (Exception ex) {
                    Logger.getLogger(FQuanLy.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cboday;
    private javax.swing.JComboBox cbogt;
    private javax.swing.JComboBox cbomoth;
    private javax.swing.JComboBox cboyear;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel lbanh;
    private javax.swing.JTextField tfanh;
    private javax.swing.JTextArea tfghichu;
    private javax.swing.JTextField tfhocvan;
    private javax.swing.JTextField tfhten;
    private javax.swing.JTextField tfnc;
    private javax.swing.JTextField tfnghenhiep;
    private javax.swing.JTextField tfns;
    private javax.swing.JTextField tfq;
    private javax.swing.JTextField tfquanhe;
    private javax.swing.JTextField tfscmnd;
    private javax.swing.JTextField tftongiao;
    private javax.swing.JTextField tfttg;
    // End of variables declaration//GEN-END:variables
}
