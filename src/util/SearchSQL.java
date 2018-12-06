package util;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchSQL{
	ResultSet rs;
	public SearchSQL(ResultSet _rs){ rs = _rs; }

	/**
	 * 返回对应属性值结果
	 * @param map 属性名
	 *
	 * */
	public Object searchSimpleRow(String map){
		try{
			rs.last();
			if(1 != rs.getRow())
				return null;
			rs.absolute(1);
			return rs.getObject(map);
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 返回rs第一行index索引下属性值
	 *
	 * */
	public Object searchSimpleRow(int index){
		try{
			rs.last();
			if(1 != rs.getRow())
				return null;
			rs.absolute(1);
			return rs.getObject(index);
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 返回多条 `属性`值 结果
	 * @param primary_key_name 主键名
	 * @param primary_key 主键value
	 * @param map 属性名
	 *
	* */
	public Object searchMultiRow(String primary_key_name, String primary_key, String map){
		int orig_locate = 1;
		try{
			orig_locate = rs.getRow();
			rs.beforeFirst();//jump before first row
			while(rs.next()){
				if(primary_key.equals(rs.getString(primary_key_name)))
					return rs.getObject(map);
			}
			return null;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}finally {
			try{ rs.absolute(orig_locate);
			}catch(SQLException e){ e.printStackTrace(); }
		}
	}
	/**
	 * 定位rs到一行指定主键所在行
	 * @param primary_key_name 主键名
	 * @param primary_key 主键value
	 *
	 * */
	public void locate(String primary_key_name, String primary_key){
		 try{
			rs.beforeFirst();
			while(rs.next()){//nextrow
				if(primary_key.equals(rs.getString(primary_key_name)))
					break;
			}
		}catch(SQLException e){
			e.printStackTrace();
		} 
	}

	public static Object searchSimpleRow(ResultSet _rs, String map){
		try{
			_rs.last();
			if(1 != _rs.getRow())
				return null;
			_rs.absolute(1);
			return _rs.getObject(map);
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 返回_rs第一行index索引下属性值
	 *
	 * */
	public static Object searchSimpleRow(ResultSet _rs, int index){
		try{
			_rs.last();
			if(1 != _rs.getRow())
				return null;
			_rs.absolute(1);
			return _rs.getObject(index);
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 返回多条 `属性`值 结果
	 * @param primary_key_name 主键名
	 * @param primary_key 主键value
	 * @param map 属性名
	 *
	* */
	public static Object searchMultiRow(ResultSet _rs, String primary_key_name, String primary_key, String map){
		int orig_locate = 1;
		try{
			orig_locate = _rs.getRow();
			_rs.beforeFirst();//jump before first row
			while(_rs.next()){
				if(primary_key.equals(_rs.getString(primary_key_name)))
					return _rs.getObject(map);
			}
			return null;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}finally {
			try{ _rs.absolute(orig_locate);
			}catch(SQLException e){ e.printStackTrace(); }
		}
	}
	/**
	 * static方法 定位_rs到一行指定主键所在行
	 * @param primary_key_name 主键名
	 * @param primary_key 主键value
	 *
	 * */
	public static boolean locate(ResultSet _rs, String primary_key_name, String primary_key){
		 try{
			_rs.beforeFirst();
			while(_rs.next()){
				if(primary_key.equals(_rs.getString(primary_key_name)))
					break;
			}
			// 遍历整张表都没查到的情况
			if(_rs.isAfterLast())
				return false;
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		} 
	}
}
