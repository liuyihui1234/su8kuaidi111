package org.kuaidi.service.springboot.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

import org.kuaidi.bean.domain.EforcesBaggingScan;
import org.kuaidi.dao.EforcesBaggingScanMapper;
import org.kuaidi.iservice.IEforcesBiggingScanService;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "1.0.0")
public class EforcesBaggingScanServiceImpl implements IEforcesBiggingScanService {

    @Autowired
    EforcesBaggingScanMapper scanMapper;

	@Override
	public PageInfo<EforcesBaggingScan> getAll(Integer page, Integer size,Integer incid) {
		PageHelper.startPage(page,size);
		List<EforcesBaggingScan> sentScan = scanMapper.selectAll(incid);
		final  PageInfo<EforcesBaggingScan> pageInfo = new PageInfo<>(sentScan);
		return pageInfo;
	}

	@Override
	public EforcesBaggingScan getById(Integer id) {
		
		return scanMapper.selectByPrimaryKey(id);
	}

	@Override
	public void deleteById(Integer[] id) {
		scanMapper.updateById(id);
	}

	@Override
	public void updateById(EforcesBaggingScan record) {
		scanMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public void addRecord(EforcesBaggingScan record) {
		scanMapper.insertSelective(record);
	}

	@Override
	public Integer addRecordList(List<EforcesBaggingScan> baggingScan) {
		// TODO Auto-generated method stub
		return scanMapper.insertBatch(baggingScan);
	}

	@Override
	public List<EforcesBaggingScan> getBaggingScanByBagNum(String bagNumber) {
		// TODO Auto-generated method stub
		return scanMapper.selectByBagNumber(bagNumber);
	}
}
