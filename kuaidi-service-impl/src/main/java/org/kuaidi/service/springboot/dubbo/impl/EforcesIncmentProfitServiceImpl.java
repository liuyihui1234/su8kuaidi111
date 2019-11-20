package org.kuaidi.service.springboot.dubbo.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuaidi.bean.domain.EforcesIncmentProfit;
import org.kuaidi.dao.EforcesIncmentProfitMapper;
import org.kuaidi.iservice.IEforcesIncmentProfitService;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service(version = "1.0.0")
public class EforcesIncmentProfitServiceImpl implements IEforcesIncmentProfitService{
	
	@Autowired
	private EforcesIncmentProfitMapper  incmentProfitDao; 

	@Override
	public Integer saveAllList(List<EforcesIncmentProfit> incprofit) {
		// TODO Auto-generated method stub
		return incmentProfitDao.insertList(incprofit);
	}

	@Override
	public PageInfo<Map<String, Object>> getIncmentProfitByPage(int page, int pageSize, String parentId, String incName) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page,pageSize);
		
		List<Map<String,Object>>  list = incmentProfitDao.getIncmentProfitByIncName(incName);
		
		if(StringUtils.isEmpty(parentId)) {
			 list = incmentProfitDao.getIncmentProfitByIncName(incName);
		}else {
			list = incmentProfitDao.getIncmentProfitByParam(parentId, incName);
		}
		
		final  PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

}
