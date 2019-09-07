package org.kuaidi.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date; 
import java.util.List;
import java.util.Random;


/**
 * 产生支付订单号
 */
public class OrderNumUtil {

	public static final String dtLong = "yyyyMMddHHmmss";

	public static String getOrderNum(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(dtLong);
		Random  random = new Random();
		int num = random.nextInt(1000);
		String randomNum = num + "";
		while(randomNum.length() < 3){
			randomNum = "0" + randomNum;
		}
		randomNum =  df.format(date) + randomNum;
		return randomNum;
	}
	
	public static void main(String [] args){
		String order = OrderNumUtil.getOrderNum();
		System.out.println(order);
//		List<String> list = new ArrayList<>();
//		list.add("ad_u46");
//		JPushUtil.Push_RX(list,"点击查看您积分剩余详情！", 2, 48, "");
	}
	
	
	/**
	 * 判断商家积分是否低于安全预警 如果低于安全预警(100分)
	 */
	public static boolean traderJifenUnderLine(Integer traderId) {
		boolean flag = true;
		try {} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	

}
