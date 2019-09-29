package org.kuaidi.service.springboot.dubbo.impl;

import java.util.List;

import org.kuaidi.bean.domain.EforcesRemovingBagScan;
import org.kuaidi.dao.EforcesRemovingBagScanMapper;
import org.kuaidi.iservice.IEforcesRemovingBagScanService;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service(version = "1.0.0")
public class EforcesRemovingBagScanServiceImpl implements IEforcesRemovingBagScanService {

	@Autowired
	EforcesRemovingBagScanMapper scanDao;

	public PageInfo<EforcesRemovingBagScan> getAll(Integer page, Integer size, Integer incid) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, size);
		List<EforcesRemovingBagScan> sentScan = scanDao.selectAll(incid);
		final PageInfo<EforcesRemovingBagScan> pageInfo = new PageInfo<>(sentScan);
		return pageInfo;
	}

	@Override 
	public EforcesRemovingBagScan getById(Integer id) { 
		return scanDao.selectByPrimaryKey(id); 
	
	}

	@Override 
	public void addWeighingScan(EforcesRemovingBagScan record) {
		scanDao.insertSelective(record);
		
	}

	@Override 
	public int deleteById(List<Integer> id) {
		return scanDao.updateIsDeleteById(id);
		
	}

	@Override
	public void setById(EforcesRemovingBagScan record) {
		scanDao.updateByPrimaryKeySelective(record);
		
	}

	@Override
	public Integer addRecordList(List<EforcesRemovingBagScan> baggingScan) {
		// TODO Auto-generated method stub
		return scanDao.insertBatch(baggingScan);
	}



}
