package org.kuaidi.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.kuaidi.bean.domain.EforcesWeighingScan;

public interface EforcesWeighingScanMapper {
    Integer updateIsDeleteById(List<Integer> ids);

    int insert(EforcesWeighingScan record);

    int insertSelective(EforcesWeighingScan record);

    EforcesWeighingScan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EforcesWeighingScan record);

    int updateByPrimaryKeyWithBLOBs(EforcesWeighingScan record);

    int updateByPrimaryKey(EforcesWeighingScan record);
    
    List<EforcesWeighingScan> selectAll(String paramter);
    
    List<EforcesWeighingScan> getWeightScanByParam(@Param("incNum")String incNum, @Param("number") String number);

	List<Map<String , Object>> getWeightStatisticByParam(@Param("incNum") String incNum, @Param("billsNumList") List<String> billsNumList, 
			@Param("sendCityList") List<String> sendCityList,@Param("startTime") String startTime, @Param("endTime") String endTime);
    
}