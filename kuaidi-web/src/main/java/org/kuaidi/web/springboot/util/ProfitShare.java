package org.kuaidi.web.springboot.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.kuaidi.bean.domain.EforcesIncmentProfit;
import org.kuaidi.bean.domain.EforcesOrder;
import org.kuaidi.bean.domain.EforcesRectoOrder;
import org.kuaidi.bean.domain.EforcesShareProfit;
import org.kuaidi.bean.domain.EforcesTransportedscan;
import org.kuaidi.bean.vo.ShareMoney;
import org.kuaidi.iservice.IEforcesOrderService;
import org.kuaidi.iservice.IEforcesRectoOrderService;
import org.kuaidi.iservice.IEforcesTransportedscanService;
import org.kuaidi.web.springboot.util.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Reference;

@Component
public class ProfitShare {
	
	@Autowired
    private ProfitMapUtil profitUtil;
	
	@Autowired
	RedisUtil  redisUtil; 
	
	@Reference(version = "1.0.0")
	IEforcesOrderService  orderService ; 
	
	@Reference(version = "1.0.0")
	IEforcesTransportedscanService  transferScanService;
	
	@Reference(version = "1.0.0")
	IEforcesRectoOrderService  rectoOrderService; 
	
//  @Scheduled(cron = "0 * * * * ? ")
    public void shareProfit(String billsNums) {
//    	Set<String> setBillsNumber = redisUtil.getAllLikeKeys(Config.REDISBILLSPREX);
    	List<String> billsNumber = new ArrayList<String>();
//    	Iterator it = setBillsNumber.iterator();
//    	while(it.hasNext()) {
//    		String key = (String)it.next();
//    		if(StringUtils.isNotEmpty(key) &&  redisUtil.get(key) != null ) {
//    			billsNumber.add(redisUtil.get(key).trim());
//    		}
//    	}
    	
    	/*
    	 *测试代码 
    	 **/ 
    	if(billsNums != null && billsNums.length() > 0 ) {
    		String [] sectionNumber = billsNums.split(",");
    		if(sectionNumber != null && sectionNumber.length > 0 ) {
    			for(String item : sectionNumber ) {
    				if(item != null && item.length() > 0 ) {
    					billsNumber.add(item.trim());
    				}
    			}
    		}
    	}
    	List<EforcesOrder> orderList = orderService.getAllNumberMsg(billsNumber);
    	// 查询转运记录
    	List<EforcesTransportedscan> transferList =  
    			transferScanService.selectByBillsnumberList(billsNumber);
    	//将转运订单
    	Map <String , EforcesTransportedscan> transferScanMap 
    				= convertTransferScanListToMap(transferList);
    	
    	List<EforcesIncmentProfit> incmentProfitList = new ArrayList<EforcesIncmentProfit>();
    	/*
    	 * 遍历订单表，对完成的订单进行分润。
    	 */
    	for(int i = 0 ; i < orderList.size() ; i++) {
    		EforcesOrder  orderInfo = orderList.get(i);
    		String billsNumItem = orderInfo.getNumber();
    		if(billsNumItem != null ) {
    			//转运
    			if(transferScanMap.containsKey(billsNumItem.trim())) {
//    				EforcesTransportedscan   transpordscan = transferScanMap.get(billsNumItem.trim());
//    				List<EforcesIncmentProfit>  list =  shareTransferProfit(orderInfo, transpordscan);
//    				if(list != null && list.size() > 0 ) {
//    					incmentProfitList.addAll(list);
//    				}
    			}else {
    				List<EforcesIncmentProfit>  list = shareProfit(orderInfo);
    				if(list != null && list.size() > 0 ) {
    					incmentProfitList.addAll(list);
    				}
    			}
    		}
    	}
    }
    /*
     * 将转运的订单给封装成map类型的数据结构
     * */
	private Map<String ,EforcesTransportedscan> convertTransferScanListToMap(List<EforcesTransportedscan> transferList) {
		Map<String ,EforcesTransportedscan>  transferMap = 
    			new HashMap<String ,EforcesTransportedscan>();
    	if(transferList.size() > 0 ) {
    		for(int i = 0 ; i < transferList.size(); i++) {
    			EforcesTransportedscan  transportScan = transferList.get(i);
    			if(transportScan != null  && StringUtils.isNotEmpty(transportScan.getBillsnumber())) {
    				transferMap.put(transportScan.getBillsnumber().trim(), transportScan);
    			}
    		}
    	}
    	return transferMap;
	}
	
	//计算需要分的钱。
	public ShareMoney computeShareMoney(Map<Integer, Map<String ,EforcesShareProfit>> map , 
			EforcesOrder  orderInfo) {
		ShareMoney  shareMoney = new ShareMoney();
		Map<String ,EforcesShareProfit> mapType =  map.get(1);
		String provinceFromTo = orderInfo.getFromprovince() + "_" +  orderInfo.getToprovince();
		EforcesShareProfit  profitItem =  mapType.get(provinceFromTo);
		float  total =  profitItem.getTotal();
		Integer weight = profitItem.getWeight();
		// 大于基础重量
		if(orderInfo.getWeight().intValue() > weight) {
			shareMoney.setAddWeight(orderInfo.getWeight().intValue() - weight);
			mapType =  map.get(2);
			EforcesShareProfit addWeightprofitItem =  mapType.get(provinceFromTo);
			shareMoney.setAddWeightSendMoney(addWeightprofitItem.getTotal() * (orderInfo.getWeight().intValue() - weight));
			mapType =  map.get(3);
			EforcesShareProfit baseReceiveprofitItem =  mapType.get(provinceFromTo);
			shareMoney.setBaseWeightReceiveMoney(baseReceiveprofitItem.getTotal());
			// 收件续重
			mapType =  map.get(4);
			EforcesShareProfit addWeightReceiveprofitItem =  mapType.get(provinceFromTo);
			shareMoney.setAddWeightReceiveMoney(addWeightReceiveprofitItem.getTotal() * 
					(orderInfo.getWeight().intValue() - weight));
			
			float baseWeightSendMoney = orderInfo.getPrice().floatValue() - (
					shareMoney.getAddWeightSendMoney() + shareMoney.getBaseWeightReceiveMoney() 
					+ shareMoney.getAddWeightReceiveMoney());
			shareMoney.setBaseWeightSendMoney(baseWeightSendMoney);
		}else {
			shareMoney.setBaseWeightSendMoney(orderInfo.getPrice().floatValue());
		}
		return shareMoney;
	}
	
	/*
	 * 转运订单分润。
	 * 现在转运，只认为在发件线上有转运
	 * （如果收件线上有转运记录的时候修改这个过程）
	 */
//	private List<EforcesIncmentProfit> shareTransferProfit(EforcesOrder orderInfo , EforcesTransportedscan  transferScan) {
//		String incNumber = transferScan.getIncid();
//		String fromProvince = orderInfo.getFromprovince();
//		String fromCity = orderInfo.getFromcity();
//		String fromArea = orderInfo.getFromarea();
//		String fromAreaStreet = orderInfo.getFromareastreet();
//		Integer level = 0;
//		if(incNumber != null && incNumber.length() == 9 ) {
//			level = 4;
//		}else if(incNumber.indexOf(fromArea) > -1) {
//			level = 3;
//		}else if(incNumber.indexOf(fromCity) > -1){
//			level = 2;
//		}else if(incNumber.indexOf(fromProvince) > -1){
//			level = 1;
//		}
//		if(level == 0 ) {
//			return null ; 
//		}
//		Integer weight = orderInfo.getWeight().intValue();
//		Map<Integer, Map<String ,EforcesShareProfit>> map = profitUtil.getProfitMap();
//		Map<String ,EforcesShareProfit> profitMap = map.get(1);
//		String provinceFromTo = orderInfo.getFromprovince() + "_" +  orderInfo.getToprovince();
//		EforcesShareProfit shareProfitItem =  profitMap.get(provinceFromTo);
//		ShareMoney  moneyDetail = computeShareMoney(map, orderInfo);
//		/*
//		 * 基础分润
//		 **/
//		float shareMoney = moneyDetail.getBaseWeightReceiveMoney();
//		Map<String,EforcesIncmentProfit>  incmentProfitMap =  
//				new HashMap<String,EforcesIncmentProfit>();
//		incmentProfitMap = baseWeightTransferShareProfit(orderInfo, shareProfitItem, shareMoney ,level , incmentProfitMap);
//		
//		//增重转运分润
//		if(moneyDetail.getAddWeight() > 0 ) {
//			incmentProfitMap = addWeightTransferShareProfit(orderInfo, shareProfitItem, shareMoney ,moneyDetail.getAddWeight(),level,  incmentProfitMap);
//		}
//		
//		List<EforcesIncmentProfit>  incmentProfitList = new ArrayList<EforcesIncmentProfit>(); 
//		if(incmentProfitMap.size() > 0 ) {
//			Iterator it = incmentProfitMap.keySet().iterator();
//			while(it.hasNext()) {
//				String key = (String)it.next();
//				if(StringUtils.isNotEmpty(key) && incmentProfitMap.get(key) != null ) {
//					EforcesIncmentProfit  incmentProfitItem = incmentProfitMap.get(key);
//					incmentProfitList.add(incmentProfitItem);
//				}
//			}
//		}
//		return incmentProfitList;
//	}
	
	/*
	 * 转运分润的时候，按照基础重量进行分润
	 * 
	 */
	private Map<String,EforcesIncmentProfit>  baseWeightTransferShareProfit(EforcesOrder orderInfo,EforcesShareProfit profitItem , float shareMoney , Integer level, 
			Map<String,EforcesIncmentProfit>  incmentProfitMap) {
		if(level <= 4) {
			String incNumber = orderInfo.getFromprovince();
			incNumber = incNumber + "00";
			EforcesIncmentProfit  incmentProfit = new EforcesIncmentProfit();
			incmentProfit.setBillsnumber(orderInfo.getNumber());
			incmentProfit.setIncid(incNumber);
			incmentProfit.setProfit(profitItem.getProvince());
			incmentProfit.setCreatetime(new Date());
			incmentProfit.setBillstime(orderInfo.getCreatetime());
			// 1 表示寄出分润
			incmentProfit.setProfittype(1);
			incmentProfitMap.put(incNumber, incmentProfit);
			shareMoney = shareMoney - profitItem.getProvince();
		}
		
		if(level <= 3) {
			String incNumber = orderInfo.getFromcity();
			incNumber = incNumber + "00";
			EforcesIncmentProfit  incmentProfit = new EforcesIncmentProfit();
			incmentProfit.setBillsnumber(orderInfo.getNumber());
			incmentProfit.setIncid(incNumber);
			incmentProfit.setProfit(profitItem.getCity());
			incmentProfit.setCreatetime(new Date());
			incmentProfit.setBillstime(orderInfo.getCreatetime());
			// 1 表示寄出分润
			incmentProfit.setProfittype(1);
			incmentProfitMap.put(incNumber, incmentProfit);
			shareMoney = shareMoney - profitItem.getCity();
		}
		// 查询交单记录。
		
		EforcesRectoOrder rectoOrder =   rectoOrderService.getByOrderNumber(orderInfo.getNumber());
		String incId = rectoOrder.getDepartid();
		if(incId != null && incId.length() > 8 ) {
			if(level <= 2) {
				String incNumber = orderInfo.getFromarea();
				incNumber = incNumber + "00";
				EforcesIncmentProfit  incmentProfit = new EforcesIncmentProfit();
				incmentProfit.setBillsnumber(orderInfo.getNumber());
				incmentProfit.setIncid(incNumber);
				incmentProfit.setProfit(profitItem.getArea());
				incmentProfit.setCreatetime(new Date());
				incmentProfit.setBillstime(orderInfo.getCreatetime());
				// 1 表示寄出分润
				incmentProfit.setProfittype(1);
				incmentProfitMap.put(incNumber, incmentProfit);
				shareMoney = shareMoney - profitItem.getCity();
			}
			
			if(level <= 1 ) {
				String incNumber = orderInfo.getFromareastreet();
				EforcesIncmentProfit  incmentProfit = new EforcesIncmentProfit();
				incmentProfit.setBillsnumber(orderInfo.getNumber());
				incmentProfit.setIncid(incNumber);
				incmentProfit.setProfit(shareMoney);
				incmentProfit.setCreatetime(new Date());
				incmentProfit.setBillstime(orderInfo.getCreatetime());
				// 1 表示寄出分润
				incmentProfit.setProfittype(1);
				incmentProfitMap.put(incNumber, incmentProfit);
			}
		}if(incId != null) {
			EforcesIncmentProfit  incmentProfit = new EforcesIncmentProfit();
			incmentProfit.setBillsnumber(orderInfo.getNumber());
			incmentProfit.setIncid(incId);
			incmentProfit.setProfit(shareMoney);
			incmentProfit.setCreatetime(new Date());
			incmentProfit.setBillstime(orderInfo.getCreatetime());
			// 1 表示寄出分润
			incmentProfit.setProfittype(1);
			incmentProfitMap.put(incId, incmentProfit);
		}
		
		return incmentProfitMap;
	}
	
//	private Map<String,EforcesIncmentProfit>  addWeightTransferShareProfit(EforcesOrder orderInfo,EforcesShareProfit profitItem , float shareMoney , Integer addWeight ,Integer level, 
//			Map<String,EforcesIncmentProfit>  incmentProfitMap) {
//		if(level <= 4) {
//			String incNumber = orderInfo.getFromprovince();
//			incNumber = incNumber + "00";
//			EforcesIncmentProfit  incmentProfit = incmentProfitMap.get(incNumber);
//			if(incmentProfit != null ) {
//				incmentProfit.setBillsnumber(orderInfo.getNumber());
//				incmentProfit.setIncid(incNumber);
//				incmentProfit.setProfit(profitItem.getProvince() + incmentProfit.getProfit());
//				incmentProfit.setCreatetime(new Date());
//				incmentProfit.setBillstime(orderInfo.getCreatetime());
//				// 1 表示寄出分润
//				incmentProfit.setProfittype(1);
//				incmentProfitMap.put(incNumber, incmentProfit);
//			}
//		}
//		if(level <= 3) {
//			String incNumber = orderInfo.getFromcity();
//			incNumber = incNumber + "00";
//			EforcesIncmentProfit  incmentProfit = incmentProfitMap.get(incNumber);
//			if(incmentProfit != null ) {
//				incmentProfit.setBillsnumber(orderInfo.getNumber());
//				incmentProfit.setIncid(incNumber);
//				incmentProfit.setProfit(profitItem.getCity() + incmentProfit.getProfit());
//				incmentProfit.setCreatetime(new Date());
//				incmentProfit.setBillstime(orderInfo.getCreatetime());
//				// 1 表示寄出分润
//				incmentProfit.setProfittype(1);
//				incmentProfitMap.put(incNumber, incmentProfit);
//			}
//		}
//		// 查找转运记录
//		EforcesRectoOrder rectoOrder =   rectoOrderService.getByOrderNumber(orderInfo.getNumber());
//		String incId = rectoOrder.getDepartid();
//		// 区县直接收货 
//		if(incId != null && incId.length() > 8 ) {
//			// 街道收货
//			String area = orderInfo.getFromarea() ; 
//			if(StringUtils.isNotEmpty(area)) {
//				EforcesIncmentProfit  incmentProfitArea = new EforcesIncmentProfit();
//				if(incmentProfitMap.containsKey(area + "00")) {
//					incmentProfitArea = incmentProfitMap.get(area + "00");
//					float areaMoney = profitItem.getArea() * addWeight + incmentProfitArea.getProfit(); 
//					incmentProfitArea.setProfit(areaMoney);
//				}else {
//					incmentProfitArea.setProfit(profitItem.getArea() * addWeight);
//				}
//				incmentProfitArea.setBillsnumber(orderInfo.getNumber());
//				incmentProfitArea.setIncid(area + "00");
//				incmentProfitArea.setCreatetime(new Date());
//				incmentProfitArea.setBillstime(orderInfo.getCreatetime());
//				// 1 表示寄出分润.
//				incmentProfitArea.setProfittype(2);
//				incmentProfitMap.put(area + "00", incmentProfitArea);
//			}
//			EforcesIncmentProfit  incmentProfitAreaStreet = new EforcesIncmentProfit();
//			float areaMoney = shareMoney - (profitItem.getProvince() +  profitItem.getCity() + profitItem.getArea());
//			if(incmentProfitMap.containsKey(incId)) {
//				incmentProfitAreaStreet =  incmentProfitMap.get(incId);
//				if(incmentProfitAreaStreet != null ) {
//					incmentProfitAreaStreet.setProfit(areaMoney + incmentProfitAreaStreet.getProfit());
//				}
//			}else {
//				incmentProfitAreaStreet.setProfit(areaMoney );
//			}
//			incmentProfitAreaStreet.setBillsnumber(orderInfo.getNumber());
//			incmentProfitAreaStreet.setIncid(incId);
//			incmentProfitAreaStreet.setCreatetime(new Date());
//			incmentProfitAreaStreet.setBillstime(orderInfo.getCreatetime());
//			// 1 表示寄出分润
//			incmentProfitAreaStreet.setProfittype(2);
//			incmentProfitMap.put(incId, incmentProfitAreaStreet);
//		}else {
//			EforcesIncmentProfit  incmentProfitCity = new EforcesIncmentProfit();
//			float areaMoney = shareMoney - (profitItem.getProvince() +  profitItem.getCity());
//			if(incmentProfitMap.containsKey(incId)) {
//				incmentProfitCity =  incmentProfitMap.get(incId);
//				incmentProfitCity.setProfit(areaMoney + incmentProfitCity.getProfit());
//			}else {
//				incmentProfitCity.setProfit(areaMoney);
//			}
//			incmentProfitCity.setBillsnumber(orderInfo.getNumber());
//			incmentProfitCity.setIncid(incId);
//			incmentProfitCity.setCreatetime(new Date());
//			incmentProfitCity.setBillstime(orderInfo.getCreatetime());
//			// 1 表示寄出分润
//			incmentProfitCity.setProfittype(2);
//			incmentProfitMap.put(incId, incmentProfitCity);
//		}
//		return incmentProfitMap; 
//	}
	
	/*
	 * 没有转运的订单进行分润。
	 */
	private List<EforcesIncmentProfit> shareProfit(EforcesOrder  orderInfo) {
		Map<Integer, Map<String ,EforcesShareProfit>> map = profitUtil.getProfitMap();
		ShareMoney  moneyDetail = computeShareMoney(map, orderInfo);
		float shareMoney = moneyDetail.getBaseWeightSendMoney() ;
		String provinceFromTo = orderInfo.getFromprovince() + "_" +  orderInfo.getToprovince();
		Map<String,EforcesIncmentProfit>  incmentProfitMap =  
				new HashMap<String,EforcesIncmentProfit>();
		// 先把寄出金额给分了。
		// 把续重金额给分了。
		Map<String ,EforcesShareProfit> mapType  = map.get(1);
		EforcesShareProfit profitItem = mapType.get(provinceFromTo);
		incmentProfitMap = baseWeightShareProfitDetail(orderInfo, profitItem, shareMoney, incmentProfitMap);
		if(moneyDetail.getAddWeight() > 0 ) {
			mapType =  map.get(2);
			profitItem =  mapType.get(provinceFromTo);
			incmentProfitMap = addWeightShareProfitDetail(orderInfo, profitItem,moneyDetail.getAddWeightSendMoney(),incmentProfitMap, moneyDetail.getAddWeight());
		}

		//跨省分配， 基础重量的收件
		mapType  = map.get(3);
		profitItem = mapType.get(provinceFromTo);
		incmentProfitMap = baseWeightShareProfitDetail(orderInfo, profitItem, shareMoney, incmentProfitMap);
		//
		if(moneyDetail.getAddWeight() > 0 ) {
			mapType  = map.get(4);
			profitItem = mapType.get(provinceFromTo);
			incmentProfitMap = addWeightShareProfitDetail(orderInfo, profitItem,moneyDetail.getAddWeightReceiveMoney(),
					incmentProfitMap, moneyDetail.getAddWeight());
		}
		/*
		 * 将对应的Map类型的数据封装成list类型。（为了合并保存）
		 */
		List<EforcesIncmentProfit>  incmentProfitList = new ArrayList<EforcesIncmentProfit>(); 
		if(incmentProfitMap.size() > 0 ) {
			Iterator it = incmentProfitMap.keySet().iterator();
			while(it.hasNext()) {
				String key = (String)it.next();
				if(StringUtils.isNotEmpty(key) && incmentProfitMap.get(key) != null ) {
					EforcesIncmentProfit  incmentProfitItem = incmentProfitMap.get(key);
					incmentProfitList.add(incmentProfitItem);
				}
			}
		}
		return incmentProfitList ; 
	}
	
	/*
	 * 基础重量发件分润详情
	 * 1 : 发件
	 * 2 ：收件
	 */
	private Map<String, EforcesIncmentProfit>  baseWeightShareProfitDetail(EforcesOrder orderInfo, EforcesShareProfit profitItem,
			float shareMoney, Map<String, EforcesIncmentProfit> incmentProfitMap) {
			//先分基础金额
		    Integer type = 1;
			String provinceCode = orderInfo.getFromprovince() ; 
			if(provinceCode != null ) {
				// 省的分润。
				EforcesIncmentProfit  incmentProfit = new EforcesIncmentProfit();
				incmentProfit.setBillsnumber(orderInfo.getNumber());
				incmentProfit.setIncid(provinceCode + "00");
				incmentProfit.setProfit(profitItem.getProvince());
				incmentProfit.setCreatetime(new Date());
				incmentProfit.setBillstime(orderInfo.getCreatetime());
				// 1 表示寄出分润
				incmentProfit.setProfittype(type);
				incmentProfitMap.put(provinceCode + "00", incmentProfit);
			}
			String cityCode = orderInfo.getFromcity() ;
			if(cityCode != null ) {
				// 市的分润
				EforcesIncmentProfit  incmentProfitCity = new EforcesIncmentProfit();
				incmentProfitCity.setBillsnumber(orderInfo.getNumber());
				incmentProfitCity.setIncid(cityCode + "00");
				incmentProfitCity.setProfit(profitItem.getCity());
				incmentProfitCity.setCreatetime(new Date());
				incmentProfitCity.setBillstime(orderInfo.getCreatetime());
				// 1 表示寄出分润
				incmentProfitCity.setProfittype(type);
				incmentProfitMap.put(cityCode + "00", incmentProfitCity);
			}
			// 从省开始分派
			// 查询交单的网点编号长度大于8 ， 说明正常交单，否则的话， 是三级网点交单。
//			EforcesRectoOrder rectoOrder =   rectoOrderService.getByOrderNumber(orderInfo.getNumber());
			String incId = orderInfo.getCreateincnumber();
			// 区县直接收货 
			if(incId != null && incId.length() > 8 ) {
				// 街道收货
				String area = orderInfo.getFromarea() ; 
				if(StringUtils.isNotEmpty(area)) {
					EforcesIncmentProfit  incmentProfitCity = new EforcesIncmentProfit();
					float areaMoney = profitItem.getArea();
					// shareMoney - (profitItem.getProvince() +  profitItem.getCity());
					incmentProfitCity.setBillsnumber(orderInfo.getNumber());
					incmentProfitCity.setIncid(area + "00");
					incmentProfitCity.setProfit(profitItem.getArea());
					incmentProfitCity.setCreatetime(new Date());
					incmentProfitCity.setBillstime(orderInfo.getCreatetime());
					// 1 表示寄出分润.
					incmentProfitCity.setProfittype(type);
					incmentProfitMap.put(area + "00", incmentProfitCity);
				}
				EforcesIncmentProfit  incmentProfitCity = new EforcesIncmentProfit();
				float areaMoney = shareMoney - (profitItem.getProvince() +  profitItem.getCity() + profitItem.getArea());
				incmentProfitCity.setBillsnumber(orderInfo.getNumber());
				incmentProfitCity.setIncid(incId);
				incmentProfitCity.setProfit(areaMoney);
				incmentProfitCity.setCreatetime(new Date());
				incmentProfitCity.setBillstime(orderInfo.getCreatetime());
				// 1 表示寄出分润
				incmentProfitCity.setProfittype(type);
				incmentProfitMap.put(incId, incmentProfitCity);
			}else {
				EforcesIncmentProfit  incmentProfitCity = new EforcesIncmentProfit();
				float areaMoney = shareMoney - (profitItem.getProvince() +  profitItem.getCity());
				incmentProfitCity.setBillsnumber(orderInfo.getNumber());
				incmentProfitCity.setIncid(incId);
				incmentProfitCity.setProfit(areaMoney);
				incmentProfitCity.setCreatetime(new Date());
				incmentProfitCity.setBillstime(orderInfo.getCreatetime());
				// 1 表示寄出分润
				incmentProfitCity.setProfittype(type);
				incmentProfitMap.put(incId, incmentProfitCity);
			}
			// 续重分成
			return incmentProfitMap; 
	}
	
	
	
	/*
	 * 基础重量发件分润详情
	 * 1 : 发件
	 * 2 ：收件
	 */
	private Map<String, EforcesIncmentProfit>  addWeightShareProfitDetail(EforcesOrder orderInfo, EforcesShareProfit profitItem,
			float shareMoney, Map<String, EforcesIncmentProfit> incmentProfitMap, int addWeight) {
			//先分基础金额
		if(addWeight > 0 ) {
			return incmentProfitMap; 
		}
		String provinceCode = orderInfo.getFromprovince() ; 
		if(provinceCode != null ) {
			// 省的分润。
			EforcesIncmentProfit incmentProfit = incmentProfitMap.get(provinceCode + "00");
			if(incmentProfit == null ) {
				incmentProfit = new EforcesIncmentProfit();
				incmentProfit.setProfit(profitItem.getProvince()* addWeight );
			}else {
				incmentProfit.setProfit(incmentProfit.getProfit() + profitItem.getProvince()* addWeight );
			}
			incmentProfit.setBillsnumber(orderInfo.getNumber());
			incmentProfit.setIncid(provinceCode + "00");
			incmentProfit.setProfit(profitItem.getProvince());
			incmentProfit.setCreatetime(new Date());
			incmentProfit.setBillstime(orderInfo.getCreatetime());
			// 1 表示寄出分润
			incmentProfit.setProfittype(2);
			incmentProfitMap.put(provinceCode + "00", incmentProfit);
		}
		String cityCode = orderInfo.getFromcity() ;
		if(cityCode != null ) {
			// 市的分润
			EforcesIncmentProfit incmentProfit = incmentProfitMap.get(cityCode + "00");
			if(incmentProfit == null ) {
				incmentProfit = new EforcesIncmentProfit();
				incmentProfit.setProfit(profitItem.getCity()* addWeight );
			}else {
				incmentProfit.setProfit(incmentProfit.getProfit() + profitItem.getCity()* addWeight );
			}
			EforcesIncmentProfit  incmentProfitCity = new EforcesIncmentProfit();
			incmentProfitCity.setBillsnumber(orderInfo.getNumber());
			incmentProfitCity.setIncid(cityCode + "00");
			incmentProfitCity.setCreatetime(new Date());
			incmentProfitCity.setBillstime(orderInfo.getCreatetime());
			// 1 表示寄出分润
			incmentProfitCity.setProfittype(2);
			incmentProfitMap.put(cityCode + "00", incmentProfitCity);
		}
		// 从省开始分派
		// 查询交单的网点编号长度大于8 ， 说明正常交单，否则的话， 是三级网点交单。
//		EforcesRectoOrder rectoOrder =   rectoOrderService.getByOrderNumber(orderInfo.getNumber());
		String incId =  orderInfo.getCreateincnumber();
		// 区县直接收货 
		if(incId != null && incId.length() > 8 ) {
			// 街道收货
			String area = orderInfo.getFromarea() ; 
			if(StringUtils.isNotEmpty(area)) {
				EforcesIncmentProfit  incmentProfitArea = new EforcesIncmentProfit();
				if(incmentProfitMap.containsKey(area + "00")) {
					incmentProfitArea = incmentProfitMap.get(area + "00");
					float areaMoney = profitItem.getArea() * addWeight + incmentProfitArea.getProfit(); 
					incmentProfitArea.setProfit(areaMoney);
				}else {
					incmentProfitArea.setProfit(profitItem.getArea() * addWeight);
				}
				incmentProfitArea.setBillsnumber(orderInfo.getNumber());
				incmentProfitArea.setIncid(area + "00");
				incmentProfitArea.setCreatetime(new Date());
				incmentProfitArea.setBillstime(orderInfo.getCreatetime());
				// 1 表示寄出分润.
				incmentProfitArea.setProfittype(2);
				incmentProfitMap.put(area + "00", incmentProfitArea);
			}
			EforcesIncmentProfit  incmentProfitAreaStreet = new EforcesIncmentProfit();
			float areaMoney = shareMoney - (profitItem.getProvince() +  profitItem.getCity() + profitItem.getArea());
			if(incmentProfitMap.containsKey(incId)) {
				incmentProfitAreaStreet = incmentProfitMap.get(incId);
				incmentProfitAreaStreet.setProfit(areaMoney + incmentProfitAreaStreet.getProfit());
			}else {
				incmentProfitAreaStreet.setProfit(areaMoney);
			}
			incmentProfitAreaStreet.setBillsnumber(orderInfo.getNumber());
			incmentProfitAreaStreet.setIncid(incId);
			incmentProfitAreaStreet.setCreatetime(new Date());
			incmentProfitAreaStreet.setBillstime(orderInfo.getCreatetime());
			// 1 表示寄出分润
			incmentProfitAreaStreet.setProfittype(2);
			incmentProfitMap.put(incId, incmentProfitAreaStreet);
		}else {
			EforcesIncmentProfit  incmentProfitCity = new EforcesIncmentProfit();
			float areaMoney = shareMoney - (profitItem.getProvince() +  profitItem.getCity());
			if(incmentProfitMap.containsKey(incId)) {
				incmentProfitCity = incmentProfitMap.get(incId);
				incmentProfitCity.setProfit(areaMoney + incmentProfitCity.getProfit());
			}else {
				incmentProfitCity.setProfit(areaMoney);
			}
			incmentProfitCity.setBillsnumber(orderInfo.getNumber());
			incmentProfitCity.setIncid(incId);
			incmentProfitCity.setCreatetime(new Date());
			incmentProfitCity.setBillstime(orderInfo.getCreatetime());
			// 1 表示寄出分润
			incmentProfitCity.setProfittype(2);
			incmentProfitMap.put(incId, incmentProfitCity);
		}
		// 续重分成
		return incmentProfitMap; 
	}
	
}
