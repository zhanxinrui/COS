package model;
/**
 *定义了送餐员sender类，他是单独的类，不属于餐厅的员工,仅负责配餐
 *
 * @author Zhanxinrui
 *
 * @Time 2018-11-24 13:31:01
 */

import DAO.UpdatableSQL;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Sender {

    private String id;
    private String name;
    private String password;

	private ArrayList<String> changed_field;
	private ArrayList<Object> changed_value;

    public Sender(){}

    public Sender(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
    public String getId() { return id; }
    public void setId(String id) {
		this.id = id;
	}
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
		changed_field.add("name");
		changed_value.add(name);
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
		changed_field.add("passwd");
		changed_value.add(password);
    }

	public boolean applyChangeToDb(ResultSet rs){
		if(UpdatableSQL.update(rs, (String[])changed_field.toArray(), changed_value.toArray())){
			cleanChange();
			return true;
		}
		else{
			cleanChange();
			return false;
		}
	}

	public void cleanChange(){
		changed_field.clear();
		changed_value.clear();
	}
}
