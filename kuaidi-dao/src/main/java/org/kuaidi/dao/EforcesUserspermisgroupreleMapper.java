package org.kuaidi.dao;

import org.kuaidi.bean.domain.EforcesUserspermisgrouprele;

public interface EforcesUserspermisgroupreleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesUserspermisgrouprele record);

    int insertSelective(EforcesUserspermisgrouprele record);

    EforcesUserspermisgrouprele selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EforcesUserspermisgrouprele record);

    int updateByPrimaryKey(EforcesUserspermisgrouprele record);
}