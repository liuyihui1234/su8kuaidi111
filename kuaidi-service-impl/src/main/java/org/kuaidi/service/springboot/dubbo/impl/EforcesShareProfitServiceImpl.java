package org.kuaidi.service.springboot.dubbo.impl;

import java.util.List;

import org.kuaidi.bean.domain.EforcesShareProfit;
import org.kuaidi.dao.EforcesShareProfitMapper;
import org.kuaidi.iservice.IEforcesShareProfitService;
import org.springframework.beans.factory.annotation.Autowired;

public class EforcesShareProfitServiceImpl implements IEforcesShareProfitService {
	
	@Autowired
	EforcesShareProfitMapper  shareProfitDao;

	@Override
	public List<EforcesShareProfit> getAllShareProfit() {
		// TODO Auto-generated method stub
		return shareProfitDao.getAllShareProfit();
	}

}
