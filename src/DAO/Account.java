package DAO;

/**dao
 *@Description 此对象用于访问数据库。
 * DAO中包含了各种数据库的操作方法。通过它的方法,
 * 结合POjo对数据库进行相关的操作.只是数据库的操作方法，不可以涉及到业务逻辑
 * */


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**账户操作*/
public class Account {
    /*example*/
    /**getAccount
     *可修改
     * */
    public static ResultSet getAccount(Connection con, String id, String passwd,String type)throws Exception {
        String sql = "select * from ? where id  = ? and passwd = ?";
        // PreparedStatement pstmt = con.prepareStatement(sql);
        // 改成可返回可更新数据库ResultSet的pstmt
        PreparedStatement pstmt = null;
        if (type.equals("Customer")) {
            sql = sql.replaceFirst("\\?", "customer");
            pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            System.out.println(sql);
            pstmt.setString(1,id);
            pstmt.setString(2,passwd);
        }
        else if (type.equals("Employee")) {
            sql = sql.replaceFirst("\\?", "employee");
            pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstmt.setString(1,id);
            pstmt.setString(2,passwd);
        }
        else if (type.equals("Menuman")) {
            sql = sql.replaceFirst("\\?", "menuman");
            pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstmt.setString(1,id);
            pstmt.setString(2,passwd);
        }
        else if (type.equals("Sender")) {
            sql = sql.replaceFirst("\\?", "sender");
            pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstmt.setString(1,id);
            pstmt.setString(2,passwd);
        }
        System.out.printf("sql语句：%s\n", pstmt.toString());
        ResultSet rs=pstmt.executeQuery();
        return rs;

    }
}
