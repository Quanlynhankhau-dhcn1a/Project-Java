/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sql.qlnk.kiemtra;


/**
 *
 * @author user
 */
public class dulieu {
    private  static  String host ,id,password,csdl;

    public static String getCsdl() {
        return csdl;
    }

    public static void setCsdl(String csdl) {
        dulieu.csdl = csdl;
    }

    public static String getHost() {
        return host;
    }

    public static void setHost(String host) {
        dulieu.host = host;
    }

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        dulieu.id = id;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        dulieu.password = password;
    }
    
    }

