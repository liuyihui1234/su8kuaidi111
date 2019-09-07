package org.kuaidi.dao;

import org.kuaidi.bean.domain.EforcesAreaStreet;

public interface EforcesAreaStreetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesAreaStreet record);

    int insertSelective(EforcesAreaStreet record);

    EforcesAreaStreet selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EforcesAreaStreet record);

    int updateByPrimaryKeyWithBLOBs(EforcesAreaStreet record);

    int updateByPrimaryKey(EforcesAreaStreet record);
}