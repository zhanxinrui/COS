package model;

/**
 *定义了顾客customer类，继承自员工worker,主要成员都在worker
 *
 * @author Zhanxinrui
 *
 * @Time 2018-11-24 13:31:01
 */
public class Customer extends Worker {
    public Customer(){}
    public Customer(String id, String name, String password) {
        super(id,name,password);
    }

    public Customer(String id, String name, String password, String email, String address) {
        super(id,name,password,email,address);
    }

}
