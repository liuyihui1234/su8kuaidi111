package org.kuaidi.service.springboot.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

import org.kuaidi.bean.domain.EforcesCusinformation;
import org.kuaidi.dao.EforcesCusinformationMapper;
import org.kuaidi.iservice.IEforcesCusinformationService;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "1.0.0")
public class EforcesCusinformationServiceImpl implements IEforcesCusinformationService {

    @Autowired
    EforcesCusinformationMapper  mationMapper;

	@Override
	public PageInfo<EforcesCusinformation> getAll(Integer page, Integer size) {
		PageHelper.startPage(page,size);
		List<EforcesCusinformation> sentScan = mationMapper.selectAll();
		final  PageInfo<EforcesCusinformation> pageInfo = new PageInfo<>(sentScan);
		return pageInfo;
	}

	@Override
	public EforcesCusinformation getById(Integer id) {
		
		return mationMapper.selectByPrimaryKey(id);
	}

	@Override
	public void deleteById(Integer[] id) {
		mationMapper.updateIsDeleteById(id);
	}

	@Override
	public void updateById(EforcesCusinformation record) {
		mationMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public void addRecord(EforcesCusinformation record) {
		mationMapper.insertSelective(record);
	}
}
