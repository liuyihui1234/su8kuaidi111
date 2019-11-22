package org.kuaidi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.kuaidi.bean.domain.EforcesPrice;

public interface EforcesPriceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesPrice record);

    EforcesPrice selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(EforcesPrice record);
    
    /*
             * 根据发出的省份和接收的省份查询价格。
     */
    List<EforcesPrice>  selectByProvinceCodes(@Param("fromProvinceId") String fromProvinceId ,
    		@Param("toProvinceId") String toProvinceId, @Param("status") String status );

    
    List<Map<String,Object>>  selectByParam(@Param("fromProvinceId") String fromProvinceId ,
    		@Param("toProvinceId") String toProvinceId, @Param("status") String status );

    /**
     * 分页查询地区价格表
     * @return
     */
    List<EforcesPrice> getByPrice();

    /**
     * 删除地区价格信息
     * @param id
     * @return
     */
    Integer removeByPrice(Integer[] id);

    /**
     * 修改地区价格信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(EforcesPrice record);

    /**
     * 添加地区价格信息
     * @param record
     * @return
     */
    int insertSelective(EforcesPrice record);
}