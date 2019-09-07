package org.kuaidi.dao;

import java.util.List;

import org.kuaidi.bean.domain.EforcesCorp;

public interface EforcesCorpMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesCorp record);

    int insertSelective(EforcesCorp record);

    EforcesCorp selectByPrimaryKey(Integer id);
    
    List<EforcesCorp>  selectAllRecord();

    int updateByPrimaryKeySelective(EforcesCorp record);

    int updateByPrimaryKey(EforcesCorp record);
}