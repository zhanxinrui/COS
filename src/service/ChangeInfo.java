package service;

import java.util.ArrayList;
import util.UpdatableSQL;
import model.Worker;

public class ChangeInfo{

	Worker user;
	// 数据变动链表
	// 更改属性在数据库中的列名字
	private ArrayList<String> changed_field;
	// 属性的新值
	private ArrayList<Object> changed_value;

	private static int SALARY_PAY = 1;

	ChangeInfo(Worker _user){ user = _user; }

	public void setName(String name) {
		user.setName(name);
		changed_field.add("name");
		changed_value.add(name);
	}
	public void setPassword(String password) {
		user.setPassword(password);
		changed_field.add("passwd");
		changed_value.add(password);
	}
	public void setEmail(String email){
		this.setEmail(email);
		changed_field.add("email");
		changed_value.add(email);
	}
	public void setAddress(String addr){
		user.setAddress(addr);
		changed_field.add("addr");
		changed_value.add(addr);
	}

	// 将信息变动更新到数据库
	public boolean applyChangeToDb(){
		if(UpdatableSQL.update(user.rs_worker, (String[])changed_field.toArray(), changed_value.toArray())){
			cleanChange();
			return true;
		}
		else{
			cleanChange();
			return false;
		}
	}

	public void signupSalaryPay(){
		changed_field.add("salary_pay");
		changed_value.add(SALARY_PAY);
	}

	public void cleanChange(){
		changed_field.clear();
		changed_value.clear();
	}
}
