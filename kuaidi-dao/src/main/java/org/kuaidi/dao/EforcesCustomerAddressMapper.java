package org.kuaidi.dao;

import java.util.List;

import org.kuaidi.bean.domain.EforcesCustomerAddress;

public interface EforcesCustomerAddressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesCustomerAddress record);

    int insertSelective(EforcesCustomerAddress record);

    EforcesCustomerAddress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EforcesCustomerAddress record);

    int updateByPrimaryKey(EforcesCustomerAddress record);
    /*
               * 根据用户的openId 查询对应的地址
     */
    List<EforcesCustomerAddress>  selectByOpenId(String openId);
    
    int updateAddressByOpenId(String openId);
    
}