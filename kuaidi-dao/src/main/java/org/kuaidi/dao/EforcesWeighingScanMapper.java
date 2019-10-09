package org.kuaidi.dao;

import java.util.List;

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
    
    List<EforcesWeighingScan> selectAll(Integer paramter);
    
    List<EforcesWeighingScan> getWeightScanByParam(@Param("incNum")String incNum, @Param("number") String number);
    
}