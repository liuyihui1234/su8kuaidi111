package org.kuaidi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.kuaidi.bean.domain.EforcesCustomerSign;
import org.omg.PortableInterceptor.INACTIVE;

public interface EforcesCustomerSignMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesCustomerSign record);

    /**
     * 动态添加一条签收记录
     * g
     *
     * @param record
     * @return
     */
    int insertSelective(EforcesCustomerSign record);

    EforcesCustomerSign selectByPrimaryKey(Integer id);

    EforcesCustomerSign selectByNumber(String number);

    int updateByPrimaryKeySelective(EforcesCustomerSign record);

    int updateByPrimaryKey(EforcesCustomerSign record);

    List<EforcesCustomerSign> selectByDeliveryUserId(String deliveryUserId);

    /**
     * 根据快递员员工号查询该员工已送达的订单
     * g
     *
     * @param operatorid
     * @return
     */
    List<EforcesCustomerSign> selectByOperatorId(String operatorid);

    List<EforcesCustomerSign> getAllSign(String incid);

    int DeleteById(List<Integer> list);
    
    List<Map<String, Object>> webSitCustomSignByParam(@Param("incNum")String incNum, @Param("province")String province, @Param("city")String city,
    		@Param("area") String area, @Param("time") String time);

}