package org.kuaidi.service.springboot.dubbo.impl;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesLogisticStracking;
import org.kuaidi.dao.EforcesLogisticStrackingMapper;
import org.kuaidi.iservice.IEforceslogisticstrackingService;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;

@Service(version = "1.0.0")
public class EforceslogisticstrackingServiceImpl implements IEforceslogisticstrackingService {

	@Autowired
	EforcesLogisticStrackingMapper strackingMapper;
	@Override
	public List<EforcesLogisticStracking> getByBillsNumber(String billsNumber) {			
			return strackingMapper.selectByBillsNumber(billsNumber);
		
	}

	@Override
	public int insertLogisticSelective(EforcesLogisticStracking record) {
		return strackingMapper.insertSelective(record);
	}

	@Override
	public int insertLogList(List<EforcesLogisticStracking> list) {
		return strackingMapper.insertSelectiveList(list);
	}

	/**
	 * 物流跟踪记录
	 * @param page
	 * @param size
	 * @return
	 */
	@Override
	public PageInfo<EforcesLogisticStracking> getListStracking(Integer page, Integer size,String billsnumber) {
		PageHelper.startPage(page,size);
		List<EforcesLogisticStracking> list = strackingMapper.getListStracking(billsnumber);
		final PageInfo<EforcesLogisticStracking> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	/**
	 * 删除物流跟踪记录
	 * @param id
	 * @return
	 */
	@Override
	public Integer deleteLogisticStraking(Integer[] id) {
		return strackingMapper.deleteLogisticStraking(id);
	}

	/**
	 * 根据多个运单编号查询
	 * @param billsNumber
	 * @return
	 */
	@Override
	public List<EforcesLogisticStracking> getListBillsNumber(String[] billsNumber) {
		return strackingMapper.getListBillsNumber(billsNumber);
	}

	@Override
	public Date selectMaxTime(String billsNumber) {
		return strackingMapper.selectMaxTime(billsNumber);
	}

	@Override
	public String selectMaxMark(String billsNumber) {
		return strackingMapper.selectMaxMark(billsNumber);
	}

	@Override
	public List<EforcesLogisticStracking> getStrackingMaxTimeByNumber(List<String> billsNumber) {
		// TODO Auto-generated method stub
		return strackingMapper.getListStrackingByNumber(billsNumber);
	}
}
