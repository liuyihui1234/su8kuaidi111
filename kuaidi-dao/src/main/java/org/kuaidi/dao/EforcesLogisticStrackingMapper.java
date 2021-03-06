package org.kuaidi.dao;

import org.kuaidi.bean.domain.EforcesLogisticStracking;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface EforcesLogisticStrackingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesLogisticStracking record);

    List<EforcesLogisticStracking> selectByBillsNumber(String billsNumber);

    /**
     * 动态添加一条物流记录 operationtime   sql中默认为now()
     * @param record
     * @return
     */
    int insertSelective(EforcesLogisticStracking record);

    int insertSelectiveList(List<EforcesLogisticStracking> list);

    EforcesLogisticStracking selectByPrimaryKey(Integer id);

    Date selectMaxTime(String billsNumber);

    int updateByPrimaryKeySelective(EforcesLogisticStracking record);

    int updateByPrimaryKey(EforcesLogisticStracking record);
    
    String  selectMaxMark(String billsNumber);

    /**
     * 物流跟踪记录
     * @param
     * @return
     */
    List<EforcesLogisticStracking> getListStracking(String billsnumber);

    /**
     * 删除物流跟踪记录
     * @param id
     * @return
     */
    Integer deleteLogisticStraking(Integer[] id);

    /**
     * 根据多个运单编号查询
     * @param billsNumber
     * @return
     */
    List<EforcesLogisticStracking> getListBillsNumber(String[] billsNumber);
    
    /*
     * 查询订单信息
     */
    List<EforcesLogisticStracking>  getListStrackingByNumber(List<String> billsNumber);


}