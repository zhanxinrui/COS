package service;
import Util.SQL;
import Util.UpdatableSQL;

public class Main{
	public static void main(String[] args) throws Exception{
		SQL sql = new SQL();
		sql.connect();

		// 注册用户
		/*
		// id, 密码, 地址, 邮箱, 姓名
		if(Account.workerAdd(Account.makeWorker("16130120", "hgq", "123456qq", "email@qq.com", "xian"), "Customer"))
			System.out.println("用户增加成功");
		*/

		// 用户登录
		model.Worker user = Account.workerLogin(SQL.conn, "16130120", "hgq", "Customer");
		// Sender登录将worker改成sender
		if(user == null) {
			System.out.println("login failed");
			return;
		}
		else {
			System.out.println("login sucess!");
		}
		if(user.rs_worker == null)
			System.out.println("error : rs_work is null");
		if(Account.unRegisterSalaryPaid(user))
			System.out.println("register success!");
		else
			return;

		sql.close();
	}
}
