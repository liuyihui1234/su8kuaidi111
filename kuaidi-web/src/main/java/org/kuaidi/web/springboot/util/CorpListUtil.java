package org.kuaidi.web.springboot.util;

import java.util.Map;
import org.kuaidi.bean.domain.EforcesCorp;
import org.kuaidi.iservice.IEforcesCorp;
import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Reference;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Component
public class CorpListUtil {
	
	private Map<Integer, String>  corpMap = new HashMap<Integer, String>();
	
	private Date timeStamp = null; 
	
	@Reference(version = "1.0.0")
	private IEforcesCorp corpService; 
	
	public Map<Integer, String> getCorpMap(){
		if(timeStamp == null || timeStamp.getTime() + 20*60*1000 < System.currentTimeMillis()) {
			List <EforcesCorp>  corpList =  corpService.getAllEforcesCorp();
			if(corpList != null && corpList.size() > 0 ) {
				corpMap = new HashMap<Integer, String>();
				for(int i = 0 ; i < corpList.size() ; i++) {
					EforcesCorp corpInfo  = corpList.get(i);
					corpMap.put(corpInfo.getId(), corpInfo.getCode());
				}
				timeStamp = new Date();
			}
			return corpMap;
		}else {
			return corpMap;
		}
	}
	/*
	 标识（1:收件交单扫描2:收件称重扫描3:发件扫描4:到件扫描5:派件扫描6:客户签收扫描,7装袋扫描，8解袋扫描，9留仓件、问题件扫描）
         （10:寄件异常出站，11:问题件，12:拒收，13:退件，14:清关中，15:已清关，）
           快递单当前签收状态，包括0在途中、1已揽收、2疑难、3已签收、4退签、5同城派送中、6退回、7转单等7个状态，其中4-7需要另外开通才有效
	 */
	public static Map<Integer , String> getMarkMap(){
		Map<Integer , String> mark = new HashMap<Integer ,String>();
		mark.put(1, "1");
		mark.put(2, "1");
		mark.put(3, "0");
		mark.put(4, "0");
		mark.put(5, "5");
		mark.put(6, "3");
		mark.put(7, "1");
		mark.put(8, "0");
		mark.put(9, "2");
		mark.put(10, "2");
		mark.put(11, "2");
		mark.put(12, "4");
		mark.put(13, "6");
		mark.put(14, "0");
		mark.put(15, "0");
		return mark;
	}
}
