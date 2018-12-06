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
//<<<<<<< HEAD
 //   private String feature;
//=======
    private String feature;///!!!!  我feature改成了featrue把它改成了 有3种选项 feature 和cheap 和 ordinary/
//>>>>>>> 61fc531044dd997e84dbae75294809dead455ebc
    private Blob pic;

    public Food(){}
    public Food(String id, String name, double price, int remain) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.remain = remain;
//<<<<<<< HEAD
  //      this.feature = null;
////=======
        this.feature = "ord";
    }
    public Food(String id, String name, double price, int remain, String feature, Blob pic) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.remain = remain;
        this.feature = feature;
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
    public String getSpecial(){return feature;}
    public void setSpecial(String feature){this.feature = feature;}
//<<<<<<< HEAD
    public Blob getPic(){return pic;}
    public void setPic(Blob pic){this.pic = pic;}
//=======
//>>>>>>> 61fc531044dd997e84dbae75294809dead455ebc
}
