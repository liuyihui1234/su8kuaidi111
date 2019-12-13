package org.kuaidi.web.springboot.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tracker {

    /** Apikey*/
    private String Apikey="a3dafa3c-7d76-4c1a-8388-46fb2e3904f7";

    /**
     * Json
     */
    public String orderOnlineByJson(String requestData,String urlStr,String type) throws Exception{
        //---headerParams
        Map headerparams = new HashMap();
        headerparams.put("Trackingmore-Api-Key", Apikey);
        headerparams.put("Content-Type", "application/json");
        //---bodyParams
        List bodyParams = new ArrayList();
        String result =null;
        if(type.equals("post")){

            String ReqURL="http://api.trackingmore.com/v2/trackings/post";
            bodyParams.add(requestData);
            result=sendPost(ReqURL, headerparams , bodyParams,"POST");

        }else if(type.equals("get")){

            String ReqURL="http://api.trackingmore.com/v2/trackings/get";
            String RelUrl = ReqURL+urlStr;
            result=sendPost(RelUrl, headerparams , bodyParams,"GET");

        }else if(type.equals("batch")){

            String ReqURL="http://api.trackingmore.com/v2/trackings/batch";
            bodyParams.add(requestData);
            result=sendPost(ReqURL, headerparams , bodyParams,"POST");

        }else if(type.equals("codeNumberGet")){

            String ReqURL="http://api.trackingmore.com/v2/trackings";
            String RelUrl = ReqURL+urlStr;
            result=sendGet(RelUrl, headerparams,"GET");

        }else if(type.equals("codeNumberPut")){

            String ReqURL="http://api.trackingmore.com/v2/trackings";
            bodyParams.add(requestData);
            String RelUrl = ReqURL+urlStr;
            result=sendPost(RelUrl, headerparams , bodyParams,"PUT");

        }else if(type.equals("codeNumberDelete")){

            String ReqURL="http://api.trackingmore.com/v2/trackings";
            String RelUrl = ReqURL+urlStr;
            result=sendGet(RelUrl, headerparams ,"DELETE");

        }else if(type.equals("realtime")){

            String ReqURL="http://api.trackingmore.com/v2/trackings/realtime";
            bodyParams.add(requestData);
            result=sendPost(ReqURL, headerparams , bodyParams,"POST");

        }else if(type.equals("carriers")){

            String ReqURL="http://api.trackingmore.com/v2/carriers";
            result=sendGet(ReqURL, headerparams ,"GET");

        }else if(type.equals("carriers/detect")){

            String ReqURL="http://api.trackingmore.com/v2/carriers/detect";
            bodyParams.add(requestData);
            result=sendPost(ReqURL, headerparams , bodyParams,"POST");

        }else if(type.equals("update")){

            String ReqURL="http://api.trackingmore.com/v2/trackings/update";
            bodyParams.add(requestData);
            result=sendPost(ReqURL, headerparams , bodyParams,"POST");

        }else if(type.equals("getuserinfo")){

            String ReqURL="http://api.trackingmore.com/v2/trackings/getuserinfo";
            result=sendGet(ReqURL, headerparams ,"GET");

        }else if(type.equals("getstatusnumber")){

            String ReqURL="http://api.trackingmore.com/v2/trackings/getstatusnumber";
            result=sendGet(ReqURL, headerparams , "GET");

        }else if(type.equals("notupdate")){

            String ReqURL="http://api.trackingmore.com/v2/trackings/notupdate";
            bodyParams.add(requestData);
            result=sendPost(ReqURL, headerparams , bodyParams,"POST");

        }else if(type.equals("remote")){

            String ReqURL="http://api.trackingmore.com/v2/trackings/remote";
            bodyParams.add(requestData);
            result=sendPost(ReqURL, headerparams , bodyParams,"POST");

        }else if(type.equals("costtime")){

            String ReqURL="http://api.trackingmore.com/v2/trackings/costtime";
            bodyParams.add(requestData);
            result=sendPost(ReqURL, headerparams , bodyParams,"POST");

        }else if(type.equals("delete")){

            String ReqURL="http://api.trackingmore.com/v2/trackings/delete";
            bodyParams.add(requestData);
            result=sendPost(ReqURL, headerparams , bodyParams,"POST");

        }else if(type.equals("updatemore")){

            String ReqURL="http://api.trackingmore.com/v2/trackings/updatemore";
            bodyParams.add(requestData);
            result=sendPost(ReqURL, headerparams , bodyParams,"POST");

        }
        return result;
    }


    private String sendPost(String url, Map<String, String> headerParams , List<String> bodyParams,String mothod) {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            URL realUrl = new URL(url);
            HttpURLConnection conn =(HttpURLConnection) realUrl.openConnection();

            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setRequestMethod(mothod);

            for (Map.Entry <String, String>entry : headerParams.entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
            conn.connect();

            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");

            StringBuffer sbBody = new StringBuffer();
            for (String str : bodyParams) {
                sbBody.append(str);
            }
            out.write(sbBody.toString());

            out.flush();

            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result.toString();
    }

    public static String sendGet(String url, Map <String, String>headerParams,String mothod ) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url;
            URL realUrl = new URL(urlNameString);

            HttpURLConnection connection =(HttpURLConnection) realUrl.openConnection();

            connection.setRequestMethod(mothod);

            for (Map.Entry <String, String> entry : headerParams.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }

            connection.connect();

            Map<String, List<String>> map = connection.getHeaderFields();

            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }

            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("Exception " + e);
            e.printStackTrace();
        }

        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /*
     * tracker  追踪业务流
     */
    public static void main(String [] args) {
    	String  urlStr ="";
    	String requestData="{\"tracking_number\":\"550000314986365\",\"carrier_code\":\"bestex\"}";
    	try {
            String result = new Tracker().orderOnlineByJson(requestData,urlStr,"realtime");
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}