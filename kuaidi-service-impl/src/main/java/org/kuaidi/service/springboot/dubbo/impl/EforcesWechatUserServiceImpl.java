package org.kuaidi.service.springboot.dubbo.impl;

import org.kuaidi.bean.domain.EforcesWechatUser;
import org.kuaidi.dao.EforcesWechatUserMapper;
import org.kuaidi.iservice.EforcesWechatUserService;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;

@Service(version = "1.0.0")
public class EforcesWechatUserServiceImpl implements EforcesWechatUserService{
	
	@Autowired
	EforcesWechatUserMapper eforcesWechatUserDao;
	
	@Override
	public int deleteByPrimaryKey(String openid) {
		// TODO Auto-generated method stub
		return eforcesWechatUserDao.deleteByPrimaryKey(openid);
	}

	@Override
	public int insert(EforcesWechatUser record) {
		// TODO Auto-generated method stub
		return eforcesWechatUserDao.insert(record);
	}

	@Override
	public int insertSelective(EforcesWechatUser record) {
		// TODO Auto-generated method stub
		return eforcesWechatUserDao.insertSelective(record);
	}

	@Override
	public EforcesWechatUser selectByPrimaryKey(String openid) {
		// TODO Auto-generated method stub
		return eforcesWechatUserDao.selectByPrimaryKey(openid);
	}

	@Override
	public int updateByPrimaryKeySelective(EforcesWechatUser record) {
		// TODO Auto-generated method stub
		return eforcesWechatUserDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(EforcesWechatUser record) {
		// TODO Auto-generated method stub
		return eforcesWechatUserDao.updateByPrimaryKey(record);
	}

}
