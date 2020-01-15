package org.kuaidi.iservice;

import org.kuaidi.bean.domain.EforcesSubscribekuaidi100;

public interface IEforcesSubscribeService {
	
	EforcesSubscribekuaidi100 getSubscribeByBillNUm(String billNumber);
	
	Integer addSubscribe(EforcesSubscribekuaidi100  subscribeInfo);
	
}
