package org.kuaidi.service.springboot.dubbo.impl;

import java.util.List;

import org.kuaidi.bean.domain.EforcesRectoOrder;
import org.kuaidi.dao.EforcesRectoOrderMapper;
import org.kuaidi.iservice.IEforcesRectoOrderService;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service(version = "1.0.0")
public class EforcesRectoOrderService implements IEforcesRectoOrderService {

	@Autowired
	private EforcesRectoOrderMapper  rectoOrderDao ; 
	
	@Override
	public int getUserRectoOrderCount(String userNum) {
		// TODO Auto-generated method stub
		return rectoOrderDao.selectCountByUserNum(userNum);
	}

	@Override
	public PageInfo<EforcesRectoOrder> getAll(Integer page,Integer size,String incid) {
		PageHelper.startPage(page,size);
		List<EforcesRectoOrder>list=rectoOrderDao.selectAll(incid);
		final  PageInfo<EforcesRectoOrder> pageInfo = new PageInfo<>(list);

		return pageInfo;
	}

	@Override
	public EforcesRectoOrder getById(Integer id) {
		return rectoOrderDao.selectByPrimaryKey(id);
	}

	@Override
	public Integer deleteById(List<Integer> id) {
		 return rectoOrderDao.updateIsDeleteById(id);
	}

	@Override
	public void addRectoOrder(EforcesRectoOrder record) {
		rectoOrderDao.insertSelective(record);
	}

	@Override
	public void setById(EforcesRectoOrder record) {
		rectoOrderDao.updateByPrimaryKeySelective(record);
		
	}

}
