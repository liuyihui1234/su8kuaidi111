package org.kuaidi.dao;

import org.apache.ibatis.annotations.Param;
import org.kuaidi.bean.domain.EforcesShareProfit;
import java.util.List;
import java.util.Map;

public interface EforcesShareProfitMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesShareProfit record);

    int insertSelective(EforcesShareProfit record);

    EforcesShareProfit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EforcesShareProfit record);

    int updateByPrimaryKey(EforcesShareProfit record);
    
    List<EforcesShareProfit>getAllShareProfit();
    
    List<Map<String, Object>>getShareProfitByParam(@Param("fromProvince")String fromProvince,
    		@Param("toProvince") String toProvince ,@Param("status") Integer status);
    
    Integer deleteByIds(Integer [] id);
    
}