package org.kuaidi.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	public static Date addYears(int years) {
		Date date = new Date();
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);//设置起时间
	    cal.add(Calendar.YEAR, years);//增加一年
	    return cal.getTime();
	}
	
}
