package org.kuaidi.dao;

import org.kuaidi.bean.domain.EforcesOrderIdentity;

public interface EforcesOrderIdentityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesOrderIdentity record);

    int insertSelective(EforcesOrderIdentity record);

    EforcesOrderIdentity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EforcesOrderIdentity record);

    int updateByPrimaryKey(EforcesOrderIdentity record);
}