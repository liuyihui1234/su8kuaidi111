package org.kuaidi.dao;

import java.util.List;

import org.kuaidi.bean.domain.EforcesCustomerSign;

public interface EforcesCustomerSignMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesCustomerSign record);

    /**
     * 动态添加一条签收记录
     * g
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
     * @param operatorid
     * @return
     */
    List<EforcesCustomerSign> selectByOperatorId(String operatorid);

    List<EforcesCustomerSign> getAllSign(Integer incid);

}