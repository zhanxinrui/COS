package model;

/**
 *定义了餐厅员工employee类，继承自顾客customer,主要成员都在customer的父类worker中定义了
 *
 * @author Zhanxinrui
 *
 * @Time 2018-11-24 13:31:01
 */
public class Employee extends Customer {
    public Employee(){}
    public Employee(String id, String name, String password) {
        super(id,name,password);
    }


    public Employee(String id, String name, String password, String email, String address) {
        super(id,name,password,email,address);
    }
}