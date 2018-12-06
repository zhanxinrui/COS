package service;

import java.util.ArrayList;
import model.Food;
import model.Worker;

public class Menu{
    public static ArrayList<Food> getMenu(Worker w){
		return ManageMenu.getMenu(w.rs_food);
	}
    public static boolean prepareMeal(Food f){//备餐 要求前端更改Food的数量,根据加减 在一个界面
		if(ManageMenu.changeMenu(f))
			return true;
		return false;
	}
    public static boolean addNewMeal(Worker w, Food f){//菜单管理员
		if(ManageMenu.addMenu(w.rs_food, f))
			return true;
		return false;
	}
    public static void delMeal(Worker a, ArrayList<Food> f){//菜单管理员删菜
		for(int i = 0; i < f.size(); i++){
			try{
				if(!ManageMenu.addMenu(a.rs_food, f.get(i))){
					String error = f.get(i).getId() + "无法删除,可能该条目已不存在";
					throw new Exception(error);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
