package org.kuaidi.web.springboot.util;

import java.util.ArrayList;
import java.util.List;

import org.kuaidi.bean.trackingmore.KdApiSearchEntity;
import org.kuaidi.bean.trackingmore.TraceEntity;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Comm {
	
	
	public static KdApiSearchEntity  KdApiSearch(String zhCode , String zhNumber) {
		KdApiSearchEntity  searchEntity = new KdApiSearchEntity();
		KdniaoTrackQueryAPI api = new KdniaoTrackQueryAPI();
		try {
			String result = api.getOrderTracesByJson(zhCode, zhNumber);
			JSONObject  data = JSONObject.fromObject(result);
			searchEntity.setLogisticCode(data.getString("LogisticCode"));
			searchEntity.setShipperCode(data.getString("ShipperCode"));
			searchEntity.setState(Integer.parseInt(data.getString("state")));
			searchEntity.setEBusinessID(data.getString("EBusinessID"));
			searchEntity.setReason(data.getString("Reason"));
			searchEntity.setSuccess(data.getBoolean("Reason"));
			JSONArray  arr = data.getJSONArray("Traces");
			if(arr != null && arr.size() > 0 ) {
				for(int i = 0 ; i < arr.size(); i++ ) {
					TraceEntity  traceEntity =  new TraceEntity();
					JSONObject item = arr.getJSONObject(i);
					
					
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null ; 
	}
	
	/*
	 *根据转运标号和转运订单号，查询物流信息 
	 *在这里认为是zhCode  ， 是tracking 平台上的编号。
	 */
	public static List <TraceEntity> TrackingSearch(String zhCode , String zhNumber) {
		Tracker  tracker = new Tracker();
    	String  urlStr ="";
    	String requestData="{\"tracking_number\":\"%s\",\"carrier_code\":\"%s\"}";
    	requestData = String.format(requestData,zhNumber, zhCode);
    	System.out.println(requestData);
    	List <TraceEntity>  traceList = new ArrayList<TraceEntity>();
    	try {
            String result = tracker.orderOnlineByJson(requestData,urlStr,"realtime");
			System.out.println(result);
			JSONObject data = JSONObject.fromObject(result);
			if(data != null && data.containsKey("meta") &&
					data.getJSONObject("meta") != null) {
				JSONObject meta = data.getJSONObject("meta");
				if(meta.containsKey("code") && meta.getInt("code") == 200) {
					if(data.containsKey("origin_info") && 
									data.get("origin_info") != null) {
						JSONObject origin = data.getJSONObject("origin_info");
						if(origin != null && origin.containsKey("trackinfo")) {
							JSONArray  trackArr = origin.getJSONArray("trackinfo");
							if(trackArr != null  && trackArr.size() > 0 ) {
								for(int i = 0 ; i < trackArr.size() ; i++) {
									JSONObject item = trackArr.getJSONObject(i);
									TraceEntity trace = new TraceEntity();
									if(item != null ) {
										trace.setAcceptTime(item.getString("Date"));
										trace.setAcceptStation(item.getString("checkpoint_status"));
										trace.setRemark(item.getString("StatusDescription"));
									}
									traceList.add(trace);
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return traceList ; 
	}
	

}
