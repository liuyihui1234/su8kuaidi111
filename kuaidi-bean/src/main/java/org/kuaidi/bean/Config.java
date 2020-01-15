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
	public static final int ORDER_SIZE = 7;
	
	public static final long APP_LOGIN_TIMEOUT = 24 * 60 * 60;

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
	
	public static final String REDISBILLSPREX = "FINISH_BILLSNUMBER";
	// web图形验证码前缀
	public static final String WEBCODE = "WEB:CODE:";

	public static final int EXPIRETIME = 2*60*60;
	
	public static final int YZMVALIDATETIME = 3; 
	
	//快递鸟企业编号和url。
	//电商ID
	public static String EBusinessID="1596164";
	//电商加密私钥，快递鸟提供，注意保管，不要泄漏
	public static String AppKey="d692a64c-908a-4635-8829-baff68f34e53";
	
	//快递  100 密钥
	public static String kuai8SecretKey = "";


}
