package org.kuaidi.dao;

import org.apache.ibatis.annotations.Param;
import org.kuaidi.bean.domain.EforcesSubscribekuaidi100;

public interface EforcesSubscribekuaidi100Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesSubscribekuaidi100 record);

    int insertSelective(EforcesSubscribekuaidi100 record);

    EforcesSubscribekuaidi100 selectByPrimaryKey(Integer id);
    
    EforcesSubscribekuaidi100 selectByBillNumber(@Param("billNumber")String billNumber);

    int updateByPrimaryKeySelective(EforcesSubscribekuaidi100 record);

    int updateByPrimaryKey(EforcesSubscribekuaidi100 record);
}