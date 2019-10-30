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
}
