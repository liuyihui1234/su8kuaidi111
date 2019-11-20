package org.kuaidi.service.springboot.dubbo.impl;

import java.util.List;

import org.kuaidi.bean.domain.EforcesContraband;
import org.kuaidi.bean.domain.EforcesDefaultBankInfo;
import org.kuaidi.bean.domain.EforcesDictionary;
import org.kuaidi.bean.domain.EforcesIncDefaultPrice;
import org.kuaidi.dao.EforcesContrabandMapper;
import org.kuaidi.dao.EforcesDefaultBankInfoMapper;
import org.kuaidi.dao.EforcesDictionaryMapper;
import org.kuaidi.dao.EforcesIncDefaultPriceMapper;
import org.kuaidi.iservice.IDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;

//注册为 Dubbo 服务
@Service(version = "1.0.0")
public class DictionaryServiceImpl implements IDictionaryService {
	
	@Autowired
	private EforcesIncDefaultPriceMapper  defaultPriceDao;
	
	@Autowired
	private EforcesDefaultBankInfoMapper  defaultBankInfo;
	
	@Autowired
	private EforcesContrabandMapper contrabandDao; 
	
	@Autowired
	private EforcesDictionaryMapper  dictionaryDao;
	
	

	public EforcesIncDefaultPrice getDefaultPriceById(Integer priceId) {
		// TODO Auto-generated method stub
		return defaultPriceDao.selectByPrimaryKey(priceId);
	}

	@Override
	public List<EforcesDefaultBankInfo> getAllBankInfo() {
		// TODO Auto-generated method stub
		return defaultBankInfo.selectAllBankInfo();
	}

	@Override
	public List<EforcesContraband> getContrabandByName(String name) {
		// TODO Auto-generated method stub
		return contrabandDao.selectByName(name);
	}

	@Override
	public EforcesDictionary getDictionaryById(Integer id) {
		// TODO Auto-generated method stub
		return dictionaryDao.selectByPrimaryKey(id);
	}

}
