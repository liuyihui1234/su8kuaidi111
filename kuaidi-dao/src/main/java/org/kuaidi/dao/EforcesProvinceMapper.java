package org.kuaidi.dao;

import java.util.List;

import org.kuaidi.bean.domain.EforcesProvince;

public interface EforcesProvinceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesProvince record);

    int insertSelective(EforcesProvince record);

    EforcesProvince selectByPrimaryKey(Integer id);
    
    List<EforcesProvince>selectAll();

    int updateByPrimaryKeySelective(EforcesProvince record);

    int updateByPrimaryKey(EforcesProvince record);
}