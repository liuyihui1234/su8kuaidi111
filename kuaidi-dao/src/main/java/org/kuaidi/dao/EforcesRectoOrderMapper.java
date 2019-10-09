package org.kuaidi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.kuaidi.bean.domain.EforcesRectoOrder;

public interface EforcesRectoOrderMapper {
	
    int deleteByPrimaryKey(Integer id);
    
    Integer updateIsDeleteById(List<Integer> id);

    int insert(EforcesRectoOrder record);

    /**
     * 添加扫描订单 默认扫描时间是now（）
     * @param record
     * @return
     */
    int insertSelective(EforcesRectoOrder record);

    EforcesRectoOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EforcesRectoOrder record);

    int updateByPrimaryKey(EforcesRectoOrder record);
    
    int selectCountByUserNum(String userNum);
    /**
               * 查询全部扫描订单
     * @return
     */
    List<EforcesRectoOrder> selectAll(Integer paramter);
    
    List <EforcesRectoOrder> getRectoOrderByNumber(@Param("incNum") String incNum , @Param("Number")String Number);
	
    
}