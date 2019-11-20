package org.kuaidi.web.springboot.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.kuaidi.bean.Config;
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
    public void shareProfit() {
    	Set<String> setBillsNumber = redisUtil.getAllLikeKeys(Config.REDISBILLSPREX);
    	List<String> billsNumber = new ArrayList<String>();
    	Iterator it = setBillsNumber.iterator();
    	while(it.hasNext()) {
    		String numberItem = (String)it.next();
    		if(StringUtils.isNotEmpty(numberItem)) {
    			billsNumber.add(numberItem.trim());
    		}
    	}
    	List<EforcesOrder> orderList = orderService.getAllNumberMsg(billsNumber);
    	// 查询转运记录
    	List<EforcesTransportedscan> transferList =  
    			transferScanService.selectByBillsnumberList(billsNumber);
    	//将转运订单
    	Map <String , EforcesTransportedscan> transferScanMap 
    				= convertTransferScanListToMap(transferList);
    	/*
    	 * 遍历订单表，对完成的订单进行分润。
    	 */
    	for(int i = 0 ; i < orderList.size() ; i++) {
    		EforcesOrder  orderInfo = orderList.get(i);
    		String billsNumItem = orderInfo.getNumber();
    		if(billsNumItem != null ) {
    			//转运
    			if(transferScanMap.containsKey(billsNumItem.trim())) {
    				
    			}else {
    				
    			}
    			
    		}
    	}
    	
    }

	private Map<String ,EforcesTransportedscan> convertTransferScanListToMap(List<EforcesTransportedscan> transferList) {
		Map<String ,EforcesTransportedscan>  transferMap = 
    			new HashMap<String ,EforcesTransportedscan>();
    	if(transferList.size() > 0 ) {
    		for(int i = 0 ; i < transferList.size(); i++) {
    			EforcesTransportedscan  transportScan = transferList.get(i);
    			if(transportScan != null ) {
    				
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
	 * 没有转运的订单进行分润。
	 */
	private boolean shareProfit(EforcesOrder  orderInfo) {
		boolean flage = true; 
		Map<Integer, Map<String ,EforcesShareProfit>> map = profitUtil.getProfitMap();
		ShareMoney  moneyDetail = computeShareMoney(map, orderInfo);
		float shareMoney = moneyDetail.getBaseWeightSendMoney() ;
		String provinceFromTo = orderInfo.getFromprovince() + "_" +  orderInfo.getToprovince();
		Map<String,EforcesIncmentProfit>  incmentProfitMap =  
				new HashMap<String,EforcesIncmentProfit>();
		// 先把寄出金额给分了。
		// 把续重金额给分了。
		if(StringUtils.equals(orderInfo.getFromprovince(), orderInfo.getToprovince())) {
			
		}else { //
			Map<String ,EforcesShareProfit> mapType  = map.get(1);
			EforcesShareProfit profitItem = mapType.get(provinceFromTo);
			incmentProfitMap = baseWeightShareProfitDetail(orderInfo, profitItem, shareMoney, incmentProfitMap);
			if(moneyDetail.getAddWeightSendMoney() > 0 ) {
				/*
				 * EforcesOrder orderInfo, EforcesShareProfit profitItem,
				 * float shareMoney, Map<String, EforcesIncmentProfit> incmentProfitMap, int addWeight
				 */
				Integer weight = orderInfo.getWeight().intValue() - profitItem.getWeight();
				mapType =  map.get(2);
				profitItem =  mapType.get(provinceFromTo);
				incmentProfitMap = addWeightShareProfitDetail(orderInfo, profitItem,moneyDetail.getAddWeightSendMoney(),incmentProfitMap, weight);
			}
		}
		/*
		 * 派件金额进行分润
		 * 省内
		 **/
		if(StringUtils.equals(orderInfo.getFromprovince(), orderInfo.getToprovince())) {
			
		}else {
			//跨省分配， 基础重量的收件
			Map<String ,EforcesShareProfit> mapType  = map.get(3);
			EforcesShareProfit profitItem = mapType.get(provinceFromTo);
			incmentProfitMap = baseWeightShareProfitDetail(orderInfo, profitItem, shareMoney, incmentProfitMap);
			//
			Integer weight = orderInfo.getWeight().intValue() - profitItem.getWeight();
			mapType  = map.get(4);
			profitItem = mapType.get(provinceFromTo);
			incmentProfitMap = addWeightShareProfitDetail(orderInfo, profitItem,moneyDetail.getAddWeightReceiveMoney(),
					incmentProfitMap, weight);
			
			
		}
		return flage ; 
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
			EforcesRectoOrder rectoOrder =   rectoOrderService.getByOrderNumber(orderInfo.getNumber());
			String incId = rectoOrder.getDepartid();
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
		EforcesRectoOrder rectoOrder =   rectoOrderService.getByOrderNumber(orderInfo.getNumber());
		String incId = rectoOrder.getDepartid();
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
			incmentProfitAreaStreet.setBillsnumber(orderInfo.getNumber());
			incmentProfitAreaStreet.setIncid(incId);
			incmentProfitAreaStreet.setProfit(areaMoney);
			incmentProfitAreaStreet.setCreatetime(new Date());
			incmentProfitAreaStreet.setBillstime(orderInfo.getCreatetime());
			// 1 表示寄出分润
			incmentProfitAreaStreet.setProfittype(2);
			incmentProfitMap.put(incId, incmentProfitAreaStreet);
		}else {
			EforcesIncmentProfit  incmentProfitCity = new EforcesIncmentProfit();
			float areaMoney = shareMoney - (profitItem.getProvince() +  profitItem.getCity());
			incmentProfitCity.setBillsnumber(orderInfo.getNumber());
			incmentProfitCity.setIncid(incId);
			incmentProfitCity.setProfit(areaMoney);
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
