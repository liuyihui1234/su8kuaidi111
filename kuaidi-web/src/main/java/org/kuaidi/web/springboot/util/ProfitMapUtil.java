package org.kuaidi.web.springboot.util;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.kuaidi.bean.domain.EforcesShareProfit;
import org.kuaidi.iservice.IEforcesShareProfitService;
import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Reference;

@Component
public class ProfitMapUtil {
	
	private Map<Integer, Map<String ,EforcesShareProfit>>  shareProfitMap = new HashMap<Integer, Map<String , EforcesShareProfit>>();
	
	private Date timeStamp = null; 
	
	@Reference(version = "1.0.0")
	IEforcesShareProfitService profitService;
	
	/*
	 * 每隔三个小时查询一次数据库，防止数据更新没有及时的生效。
	 * 一般情况下这个表不会发生改变的。
	 */
	public Map<Integer, Map<String ,EforcesShareProfit>> getProfitMap(){
		if(timeStamp == null || timeStamp.getTime() + 3*60*60*1000 < System.currentTimeMillis()) {
			//获得所有分润信息
			List <EforcesShareProfit>  shareList =  profitService.getAllShareProfit();
			if(shareList != null && shareList.size() > 0 ) {
				shareProfitMap = new HashMap<Integer, Map<String ,EforcesShareProfit>>();
				for(int i = 0 ; i < shareList.size() ; i++) {
					EforcesShareProfit shareProfitItem  = shareList.get(i);
					if(shareProfitItem != null ) {
						Integer profitType = shareProfitItem.getType();
						if(profitType != null ) {
							Map<String, EforcesShareProfit>  shareProfitType = null; 
							if(shareProfitMap.containsKey(profitType)) {
								shareProfitType = shareProfitMap.get(profitType);
							}else {
								shareProfitType = new HashMap<String, EforcesShareProfit>();
							}
							String fromToProvince = shareProfitItem.getFromprovinceid() 
									+ "_" + shareProfitItem.getToprovinceid();
							shareProfitType.put(fromToProvince, shareProfitItem);
							shareProfitMap.put(profitType, shareProfitType);
						}
					}
				}
				timeStamp = new Date();
			}
			return shareProfitMap;
		}else {
			return shareProfitMap;
		}
	}
}
