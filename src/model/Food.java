package model;

import java.sql.Blob;
/**
 *定义了食品food类，
 *
 * @author Zhanxinrui
 *
 * @Time 2018-11-24 13:31:01
 */
public class Food {

    private String id;
    private String name;
    private double price;
    private int remain;
    private String feature;/!!!!  我special改成了featrue把它改成了 有3种选项 special 和cheap 和 ordinary/
    private Blob pic;

    public Food(){}
    public Food(String id, String name, double price, int remain) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.remain = remain;
        this.special = "ord";
        this.pic = null;
    }
    public Food(String id, String name, double price, int remain, String special, Blob pic) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.remain = remain;
        this.special = special;
        this.pic = pic;
    }
    public String getId(){return id;}
    public void setId(String id){this.id = id;}
    public String getName(){return name;}
    public void setName(String name){this.name = name;}
    public double getPrice(){return price;}
    public void setPrice(double price){this.price = price;}
    public int getRemain(){return remain;}
    public void SetRemain(int remain){this.remain = remain;}
    public String getSpecial(){return special;}
    public void setSpecial(String special){this.special = special;}
}
