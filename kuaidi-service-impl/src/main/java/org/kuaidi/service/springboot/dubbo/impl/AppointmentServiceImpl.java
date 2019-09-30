package org.kuaidi.service.springboot.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesAppointment;
import org.kuaidi.dao.EforcesAppointmentMapper;
import org.kuaidi.iservice.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

//注册为 Dubbo 服务
@Service(version = "1.0.0")
public class AppointmentServiceImpl implements AppointmentService {
    
	@Autowired
    EforcesAppointmentMapper appointment;

    @Override
    public EforcesAppointment selectAppointmentByPrimaryKey(Integer id) {
        return appointment.selectByPrimaryKey(id);
    }

    @Override
    public int insertAppointment(EforcesAppointment eforcesAppointment) {
        return appointment.insertSelective(eforcesAppointment);
    }

    @Override
    public PageInfo<EforcesAppointment> selectAppointmentByRecipient(Integer pageNum, Integer pageSize, List<String> recipient) {
        PageHelper.startPage(pageNum,pageSize);
        List<EforcesAppointment> list = appointment.selectAppointmentByRecipient(recipient);
        final PageInfo<EforcesAppointment> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<EforcesAppointment> selectAppointmentByStreet(Integer pageNum, Integer pageSize, String fromareastreet) {
        PageHelper.startPage(pageNum,pageSize);
        List<EforcesAppointment> list = appointment.selectAppointmentByStreet(fromareastreet);
        final PageInfo<EforcesAppointment> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int updateStrtus1ById(Integer id,String acceptid,String acceptname) {
        return appointment.updateStatus1ById(id,acceptid,acceptname);
    }

    @Override
    public int updateStrtus2ById(Integer id) {
        return appointment.updateStatus2ById(id);
    }

    @Override
    public PageInfo<EforcesAppointment> selsctStatus1ByAcceptid(Integer pageNum, Integer pageSize, String acceptid) {
        PageHelper.startPage(pageNum,pageSize);
        List<EforcesAppointment> list = appointment.selsctStatus1ByAcceptid(acceptid);
        final PageInfo<EforcesAppointment> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 预约单管理
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<EforcesAppointment> getlistAllMsg(EforcesAppointment Eforces) {
        List<EforcesAppointment> list = appointment.getlistAllMsg(Eforces);
        final PageInfo<EforcesAppointment> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 删除预约单管理
     * @param id
     * @return
     */
    @Override
    public Integer deleteAppointment(Integer[] id) {
        return appointment.deleteAppointment(id);
    }

    /**
     * 修改预约订单
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(EforcesAppointment record) {
        return appointment.updateByPrimaryKeySelective(record);
    }
}
