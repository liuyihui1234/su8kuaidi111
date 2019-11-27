package org.kuaidi.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 得到以前或以后的日期
 * Calendar.getInstance()是获取一个Calendar对象并可以进行时间的计算，时区的指定
 * new Date()是创建了一个date对象，默认是utc格式的。
 */
public class TimeDayUtil {

    /**
     * inde 为正表示当前时间加天数，为负表示当前时间减天数
     * @param index   ！！
     * @return
     */
    public static String getTimeDay(int index){
        TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
        TimeZone.setDefault(tz);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        calendar.set(Calendar.DAY_OF_MONTH,index);
        String date = fmt.format(calendar.getTime());
        return date;
    }

    /**
     * 获取当前日期 天数
     * @param index 为正表示当前时间加天数，为负表示当前时间减天数
     * @return
     */
    public static String getDateTime(int index){
/*        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");*/
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar thisday = Calendar.getInstance();
        //过去N天
        thisday.setTime(new Date());
        thisday.add(Calendar.DATE, index);
        Date d = thisday.getTime();
        String day = format.format(d);
        System.out.println("过去"+index+"天："+day);
        return day;
    }

    /**
     * 获取当前日期 月
     * @param index 为正表示当前时间加天数，为负表示当前时间减天数
     * @return
     */
    public static String getMonthTime(int index){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        //过去N月
        c.setTime(new Date());
        c.add(Calendar.MONTH, index);
        Date m = c.getTime();
        String mon = format.format(m);
        System.out.println("过去一个月："+mon);
        return mon;

/*    //过去三个月
        c.setTime(new Date());
        c.add(Calendar.MONTH, -3);
        Date m3 = c.getTime();
        String mon3 = format.format(m3);
        System.out.println("过去三个月："+mon3);
        return mon3
        */
    }

    /**
     * 获取当前日期 年
     * @param index 为正表示当前时间加天数，为负表示当前时间减天数
     * @return
     */
    public static String getYearTime(int index){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        //过去一年
        c.setTime(new Date());
        c.add(Calendar.YEAR, index);
        Date y = c.getTime();
        String year = format.format(y);
        System.out.println("过去一年："+year);
        return year;
    }
    
    /*
            * 获得明天的某个时间
     */
    public static String getNextDay(int index){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        //过去一年
        c.setTime(new Date());
        c.add(c.DATE,1);
        Date y = c.getTime();
        String year = format.format(y);
        System.out.println("明天："+year);
        return year;
    }
    
    public static String getCurrentDate() {
    	SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    	return format.format(new Date());
    }
}
