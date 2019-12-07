package org.kuaidi.iservice;

import java.util.List;
import java.util.Map;

import org.kuaidi.bean.domain.EforcesCustomerSign;
import org.kuaidi.bean.domain.EforcesLogisticStracking;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IEforcesCustomerSignService {
    /**
     * 根据快递员员工号查询该员工已送达的订单
     * g
     *
     * @param operatorid
     * @return
     */
    List<EforcesCustomerSign> selectByOperatorId(String operatorid);

    /**
     * 动态添加一条签收记录
     * g
     *
     * @param customerSign
     * @return
     */
    int insertCustomerSign(EforcesCustomerSign customerSign, EforcesLogisticStracking logistic );

    /**
     * 根据订单号查询该签收订单的详情
     * g
     *
     * @param number
     * @return
     */
    EforcesCustomerSign selectByNumber(String number);

    /**
     * 根据参数派件用户id进行查询
     *
     * @param deliveryUserId
     * @return
     */
    PageInfo<EforcesCustomerSign> getByDeliveryUserId(Integer pageNum, Integer pageSize, String deliveryUserId);

    /**
     * 根据incid获取所有签收信息
     * @param incid
     * @return
     */
    PageInfo<EforcesCustomerSign> getAllSign(Integer page,Integer limit, String incid);

    /**
     * 根据id查询签收信息
     * @param id
     * @return
     */

    EforcesCustomerSign selectByPrimaryKey(Integer id);

    int insertCustomer(EforcesCustomerSign record);

    int updateByPrimaryKeySelective(EforcesCustomerSign record);

    int DeleteById(List<Integer> id);
    
    PageInfo<Map<String,Object>>  webSitCustomSignByPage(Integer pageNum , Integer pageSize ,
    		String  incNum , String province , String city , String area, String time);
    
    List<Map<String,Object>>  webSitCustomSignByParam(String  incNum , String province , 
    		String city , String area, String time);
    
    /*
             * 业务员签收监控
     */
    List<Map<String,Object>> customSignByUser(String incNum ,String province , 
    		String city , String area, Integer userId , String time);
}
