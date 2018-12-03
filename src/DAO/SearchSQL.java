package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchSQL{
	ResultSet rs;
	public SearchSQL(ResultSet _rs){ rs = _rs; }

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

	public Object searchMultiRow(String primary_key_name, String primary_key, String map){
		int orig_locate = 1;
		try{
			orig_locate = rs.getRow();
			rs.beforeFirst();
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

	public void locate(String primary_key_name, String primary_key){
		 try{
			rs.beforeFirst();
			while(rs.next()){
				if(primary_key.equals(rs.getString(primary_key_name)))
					break;
			}
		}catch(SQLException e){
			e.printStackTrace();
		} 
	}
	public static void locate(ResultSet _rs, String primary_key_name, String primary_key){
		 try{
			_rs.beforeFirst();
			while(_rs.next()){
				if(primary_key.equals(_rs.getString(primary_key_name)))
					break;
			}
		}catch(SQLException e){
			e.printStackTrace();
		} 
	}
}
