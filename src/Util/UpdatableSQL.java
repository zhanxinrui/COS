package Util;


import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdatableSQL extends SQL {
	String table_name;
	ResultSet rs;

	public UpdatableSQL(){}
	public UpdatableSQL(ResultSet rs){
		this.rs = rs;
	}
	public UpdatableSQL(String table_name){
		this.table_name = table_name;
	}

	/**
	 * update accroding to index and object array
	 * @param column_index  index
	 * @param column_value whatever object
	 */

	private static void updateRowOrigin(ResultSet rs, int[] column_index, Object[] column_value) throws Exception{
		// 索引号index超出范围会抛出SQLException异常
		for(int i = 0, index; i < column_value.length; i++){
			index = column_index[i];
			// String
			if(column_value[i] instanceof String)
				rs.updateString(index, (String)column_value[i]);
			// int
			else if(column_value[i] instanceof Integer) {
				System.out.print("该整数位置与值");
				System.out.println(i);
				System.out.println(column_index[i]);
				rs.updateInt(index, (int)column_value[i]);}
			// double
			else if(column_value[i] instanceof Double)
				rs.updateDouble(index, (double)column_value[i]);
			// boolean
			else if(column_value[i] instanceof Boolean)
				rs.updateBoolean(index, (boolean)column_value[i]);
			// java.sql.Date
			else if(column_value[i] instanceof java.sql.Date)
				rs.updateDate(index, (java.sql.Date)column_value[i]);
			// java.sql.Blob
			else if(column_value[i] instanceof java.sql.Blob)
				rs.updateBlob(index, (java.sql.Blob)column_value[i]);
			// java.sql.Time
			else if(column_value[i] instanceof java.sql.Time)
				rs.updateTime(index, (java.sql.Time)column_value[i]);
			// java.io.InputStream
			else if(column_value[i] instanceof java.io.InputStream)
				rs.updateBinaryStream(index, (java.io.InputStream)column_value[i]);

			else
				throw new Exception("Invalid column_value");
		}
	}

	/**
	 * update accroding to index (which is a `String` )and object array
	 * @param column_map  index string
	 * @param column_value whatever object
	 */
	private static void updateRowOrigin(ResultSet rs, String[] column_map, Object[] column_value) throws Exception{
		// 索引号column_map[i]超出范围会抛出SQLException异常
		for(int i = 0; i < column_value.length; i++){
			// String
			if(column_value[i] instanceof String)
				rs.updateString(column_map[i], (String)column_value[i]);
			// String
			else if(column_value[i] instanceof Integer)
				rs.updateInt(column_map[i], (int)column_value[i]);
			// double
			else if(column_value[i] instanceof Double)
				rs.updateDouble(column_map[i], (double)column_value[i]);
			// boolean
			else if(column_value[i] instanceof Boolean)
				rs.updateBoolean(column_map[i], (boolean)column_value[i]);
			// java.sql.Date
			else if(column_value[i] instanceof java.sql.Date)
				rs.updateDate(column_map[i], (java.sql.Date)column_value[i]);
			// java.sql.Blob
			else if(column_value[i] instanceof java.sql.Blob)
				rs.updateBlob(column_map[i], (java.sql.Blob)column_value[i]);
			// java.sql.Time
			else if(column_value[i] instanceof java.sql.Time)
				rs.updateTime(column_map[i], (java.sql.Time)column_value[i]);
			// java.io.InputStream
			else if(column_value[i] instanceof java.io.InputStream)
				rs.updateBinaryStream(column_map[i], (java.io.InputStream)column_value[i]);

			else
				throw new Exception("Invalid column_value");
		}
	}

	/**
	 * insert accroding to index (which is a `int` )and object array
	 * @param column_index  index
	 * @param column_value whatever object
	 */
	private static boolean insertRow(ResultSet rs, int[] column_index, Object[] column_value){
		try{
			rs.moveToInsertRow();
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("moveToInsertRow failed\n");
			return false;
		}
		try{
			updateRowOrigin(rs, column_index, column_value);
			// 插入数据时主键冲突可能会抛出SQLIntegrityConstraintViolationException异常 
			rs.insertRow();
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}catch(Exception e1){
			e1.printStackTrace();
			return false;
		}finally{
			try{
				rs.moveToCurrentRow();
			}catch(SQLException e){
				e.printStackTrace();
				System.out.println("moveToCurrentRow failed\n");
				return false;
			}
		}
	}

	/**
	 * insert accroding to index (which is a `string` )and object array
	 * @param column_map  index
	 * @param column_value whatever object
	 */
	private static boolean insertRow(ResultSet rs, String[] column_map, Object[] column_value){
		try{
			rs.moveToInsertRow();
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("moveToInsertRow failed\n");
			return false;
		}
		try{
			updateRowOrigin(rs, column_map, column_value);
			// 插入数据时主键冲突可能会抛出SQLIntegrityConstraintViolationException异常 
			rs.insertRow();
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("out of Range");
			return false;
		}catch(Exception e1){
			e1.printStackTrace();
			return false;
		}finally{
			try{
				rs.moveToCurrentRow();
			}catch(SQLException e){
				e.printStackTrace();
				System.out.println("moveToCurrentRow failed\n");
				return false;
			}
		}
	}

	
	// 调用-----------------insertRow私有方法---------------------
	// 接受外部column_value,使用类成员变量rs
	public boolean insert(Object[] column_value){
		int[] local_column_index = new int[column_value.length];
		for(int i = 0; i < column_value.length; i++)
			local_column_index[i] = i + 1;
		if(UpdatableSQL.insertRow(rs, local_column_index, column_value))
			return true;
		return false;
	}
	// 接受外部column_value, 外部rs.
	public static boolean insert(ResultSet _rs, Object[] column_value){
		int[] local_column_index = new int[column_value.length];
		System.out.println("the rows of column_value:");
		System.out.println(column_value.length);
		for(int i = 0; i < column_value.length; i++)
			local_column_index[i] = i + 1;
		if(UpdatableSQL.insertRow(_rs, local_column_index, column_value))
			return true;
		return false;
	}

	// 接受外部column_value, 外部column_index, 使用类成员变量rs
	public boolean insert(int[] column_index, Object[] column_value){
		if(UpdatableSQL.insertRow(rs, column_index, column_value))
			return true;
		return false;
	}
	// 接受外部column_value, 外部column_index, 外部rs.
	public static boolean insert(ResultSet _rs, int[] column_index, Object[] column_value){
		if(UpdatableSQL.insertRow(_rs, column_index, column_value))
			return true;
		return false;
	}

	// 接受外部column_value, 外部column_map, 使用类成员变量rs
	public boolean insert(String[] column_map, Object[] column_value){
		if(UpdatableSQL.insertRow(rs, column_map, column_value))
			return true;
		return false;
	}
	// 接受外部column_value, 外部column_map, 外部rs.
	public static boolean insert(ResultSet _rs, String[] column_map, Object[] column_value){
		if(UpdatableSQL.insertRow(_rs, column_map, column_value))
			return true;
		return false;
	}

	// -----------------调用insert公有方法---------------------
	
	/* 接受外部column_value,
	 * 使用类成员变量table_name, 
	 * 使用方法局部变量rs
	 */
	public boolean insertToTable(Object[] column_value){
		ResultSet _rs = super.updatableQuery("select * from " + table_name + " limit 0");
		// change access to static
		// if(this.insert(local_rs, column_value))
		if(UpdatableSQL.insert(_rs, column_value)){
			super.closeStatement(_rs);
			return true;
		}
		super.closeStatement(_rs);
		return false;
	}
	
	/* 接受外部column_value, 外部table_name
	 * 使用方法局部变量rs
	 */
	public boolean insertToTable(Object[] column_value, String _table_name){
		ResultSet local_rs = super.updatableQuery("select * from " + _table_name + " limit 0");
		if(UpdatableSQL.insert(local_rs, column_value)){
			super.closeStatement(local_rs);
			return true;
		}
		super.closeStatement(local_rs);
		return false;
	}

	/* 接受外部column_value, 外部column_index
	 * 使用类成员变量table_name, 
	 * 使用方法局部变量rs
	 */
	public boolean insertToTable(int[] column_index, Object[] column_value){
		ResultSet _rs = super.updatableQuery("select * from " + table_name + " limit 0");
		if(UpdatableSQL.insert(_rs, column_index, column_value)){
			super.closeStatement(_rs);
			return true;
		}
		super.closeStatement(_rs);
		return false;
	}

	/* 接受外部column_value, 外部column_index, 外部table_name
	 * 使用方法局部变量rs
	 */
	public boolean insertToTable(int[] column_index, Object[] column_value, String _table_name){
		ResultSet _rs = super.updatableQuery("select * from " + _table_name + " limit 0");
		if(UpdatableSQL.insert(_rs, column_index, column_value)){
			super.closeStatement(_rs);
			return true;
		}
		super.closeStatement(_rs);
		return false;
	}


	/* 接受外部column_value, 外部column_map
	 * 使用类成员变量table_name, 
	 * 使用方法局部变量rs
	 */
	public boolean insertToTable(String[] column_map, Object[] column_value){
		ResultSet _rs = super.updatableQuery("select * from " + table_name + " limit 0");
		if(UpdatableSQL.insert(_rs, column_map, column_value)){
			super.closeStatement(_rs);
			return true;
		}
		super.closeStatement(_rs);
		return false;
	}

	/* 接受外部column_value, 外部column_map, 外部table_name
	 * 使用方法局部变量rs
	 */
	public boolean insertToTable(String[] column_map, Object[] column_value, String _table_name){
		ResultSet _rs = super.updatableQuery("select * from " + _table_name + " limit 0");
		if(UpdatableSQL.insert(_rs, column_map, column_value)){
			super.closeStatement(_rs);
			return true;
		}
		super.closeStatement(_rs);
		return false;
	}


	// -------------update---------------------
	private static boolean updateRow(ResultSet rs, int[] column_index, Object[] column_value){
		try{
			updateRowOrigin(rs, column_index, column_value);
			// 插入数据时主键冲突可能会抛出SQLIntegrityConstraintViolationException异常 
			rs.updateRow();
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("out of Range");
			return false;
		}catch(Exception e1){
			e1.printStackTrace();
			return false;
		}finally{
			try{
				rs.moveToCurrentRow();
			}catch(SQLException e){
				e.printStackTrace();
				System.out.println("moveToCurrentRow failed\n");
				return false;
			}
		}
	}
	private static boolean updateRow(ResultSet rs, String[] column_map, Object[] column_value){
		try{
			updateRowOrigin(rs, column_map, column_value);
			// 插入数据时主键冲突可能会抛出SQLIntegrityConstraintViolationException异常 
			rs.updateRow();
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}catch(Exception e1){
			e1.printStackTrace();
			return false;
		}finally{
			try{
				rs.moveToCurrentRow();
			}catch(SQLException e){
				e.printStackTrace();
				System.out.println("moveToCurrentRow failed\n");
				return false;
			}
		}
	}

	// 调用-----------------updateRow私有方法---------------------
	// 接受外部column_value,使用类成员变量rs
	public boolean update(Object[] column_value){
		int[] local_column_index = new int[column_value.length];
		for(int i = 0; i < column_value.length; i++)
			local_column_index[i] = i + 1;
		if(UpdatableSQL.updateRow(rs, local_column_index, column_value))
			return true;
		return false;
	}
	// 接受外部column_value, 外部rs.
	public static boolean update(ResultSet _rs, Object[] column_value){
		int[] local_column_index = new int[column_value.length];
		for(int i = 0; i < column_value.length; i++)
			local_column_index[i] = i + 1;
		if(UpdatableSQL.updateRow(_rs, local_column_index, column_value))
			return true;
		return false;
	}

	// 接受外部column_value, 外部column_index, 使用类成员变量rs
	public boolean update(int[] column_index, Object[] column_value){
		if(UpdatableSQL.updateRow(rs, column_index, column_value))
			return true;
		return false;
	}
	// 接受外部column_value, 外部column_index, 外部rs.
	public static boolean update(ResultSet _rs, int[] column_index, Object[] column_value){
		if(UpdatableSQL.updateRow(_rs, column_index, column_value))
			return true;
		return false;
	}
	// 接受外部value, 外部index, 使用类成员变量rs
	public boolean update(int index, Object value){
		int[] column_index = new int[]{index};
		Object[] column_value = new Object[]{value};
		if(UpdatableSQL.updateRow(rs, column_index, column_value))
			return true;
		return false;
	}
	// 接受外部value, 外部index, 外部rs.
	public static boolean update(ResultSet _rs, int index, Object value){
		int[] column_index = new int[]{index};
		Object[] column_value = new Object[]{value};
		if(UpdatableSQL.updateRow(_rs, column_index, column_value))
			return true;
		return false;
	}

	// 接受外部column_value, 外部column_map, 使用类成员变量rs
	public boolean update(String[] column_map, Object[] column_value){
		if(UpdatableSQL.updateRow(rs, column_map, column_value))
			return true;
		return false;
	}
	// 接受外部column_value, 外部column_map, 外部rs.
	public static boolean update(ResultSet _rs, String[] column_map, Object[] column_value){
		if(UpdatableSQL.updateRow(_rs, column_map, column_value))
			return true;
		return false;
	}
	// 接受外部value, 外部map, 使用类成员变量rs
	public boolean update(String map, Object value){
		String[] column_map = new String[]{map};
		Object[] column_value = new Object[]{value};
		if(UpdatableSQL.updateRow(rs, column_map, column_value))
			return true;
		return false;
	}
	// 接受外部value, 外部map, 外部rs.
	public static boolean update(ResultSet _rs, String map, Object value){
		String[] column_map = new String[]{map};
		Object[] column_value = new Object[]{value};
		System.out.print(map + "对应的新值：");
		System.out.println(value);
		if(UpdatableSQL.updateRow(_rs, column_map, column_value))
			return true;
		return false;
	}

	// -----------------调用update公有方法---------------------
	
	/* 接受外部column_value,
	 * 使用类成员变量table_name, 
	 * 使用方法局部变量rs
	 */
	public boolean updateToTable(Object[] column_value, String primary_key_name, String primary_key){
		ResultSet _rs = super.updatableQuery("select * from " + table_name + " where " + primary_key_name + " = '" + primary_key + "'");
		try{
			if(!_rs.next()){
				System.out.println("update failed, cannot search the specified row\n");
				super.closeStatement(_rs);
				return false;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		// change access to static
		// if(this.update(local_rs, column_value))
		if(UpdatableSQL.update(_rs, column_value)){
			super.closeStatement(_rs);
			return true;
		}
		super.closeStatement(_rs);
		return false;
	}
	
	/* 接受外部column_value, 外部table_name
	 * 使用方法局部变量rs
	 */
	public boolean updateToTable(Object[] column_value, String _table_name, String primary_key_name, String primary_key){
		ResultSet _rs = super.updatableQuery("select * from " + _table_name + " where " + primary_key_name + " = '" + primary_key + "'");
		try{
			if(!_rs.next()){
				System.out.println("update failed, cannot search the specified row\n");
				super.closeStatement(_rs);
				return false;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		if(UpdatableSQL.update(_rs, column_value)){
			super.closeStatement(_rs);
			return true;
			}
		super.closeStatement(_rs);
		return false;
	}

	/* 接受外部column_value, 外部column_index
	 * 使用类成员变量table_name, 
	 * 使用方法局部变量rs
	 */
	public boolean updateToTable(int[] column_index, Object[] column_value, String primary_key_name, String primary_key){
		ResultSet _rs = super.updatableQuery("select * from " + table_name + " where " + primary_key_name + " = '" + primary_key + "'");
		try{
			if(!_rs.next()){
				System.out.println("update failed, cannot search the specified row\n");
				super.closeStatement(_rs);
				return false;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		if(UpdatableSQL.update(_rs, column_index, column_value)){
			super.closeStatement(_rs);
			return true;
		}
		super.closeStatement(_rs);
		return false;
	}

	/* 接受外部column_value, 外部column_index, 外部table_name
	 * 使用方法局部变量rs
	 */
	public boolean updateToTable(int[] column_index, Object[] column_value, String _table_name, String primary_key_name, String primary_key){
		ResultSet _rs = super.updatableQuery("select * from " + _table_name + " where " + primary_key_name + " = '" + primary_key + "'");
		try{
			if(!_rs.next()){
				System.out.println("update failed, cannot search the specified row\n");
				super.closeStatement(_rs);
				return false;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		if(UpdatableSQL.update(_rs, column_index, column_value)){
			super.closeStatement(_rs);
			return true;
		}
		super.closeStatement(_rs);
		return false;
	}


	/* 接受外部column_value, 外部column_map
	 * 使用类成员变量table_name, 
	 * 使用方法局部变量rs
	 */
	public boolean updateToTable(String[] column_map, Object[] column_value, String primary_key_name, String primary_key){
		ResultSet _rs = super.updatableQuery("select * from " + table_name + " where " + primary_key_name + " = '" + primary_key + "'");
		try{
			if(!_rs.next()){
				System.out.println("update failed, cannot search the specified row\n");
				super.closeStatement(_rs);
				return false;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		if(UpdatableSQL.update(_rs, column_map, column_value)){
			super.closeStatement(_rs);
			return true;
		}
		super.closeStatement(_rs);
		return false;
	}

	/* 接受外部column_value, 外部column_map, 外部table_name
	 * 使用方法局部变量rs
	 */
	public boolean updateToTable(String[] column_map, Object[] column_value, String _table_name, String primary_key_name, String primary_key){
		ResultSet _rs = super.updatableQuery("select * from " + _table_name + " where " + primary_key_name + " = '" + primary_key + "'");
		try{
			if(!_rs.next()){
				System.out.println("update failed, cannot search the specified row\n");
				super.closeStatement(_rs);
				return false;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		if(UpdatableSQL.update(_rs, column_map, column_value)){
			super.closeStatement(_rs);
			return true;
		}
		super.closeStatement(_rs);
		return false;
	}
}
