package org.kuaidi.dao;

import org.kuaidi.bean.domain.EforcesUsersgrouprele;

import java.util.List;

public interface EforcesUsersgroupreleMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByUserId(Integer id);


    int insert(EforcesUsersgrouprele record);

    int insertForeach(List<EforcesUsersgrouprele> list);

    int insertSelective(EforcesUsersgrouprele record);

    EforcesUsersgrouprele selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EforcesUsersgrouprele record);

    int updateByPrimaryKey(EforcesUsersgrouprele record);
}