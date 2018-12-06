package test;
import java.util.ArrayList;

import model.*;
import service.ManageMenu;
import service.Menu;
import service.OrderFood;
import util.SQL;

public class TestFood{
	public static void main(String[] args) throws Exception{
		SQL sql = new SQL();
		sql.connect();
		Worker user = new Worker();
		user.setId("i am god");
		user.setSalary_pay(1);

		// 连接food表
		user.initialResultSetFood(sql);

		// 连接order表
		user.initialResultSetOrder(sql);

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
		for(Food fo : f){
			System.out.printf("%s	%s	%s	%f	%d	%s\n", fo.getId(), fo.getPic(), fo.getName(), fo.getPrice(), fo.getRemain(), fo.getSpecial());
		}
		OrderFood.OrderMeal(f, user, 998, null, 0);

		java.util.Date date = new java.util.Date();
		OrderFood.OrderMeal(f, user, date, 998, "server me", 1);
		sql.close();
	}
}
