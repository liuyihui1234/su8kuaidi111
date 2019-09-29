package org.kuaidi.iservice;

import java.util.List;
import org.kuaidi.bean.domain.EforcesCustomerAddress;

public interface IEforcesAddressService {

	List<EforcesCustomerAddress> getAllAddressByOpenId(String openId);
	
	Integer saveAddress(EforcesCustomerAddress  address);
	
	Integer deleteAddress(Integer id);

}
