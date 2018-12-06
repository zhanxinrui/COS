package util;
import java.sql.*;

public class SQL{
	 
    // JDBC 驱动名及数据库URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://39.108.229.233:3306/COS?useSSL=false";
 
    // 数据库的用户名与密码
    private static final String USER = "toor";
    private static final String PASS = "134679";

	// 数据库的连接与查询
	public static Connection conn;

	/* The object used for executing a static SQL statement and returning the results it produces.
	 * By default, only one ResultSet object per Statement object can be open at the same time. 
	 * Therefore, if the reading of one ResultSet object is interleaved with the reading of another, 
	 * each must have been generated by different Statement objects. All execution methods in the 
	 * Statement interface implicitly close a current ResultSet object of the statement if an open one exists.
	 */
	 // 官方文档建议使用完后立即释放,即释放占用了的数据库和JDBC资源
	 // 建议不要等它自动关闭(没说Connection关闭后,它也会关闭)
	 // Statement stmt;  因为Statement对ResultSet的影响太大了，所以使用不显示的写出stmt这个引用
	 // Statement stmt = ResultSet.getStatement
	

	/**
	 * 	 建立连接
	 * */

	public boolean connect(){
		try{
			// 注册JDBC驱动
			Class.forName(JDBC_DRIVER);

            // 打开链接
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
			return true;
		}catch(SQLException e){
            // 处理 JDBC 错误
			e.printStackTrace();
			return false;
		}catch(Exception e1){
            // 处理其他JDBC 错误
			e1.printStackTrace();
			return false;
		}
	}

	/**
	 * 	 执行查询语句
	 * */
	public ResultSet query(String sql) {
		/* Note: A ResultSet object is automatically closed by the Statement object 
		 * that generated it when that Statement object is closed, re-executed, or is 
		 * used to retrieve the next result from a sequence of multiple results.
		 */
		ResultSet result = null;
		try {
			result = conn.createStatement().executeQuery(sql);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 返回一个可以对数据库进行增删改的ResultSet对象
	 */
	public ResultSet updatableQuery(String sql){
		/**
		 * 返回一个可以对数据库进行增删改的ResultSet对象
		 * 通过createStatement,execteQuery创建
		 */
		ResultSet result = null;
		try {
			/* resultSetType - a result set type; one of ResultSet.TYPE_FORWARD_ONLY, 
			 * ResultSet.TYPE_SCROLL_INSENSITIVE, or ResultSet.TYPE_SCROLL_SENSITIVE
			 *
			 * resultSetConcurrency - a concurrency type; one of ResultSet.CONCUR_READ_ONLY 
			 * or ResultSet.CONCUR_UPDATABLE
			 */
			result = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 返回一个可以对数据库进行增删改的ResultSet对象
	 * 通过createStatement,execteQuery创建
	 */
	public ResultSet scroll_InsensitiveUpdatableQuery(String sql){
		ResultSet result = null;
		try {
			result = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 	返回一个可以对数据库进行增删改的ResultSet对象
	 * 	通过prepareStatement,execteQuery 创建
	 * */
	public ResultSet preUpdatebleQuery(String table, String map, String map1){
		String sql = "select *from ? where ?=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, table); 
			pstmt.setString(2, map);
			pstmt.setString(3, map1);
			rs = pstmt.executeQuery();
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		return rs;
	}
	/**
	 * 	关闭statement
	 *
	 */
	public void closeStatement(ResultSet result){
		try{
			// stmt.close();
			result.getStatement().close();
		}catch(SQLException e){
			System.out.print("close Statement failed, it may has been closed\n");
			e.printStackTrace();
		}
	}
	/**
	 *关闭连接
	 * */
	public void close(){
		try{
			conn.close();
		}catch(SQLException e){
			System.out.print("close Connection failed, it may has been closed\n");
			e.printStackTrace();
		}
	}
}
