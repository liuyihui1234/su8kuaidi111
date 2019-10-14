package org.kuaidi.service.springboot.dubbo.impl;

import java.util.List;
import org.kuaidi.bean.domain.EforcesCustomerAddress;
import org.kuaidi.dao.EforcesCustomerAddressMapper;
import org.kuaidi.iservice.IEforcesAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;

@Service(version = "1.0.0",interfaceClass=IEforcesAddressService.class,timeout=12000)
public class EforcesAddressServiceImpl implements IEforcesAddressService {

	@Autowired
	EforcesCustomerAddressMapper  addressDao ; 
	
	@Override
	public List<EforcesCustomerAddress> getAllAddressByOpenId(String openId) {
		// TODO Auto-generated method stub
		return addressDao.selectByOpenId(openId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer saveAddress(EforcesCustomerAddress address) {
		// TODO Auto-generated method stub
		if(address == null || address.getOpenid() == null) {
			return 0 ; 
		}
		//是默认地址的时候。
		if(StringUtils.isEquals(address.getType(), "1")) {
			addressDao.updateAddressByOpenId(address.getOpenid().trim());
		}
		int rst = addressDao.insertSelective(address);
		return rst;
	}

	@Override
	public Integer deleteAddress(Integer id) {
		// TODO Auto-generated method stub
		return addressDao.deleteByPrimaryKey(id);
	}

	@Override
	public EforcesCustomerAddress selectByPrimaryKey(Integer id) {
		
		return addressDao.selectByPrimaryKey(id);
	}

}
