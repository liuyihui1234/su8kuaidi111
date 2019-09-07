package org.kuaidi.dao;

import org.apache.ibatis.annotations.Param;
import org.kuaidi.bean.domain.EforcesIncDefaultPrice;

import java.util.List;

public interface EforcesIncDefaultPriceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesIncDefaultPrice record);

    int insertSelective(EforcesIncDefaultPrice record);


    EforcesIncDefaultPrice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EforcesIncDefaultPrice record);

    int updateByPrimaryKey(EforcesIncDefaultPrice record);

    /**
     * 查询所有信息
     * @return
     */
    List<EforcesIncDefaultPrice> getList(Integer id);

    /**
     * 修改
     * @return
     */
    int updateIncDefalutPrice(@Param("joinprice") String joinprice,@Param("remark")String remark,@Param("checkRemark")String checkRemark,@Param("id")int id);
}