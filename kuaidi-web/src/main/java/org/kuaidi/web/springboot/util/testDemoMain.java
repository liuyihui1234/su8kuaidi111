package org.kuaidi.web.springboot.util;

import java.util.GregorianCalendar;

public class testDemoMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
//	   public static boolean IDCardValidate(String IDStr) {
//	        String[] ValCodeArr = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };
//	        String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2" };
//	        String Ai = "";
//	        // ================ 号码的长度18位 ================
//	        if (IDStr.length() != 18) {
//	            return false;
//	        }
//	        // ================ 数字 除最后以为都为数字 ================
//	        if (IDStr.length() == 18) {
//	            Ai = IDStr.substring(0, 17);
//	        }
//	        if (isNumeric(Ai) == false) {
//	            //errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
//	            return false;
//	        }
//	        // ================ 出生年月是否有效 ================
//	        String strYear = Ai.substring(6, 10);// 年份
//	        String strMonth = Ai.substring(10, 12);// 月份
//	        String strDay = Ai.substring(12, 14);// 日
//	        if (isDate(strYear + "-" + strMonth + "-" + strDay) == false) {
////	          errorInfo = "身份证生日无效。";
//	            return false;
//	        }
//	        GregorianCalendar gc = new GregorianCalendar();
//	        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
//	        try {
//	            if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150 || (gc.getTime().getTime() - s.parse(strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
//	                //errorInfo = "身份证生日不在有效范围。";
//	                return false;
//	            }
//	        } catch (NumberFormatException e) {
//	            e.printStackTrace();
//	        } catch (java.text.ParseException e) {
//	            e.printStackTrace();
//	        }
//	        if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
//	            //errorInfo = "身份证月份无效";
//	            return false;
//	        }
//	        if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
//	            //errorInfo = "身份证日期无效";
//	            return false;
//	        }
//	        // ================ 地区码时候有效 ================
//	        Hashtable h = GetAreaCode();
//	        if (h.get(Ai.substring(0, 2)) == null) {
//	            //errorInfo = "身份证地区编码错误。";
//	            return false;
//	        }
//	        // ================ 判断最后一位的值 ================
//	        int TotalmulAiWi = 0;
//	        for (int i = 0; i < 17; i++) {
//	            TotalmulAiWi = TotalmulAiWi + Integer.parseInt(String.valueOf(Ai.charAt(i))) * Integer.parseInt(Wi[i]);
//	        }
//	        int modValue = TotalmulAiWi % 11;
//	        String strVerifyCode = ValCodeArr[modValue];
//	        Ai = Ai + strVerifyCode;
//
//	        if (IDStr.length() == 18) {
//	            if (Ai.equals(IDStr) == false) {
//	                //errorInfo = "身份证无效，不是合法的身份证号码";
//	                return false;
//	            }
//	        } else {
//	            return true;
//	        }
//	        return true;
//	    }

}
