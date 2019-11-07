package org.kuaidi.service.springboot.dubbo.impl;

import java.util.List;

import org.kuaidi.bean.domain.EforcesUserAddress;
import org.kuaidi.dao.EforcesUserAddressMapper;
import org.kuaidi.iservice.IEforcesUserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service(version = "1.0.0")
public class EforcesUserAddressServiceImpl implements IEforcesUserAddressService {

	@Autowired
	private EforcesUserAddressMapper  userAddressDao;
	
	@Override
	public Integer insertUserAddress(EforcesUserAddress address) {
		// TODO Auto-generated method stub
		return userAddressDao.insertSelective(address);
	}

	@Override
	public PageInfo<EforcesUserAddress> findByNameOrPhone(Integer pageNum, Integer pageSize ,Integer userId , String param,int status) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum,pageSize);
		List <EforcesUserAddress>  list = userAddressDao.findByNameOrPhone(userId, param,status);
		final PageInfo<EforcesUserAddress> info = new PageInfo<>(list);
		return info; 
	}

	@Override
	public Integer delUserAddress(Integer id) {
		// TODO Auto-generated method stub
		return userAddressDao.deleteByPrimaryKey(id);
	}

	@Override
	public EforcesUserAddress findById(Integer id) {
		// TODO Auto-generated method stub
		return userAddressDao.selectByPrimaryKey(id);
	}

	@Override
	public Integer updateUserAddress(EforcesUserAddress address) {
		// TODO Auto-generated method stub
		return userAddressDao.updateByPrimaryKeySelective(address);
	}

}
