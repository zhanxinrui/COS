package test;

import util.SQL;
import util.UpdatableSQL;

import java.sql.ResultSet;
import java.sql.SQLException;

import sun.security.timestamp.TSRequest;

public class TestInsert{
	// ResultSet next有抛出SQLException异常的可能,但我打算处理
	public static void main(String[] args) throws SQLException{
		SQL sql = new SQL();
		if(!sql.connect())
			return;

		ResultSet rs = sql.updatableQuery("select * from test");

/*		// 插入
		rs.moveToInsertRow();
		// 这里插入的值有数据库原有的值冲突的话
		// 会抛出SQLIntegrityConstraintViolationException异常
		rs.updateString("id", "hhhh");
		rs.updateString(2, "hahaha");

		// 超出列范围后会抛SQLException异常
		rs.updateString(3, "hahaha");
		// 更新数据库
		try{
			/* SQLException - if a database access error occurs; the result set concurrency is CONCUR_READ_ONLY,
			 * this method is called on a closed result set, if this method is called when the cursor
			 * is not on the insert row, or if not all of non-nullable columns in the insert row have been given a non-null value
			 * /
			rs.insertRow();
		}catch(SQLIntegrityConstraintViolationException e){
			System.out.print("很有可能是插入主键冲突了.\n");
			e.printStackTrace();
		}
		// 返回原位置
		rs.moveToCurrentRow();

*/
		Object[] column_value = new Object[2];
		String m = null;
		Integer m;
		column_value[0] = 1988;
		column_value[1] = m;
		//UpdatableSQL upsql = new UpdatableSQL(rs);
		//if(column_value[1] instanceof Integer)
			//System.out.println("judge error!");
		//System.out.println("judge right!");
		//upsql.insert(column_value);
		rs.moveToInsertRow();
		rs.updateObject(1, m);
		rs.updateObject(2, column_value[1]);
		rs.insertRow();
		rs.close();
		rs = sql.query("select id, str from test");
		while(rs.next()){
			// 通过字段检索
			// Column names used as input to getter methods are case insensitive.
			// 用作getter方法输入的列名不区分大小写
			int i = rs.getInt(1);
			String name = rs.getString(1);
			String name1 = rs.getString(2);
			// 输出数据
			System.out.print(i + ": " + name + ": " + name1 +"\n");
		}
		// 完成后关闭
		sql.close();
		if(rs.isClosed())
			System.out.println("The ResultSet has been closed with the Connection close\n");
		System.out.println("Goodbye!");
	}
}
