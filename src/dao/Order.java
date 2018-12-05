package dao;

import model.Food;
import util.dateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import util.dateUtil.*;
public class Order {
    /**
     * 判断senderId是否与当前rs一致,
     * @return 一致:返回一个由SenderId决定的Order
     * @return 不一致:返回null
     * @param rs 传入的resultset类型的order集合
     * @param senderId 送货员id
     * */
    public static model.Order getOrderBySenderId(ResultSet rs, String senderId) throws SQLException, ParseException {
        // attribute in table order of sql:
        // o_id,u_id,have_paid,sdr_id,send_t,arr_t,tag,paid_way,status,addr
        model.Order orderIns = null;
        try {
            if (rs.getString("s_id") == senderId) {
                orderIns = new model.Order();
                orderIns.setId(rs.getString("o_id"));
                orderIns.setCustomerId(rs.getString("u_id"));
                orderIns.setHavePaid(rs.getBoolean("havepaid"));
                orderIns.setSenderId(rs.getString("sdr_id"));
                orderIns.setSendTime(dateUtil.strToDate(rs.getString("sent_t")));
                orderIns.setArriveTime(dateUtil.strToDate(rs.getString("arr_t")));
                orderIns.setTag(rs.getString("tag"));
                orderIns.setPaidWay(rs.getString("paid_way"));
                orderIns.setStatus(rs.getInt("status"));
                orderIns.setAddress(rs.getString("addr"));
                orderIns.setRequestTime(dateUtil.strToDate(rs.getString("req_t")));
                orderIns.setMoney(rs.getDouble("money"));

            }
            return orderIns;
        }catch(Exception e){
//            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 返回一个Order订单决定的所有菜,即一个顾客点的所有菜
     * 从list数据库获得
     *
     * */
    public static ArrayList<model.Food> getOrderFoodList(Connection conn, String o_id) throws SQLException, ParseException {
        String sql = "select  * from list,food where o_id  = ? and food.id = list.f_id";
        PreparedStatement pstmt = null;
        ArrayList<model.Food> foodList = new ArrayList<model.Food>();
        try {
            pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstmt.setString(1,o_id);
            ResultSet rsIns = pstmt.executeQuery();
            rsIns.beforeFirst();//recover cursor to the default position
            while(rsIns.next()){
                Food foodIns = new Food();
                foodIns.setId(rsIns.getString("food.id"));
                foodIns.setName(rsIns.getString("name") );
                foodIns.setPrice(rsIns.getDouble("price"));
                foodIns.setSpecial(rsIns.getString("u_id"));
                foodList.add(foodIns);
            }
            return foodList;
        }catch(Exception e){
//            e.printStackTrace();
            throw e;
        }
    }
    /**
     * 获得
     *
     * */

}
