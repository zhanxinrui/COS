package model;
/**
 *定义了菜单管理员menuman类，继承自餐厅员工employee,主要成员都在employee的父类customer的父类worker中定义了
 *
 * @author Zhanxinrui
 *
 * @Time 2018-11-24 13:31:01
 */

public class Menuman extends Employee {

    public Menuman(){}

    public Menuman(String id, String name, String password) {
        super(id,name,password);
    }


    public Menuman(String id, String name, String password, String email, String address) {
        super(id, name, password, email, address);
    }

	public boolean insert(){
		return true;
	}
}
