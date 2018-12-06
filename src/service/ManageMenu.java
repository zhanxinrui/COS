package service;

import util.UpdatableSQL;
import model.Food;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManageMenu{
	public static boolean addMenu(ResultSet rs, Food food){
		String[] column_map = null;
		Object[] column_value = null;
		column_map = new String[]{"id", "name", "remain", "pic", "price", "feature"};
		column_value = new Object[]{
			food.getId(), food.getName(), food.getRemain(), food.getPic(), food.getPrice(), food.getSpecial()
		};
		if(UpdatableSQL.insert(rs, column_map, column_value))
			return true;
		return false;
	}

	/**
	 * 参数虽然为food, 但唯一标志表中某一项的是id
	 * 数据库中对应id的food将会被删除
	 */
	public static boolean deleteMenu(ResultSet rs, Food food){
		if(UpdatableSQL.delete(rs, "id", food.getId()))
			return true;
		return false;
	}

	/**
	 * 参数为rs与Food对象
	 * 用于更新Food对象当前内容到数据库中
	 */
	public static boolean changeMenu(Food food){
		UpdatableSQL up_sql = new UpdatableSQL("food");
		String[] column_map = null;
		Object[] column_value = null;
			column_map = new String[]{"id", "name", "remain", "price", "feature"};
			column_value = new Object[]{
				food.getId(), food.getName(), food.getRemain(), food.getPrice(), food.getSpecial()
			};
		// 这类方法可以写成静态的,谁有时间改了在这儿做个标记
		if(up_sql.updateToTable(column_map, column_value, "id", food.getId()))
			return true;
		return false;
	}
	public static ArrayList<Food> getMenu(ResultSet rs) throws Exception{
		ArrayList<Food> menu = new ArrayList<Food>();
		try{
			rs.beforeFirst();
			while(rs.next()){
				dao.Order.getFoodFromRs(rs);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return menu;
	}
}
