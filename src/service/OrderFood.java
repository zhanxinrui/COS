package service;

import java.util.Date;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import model.Worker;
import model.Order;
import model.Food;
import util.UpdatableSQL;
import util.SearchSQL;

public class OrderFood{

	Worker user;
	UpdatableSQL up_sql_order = new UpdatableSQL(user.rs_order);

	// 用于生成o_id
	// 用于取当前日期的年月日分
	static SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmm");
	static int count_id = 100;
	
	private static final int INIT_STATUS = 1, CANCEL_STATUS = 6, 
			SALARY_PAY = 1, NO_SALARY_PAY = 0,
			NO_PAID = 0, HAVA_PAID = 1;

	public OrderFood(Worker _user){ user = _user; }

	// -------------静态方法定义在前面---------------//
    public static Order OrderMeal(ArrayList<Food> f, Worker customer, double money, String tag, int paid_way)throws Exception{
		Order order = new Order(); 
		if(paid_way == SALARY_PAY)
			if(customer.getSalary_pay() == NO_SALARY_PAY){
				System.out.println("您未注册工资支付");
				throw new Exception("您未注册工资支付");
			}
		order.setPaidWay(paid_way);
		order.setId(OrderFood.generateO_id(customer));
		order.setCustomerId(customer.getId());
		order.setOrderList(f);
		order.setMoney(money);
		order.setTag(tag);
		order.setHavePaid(INIT_STATUS);
		return order;
	}
    public static Order OrderMeal(ArrayList<Food> f, Worker customer, Date time, double money, String tag, int paid_way)throws Exception{
		Order order = OrderMeal(f, customer, money, tag, paid_way);
		order.setRequestTime(time);
		return order;
	}
    public static ArrayList<Order> getOrderList(Worker w) throws Exception{//顾客 customer: 查看订单
		ArrayList<Order> list = new ArrayList<Order>();
		try{
			w.rs_order.beforeFirst();
		}catch(SQLException e){
				e.printStackTrace();
		}
		while(w.rs_order.next())
			list.add(dao.Order.getOrderFromRs(w.rs_order));
		return list;
	}
    public static boolean cancelOrder(Worker w, ArrayList<Order> o){//顾客customer: 取消订单       status6
		for(int i = 0; i < o.size(); i++){
			try{
				if(OrderFood.cancelOrder(w.rs_order, o.get(i).getOrderId())){
					String error = o.get(i).getOrderId() + "无法取消,可能该条目已不存在";
					throw new Exception(error);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return true;
	}

	private static boolean cancelOrder(ResultSet rs, String o_id){
		SearchSQL.locate(rs, "o_id", o_id);
		// 订单状态改为取消
		if(!UpdatableSQL.update(rs, "status", CANCEL_STATUS))
			return false;
		System.out.println("已发送退款请求...\n退款成功");
		return true;
	}


	private static String generateO_id(Worker user){
		String o_id = null, temp_id = null;
		Date current_time = new Date();
		o_id = sdf.format(current_time);

		if((count_id++) == 200)
			count_id = 100;
		temp_id = Integer.toOctalString(count_id).substring(1);
		// 以当前时间年月日时分各两位 + 用户id + temp_id作为唯一订单号
		o_id = o_id + user.getId() + temp_id;
		return o_id;
	}
	
	// 所有worker都能点餐
	// 无tag,特殊要求
	public boolean orderFood(String food_id, int paid_way){
		int hava_paid = NO_PAID, status = INIT_STATUS;
		if(paid_way == SALARY_PAY){
			System.out.println("已选择工资支付，正在发送付费请求.");
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

		if(up_sql_order.insert(column_map, column_value))
			return true;
		return false;
	}
	// 有tag,特殊要求
	public boolean orderFood(String food_id, int paid_way, String  tag){
		int hava_paid = NO_PAID, status = INIT_STATUS;
		if(paid_way == SALARY_PAY){
			System.out.println("已选择工资支付，正在发送付费请求.");
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
		if(up_sql_order.insert(column_map, column_value))
			return true;
		return false;
	}

	public boolean cancelOrder(String o_id){
		SearchSQL.locate(user.rs_order, "o_id", o_id);
		// 订单状态改为取消
		if(!up_sql_order.update("status", CANCEL_STATUS))
			return false;
		System.out.println("已发送退款请求...\n退款成功");
		return true;
		// 如果以付款,那么退款
		/*
		try{
		if(user.rs_order.getInt("hava_paid") == HAVA_PAID)
			refundFromSalary(getFoodPrice(user.rs_order.getString("f_id")));
		}catch(SQLException e){
			e.printStackTrace();
		}
		*/
	}

	/*
	public float getFoodPrice(String id){
		SearchSQL search_sql = new SearchSQL(user.rs_food);		
		Object obj = search_sql.searchMultiRow("id", id, "price");
		return (float)obj;
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
	*/

}
