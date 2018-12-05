package test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class testgenid{
	public static void main(String[] args){
		int id = 1000;
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");

		String o_id = null, temp_id = null;
		Date current_time = new Date();
		o_id = sdf.format(current_time);

		// 控制id在1000~1999之间
		while((id++) == 2000)
			id = 1000;
		temp_id = Integer.toString(id);
		// 舍去串的第一位即000~999
		temp_id = temp_id.substring(1);
		o_id = o_id + temp_id;
		System.out.println(o_id);

	}
}
