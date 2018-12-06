package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
* 定义了一些时间转换的方便函数
*
* */


public  class dateUtil {
    static SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmm");

    /*
    * 字符串转Date对象
    * */
    public static Date strToDate(String s) throws ParseException {
        return sdf.parse(s);

    }
    /*
    * 日期转字符串
    * */
    public static String dateToStr(Date d) throws ParseException {

        return  sdf.format(d);
    }
//    public static String getNow()throws ParseException{
//        Date now = new Date();
//
//    }

}
