package model;

import java.util.Date;
/**
 *定义了订单order类，
 *
 * @author Zhanxinrui
 *
 * @Time 2018-11-24 13:31:01
 */
public class Order {

    private String orderId;
    private String customerId;
    private String senderId;
    private boolean havePaid;
    private Date sendTime;
    private Date arriveTime;
    private Date requestTime;
    private String tag ;
    private String paidWay;
//    status 1         2             3            4              5              6
//           已下单   餐厅接单      ready         配送接单        已送达        已取消
    private int status;
    private String address;
    private double money;
    private String orderList;
    /**
     * @param orderId 订单编号
     * @param customerId 顾客编号
     * @param SenderId 配送员编号
     * @param havePaid 是否支付
     * @param sendTime 配送开始时间
     * @param arriveTime 到达时间
     * @Param requestTime 客户要求送货时间
     * @param tag 客户特殊要求
     * @param paidWay 支付方式
     * @param status 订单状态
     * @param address 配送地址
     * @param money 订单总金额
     * @param orderList 订单消费物品列表
     */

    public Order(String orderId, String customerId, String SenderId, boolean havePaid, Date sendTime, Date arriveTime,
                 Date requestTime, String tag, String paidWay, int status, String address, double money, String orderList) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.senderId = senderId;
        this.havePaid = havePaid;
        this.sendTime = sendTime;
        this.arriveTime = arriveTime;
        this.requestTime = requestTime;
        this.tag = tag;
        this.paidWay = paidWay;
        this.status = status;
        this.address = address;
        this.money = money;
        this.orderList = orderList;
    }

    public String getOrderId() {
        return orderId;
    }
    public void setId(String id) {
        this.orderId = id;
    }
    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String id) {
        this.customerId = id;
    }
    public String getSenderId() {
        return senderId;
    }
    public void setSenderId(String id) {
        this.senderId = id;
    }
    public boolean getHavePaid() {
        return havePaid;
    }
    public void setHavePaid(boolean havePaid) {
        this.havePaid = havePaid;
    }
    public Date getSendTime() {
        return sendTime;
    }
    public void setSendTime(Date sendTime) {
        this.sendTime= sendTime;
    }
    public Date getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }
    public Date getRequestTime() {
        return requestTime;
    }
    public void setRequestTime(Date requestTime) {
        this.requestTime= requestTime;
    }
    public String getTag() {
        return tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
    public String getPaidWay() {
        return paidWay;
    }
    public void setPaidWay(String paidWay) {
        this.paidWay = paidWay;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public double getMoney() {
        return money;
    }
    public void setMoney(double money) {
        this.money = money;
    }
    public String getOrderList() {
        return orderList;
    }
    public void setOrderList(String orderList) {
        this.orderList = orderList;
    }

}
