package util;

/**
 * <p>Title: checkNull</p>
 *
 * @author zhanxirnui
 * @date 2018年12月06日
 */

/**
 * 检查
 * */
public class checkNull {
    public static String checkNullString(String str){
        if(str.equals("NULL"))
            return null;
        else
            return str;
    }
}
