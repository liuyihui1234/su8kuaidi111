package org.kuaidi.iservice;

import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesAppointment;

import java.util.List;

public interface AppointmentService {

    /**
     * 根据主键查找对应的appointment
     * @param id
     * @return
     */
    EforcesAppointment selectAppointmentByPrimaryKey(Integer id);

    /**
     * 用户新建预约订单
     * @param eforcesAppointment
     * @return
     */
    int insertAppointment(EforcesAppointment eforcesAppointment);

    /**
     * 根据该快递员所负责的范围的recipientid来查找范围内的预约单
     * @param recipient
     * @return
     */
    PageInfo<EforcesAppointment> selectAppointmentByRecipient(Integer pageNum, Integer pageSize, List<String> recipient);

    /**
     * 根据快递员所属的街道 查找对应的预约单
     * g
     * @param pageNum
     * @param pageSize
     * @param fromareastreet
     * @return
     */
    PageInfo<EforcesAppointment> selectAppointmentByStreet(Integer pageNum, Integer pageSize,String fromareastreet);
    /**
     * 快递员接单时，根据订单id修改订单状态为已接单（status为1）
     * @param id
     * @return
     */
    int updateStrtus1ById(Integer id,String acceptid,String acceptname);

    /**
     * 快递员收件时，根据订单id修改订单状态为已收件（status为2）
     * @param id
     * @return
     */
    int updateStrtus2ById(Integer id);

    /**
     * 根据快递员的编号查询该快递员的待收件订单
     * @param acceptid
     * @return
     */
    PageInfo<EforcesAppointment> selsctStatus1ByAcceptid(Integer pageNum, Integer pageSize, String acceptid);

    /**
     * 预约单管理
     * @param page
     * @param size
     * @return
     */
    PageInfo<EforcesAppointment> getlistAllMsg(Integer page,Integer size);

    /**
     * 删除预约单管理
     * @param id
     * @return
     */
    Integer deleteAppointment(Integer[] id);

}
