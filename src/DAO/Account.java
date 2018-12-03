package DAO;

/**DAO
 *@Description 此对象用于访问数据库。
 * DAO中包含了各种数据库的操作方法。通过它的方法,
 * 结合POjo对数据库进行相关的操作.只是数据库的操作方法，不可以涉及到业务逻辑
 * */


import model.Menuman;
import model.Sender;
import model.Worker;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**账户操作*/
public class Account {
    /*example*/
    /**getAccount
     *可修改
     * */
    public static ResultSet getAccount(Connection con, String id, String passwd,String type)throws Exception {
        String sql = "select *from ? where ?=? and passwd=?";
        // PreparedStatement pstmt = con.prepareStatement(sql);
		// 改成可返回可更新数据库ResultSet的pstmt
        PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        if (type.equals("Customer")) {
            pstmt.setString(1, "customer");
            pstmt.setString(2, "id");
            pstmt.setString(3,id);
            pstmt.setString(4,passwd);
        }
        else if (type.equals("Employee")) {
            pstmt.setString(1, "employee");
            pstmt.setString(2, "id");
            pstmt.setString(3,id);
            pstmt.setString(4,passwd);
        }
        else if (type.equals("Menuman")) {
            pstmt.setString(1, "menuman");
            pstmt.setString(2, "id");
            pstmt.setString(3,id);
            pstmt.setString(4,passwd);
        }
        else if (type.equals("Sender")) {
            pstmt.setString(1, "sender");
            pstmt.setString(2, "id");
            pstmt.setString(3,id);
            pstmt.setString(4,passwd);
        }
        ResultSet rs=pstmt.executeQuery();
        return rs;

    }
}
