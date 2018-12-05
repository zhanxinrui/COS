package service;

import Util.UpdatableSQL;
import model.Sender;
import model.Worker;
//import DAO.Account ;
import java.sql.Connection;
import java.sql.ResultSet;

public class Account {
    /**
     * 登录
     *
     * @return 成功返回Worker，失败返回Worker = null
     * @params connection 大概是从view中创建？这个不确定，待讨论，
     * @params worker
     * @params type worker引用的类型
     */
	public static int SALARY_PAY = 1, NO_SALARY_PAY = 0;

    public static Worker workerLogin(Connection con, String id, String password, String type) throws Exception{
        Worker rsWorker = null;
        ResultSet rs = DAO.Account.getAccount( con,id,password,type);
        if(rs.next()){
        	rsWorker = new Worker();
            rsWorker.setId(rs.getString("id"));
            rsWorker.setPassword(rs.getString("passwd"));
            // rsWorker.setAddress(rs.getString("address"));
            rsWorker.setAddress(rs.getString("addr"));
            rsWorker.setEmail(rs.getString("email"));
            rsWorker.setName(rs.getString("name"));
			rsWorker.setSalary_pay(rs.getInt("salary_pay"));
            // 维护这个数据库连接,用以改变个人数据
            rsWorker.SetRsWorker(rs);
        }
		// else return null;
        return rsWorker;
    }

    public static Sender senderLogin(Connection con, String id,String password, String type) throws Exception{
        Sender rsSender = null;
        ResultSet rs = DAO.Account.getAccount( con,id,password,type);
        if(rs.next()){
            rsSender = new Sender();
            rsSender.setId(rs.getString("id"));
            rsSender.setPassword(rs.getString("passwd"));
            rsSender.setName(rs.getString("name"));
        }
		// else return null;
        return rsSender;
    }
    //
//    /**
//     * 添加用户
//
    public static boolean senderAdd(Sender user, String type) throws Exception {
        UpdatableSQL up_sql = new UpdatableSQL();
        Object[] column_value = new Object[]{
                user.getId(), user.getPassword(), user.getName()
        };
        up_sql.insertToTable(column_value, "sender");
        return true;
    }
    public static boolean workerAdd(Worker user, String type) throws Exception {
        UpdatableSQL up_sql = new UpdatableSQL();
        if (type.equals("Customer")) {
            Object[] column_value = new Object[]{
				user.getId(), user.getPassword(), user.getAddress(), user.getEmail(), user.getName(), user.getSalary_pay(),
            };
            if(!up_sql.insertToTable(column_value, "customer"))
				return false;
        }
        else if (type.equals("Employee")) {
            Object[] column_value = new Object[]{
				user.getId(), user.getPassword(), user.getAddress(), user.getEmail(), user.getName(), user.getSalary_pay(),

            };
            if(!up_sql.insertToTable(column_value, "employee"))
				return false;
            // 最后一个形参代表工资
            // 可以通过输入更改
        }
        else if (type.equals("Menuman")) {
            Object[] column_value = new Object[]{
				user.getId(), user.getPassword(), user.getAddress(), user.getEmail(), user.getName(), user.getSalary_pay(),

            };
            if(up_sql.insertToTable(column_value, "menuman"))
				return false;
        }
        else
            return false;
        return true;
    }
	public static Worker makeWorker(String id, String password, String addr, String email, String name) throws Exception{
        Worker rsWorker = new Worker();
		rsWorker.setId(id);
		rsWorker.setPassword(password);
		rsWorker.setAddress(addr);
		rsWorker.setEmail(email);
		rsWorker.setName(name);
        return rsWorker;
    }
	public static boolean registerSalaryPaid(Worker user, String anything_id){
		// send info to the company system
		// 1代表注册工资支付
		user.setSalary_pay(SALARY_PAY);

		if(!UpdatableSQL.update(user.rs_worker, "salary_pay",SALARY_PAY)) {
			return false;
		}
		return true;
	}
	public static boolean unRegisterSalaryPaid(Worker user){
		// send info to the company system
		// 1代表注册工资支付
		user.setSalary_pay(NO_SALARY_PAY);

		if(!UpdatableSQL.update(user.rs_worker, "salary_pay", NO_SALARY_PAY)) {
			return false;
		}
		return true;
	}
}
	/*
    public boolean salaryIdAdd(Worker user, UpdatableSQL up_sql, String id, float salary){
        Object[] column_value = new Object[]{
                user.getId(), salary, salary
        };
        // salary表名
        if(up_sql.insertToTable(column_value, "salary"))
            return true;
        return false;
    }
	*/
