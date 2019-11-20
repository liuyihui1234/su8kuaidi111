package org.kuaidi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.kuaidi.bean.domain.EforcesIncmentProfit;

public interface EforcesIncmentProfitMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesIncmentProfit record);

    int insertSelective(EforcesIncmentProfit record);
    
    int insertList(List<EforcesIncmentProfit> list);

    EforcesIncmentProfit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EforcesIncmentProfit record);

    int updateByPrimaryKey(EforcesIncmentProfit record);
    
    List<Map<String,Object>> getIncmentProfitByParam(@Param("parentIncNumber") String parentIncNumber 
    		,@Param("incName") String incName);
    
    List<Map<String,Object>> getIncmentProfitByIncName(@Param("incName") String incName);
}