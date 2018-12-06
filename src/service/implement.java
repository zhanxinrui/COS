package service;

import model.Food;
import model.Order;
import model.Sender;
import model.Worker;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
/**
 * 只是定义一个`接口`,给出方法的定义, 所有的功能实现后应该删除
 * */
public interface implement {


    //    用户:
    Worker workerLogin(String id, String password, String type);//worker登陆
    Sender senderLogin(String id, String password, String type);//Sender登陆
    Worker signUp(String id, String name, String addr, String email); //worker注册
    boolean salaryPaid(Worker a);//注册工资支付

    //    食物：
    ArrayList<Food> getMenu(Worker w);//woker 查看菜单
    boolean prepareMeal(Food f);//备餐 要求前端更改Food的数量,根据加减 在一个界面
    boolean addNewMeal(Worker a, ArrayList<Food> m);//菜单管理员
    boolean delMeal(Worker a, ArrayList<Food> f);//菜单管理员删菜

    //    订单:
    Order OrderMeal(ArrayList<Food> f, Worker customer, Date time, double money, String Tag, String Paid_way); //点餐 status 1
    ArrayList<Order> getOrderList(Worker w);//顾客 customer: 查看订单
    boolean cancelOrder(Worker w, ArrayList<Order> o);//顾客customer: 取消订单       status6
    boolean receiveOrderByCafeteria(Worker w, ArrayList<Order> list);//餐厅员工 employee: 餐厅接受订单 由餐厅员工操作 status2
    boolean requestSendOrder(Worker w, ArrayList<Order> list);//餐厅员工employee: 请求送餐 由餐厅员工操作 status3

    新加<<<   ArrayList<Order>getAvailOrderBySender(Sender s);  //送餐员查看可以接的订单  2018/12/6 13:01 +
    boolean receiveOrderBySender(Sender s, ArrayList<Order> list);//送餐员Sender: 接受订单 status4         未实现zxr
    ArrayList<Order> getReceiveOrderBySender(Sender s);//送餐员Sender: 获取送餐员接受的订单                                已实现zxr
    boolean saveOrder(Sender s);//送餐员Sender: 打印送餐说明                                        已实现                                                                                      已实现zxr
    boolean recordReceivedOrder(Sender s, ArrayList<Order>o, Date time);//送餐员Sender: 记录已经到达的订单即支付 status 5
}
