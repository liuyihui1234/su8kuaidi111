package org.kuaidi.dao;

import java.util.List;

import org.kuaidi.bean.domain.EforcesGroupModule;

public interface EforcesGroupModuleMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesGroupModule record);

    int insertSelective(EforcesGroupModule record);

    EforcesGroupModule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EforcesGroupModule record);

    int updateByPrimaryKey(EforcesGroupModule record);
    
    List<EforcesGroupModule> selectByGroupId(Integer groupId);
    
    int deleteByGroupId(Integer groupId);
    
    int insertList(List<EforcesGroupModule> record);
    
    int delByIds(List<Integer>ids);
    
}