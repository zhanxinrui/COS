package service;

import DAO.UpdatableSQL;

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

    public Worker workerLogin(Connection con, String password, String id, String type) throws Exception{
        Worker rsWorker = null;
        ResultSet rs = DAO.Account.getAccount( con,id,password,type);
        if(rs.next()){
            rsWorker = new Worker();
            rsWorker.setId(rs.getString("id"));
            rsWorker.setPassword(rs.getString("password"));
            rsWorker.setAddress(rs.getString("address"));
            rsWorker.setEmail(rs.getString("email"));
            rsWorker.setName(rs.getString("name"));
			// 维护这个数据库连接,用以改变个人数据
			rsWorker.SetRsWorker(rs);
        }
        return rsWorker;
    }

    public Sender senderLogin(Connection con, String password,String id, String type) throws Exception{
        Sender rsSender = null;
        ResultSet rs = DAO.Account.getAccount( con,id,password,type);
        if(rs.next()){
            rsSender = new Sender();
            rsSender.setId(rs.getString("id"));
            rsSender.setPassword(rs.getString("password"));
            rsSender.setName(rs.getString("name"));
        }
        return rsSender;
    }
//
//    /**
//     * 添加用户
//  
	public boolean senderAdd(Sender user, String type) throws Exception {
		UpdatableSQL up_sql = new UpdatableSQL();
		Object[] column_value = new Object[]{
			user.getId(), user.getPassword(), user.getName()
		};
		up_sql.insertToTable(column_value, "sender");
		return true;
	}

    public boolean workerAdd(Worker user, String type) throws Exception {
		UpdatableSQL up_sql = new UpdatableSQL();
		if (type.equals("Customer")) {
			Object[] column_value = new Object[]{
				user.getId(), user.getPassword(), user.getAddress(), user.getEmail(), user.getName()
			};
			up_sql.insertToTable(column_value, "customer");
        }
        else if (type.equals("Employee")) {
			Object[] column_value = new Object[]{
				user.getId(), user.getPassword(), user.getAddress(), user.getEmail(), user.getName()
			};
			up_sql.insertToTable(column_value, "employee");

			// 最后一个形参代表工资
			// 可以通过输入更改
			salaryIdAdd(user, up_sql, user.getId(), 6000);
        }
        else if (type.equals("Menuman")) {
			Object[] column_value = new Object[]{
				user.getId(), user.getPassword(), user.getAddress(), user.getEmail(), user.getName()
			};
			up_sql.insertToTable(column_value, "menuman");
        }
		else
			return false;
		return true;
    }

	public boolean salaryIdAdd(Worker user, UpdatableSQL up_sql, String id, float salary){
		Object[] column_value = new Object[]{
			user.getId(), salary, salary
		};
		// salary表名
		if(up_sql.insertToTable(column_value, "salary"))
			return true;
		return false;
	}
}
