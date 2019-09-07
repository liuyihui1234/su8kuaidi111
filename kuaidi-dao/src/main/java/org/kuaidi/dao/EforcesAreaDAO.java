package org.kuaidi.dao;

import java.util.List;

import org.kuaidi.bean.domain.EforcesArea;

public interface EforcesAreaDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesArea record);

    int insertSelective(EforcesArea record);

    EforcesArea selectByPrimaryKey(Integer id);
    
    List<EforcesArea> getAreaByCityCode(String cityCode);

    int updateByPrimaryKeySelective(EforcesArea record);

    int updateByPrimaryKey(EforcesArea record);
}