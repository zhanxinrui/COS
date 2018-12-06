package test;

import model.Order;
import model.Sender;
import util.SQL;
import util.SearchSQL;
import util.UpdatableSQL;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;


import util.SQL;

import static service.sendOrder.*;

/**
 * <p>Title: 为了测试Sender的处理订单的主要逻辑</p>
 *
 * @author zhanxirnui
 * @date 2018年12月06日
 */
public class TestSender {

//    public static void main(String[] args) throws SQLException, ParseException, IOException {
//        SQL sql = new SQL();
//        if(!sql.connect()) {
//            System.out.println("connection wrong");
//            return;
//        }
////        ResultSet rs = sql.updatableQuery("select * from COS.order where sdr_id = 161201");
////        int i = 1;
//
//        /**
//         *  ArrayList<Order>getAvailOrderBySender(Sender s);  //送餐员查看可以接的订单  2018/12/6 13:01 +
//         *  boolean receiveOrderBySender(Sender s, ArrayList<Order> list);//送餐员Sender: 接受订单 status4         未实现zxr
//         *  ArrayList<Order> getReceiveOrderBySender(Sender s);//送餐员Sender: 获取送餐员接受的订单                                已实现zxr
//         *  boolean saveOrder(Sender s);//送餐员Sender: 打印送餐说明                                        已实现                                                                                      已实现zxr
//         *  boolean recordReceivedOrder(Sender s, ArrayList<Order>o, Date time);//送餐员Sender: 记录已经到达的订单即支付 status 5
//         *
//         * */
////        while(rs.next()){
////            Object value = rs.getObject(i);
////            System.out.println("ok");
////            System.out.println(value);
////        }
////        private String id;
////        private String name;
////        private String password;
//
//        Sender s = new Sender("161201","杨丽芳","123456");
//
//        ResultSet rs = sql.updatableQuery("select * from COS.order");
//
//        s.setRsOrder(rs);
//
////        s.initialResultSetOrder(sql);getAvailOrderBySender
//        /*测试getAvailOrderBySender*/
//        ArrayList<Order> orderArrayList = getAvailOrderBySender(s);//测试获取可派送订单
//        /*public Order(String orderId, String customerId, String SenderId, int havePaid, Date sendTime, Date arriveTime,
//                 Date requestTime, String tag, int paidWay, int status, String address, double money, ArrayList<Food> orderList )*/
//
//        //        for(Order o:orderArrayList){
////            if(o.set)
////        }
////        while(rs.next()) {
////            SearchSQL.locate(s.rs_order, "o_id", "02");
//////            if(rs.getString("o_id").equals("02"))
////            orderArrayList = getOrderFromRs(rs);
////            receiveOrderBySender(s, )
////        }
//
//        /*测试receiveOrderBySender*/
////        receiveOrderBySender(s,orderArrayList);//测试派送员接受订单
////        for(Order order : orderArrayList){
////            System.out.println(order);
////        }
//
//        /*测试getReceiveOrderBySender*/
//
////        System.out.println("接受的订单");
////        ArrayList<Order> orderReceived = getReceiveOrderBySender(s);//自己接受的且未派送的
////        for(Order order : orderReceived){
////            System.out.println(order);
////        }
//
//        /*测试recordReceivedOrder*/
////        recordReceivedOrder(s,orderReceived,new Date());
//        //        ArrayList<Order> orderReceived = getReceiveOrderBySender(s);
////        for(Order order : orderReceived){
////            System.out.println(order);
////        }
//        /*测试saveOrder*/
//        saveOrder(s);
//
//
//
//    }



}
