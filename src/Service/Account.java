package Service;

import model.Sender;
import model.Worker;
//import DAO.Account ;
import java.sql.Connection;
import java.sql.ResultSet;

public class Account {
    /**
     * 登录
     *
     * @return 成功返回Worker，失败返回Worker = null
     * @params connection 大概是从view中创建？这个不确定，待讨论，
     * @params worker
     * @params type worker引用的类型
     */

    public Worker workerLogin(Connection con, String password,String id, String type) throws Exception{
        Worker rsWorker = null;
        ResultSet rs = DAO.Account.getAccount( con,id,password,type);
        if(rs.next()){
            rsWorker = new Worker();
            rsWorker.setId(rs.getString("id"));
            rsWorker.setPassword(rs.getString("password"));
            rsWorker.setAddress(rs.getString("address"));
            rsWorker.setEmail(rs.getString("email"));
            rsWorker.setName(rs.getString("name"));
        }
        return rsWorker;
    }

    public Sender senderLogin(Connection con, String password,String id, String type) throws Exception{
        Sender rsSender = null;
        ResultSet rs = DAO.Account.getAccount( con,id,password,type);
        if(rs.next()){
            rsSender = new Sender();
            rsSender.setId(rs.getString("id"));
            rsSender.setPassword(rs.getString("password"));
            rsSender.setName(rs.getString("name"));
        }
        return rsSender;
    }
//
//    /**
//     * 添加用户
//     */
//    public int workerAdd(Connection con, Worker user) throws Exception {
//    }
}
