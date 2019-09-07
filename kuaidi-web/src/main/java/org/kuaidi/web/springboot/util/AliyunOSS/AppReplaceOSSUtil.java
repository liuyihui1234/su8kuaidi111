package org.kuaidi.web.springboot.util.AliyunOSS;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;


import org.apache.commons.lang3.StringUtils;

import sun.misc.BASE64Decoder;

@SuppressWarnings("restriction")
public class AppReplaceOSSUtil {
 
		/**
		 * 閫氳繃BASE64Decoder瑙ｇ爜锛屽苟鐢熸垚鍥剧墖
		 * @param
		 */
		public static String string2Image(String imgVal) {
			 
			 String sufName = ".png";
			 String imgFilePath =  System.nanoTime() + sufName;
			// 瀵瑰瓧鑺傛暟缁勫瓧绗︿覆杩涜Base64瑙ｇ爜骞剁敓鎴愬浘鐗�
			
			 if (imgVal == null){
				return "err";
				}
			try {
				// Base64瑙ｇ爜 
				byte[] b = new BASE64Decoder().decodeBuffer(imgVal);
				for (int i = 0; i < b.length; ++i) {
					if (b[i] < 0) {
						// 璋冩暣寮傚父鏁版嵁
						b[i] += 256;
					}
				}
				File file = new File(imgFilePath);
				// 鐢熸垚Jpeg鍥剧墖 
				OutputStream out = new FileOutputStream(imgFilePath);
				out.write(b);
				out.flush();
				out.close();
				String rstFile =  AliyunOSSUtil.upload(file);
				DeleteFileUtil.delete(file.getPath());
				return rstFile;
			} catch (Exception e) {
				e.printStackTrace();
				return "err";
			}
		}
		
		public static String string2Voice(String voiceVal, String sufName) {
			if(StringUtils.isEmpty(sufName)) {
				sufName = ".wav";
			}
			 String imgFilePath =  System.nanoTime() + sufName;
			// 瀵瑰瓧鑺傛暟缁勫瓧绗︿覆杩涜Base64瑙ｇ爜骞剁敓鎴愬浘鐗�
			 if (voiceVal == null){
				return "err";
			 }
			try {
				// Base64瑙ｇ爜 
				byte[] b = new BASE64Decoder().decodeBuffer(voiceVal);
				for (int i = 0; i < b.length; ++i) {
					if (b[i] < 0) {
						// 璋冩暣寮傚父鏁版嵁
						b[i] += 256;
					}
				}
				File file = new File(imgFilePath);
				// 鐢熸垚Jpeg鍥剧墖 
				OutputStream out = new FileOutputStream(imgFilePath);
				out.write(b);
				out.flush();
				out.close();
				String rstFile =  AliyunOSSUtil.upload(file);
				DeleteFileUtil.delete(file.getPath());
				return rstFile;
			} catch (Exception e) {
				e.printStackTrace();
				return "err";
			}
		}
}
