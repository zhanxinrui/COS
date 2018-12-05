package model;

import java.sql.ResultSet;
import Util.SQL;

public  class Worker {
    private String id;
    private String name;
    private String password;
    private String email;
    private String address;

	// 默认值为0,即非注册工资支付员工
	private int salary_pay = 0;


	public ResultSet rs_order, rs_food, rs_worker, rs_salary;


	public void initialResultSet(SQL sql){
		rs_order = sql.preUpdatebleQuery("order", "u_id", id);
		rs_food = sql.updatableQuery("select * from food");
		rs_salary = sql.preUpdatebleQuery("salary", "id", id);
	}
	public void initialResultSetOrder(SQL sql){
		rs_order = sql.preUpdatebleQuery("order", "u_id", id);
	}
	public void initialResultSetFood(SQL sql){
		rs_food = sql.updatableQuery("select * from food");
	}
	public void initialResultSetSalary(SQL sql){
		rs_salary = sql.preUpdatebleQuery("salary", "id", id);
	}

    public Worker(){}
    public Worker(String id, String name, String passwd){
        this.id = id;
        this.name = name;
        this.password = passwd;
    }
	// "salary_pay"
    public Worker(String id, String name, String passwd, String email, String address){
        this.id = id;
        this.name = name;
        this.password = passwd;
        this.email = email;
        this.address = address;
    }

	// rs_worker
	public void SetRsWorker(ResultSet rs){
		rs_worker = rs;
	}

	// id
    public String getId() { return id; }
    public void setId(String id) {
		this.id = id; 
	}
	// name
    public String getName() { return name; }
    public void setName(String name) {
        this.name = name;
    }
	// password
    public String getPassword() { return password; }
    public void setPassword(String password) {
        this.password = password;
    }
	// email
    public String getEmail(){ return email; }
    public void setEmail(String email){
		this.email = email;
	}
	// address
    public String getAddress(){return address;}
    public void setAddress(String addr){
		this.address = addr;
	}
	// salary_pay
	public int getSalary_pay(){ return salary_pay; }
	public void setSalary_pay(int pay_way){
		salary_pay = pay_way;
	}
}

