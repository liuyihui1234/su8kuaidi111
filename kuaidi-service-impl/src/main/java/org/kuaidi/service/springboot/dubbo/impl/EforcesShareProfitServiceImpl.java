package org.kuaidi.service.springboot.dubbo.impl;

import java.util.List;
import java.util.Map;
import org.kuaidi.bean.Config;
import org.kuaidi.bean.domain.EforcesShareProfit;
import org.kuaidi.dao.EforcesShareProfitMapper;
import org.kuaidi.iservice.IEforcesShareProfitService;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service(version = "1.0.0")
public class EforcesShareProfitServiceImpl implements IEforcesShareProfitService {
	
	@Autowired
	EforcesShareProfitMapper  shareProfitDao;

	@Override
	public List<EforcesShareProfit> getAllShareProfit() {
		// TODO Auto-generated method stub
		return shareProfitDao.getAllShareProfit();
	}
	
	@Override
	public PageInfo<Map<String, Object>> getShareProfitByPage(Integer page, Integer pageSize, String fromProvince,
			String toProvince, Integer status) {
		// TODO Auto-generated method stub
		if(page == null ) {
			page = 1 ; 
		}
		if(pageSize == null) {
			pageSize = Config.pageSize;
		}
		PageHelper.startPage(page,pageSize);
		List<Map<String,Object>>  list = shareProfitDao.getShareProfitByParam(fromProvince, toProvince, status);
		final  PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public List<Map<String, Object>> getShareProfitByParam(String fromProvince, String toProvince, Integer status) {
		// TODO Auto-generated method stub
		return shareProfitDao.getShareProfitByParam(fromProvince, toProvince, status);
	}

	@Override
	public Integer saveShareProfit(EforcesShareProfit shareProfit) {
		// TODO Auto-generated method stub
		return shareProfitDao.insertSelective(shareProfit);
	}

	@Override
	public Integer updateShareProfit(EforcesShareProfit shareProfit) {
		// TODO Auto-generated method stub
		return shareProfitDao.updateByPrimaryKeySelective(shareProfit);
	}

	@Override
	public int removeByIds(Integer[] id) {
		// TODO Auto-generated method stub
		return shareProfitDao.deleteByIds(id);
	}
	
}
