package org.kuaidi.web.springboot.appcontroller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.kuaidi.bean.domain.EforcesLogisticStracking;
import org.kuaidi.bean.domain.EforcesOrder;
import org.kuaidi.bean.domain.EforcesSubscribekuaidi100;
import org.kuaidi.bean.trackingmore.KdApiSearchEntity;
import org.kuaidi.bean.trackingmore.TraceEntity;
import org.kuaidi.iservice.IEforcesOrderService;
import org.kuaidi.iservice.IEforcesSubscribeService;
import org.kuaidi.iservice.IEforceslogisticstrackingService;
import org.kuaidi.utils.TimeDayUtil;
import org.kuaidi.web.springboot.util.Comm;
import org.kuaidi.web.springboot.util.CorpListUtil;
import org.kuaidi.web.springboot.util.EncryptSignMD5;
import org.kuaidi.web.springboot.util.KdniaoTrackQueryAPI;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/api/track/")
public class ApiTrackController {
	
	@Reference(version = "1.0.0")
	IEforceslogisticstrackingService  logisticsService ; 
	
	@Reference(version = "1.0.0")
	IEforcesOrderService  orderService ; 
	
	@Reference(version = "1.0.0")
	IEforcesSubscribeService  subscribeService; 
	
	@RequestMapping("queryorder")
	@ResponseBody
	public JSONObject queryorder8(String Param , String Sign, String Customer) {
		JSONObject  data = new JSONObject();
		if(StringUtils.isEmpty(Param) || StringUtils.isEmpty(Sign)||
				StringUtils.isEmpty(Customer)) {
			data.put("result", false);
			data.put("returnCode", 400);
			data.put("message", "数据不完整");
			return data;
		}
		String signCreated = EncryptSignMD5.GetSignMD5(Param +  EncryptSignMD5.kuai8SecretKey + Customer);
		System.out.println("sign : " + Sign + ">>>>>>>>>>>>>>");
		System.out.println("signCreated : " + signCreated + ">>>>>>>>>>>>>>");
		if(!StringUtils.equals(signCreated, Sign)) {
			data.put("result", false);
			data.put("returnCode", 503);
			data.put("message", "验证签名失败");
			return data;
		}
		try {
			JSONObject reqParam = JSONObject.fromObject(Param);
			if(!reqParam.containsKey("num") || StringUtils.isEmpty(reqParam.getString("num"))) {
				data.put("result", false);
				data.put("returnCode", 504);
				data.put("message", "单号错误");
				return data;
			}
			String billsNumber = reqParam.getString("num");
			List<EforcesLogisticStracking> list = logisticsService.getByBillsNumber(billsNumber);
			if(list == null || list.size() == 0 ) {
				data.put("status", "201");
				data.put("message", "单号错误");
				return data;
			}
			JSONArray recs  = new JSONArray();
			EforcesOrder orderInfo = orderService.getOrderMsg(billsNumber);
			if(orderInfo  != null && orderInfo.getIsgettrace() == 1 
							&& StringUtils.isNotEmpty(orderInfo.getZhcode()) && StringUtils.isNotEmpty(orderInfo.getZhnumber()) ) {
				KdniaoTrackQueryAPI api = new KdniaoTrackQueryAPI();
				String zhcode = orderInfo.getZhcode();
				String zhnumber = orderInfo.getZhnumber();
				KdApiSearchEntity searchEntity = Comm.KdApiSearch(zhcode, zhnumber);
				// 将rst 转化成实体对象。
				if(searchEntity != null && searchEntity.isSuccess() && searchEntity.getTraces().size() > 0 ) {
					for(int i = 0 ; i < searchEntity.getTraces().size(); i++ ) {
						TraceEntity  traceEntity = searchEntity.getTraces().get(i);
						JSONObject dataItem = new JSONObject();
						dataItem.put("context", traceEntity.getRemark());
						dataItem.put("time",traceEntity.getAcceptTime());
						dataItem.put("ftime",traceEntity.getAcceptTime());
						recs.add(dataItem);
					}
				}
			}
			Map<Integer ,String> mark = CorpListUtil.getMarkMap();
			// 将结果添加到物流信息里面。
			String state = "0";
			for(int i = 0 ; i < list.size() ; i++ ) {
				EforcesLogisticStracking  statcking = list.get(i);
				if(i == list.size() - 1 ) {
					state = mark.get(statcking.getMark());
				}
				if(statcking != null ) {
					JSONObject dataItem = new JSONObject();
					String operateTime = TimeDayUtil.convertDateToStr(statcking.getOperationtime());
					dataItem.put("context", statcking.getDescription());
					dataItem.put("time",operateTime);
					dataItem.put("ftime",operateTime);
					recs.add(dataItem);
				}
			}
			data.put("message", false);
			data.put("state", state);
			data.put("status", 200);
			data.put("condition", "F00");
			data.put("ischeck", "0");
			data.put("com", reqParam.getString("com"));
			data.put("nu", billsNumber);
			data.put("returnCode", 400);
			data.put("data", recs);
			return data;
		}catch(Exception e) {
			e.printStackTrace();
			data.put("result", false);
			data.put("returnCode", "507");
			data.put("message", "");
			return data;
		}
	}
	
	
	@RequestMapping("order")
	@ResponseBody
	public JSONObject SubscribeOrder(String param , String sign, String customer) {
		JSONObject  data = new JSONObject();
		if(StringUtils.isEmpty(param) || StringUtils.isEmpty(sign)||
				StringUtils.isEmpty(customer)) {
			data.put("result", false);
			data.put("returnCode", 400);
			data.put("message", "数据不完整");
			return data;
		}
		
		String signCreated = EncryptSignMD5.GetSignMD5(param +  EncryptSignMD5.kuai8SecretKey + customer);
		if(!StringUtils.equals(signCreated, sign)) {
			data.put("result", false);
			data.put("returnCode", 503);
			data.put("message", "验证签名失败");
			return data;
		}
		try {
			JSONObject reqParam = JSONObject.fromObject(param);
			
			String operator = "" ;
			if(reqParam.containsKey("operator")) {
				operator = reqParam.getString("operator");
			}
			if(StringUtils.isEmpty(operator)) {
				operator = "order";
			}
			
			String number = "";
			if(reqParam.containsKey("code")) {
				number = reqParam.getString("code");
			}
			if(StringUtils.isEmpty(number)) {
				data.put("result", false);
				data.put("returnCode", "504");
				data.put("message", "单号错误");
				return data;
			}
			/*
			  *   判断是否有订阅
			  *   如果订阅过了就回复，重复订阅
			 **/
			EforcesSubscribekuaidi100  kuaidi100 = subscribeService.getSubscribeByBillNUm(number);
			if(kuaidi100 != null ) {
				data.put("result", false);
				data.put("returnCode", "502");
				data.put("message", "重复订阅");
				return data;
			}
			/*
			 * 插入订阅记录，返回订阅成功。
			 */
			kuaidi100 = new EforcesSubscribekuaidi100();
			kuaidi100.setNumber(number);
			kuaidi100.setCompany(number);
			kuaidi100.setCallback(number);
			kuaidi100.setCreatetime(new Date());
			
			Integer rst = subscribeService.addSubscribe(kuaidi100);
			if(rst  == 1  ) {
				data.put("result", "true");
				data.put("returnCode","200"); 
				data.put("message","成功"); 
				return data;
			}else {
				data.put("result", "false");
				data.put("returnCode","501"); 
				data.put("message","服务器错误"); 
				return data;
			}
		}catch(Exception e) {
			e.printStackTrace();
			data.put("result", "false");
			data.put("returnCode","507"); 
			data.put("message","查询异常"); 
			return data;
		}
	}
	
	@RequestMapping("queryorder81")
	@ResponseBody
	public JSONObject queryorder8ByTracking(String param , String sign, String customer) {
		JSONObject  data = new JSONObject();
		if(StringUtils.isEmpty(param) || StringUtils.isEmpty(sign)||
				StringUtils.isEmpty(customer)) {
			data.put("result", false);
			data.put("returnCode", 400);
			data.put("message", "数据不完整");
			return data;
		}
		String signCreated = EncryptSignMD5.GetSignMD5(param +  EncryptSignMD5.kuai8SecretKey + customer);
		if(!StringUtils.equals(signCreated, sign)) {
			data.put("result", false);
			data.put("returnCode", 503);
			data.put("message", "验证签名失败");
			return data;
		}
		try {
			JSONObject reqParam = JSONObject.fromObject(param);
			if(!reqParam.containsKey("num") || StringUtils.isEmpty(reqParam.getString("num"))) {
				data.put("result", false);
				data.put("returnCode", 504);
				data.put("message", "单号错误");
				return data;
			}
			String billsNumber = reqParam.getString("num");
			List<EforcesLogisticStracking> list = logisticsService.getByBillsNumber(billsNumber);
			
			if(list == null || list.size() == 0 ) {
				data.put("status", "201");
				data.put("message", "单号错误");
				return data;
			}
			JSONArray recs  = new JSONArray();
			EforcesOrder orderInfo = orderService.getOrderMsg(billsNumber);
			if(orderInfo  != null && orderInfo.getIsgettrace() == 1 
							&& StringUtils.isNotEmpty(orderInfo.getZhcode()) && StringUtils.isNotEmpty(orderInfo.getZhnumber()) ) {
				/*
				 * 获得转运数据
				 */
				KdniaoTrackQueryAPI api = new KdniaoTrackQueryAPI();
				String zhcode = orderInfo.getZhcode();
				String zhnumber = orderInfo.getZhnumber();
				KdApiSearchEntity searchEntity = Comm.KdApiSearch(zhcode, zhnumber);
				// 将rst 转化成实体对象。
				if(searchEntity != null && searchEntity.isSuccess() && searchEntity.getTraces().size() > 0 ) {
					for(int i = 0 ; i < searchEntity.getTraces().size(); i++ ) {
						TraceEntity  traceEntity = searchEntity.getTraces().get(i);
						JSONObject dataItem = new JSONObject();
						dataItem.put("context", traceEntity.getAcceptStation());
						dataItem.put("time",traceEntity.getAcceptTime());
						dataItem.put("ftime",traceEntity.getAcceptTime());
						recs.add(dataItem);
					}
				}
			}
			// 将结果添加到物流信息里面。
		}catch(Exception e) {
			e.printStackTrace();
			data.put("result", false);
			data.put("returnCode", "507");
			data.put("message", "");
			return data;
		}
		return data;
	}
}
