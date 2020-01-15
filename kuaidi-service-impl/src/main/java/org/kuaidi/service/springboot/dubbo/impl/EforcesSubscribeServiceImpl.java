package org.kuaidi.service.springboot.dubbo.impl;

import org.kuaidi.bean.domain.EforcesSubscribekuaidi100;
import org.kuaidi.dao.EforcesSubscribekuaidi100Mapper;
import org.kuaidi.iservice.IEforcesSubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;

@Service(version = "1.0.0")
public class EforcesSubscribeServiceImpl implements IEforcesSubscribeService{

	@Autowired
	private EforcesSubscribekuaidi100Mapper  kuaidi100Dao;
	
	@Override
	public EforcesSubscribekuaidi100 getSubscribeByBillNUm(String billNumber) {
		// TODO Auto-generated method stub
		return kuaidi100Dao.selectByBillNumber(billNumber);
	}

	@Override
	public Integer addSubscribe(EforcesSubscribekuaidi100 subscribeInfo) {
		// TODO Auto-generated method stub
		return kuaidi100Dao.insertSelective(subscribeInfo);
	}

}
