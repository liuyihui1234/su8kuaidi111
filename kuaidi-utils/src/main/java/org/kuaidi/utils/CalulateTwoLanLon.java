package org.kuaidi.utils;

import java.text.DecimalFormat;

/**
 * 计算两个经纬度之间的距离
 * @author 
 *
 */
public class CalulateTwoLanLon
{
    private static final double EARTH_RADIUS = 6378.137;//地球半径,单位千米
    private static double rad(double d)
    {
        return d * Math.PI / 180.0;
    }
    
    /**
     * 
     * @param lat1 第一个纬度
     * @param lng1 第一个经度
     * @param lat2 第二个纬度
     * @param lng2 第二个经度
     * @return 两个经纬度的距离
     */
    public static String getDistance(double lat1,double lng1,double lat2,double lng2)
    {
    	
    	String distance = "";
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
                Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000d;
        DecimalFormat    df   = new DecimalFormat("######0.00");  
        distance = df.format(s) + "km";
        return distance;
    }
    public static void main(String[] args)
    {
//        String distance = getDistance(34.2675560000, 108.9534750000,
//                34.2464320000, 108.9534750000);
//        System.out.println("距离"+distance+"公里");

//    	DecimalFormat    df   = new DecimalFormat("######0.00");   
//
//    	double d1 = 3.23456 ; 
//    	double d2 = 0.0;
//    	double d3 = 2.0;
//    	String rst = df.format(d1); 
//    	System.out.println(rst);
//    	rst = df.format(d2); 
//    	System.out.println(rst);
//    	rst = df.format(d3); 
//    	System.out.println(rst);
    }
    
    
}