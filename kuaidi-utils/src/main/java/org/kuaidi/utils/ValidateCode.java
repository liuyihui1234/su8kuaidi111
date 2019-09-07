package org.kuaidi.utils;

public class ValidateCode {
	
	/*
	 * 后台登录验证码
	 */
	public static int getValidateCode(){
		int random=(int)(Math.random()*10000); 
		for (int i = 0; i!= 1; ) {
			 if(random<1000){
					random=(int)(Math.random()*10000); 
			 }else {
				i=1;
			}
		} 
		return random;
	}
	
	/*
	 * 手机短信验证码
	 */
	public static String getSMSCode(){
		String validateCode = "";
		int i = 0 ; 
		while ( i < 4 ) {
			int random=(int)(Math.random()*10); 
			validateCode = validateCode + random;
			i++;
		} 
		return validateCode;
	}
	
	public static void main(String [] args){
		String validate = ValidateCode.getSMSCode();
		System.out.println(validate);

	}

}