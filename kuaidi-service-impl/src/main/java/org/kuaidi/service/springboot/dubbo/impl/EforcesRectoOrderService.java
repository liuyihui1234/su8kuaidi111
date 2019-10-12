package org.kuaidi.service.springboot.dubbo.impl;

import java.util.List;

import org.kuaidi.bean.domain.EforcesLogisticStracking;
import org.kuaidi.bean.domain.EforcesOrder;
import org.kuaidi.bean.domain.EforcesRectoOrder;
import org.kuaidi.dao.EforcesLogisticStrackingMapper;
import org.kuaidi.dao.EforcesOrderMapper;
import org.kuaidi.dao.EforcesRectoOrderMapper;
import org.kuaidi.iservice.IEforcesRectoOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service(version = "1.0.0",interfaceClass=IEforcesRectoOrderService.class,timeout=12000)
public class EforcesRectoOrderService implements IEforcesRectoOrderService {

	@Autowired
	private EforcesRectoOrderMapper  rectoOrderDao ; 
	
	@Autowired
	private EforcesOrderMapper orderDao; 
	
	@Autowired
	private EforcesLogisticStrackingMapper  logisticDao;
	
	@Override
	public int getUserRectoOrderCount(String userNum) {
		// TODO Auto-generated method stub
		return rectoOrderDao.selectCountByUserNum(userNum);
	}

	@Override
	public PageInfo<EforcesRectoOrder> getAll(Integer page,Integer size,Integer incid) {
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

	//添加收件交单信息。
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void addRectoOrder(EforcesRectoOrder record, EforcesLogisticStracking  logisticStracking) {
		/*
		 * 修改订单状态
		 * */
		String billsNumber = record.getNumber();
		if(billsNumber != null) {
			EforcesOrder orderInfo = orderDao.getOrderMsg(billsNumber);
			orderInfo.setStatus(1);
			orderDao.updateByPrimaryKeySelective(orderInfo);
		}
		// 插入交单信息
		rectoOrderDao.insertSelective(record);
		// 添加物流信息
		logisticDao.insert(logisticStracking);
	}

	@Override
	public void setById(EforcesRectoOrder record) {
		rectoOrderDao.updateByPrimaryKeySelective(record);
		
	}

	@Override
	public List<EforcesRectoOrder> getRectoOrderByNumber(String incNum, String Number) {
		// TODO Auto-generated method stub
		return rectoOrderDao.getRectoOrderByNumber(incNum, Number);
	}

}
