package org.kuaidi.web.springboot.appcontroller;


import com.alibaba.dubbo.config.annotation.Reference;
import org.kuaidi.bean.domain.EforcesAppointment;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.AppointmentService;
import org.kuaidi.web.springboot.service.AppointmentDubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/app/appoinment/")
public class AppoinmentController {

    @Autowired
    AppointmentDubboService appointmentDubboService;

    @Reference(version ="1.0.0")
    AppointmentService service;

    @RequestMapping("addAppointment")
    @CrossOrigin
    public ResultVo doAddAppointment(EforcesAppointment eforcesAppointment){
        return appointmentDubboService.addAppointment(eforcesAppointment);
    }

    @RequestMapping("findAppointment")
    @CrossOrigin
    public PageVo doFindAppointment(Integer pageNum, Integer pageSize, Integer id)throws IOException {
        return appointmentDubboService.findAppointment(pageNum,pageSize,id);
    }

    @RequestMapping("findAppointmentByStreet")
    @CrossOrigin
    public PageVo doFindAppointmentByStreet(Integer pageNum, Integer pageSize, String fromareastreet){
        return appointmentDubboService.findAppointmentByStreet(pageNum,pageSize,fromareastreet);
    }

    @RequestMapping("alterStatus1ById")
    @CrossOrigin
    public ResultVo doAlterStatus1ById(Integer id,String acceptid,String acceptname){
        return appointmentDubboService.alterStatus1ById(id,acceptid,acceptname);
    }

    @RequestMapping("findAppointment1ByAcceptid")
    @CrossOrigin
    public PageVo doFindAppointment1ByAcceptid(Integer pageNum, Integer pageSize, String acceptid)throws IOException{
        return appointmentDubboService.findAppointment1ByAcceptid(pageNum,pageSize,acceptid);
    }

    @RequestMapping("alterStatus2AddOrder")
    @CrossOrigin
    public ResultVo doAlterStatus2AddOrder(Integer id){
        return appointmentDubboService.alterStatus2AddOrder(id);
    }


    /**
     * 预约单管理
     * @param
     * @return
     */
    @RequestMapping("getlistmsg")
    @ResponseBody
    @CrossOrigin
    public PageVo getAllMsg(QueryPageVo page) throws IOException {
       return appointmentDubboService.getMsg(page);
    }
    /**
     * 删除预约单管理
     * @param
     * @return
     */
    @RequestMapping("deleteappointment")
    @CrossOrigin
    public ResultVo removeUpdate(Integer id){
        return appointmentDubboService.deleteappointment(id);
    }
    /**
     * 删除预约单管理
     * @param
     * @return
     */
    @RequestMapping("deleteappointments")
    @CrossOrigin
    public ResultVo removeUpdates(@RequestBody Integer[] array){
        return appointmentDubboService.deleteappointments(array);
    }

}
