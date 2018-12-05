/*
* this class should be a part of Order
* define the procedure of sending food by `sender`
*
* @author zxr
* @Date 2018/12/4
* */

package service;

import model.Order;
import util.SearchSQL;
import util.UpdatableSQL;
import model.Sender;
import model.Worker;
import util.dateUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static service.OrderFood.sdf;

public class sendOrder {

    Sender sdr;
    UpdatableSQL up_sql_order = new UpdatableSQL(sdr.rs_order);
    UpdatableSQL up_sql_salary = new UpdatableSQL(sdr.rs_salary);

    // 用于生成o_id
    // 用于取当前日期的年月日分
    private static final int INIT_STATUS = 1, CANCEL_STATUS = 6,
            PAY_SALARY = 1, // PAY_ARRIVE = 2
            NO_PAID = 0, HAVA_PAID = 1;

    sendOrder(Sender sdr){
        this.sdr = sdr;
    }

    //订餐员接受订单转至status4，更改send开始时间
    public static boolean receiveOrderBySender(Sender sdr,ArrayList<Order> orders){
        UpdatableSQL up_sql_order = new UpdatableSQL(sdr.rs_order);
        try{
            for(Order o:orders) {
                o.setStatus(4);
                o.setSenderId(sdr.getId());
                SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmm");
                Date date = new Date();
                String dateStr = sdf.format(date);

//                (o.getOrderId(), sdr.getId(),d);//dao  orderid,sdr_id,Date 更新

                String[] column_map = new String[]{
                        "o_id", "sdr_id","status","i",
                };
                Object[] column_value = new Object[]{
                        o.getOrderId(), o.getSenderId(),dateStr

                };
                up_sql_order.update(column_map, column_value);
            }
            return true;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //通过类成员获取更改
    public  boolean receiveOrderBySender(ArrayList<Order> orders){

        try{
            for(Order o:orders) {
                o.setStatus(4);
                o.setSenderId(sdr.getId());
                SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmm");
                Date date = new Date();
                String dateStr = sdf.format(date);

                String[] column_key = new String[]{
                        "o_id", "sdr_id","status"
                };
                Object[] column_value = new Object[]{
                        o.getOrderId(), o.getSenderId(),dateStr

                };
                up_sql_order.update(column_key, column_value);

            }
            return true;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }

    }


    /**
    * 送餐员获取自己接的订单      静态方法
    * */
    public static ArrayList<Order> getReceiveOrder(Sender sdr) throws SQLException, ParseException {
        sdr.rs_order.beforeFirst();//recover cursor to the default position
        ArrayList<Order> orderList = new ArrayList<Order>();
        while(sdr.rs_order.next()){
            Order orderIns = dao.Order.getOrderBySenderId(sdr.rs_order,sdr.getId());
            if(orderIns!=null)
                orderList.add(orderIns);
        }
        return orderList;
    }

    /**
     * 送餐员获取自己接的订单   实例类
     * */
    public  ArrayList<Order> getReceiveOrder() throws SQLException, ParseException {
        sdr.rs_order.beforeFirst();//recover cursor to the default position
        ArrayList<Order> orderList = new ArrayList<Order>();
        while(sdr.rs_order.next()){
            Order orderIns = dao.Order.getOrderBySenderId(sdr.rs_order,sdr.getId());
            if(orderIns!=null)
                orderList.add(orderIns);
        }
        return orderList;
    }

    /*
    *打印送餐说明
    * */
    public boolean saveOrder(ArrayList<order>){

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



