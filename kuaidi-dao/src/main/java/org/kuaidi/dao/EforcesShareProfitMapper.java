package org.kuaidi.dao;

import org.kuaidi.bean.domain.EforcesShareProfit;
import java.util.List;

public interface EforcesShareProfitMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesShareProfit record);

    int insertSelective(EforcesShareProfit record);

    EforcesShareProfit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EforcesShareProfit record);

    int updateByPrimaryKey(EforcesShareProfit record);
    
    List<EforcesShareProfit>getAllShareProfit();
    
}