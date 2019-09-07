package org.kuaidi.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
 
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
 
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
 
public class OCRCheck {
	static BASE64Encoder encoder = new sun.misc.BASE64Encoder();
	static BASE64Decoder decoder = new sun.misc.BASE64Decoder();
 
	public static void main(String[] args) throws FileNotFoundException {
		String host = "https://dm-51.data.aliyun.com";
		String path = "/rest/160601/ocr/ocr_idcard.json";
		String method = "POST";
		String appcode = "5b9b54eba445488db925715579d0f19a";
		Map<String, String> headers = new HashMap<String, String>();
		// 最后在header中的格式(中间是英文空格)为Authorization:APPCODE
		// 83359fd73fe94948385f570e3c139105
		headers.put("Authorization", "APPCODE " + appcode);
		String pathImage = "D:/liuyihui.jpg";
		String binaryToString = getImageBinaryToString(pathImage);
		// encoder.encodeToString(pathImage.get);
		// 根据API的要求，定义相对应的Content-Type
		headers.put("Content-Type", "application/json; charset=UTF-8");
		Map<String, String> querys = new HashMap<String, String>();
		String bodys = "{\"inputs\": [{\"image\": {\"dataType\": 50,\"dataValue\": \"" + binaryToString
				+ "\"},\"configure\": {\"dataType\": 50,\"dataValue\": \"{\\\"side\\\":\\\"face\\\"}\"}}]}";
		try {
			/**
			 * 重要提示如下: HttpUtils请从
			 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
			 * 下载
			 *
			 * 相应的依赖请参照
			 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
			 */
			HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
			
			System.out.println(EntityUtils.toString(response.getEntity()));
			JSONObject resultObj = new JSONObject(EntityUtils.toString(response.getEntity()));
			JSONArray outputArray = resultObj.getJSONArray("outputs");
			String output = outputArray.getJSONObject(0).getJSONObject("outputValue").getString("dataValue"); // 取出结果json字符串
			JSONObject out = new JSONObject(output);
			if (out.getBoolean("success")) {
				String addr = out.getString("address"); // 获取地址
				String name = out.getString("name"); // 获取名字
				String birth = out.getString("birth"); // 获取名字
				String sex = out.getString("sex"); // 获取性别
				String nationality = out.getString("nationality"); // 获取性别
				String num = out.getString("num"); // 获取身份证号
				System.out.printf(" name : %s \n sex : %s \n birth : %s \n nationality : %s \n num : %s\n address : %s\n", name, sex,birth,
						nationality, num, addr);
			} else {
				System.out.println("predict error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
 
	}
 
	/**
	 * 获取图片的base64编码数据
	 * </p>
	 * 
	 * @param imagePath
	 * @return
	 */
	public static String getImageBinaryToString(String imagePath) {
		try {
			File file = new File(imagePath);
			byte[] content = new byte[(int) file.length()];
			FileInputStream finputstream = new FileInputStream(file);
			finputstream.read(content);
			finputstream.close();
			return new String(Base64.encodeBase64(content));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
 

}