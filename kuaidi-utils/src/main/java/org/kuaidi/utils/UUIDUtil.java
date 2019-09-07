package org.kuaidi.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class UUIDUtil {

	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		String uuidStr = str.replace("-", "");
		return uuidStr;
	}

	public static String DateString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date2 = sdf.format(date);
	    return date2;
	}
	
	/**
	 * 判断是当前优惠券时间是否在startTime时间之前
	 * true:在开始时间之前
	 * false:在开始时间之后--符合
	 */
	public static boolean isDateBefore(Date date){
		Date date1 = new Date();//当前时间
		return date1.before(date);
	}
	/**
	 * 判断是当前优惠券时间是否在endTime时间之前 
	 * true:在结束时间之后
	 * false:在结束时间之前--符合
	 */
	public static boolean isDateAfter(Date date){
		Date date1 = new Date();//当前时间
		return date1.after(date);
	}
	

	/**
	 * 字符串转换unicode
	 */
	public static String string2Unicode(String string) {
		StringBuffer unicode = new StringBuffer();
		for (int i = 0; i < string.length(); i++) {
			// 取出每一个字符
			char c = string.charAt(i);
			// 转换为unicode
			unicode.append("\\u" + Integer.toHexString(c));
		}
		return unicode.toString();
	}
	
	public static void main(String [] args){
		String uuid = UUIDUtil.getUUID();
		System.out.println(uuid);
	}
	

}
