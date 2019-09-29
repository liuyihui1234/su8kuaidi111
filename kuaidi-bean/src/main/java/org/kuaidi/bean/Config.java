package org.kuaidi.bean;


public class Config {
	public static final int HTTPOK = 200;
	public static final int HTTPERR = 500;
	
	public static final int OK = 1;
	public static final int ERROR=0;
	
	//默认的分页每页显示记录的条数。
	public static final int pageSize = 15 ;


	public static final  int  signDays = 7 ;
	public static final int   signPoint = 10 ;

	//订单号自增部分的长度
	public static final int ORDER_SIZE = 3;

	//OSS存储加前缀路径
	public static final  String oosUrlPath = "http://kuaibasuyun.oss-cn-beijing.aliyuncs.com/";

	//OSS短信 模版CODE
	public static final String TemplateCode ="SMS_171851426";
	public static final String SignName = "速八快递";
	
	// 手机验证码前缀
	public static final String redisPhonePrex = "PHONE:";

	//限制验证码发送频率
	public static final String redisPhonelimt = "PHONElimit:";

	//web端登录前缀
	public static final String REDISWEBLOGINPREX = "WEB:LOGIN:";
	// app端登录前缀
	public static final String REDISAPPLOGINPREX = "APP_LOGIN:";
	// web图形验证码前缀
	public static final String WEBCODE = "WEB:CODE:";

	public static final int EXPIRETIME = 2*60*60;


}
