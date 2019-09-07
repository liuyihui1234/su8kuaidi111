package org.kuaidi.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import sun.misc.BASE64Decoder;

@SuppressWarnings("restriction")
public class AppUpLoadUtil {
 
		/**
		 * 閫氳繃BASE64Decoder瑙ｇ爜锛屽苟鐢熸垚鍥剧墖
		 * @param imgStr 瑙ｇ爜鍚庣殑string
		 * jre1.7 鐗堟湰鎵嶆湁鐨�
		 */
		public static String string2Image(String basePath , String imgVal) {
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			 File file = new File(basePath  + "/static/upload/"+sdf.format(new Date()));
			 if(!file.exists()){
				 file.mkdirs();
			 } 
			 String sufName = ".png";
			 String imgFilePath = basePath +"/static/upload/"+sdf.format(new Date())+"/" +System.nanoTime() + sufName;
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
				// 鐢熸垚Jpeg鍥剧墖 
				OutputStream out = new FileOutputStream(imgFilePath);
				out.write(b);
				out.flush();
				out.close();
				
				
				String subString = imgFilePath.substring(basePath.length() + 1);
				subString = subString.replace("\\", "/");
				return subString;
			} catch (Exception e) {
				e.printStackTrace();
				return "err";
			}
		}

}
