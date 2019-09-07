package org.kuaidi.utils;

import java.security.MessageDigest;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5Util {
	
	private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	private static String toHexString(byte[] b) {
	    StringBuilder sb = new StringBuilder(b.length * 2);
	    for (int i = 0; i < b.length; i++) {
	        sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
	        sb.append(HEX_DIGITS[b[i] & 0x0f]);
	    }
	    return sb.toString();
	}

	public static String encode(String str){
		return DigestUtils.md5Hex(str).toUpperCase();
	}
	 
	public static String encode(String str, int PKcode){
//		 MessageDigest digest = DigestUtils.getMd5Digest();
//		 digest.update(str.getBytes());
//		 byte messageDigest[] = digest.digest();
//		 String rst = toHexString(messageDigest);
//		 switch (PKcode){
//		 	case 0:
//		 		rst = rst.toUpperCase();
//		 		break;
//		 	case 1:
//		 		rst = rst.toUpperCase();
//		 		rst = rst.substring(8,24);
//		 		break;
//		 	case 2:
//		 		rst = rst.toLowerCase();
//		 		break;
//		 	case 3 :
//		 		rst = rst.toLowerCase();
//		 		rst = rst.substring(8,24);
//		 		break;
//		 }
//		 return rst;
		return "";
	}
	
	public static void main(String [] args){
		String str = "123456";
		str = Md5Util.encode(str);
		System.out.println(str);
	}
}
