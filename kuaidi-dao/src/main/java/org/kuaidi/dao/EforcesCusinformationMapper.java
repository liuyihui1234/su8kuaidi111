package org.kuaidi.dao;

import java.util.List;

import org.kuaidi.bean.domain.EforcesCusinformation;

public interface EforcesCusinformationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesCusinformation record);

    int insertSelective(EforcesCusinformation record);

    EforcesCusinformation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EforcesCusinformation record);

    int updateByPrimaryKeyWithBLOBs(EforcesCusinformation record);

    int updateByPrimaryKey(EforcesCusinformation record);
    
    List<EforcesCusinformation> selectAll();
    
    int updateIsDeleteById(Integer[] id);
}