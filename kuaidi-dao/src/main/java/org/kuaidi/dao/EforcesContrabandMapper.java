package org.kuaidi.dao;

import java.util.List;

import org.kuaidi.bean.domain.EforcesContraband;

public interface EforcesContrabandMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesContraband record);

    int insertSelective(EforcesContraband record);

    EforcesContraband selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EforcesContraband record);

    int updateByPrimaryKey(EforcesContraband record);
    
    List<EforcesContraband> selectByName(String name);
}