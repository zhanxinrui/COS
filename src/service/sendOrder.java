/*
* this class should be a part of Order
* define the procedure of sending food by `sender`
*
* @author zxr
* @Date 2018/12/4
* */

package service;

import model.Food;
import model.Order;
import util.SQL;
import util.SearchSQL;
import util.UpdatableSQL;
import model.Sender;
import model.Worker;
import util.dateUtil;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static dao.Order.getOrderFoodList;
import static service.OrderFood.sdf;

public class sendOrder {

    Sender sdr;
    UpdatableSQL up_sql_order = new UpdatableSQL(sdr.rs_order);
//    UpdatableSQL up_sql_salary = new UpdatableSQL(sdr.rs_salary);

    // 用于生成o_id
    // 用于取当前日期的年月日分
//    private static final int ORDERMADE = 1,  = 2,
//            PAY_SALARY = 1, // PAY_ARRIVE = 2
//            NO_PAID = 0, HAVA_PAID = 1;

    sendOrder(Sender sdr){
        this.sdr = sdr;
    }

    /**
     * 送餐员接受订单转至status4，更改send开始时间
     * 静态方法
     * */
    public static boolean receiveOrderBySender(Sender sdr,ArrayList<Order> orders){
        UpdatableSQL up_sql_order = new UpdatableSQL(sdr.rs_order);
        try{
            for(Order o:orders) {
                SearchSQL.locate(sdr.rs_order, "o_id", o.getOrderId());
                o.setStatus(4);
                o.setSenderId(sdr.getId());
                SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmm");
                Date date = new Date();
                String dateStr = sdf.format(date);
                String[] column_map = new String[]{
                         "sdr_id","status","send_t",
                };
                Object[] column_value = new Object[]{
                         o.getSenderId(),o.getStatus(),dateStr
                };
                up_sql_order.update(column_map, column_value);
            }
            return true;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 送餐员接受订单转至status4，更改send开始时间
     *   类成员更改
     * */
    public  boolean receiveOrderBySender(ArrayList<Order> orders){

        try{
            for(Order o:orders) {
                SearchSQL.locate(sdr.rs_order, "o_id", o.getOrderId());
                o.setStatus(4);
                o.setSenderId(sdr.getId());
                SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmm");
                Date date = new Date();
                String dateStr = sdf.format(date);
                String[] column_map = new String[]{
                        "sdr_id","status","send_t",
                };
                Object[] column_value = new Object[]{
                        o.getSenderId(),o.getStatus(),dateStr
                };
                up_sql_order.update(column_map, column_value);

            }
            return true;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }

    }


    /**
     * 送餐员获取自己接的订单
     * 静态方法
     **/
    public static ArrayList<Order> getReceiveOrder(Sender sdr) throws SQLException, ParseException {
        UpdatableSQL up_sql_order = new UpdatableSQL(sdr.rs_order);
        sdr.rs_order.beforeFirst();//recover cursor to the default position
        ArrayList<Order> orderList = new ArrayList<Order>();
        while(sdr.rs_order.next()){
            Order orderIns = dao.Order.getOrderBySenderId(sdr.rs_order,sdr.getId());//dao获取订单
            if(orderIns!=null)
                orderList.add(orderIns);
        }
        return orderList;
    }

    /**
     * 送餐员获取自己接的订单   实例方法
     *
     * */
    public ArrayList<Order> getReceiveOrder() throws SQLException, ParseException {
        sdr.rs_order.beforeFirst();//recover cursor to the default position
        ArrayList<Order> orderList = new ArrayList<Order>();
        while(sdr.rs_order.next()){
            Order orderIns = dao.Order.getOrderBySenderId(sdr.rs_order,sdr.getId());//dao to get orderList
            if(orderIns!=null)
                orderList.add(orderIns);
        }
        return orderList;
    }

    /**
     *送餐员打印送餐说明 到本地目录
     * 静态方法
     **/
    public static boolean saveOrder(Sender sdr) throws IOException, SQLException, ParseException {
        UpdatableSQL up_sql_order = new UpdatableSQL(sdr.rs_order);
        ArrayList<Order> orderList = getReceiveOrder(sdr);
        String pathname = "订餐说明.txt";//default filename
        try(FileOutputStream fileOutputStream = new FileOutputStream(pathname,true)){//Order List content out to a file
            for(Order o: orderList){
                ArrayList<Food>FoodList = getOrderFoodList(SQL.conn,o.getOrderId());
                String str = "订单编号:" + o.getOrderId() + "  用户名:" + o.getCustomerId() + "客户要求配送时间:" + o.getRequestTime() + "\n"+
                        "点菜:" ;
                for(Food f:FoodList){
                    str += (Integer.toString(FoodList.indexOf(f))+": " + f.getName()+"   ");
                }
                str+="配送地址:" + o.getAddress() + "  备注:" + o.getTag() + "\n" +
                        "订单金额:" + o.getMoney() + "  是否支付:" + o.getHavePaid()+"\n\n\n";
                fileOutputStream.write(str.getBytes());
            }
            return true;
        }catch(Exception e) {
            throw e;
        }
    }

    /**
     *送餐员打印送餐说明 到本地目录
     * 实例方法
     **/
    public  boolean saveOrder() throws IOException, SQLException, ParseException {
        ArrayList<Order> orderList = getReceiveOrder(sdr);
        String pathname = "订餐说明.txt";//default filename
        try(FileOutputStream fileOutputStream = new FileOutputStream(pathname,true)){//Order List content out to a file
            for(Order o: orderList){
                ArrayList<Food>FoodList = getOrderFoodList(SQL.conn,o.getOrderId());
                String str = "订单编号:" + o.getOrderId() + "  用户名:" + o.getCustomerId() + "客户要求配送时间:" + o.getRequestTime() + "\n"+
                        "点菜:" ;
                for(Food f:FoodList){
                    str += (Integer.toString(FoodList.indexOf(f))+": " + f.getName()+"   ");
                }
                str+="配送地址:" + o.getAddress() + "  备注:" + o.getTag() + "\n" +
                        "订单金额:" + o.getMoney() + "  是否支付:" + o.getHavePaid();
                fileOutputStream.write(str.getBytes());
            }
            return true;
        }catch(Exception e) {
            throw e;
        }
    }

    /**
     * 送餐到达后的记录 status5
     * 实例方法
     * @param oList 送达订单列表
     * @param time 送达的时间
     * */
    public boolean  recordReceivedOrder(ArrayList<Order>oList, Date time) throws SQLException {
        s.rs_order.beforeFirst();//recover cursor to the default position
        ArrayList<Order> orderList = new ArrayList<Order>();
        try{
            for(Order o:oList) {
                SearchSQL.locate(sdr.rs_order,"o_id",o.getOrderId());
                o.setSenderId(sdr.getId());
                SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmm");
                Date date = new Date();
                String dateStr = sdf.format(date);

                String[] column_key = new String[]{
                        "arr_t","status"
                };
                Object[] column_value = new Object[]{
                        dateStr,5,
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
     * 送餐到达后的记录 status5
     * 静态方法
     * @param oList 送达订单列表
     * @param time 送达的时间
     * */

    public static boolean  recordReceivedOrder(Sender s, ArrayList<Order>oList, Date time) throws SQLException {
        UpdatableSQL up_sql_order = new UpdatableSQL(sdr.rs_order);
        s.rs_order.beforeFirst();//recover cursor to the default position
        ArrayList<Order> orderList = new ArrayList<Order>();
        try{
            for(Order o:oList) {
                SearchSQL.locate(sdr.rs_order,"o_id",o.getOrderId());
                o.setSenderId(sdr.getId());
                SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmm");
                Date date = new Date();
                String dateStr = sdf.format(date);

                String[] column_key = new String[]{
                        "arr_t","status"
                };
                Object[] column_value = new Object[]{
                        dateStr,5,
                };
                up_sql_order.update(column_key, column_value);

            }
            return true;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }


    }




}



