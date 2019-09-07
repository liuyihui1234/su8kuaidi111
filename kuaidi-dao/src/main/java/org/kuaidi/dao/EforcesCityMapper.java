package org.kuaidi.dao;

import java.util.List;

import org.kuaidi.bean.domain.EforcesCity;

public interface EforcesCityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesCity record);

    int insertSelective(EforcesCity record);

    EforcesCity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EforcesCity record);

    int updateByPrimaryKey(EforcesCity record);
    
    List<EforcesCity> getCityByCode(String code);
}