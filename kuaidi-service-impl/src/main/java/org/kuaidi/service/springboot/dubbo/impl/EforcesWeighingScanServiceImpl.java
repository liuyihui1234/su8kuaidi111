package org.kuaidi.service.springboot.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.kuaidi.bean.domain.EforcesWeighingScan;
import org.kuaidi.dao.EforcesWeighingScanMapper;
import org.kuaidi.iservice.IEforcesWeighingScanService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "1.0.0")
public class EforcesWeighingScanServiceImpl implements IEforcesWeighingScanService {

	@Autowired
	EforcesWeighingScanMapper scanDao;

	public PageInfo<EforcesWeighingScan> getAll(Integer page, Integer size,Integer incid) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page,size);
		List<EforcesWeighingScan> sentScan = scanDao.selectAll(incid);
		final  PageInfo<EforcesWeighingScan> pageInfo = new PageInfo<>(sentScan);
		return pageInfo;
	}

	@Override
	public EforcesWeighingScan getById(Integer id) {
		return scanDao.selectByPrimaryKey(id);
	}

	@Override
	public void addWeighingScan(EforcesWeighingScan record) {
		scanDao.insertSelective(record);
	}

	@Override
	public Integer deleteById(List<Integer> list) {
		return scanDao.updateIsDeleteById(list);
	}

	@Override
	public void setById(EforcesWeighingScan record) {
		scanDao.updateByPrimaryKeySelective(record);
	}


}
