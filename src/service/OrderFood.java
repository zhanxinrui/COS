package service;

import java.util.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import model.Worker;
import util.UpdatableSQL;
import util.SearchSQL;

public class OrderFood{

	Worker user;
	UpdatableSQL up_sql_order = new UpdatableSQL(user.rs_order);
	UpdatableSQL up_sql_salary = new UpdatableSQL(user.rs_salary);

	// 用于生成o_id
	// 用于取当前日期的年月日分
	static SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmm");
	static int count_id = 100;
	
	private static final int INIT_STATUS = 1, CANCEL_STATUS = 6, 
			PAY_SALARY = 1, // PAY_ARRIVE = 2
			NO_PAID = 0, HAVA_PAID = 1;

	OrderFood(Worker _user){ user = _user; }

	// 所有worker都能点餐
	// 无tag,特殊要求
	public boolean orderFood(String food_id, int paid_way){
		int hava_paid = NO_PAID, status = INIT_STATUS;
		if(paid_way == PAY_SALARY){
			this.payFromSalay(this.getFoodPrice(food_id));
			hava_paid = HAVA_PAID;
		}
		String o_id = null, temp_id = null;
		Date current_time = new Date();
		o_id = sdf.format(current_time);

		if((count_id++) == 200)
			count_id = 100;
		temp_id = Integer.toOctalString(count_id).substring(1);
		// 以当前时间年月日时分各两位 + 用户id + temp_id作为唯一订单号
		o_id = o_id + user.getId() + temp_id;
		
		String[] column_map = new String[]{
			"o_id", "u_id", "hava_paid", "paid_way", "status", "addr"
		};
		Object[] column_value = new Object[]{
			o_id, user.getId(), hava_paid, paid_way, status, user.getAddress()
		};	

		up_sql_order.insert(column_map, column_value);
		return true;
	}
	// 有tag,特殊要求
	public boolean orderFood(String food_id, int paid_way, String tag){
		int hava_paid = NO_PAID, status = INIT_STATUS;
		if(paid_way == PAY_SALARY){
			this.payFromSalay(this.getFoodPrice(food_id));
			hava_paid = HAVA_PAID;
		}
		String o_id = null, temp_id = null;
		Date current_time = new Date();
		o_id = sdf.format(current_time);

		if((count_id++) == 200)
			count_id = 100;
		temp_id = Integer.toOctalString(count_id).substring(1);
		// 以当前时间年月日时分各两位 + 用户id + temp_id作为唯一订单号
		o_id = o_id + user.getId() + temp_id;
		
		String[] column_map = new String[]{
			"o_id", "u_id", "hava_paid", "paid_way", "status", "addr", "tag"
		};
		Object[] column_value = new Object[]{
			o_id, user.getId(), hava_paid, paid_way, status, user.getAddress()
		};	
		up_sql_order.insert(column_map, column_value);
		return true;
	}

	public void cancelOrder(String o_id){
		SearchSQL.locate(user.rs_order, "o_id", o_id);
		// 订单状态改为取消
		up_sql_order.update("status", CANCEL_STATUS);
		// 如果以付款,那么退款
		try{
		if(user.rs_order.getInt("hava_paid") == HAVA_PAID)
			refundFromSalary(getFoodPrice(user.rs_order.getString("f_id")));
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	// 返回值: 0 支付成功	-1 余额不足		1 支付失败
	public int payFromSalay(float price){
		// 保证rs对象在正确的行上
		try{
			user.rs_salary.absolute(1);
			float remain = user.rs_salary.getFloat("remain");
			remain = remain - price;
			if(remain < 0)
				return -1;
			user.rs_salary.updateFloat("remain", remain - price);
			user.rs_salary.updateRow();
			return 0;
		}catch(SQLException e){
			return 1;
		}
	}
	public int refundFromSalary(float price){
		return payFromSalay(-price);
	}

	public float getFoodPrice(String id){
		SearchSQL search_sql = new SearchSQL(user.rs_food);		
		Object obj = search_sql.searchMultiRow("id", id, "price");
		return (float)obj;
	}
}
