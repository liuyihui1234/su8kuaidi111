package org.kuaidi.dao;

import org.apache.ibatis.annotations.Param;
import org.kuaidi.bean.domain.EforcesAppointment;

import java.util.List;

public interface EforcesAppointmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesAppointment record);

    /**
     * 用户新建预约订单
     * @param record
     * @return
     */
    int insertSelective(EforcesAppointment record);

    EforcesAppointment selectByPrimaryKey(Integer id);

    /**
     * 修改预约订单信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(EforcesAppointment record);

    int updateByPrimaryKey(EforcesAppointment record);

    /**
     * 根据该快递员所负责的范围的recipientid来查找范围内的预约单
     * @param recipient
     * @return
     */
    List selectAppointmentByRecipient(List<String> causeIds);

    /**
     * 根据快递员所属的街道 查找对应的预约单
     * g
     * @param fromareastreet
     * @return
     */
    List selectAppointmentByStreet(String fromareastreet);


    /**
     * 快递员接单时，根据订单id修改订单状态为已接单（status为1）
     * @param id
     * @return
     */
    int updateStatus1ById(@Param("id") Integer id, @Param("acceptid") String acceptid, @Param("acceptname") String acceptname);

    /**
     * 快递员收件时，根据订单id修改订单状态为已收件（status为2）
     * @param id
     * @return
     */
    int updateStatus2ById(Integer id);

    /**
     * 根据快递员的员工号查找状态为待收件的快递（Status为1）
     * @return
     */
   List<EforcesAppointment> selsctStatus1ByAcceptid(String acceptid);


    /**
     * 预约单管理
     * @return
     */
   List<EforcesAppointment> getlistAllMsg(EforcesAppointment Eforces);

    /**
     * 删除预约单管理
     * @param id
     * @return
     */
    Integer deleteAppointment(Integer[] id);
}