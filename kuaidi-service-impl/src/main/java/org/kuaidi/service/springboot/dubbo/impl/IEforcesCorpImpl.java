package org.kuaidi.service.springboot.dubbo.impl;

import java.util.List;

import org.kuaidi.bean.domain.EforcesCorp;
import org.kuaidi.dao.EforcesCorpMapper;
import org.kuaidi.iservice.IEforcesCorp;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;

@Service(version = "1.0.0")
public class IEforcesCorpImpl implements IEforcesCorp {
	
	@Autowired
	private EforcesCorpMapper corpDao;

	@Override
	public List<EforcesCorp> getAllEforcesCorp() {
		// TODO Auto-generated method stub
		return corpDao.selectAllRecord();
	}

	@Override
	public EforcesCorp getEforcesCorpById(Integer id) {
		// TODO Auto-generated method stub
		return corpDao.selectByPrimaryKey(id);
	}
	
}
