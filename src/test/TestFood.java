package test;
import java.util.ArrayList;

import model.*;
import service.ManageMenu;
import service.Menu;
import util.SQL;

public class TestFood{
	public static void main(String[] args) throws Exception{
		SQL sql = new SQL();
		sql.connect();
		Worker user = new Worker();
		user.initialResultSetFood(sql);

		Food food = new Food();
		ArrayList<Food> f = null;
		food.setId("1000");
		food.setName("满汉大全席");
		food.setPic("www.sohu.com");
		food.setSpecial("更新下");
		// ManageMenu.changeMenu(food);   测试成功
		// ManageMenu.addMenu(user.rs_food, food); 测试成功
		// ManageMenu.deleteMenu(user.rs_food, food); 测试成功
		f = service.Menu.getMenu(user);
		System.out.println(f.size());
		sql.close();
	}
}
