package org.kuaidi.web.springboot.util;

import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import net.sf.json.JSONObject;

public class kuaidi100 {
	
	public static void push(String method){
		String url = "";
		switch(method) {
			case "pushtrace":
				url = "http://suyun.kuai8.com.cn/api/track/order";
				break;
		}	
	}
	
	
	public static JSONObject PushTrace(String number) {
		JSONObject data = new JSONObject();		
		return data; 
	}	
	
	/*
	 * 发送post请求。
	 */
	 public static Object sendPost(String url, List<NameValuePair> nameValuePairList) throws Exception{
	        JSONObject jsonObject = null;
	        CloseableHttpClient client = null;
	        CloseableHttpResponse response = null;
	        try{
	            /**
	             *  创建一个httpclient对象
	             */
	            client = HttpClients.createDefault();
	            
	            /**
	             * 创建一个post对象
	             */
	            HttpPost post = new HttpPost(url);
	            
	            /**
	             * 包装成一个Entity对象
	             */
	            StringEntity entity = new UrlEncodedFormEntity(nameValuePairList, "UTF-8");
	            /**
	             * 设置请求的内容
	             */
	            post.setEntity(entity);
	            /**
	             * 设置请求的报文头部的编码
	             */
	            post.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));
	            /**
	             * 设置请求的报文头部的编码
	             */
	            post.setHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));
	            /**
	             * 执行post请求
	             */
	            response = client.execute(post);
	            /**
	             * 获取响应码
	             */
	            int statusCode = response.getStatusLine().getStatusCode();
	            if ( 200 == statusCode){
	                /**
	                 * 通过EntityUitls获取返回内容
	                 */
	                String result = EntityUtils.toString(response.getEntity(),"UTF-8");
	                /**
	                 * 转换成json,根据合法性返回json或者字符串
	                 */
	                try{
	                    jsonObject = JSONObject.fromObject(result);
	                    return jsonObject;
	                }catch (Exception e){
	                    return result;
	                }
	            }else{
//	                LOGGER.error("HttpClientService-line: {}, errorMsg：{}", 146, "POST请求失败！");
	            }
	        }catch (Exception e){
//	            LOGGER.error("HttpClientService-line: {}, Exception：{}", 149, e);
	        }finally {
	            response.close();
	            client.close();
	        }
	        return null;
	    }
	
}
