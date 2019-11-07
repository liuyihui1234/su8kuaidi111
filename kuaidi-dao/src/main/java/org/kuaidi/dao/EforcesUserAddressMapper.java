package org.kuaidi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.kuaidi.bean.domain.EforcesUserAddress;

public interface EforcesUserAddressMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesUserAddress record);

    int insertSelective(EforcesUserAddress record);

    EforcesUserAddress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EforcesUserAddress record);

    int updateByPrimaryKey(EforcesUserAddress record);

	List<EforcesUserAddress> findByNameOrPhone(@Param("userId")  Integer userId , @Param("param")  String param, @Param("status") Integer status);
	
}