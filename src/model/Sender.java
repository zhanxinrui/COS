package model;
/**
 *定义了送餐员sender类，他是单独的类，不属于餐厅的员工,仅负责配餐
 *
 * @author Zhanxinrui
 *
 * @Time 2018-11-24 13:31:01
 */


public class Sender {

    private String id;
    private String name;
    private String password;

    public Sender(){}

    public Sender(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


}
