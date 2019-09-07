package org.kuaidi.utils;

public class SubStrUtil {
	
	public static String getSubStrNext(String lName , String  sName, int rstLen ) {
		String subStr = lName.substring(sName.length() , lName.length());
		int subNum = Integer.parseInt(subStr);
		subNum ++;
		subStr = subNum + "";
		int subLen = subStr.length() ;
		for(int i = subLen; i < rstLen; i++) {
			subStr = "0" + subStr;
		}
		return subStr; 
	}

}
