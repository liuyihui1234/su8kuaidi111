package org.kuaidi.dao;

import java.util.List;

import org.kuaidi.bean.domain.EforcesBaggingScan;

public interface EforcesBaggingScanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesBaggingScan record);

    int insertSelective(EforcesBaggingScan record);

    EforcesBaggingScan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EforcesBaggingScan record);

    int updateByPrimaryKeyWithBLOBs(EforcesBaggingScan record);

    int updateByPrimaryKey(EforcesBaggingScan record);
    
    List<EforcesBaggingScan> selectAll(Integer paramter);
    
    int updateById(Integer[] id);
    
    int insertBatch(List<EforcesBaggingScan> list);
    
    List<EforcesBaggingScan> selectByBagNumber(String baggingid); 
    
}