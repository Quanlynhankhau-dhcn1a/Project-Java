/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sql.qlnk;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class MySQLConnect {

    String Host = "";     // Thông tin Host của DataBase Server mình connect tới.
    String UserName = ""; // Thông tin tên đăng nhập cua DataBase Server.
    String Password = ""; // Thông tin mật khẩu của DataBase Server.
    String DataBase = ""; // Tên DataBase muốn connect tới.
    Connection connect = null;    // Khởi tạo Connection bằng null;
    Statement statement = null;   // Khởi tạo Statement để thực thi lệnh SQL;
    ResultSet result = null;     // Khởi tạo ResultSet chứa dử liệu khi thực thi câu lệnh Select.

    // Hàm khởi tạo và truyền thông tin của DataBase Server.
    public MySQLConnect(String Host,
            String UserName,
            String Password,
            String DataBase) {
        this.DataBase = DataBase;
        this.Host = Host;
        this.Password = Password;
        this.UserName = UserName;
    }

    // Hàm kiểm tra xem Driver connect MySQL đã sẵn sàng hay chưa.
    protected void driverTest() throws Exception {
        try {
            //Kiểm tra Class Name.
            Class.forName("org.gjt.mm.mysql.Driver");
        } // Nếu chưa tồng tại thì mém lỗi ra ngoài.
        catch (java.lang.ClassNotFoundException e) {
            throw new Exception("MySQL JDBC Driver not found ... ");
        }
    }

    // Hàm lấy Connecttion
    protected Connection getConnect() throws Exception {
        // Nếu connetion null thì khởi tạo mới.
        if (this.connect == null) {
            // Kiểm tra Driver
            driverTest();

            // Tạo Url kết nối đền DataBase Server
            String url = "jdbc:mysql://" + this.Host + ":3306/" + this.DataBase + "?useUnicode=true&characterEncoding=UTF-8";
            try {
                // Tạo Connection thông qua Url
                this.connect = DriverManager.getConnection(url, this.UserName, this.Password);
            } // Nếu không thành công ném lỗi ra ngoài.
            catch (java.sql.SQLException e) {
                JOptionPane.showMessageDialog(null,"Không thể kết nối đến DataBase Server: " + url + " " + e.getMessage());
            }
        }
        // Trả connection ra ngoài.
        return this.connect;
    }

    // Tạo Statement để thực thi câu Query
    protected Statement getStatement() throws Exception {
        // Kiểm tra statement nếu = null hoặc đã đóng.
        if (this.statement == null ? true : this.statement.isClosed()) {
            // Khởi tạo một statement mới.
            this.statement = this.getConnect().createStatement();
        }
        // Trả staement ra ngoài.
        return this.statement;
    }

    // Hàm thực thi câu lệnh Select lấy dữ liệu từ CSDL
    public ResultSet excuteQuery(String Query) throws Exception {
        try {
            // Thực thi câu lệnh.
            this.result = getStatement().executeQuery(Query);
        } // Nếu không thành công ném lỗi ra ngoài.
        catch (Exception e) {
            throw new Exception("Erro: " + e.getMessage() + " - " + Query);
        }
        // Trả kết quả ra ngoài.
        return this.result;
    }

    // Thức thi các câu lệnh Inser, Update, Delete
    public int executeUpdate(String Query) throws Exception {
        //Khai báo biến int lưu trữ kết quả tình trạng thực thi câu lệnh Query.
        int res = Integer.MIN_VALUE;
        try {
            //Thực thi câu lệnh.
            res = getStatement().executeUpdate(Query);
        } //Nếu không thành công ném lỗi ra ngoài.
        catch (Exception e) {
            throw new Exception("Erro: " + e.getMessage() + " - " + Query);
        } finally {
            //Đóng kết nối.
            this.Close();
        }
        //Trả kết quả ra ngoài.
        return res;
    }

    // Hàm đóng kết nối.
    public void Close() throws SQLException {
        // Nếu Result chưa đóng. Đóng result
        if (this.result != null && !this.result.isClosed()) {
            this.result.close();
            this.result = null;
        }
        // Nếu statement chưa đóng. Đóng statement.
        if (this.statement != null && !this.statement.isClosed()) {
            this.statement.close();
            this.statement = null;
        }
        // Nếu connection chưa đóng. Đóng connection.
        if (this.connect != null && !this.connect.isClosed()) {
            this.connect.close();
            this.connect = null;
        }
    }
}
