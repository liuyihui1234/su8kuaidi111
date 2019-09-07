package org.kuaidi.dao;

import org.kuaidi.bean.domain.EforcesUsersgroup;

import java.util.List;

public interface EforcesUsersgroupMapper {

    List<EforcesUsersgroup> selectAllIGroup();

    int deleteByPrimaryKey(EforcesUsersgroup id);

//    int deleteByUserId(Integer userid);

    int insert(EforcesUsersgroup record);

    int insertSelective(EforcesUsersgroup record);

    EforcesUsersgroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EforcesUsersgroup record);

    int updateByPrimaryKey(EforcesUsersgroup record);


}