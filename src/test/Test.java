package test;

import Service.SQL;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test{
	// ResultSet next有抛出SQLException异常的可能
	public static void main(String[] args) throws SQLException{
		SQL sql = new SQL();
		if(!sql.connect())
			return;
		ResultSet rs = sql.query("show tables");
		rs.absolute(1);
		/* next到最后一行之后且对行操作或rs关闭（数据库访问出错）会抛出SQLException异常
		 * Throws:
		 * SQLException - if a database access error occurs or this method is called on a closed result set
		 */
		while(rs.next()){
			// 通过字段检索
			// Column names used as input to getter methods are case insensitive.
			// 用作getter方法输入的列名不区分大小写
			// int i = rs.getInt(1);
			String name = rs.getString(1);
			// 输出数据
			System.out.print(": " + name + "\n");
		}
		rs.absolute(1);
		// ps:索引号全是1,查找第一列还是查找第一个为String的列？
		// int i = rs.getInt(1);
		String name = rs.getString(1);
		System.out.print(": " + name + "\n");
		// 完成后关闭
		sql.close();
		System.out.println("Goodbye!");
	}
}
