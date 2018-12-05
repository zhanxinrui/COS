package dao;

import util.dateUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import util.dateUtil.*;
public class Order {
    /**
     * 判断senderId是否与当前rs一致,
     * 返回一个由SenderId决定的Order
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
}
