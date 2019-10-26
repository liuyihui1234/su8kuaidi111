package org.kuaidi.iservice;


import org.kuaidi.bean.domain.EforcesUserAddress;

import com.github.pagehelper.PageInfo;

public interface IEforcesUserAddressService {
	
	Integer insertUserAddress(EforcesUserAddress address);
	
	PageInfo<EforcesUserAddress>  findByNameOrPhone(Integer pageNum, Integer pageSize , 
			Integer userId , String param);
	
	Integer delUserAddress(Integer id);
	
	EforcesUserAddress findById(Integer id);
	
	Integer updateUserAddress(EforcesUserAddress address);

	
}
