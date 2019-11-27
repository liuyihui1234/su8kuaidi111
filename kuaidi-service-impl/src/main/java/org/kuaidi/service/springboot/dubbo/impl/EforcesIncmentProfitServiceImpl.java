package org.kuaidi.service.springboot.dubbo.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuaidi.bean.Config;
import org.kuaidi.bean.domain.EforcesIncment;
import org.kuaidi.bean.domain.EforcesIncmentProfit;
import org.kuaidi.bean.domain.EforcesShareProfit;
import org.kuaidi.dao.EforcesIncmentMapper;
import org.kuaidi.dao.EforcesIncmentProfitMapper;
import org.kuaidi.dao.EforcesShareProfitMapper;
import org.kuaidi.iservice.IEforcesIncmentProfitService;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service(version = "1.0.0")
public class EforcesIncmentProfitServiceImpl implements IEforcesIncmentProfitService{
	
	@Autowired
	private EforcesIncmentProfitMapper  incmentProfitDao; 
	
	@Autowired
	private EforcesIncmentMapper  incmentDao;
	
	@Autowired
	private EforcesShareProfitMapper  shareProfitDao; 

	@Override
	public Integer saveAllList(List<EforcesIncmentProfit> incprofit) {
		// TODO Auto-generated method stub
		/*
		 * 对数据进行补全， 根据 网点的编号查询所有的网点信息
		 */
		List<String> incNumList = new ArrayList<String>();
		for(EforcesIncmentProfit  incmentProfitItem : incprofit) {
			if(incmentProfitItem != null && incmentProfitItem.getIncid() != null ) {
				incNumList.add(incmentProfitItem.getIncid().trim());
			}
		}
		if(incNumList.size() > 0 ) {
			List<EforcesIncment> list = incmentDao.selectIncmentByNumber(incNumList);
			// 获取数据
			Map<String , EforcesIncment> incMap = new HashMap<String , EforcesIncment>();
			if(list != null && list.size() > 0 ) {
				for(int i = 0 ; i < list.size() ; i++) {
					EforcesIncment  incment = list.get(i);
					if(incment != null ) {
						incMap.put(incment.getNumber(), incment);
					}
				}
			}
			if(incMap.size() > 0 ) {
				for(EforcesIncmentProfit  item : incprofit) {
					if(item != null ) {
						String incNum = item.getIncid();
						if(incNum != null && incMap.containsKey(incNum.trim())) {
							EforcesIncment incment = incMap.get(incNum.trim());
							if(incment != null) {
								if(incment.getName() != null) {
									item.setIncname(incment.getName().trim());
								}
								if(incment.getLagearea() != null ) {
									item.setBigzonename(incment.getLagearea().trim());
								}
							}
						}
					}
				}
			}
		}
		/*
		 * 将网点信息更新到分润表中
		 * （这些信息为了查询方便故意让他重复的）。
		 */
		return incmentProfitDao.insertList(incprofit);
	}

	@Override
	public PageInfo<Map<String, Object>> getIncmentProfitByPage(int page, int pageSize, String parentId, String incName) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page,pageSize);
		List<Map<String,Object>>  list = null;
		if(StringUtils.isEmpty(parentId)) {
			 list = incmentProfitDao.getIncmentProfitByIncName(incName);
		}else {
			list = incmentProfitDao.getIncmentProfitByParam(parentId, incName);
		}
		
		final  PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	
	

}
