package org.kuaidi.web.springboot.appcontroller;


import com.alibaba.dubbo.config.annotation.Reference;
import org.kuaidi.bean.domain.EforcesAppointment;
import org.kuaidi.bean.domain.EforcesIncment;
import org.kuaidi.bean.domain.EforcesUser;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.AppointmentService;
import org.kuaidi.web.springboot.core.authorization.Authorization;
import org.kuaidi.web.springboot.service.AppointmentDubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

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
    @Authorization
    public ResultVo doAlterStatus2AddOrder(HttpServletRequest request, Integer id){
    	EforcesUser userInfo = (EforcesUser)request.getAttribute("user");
    	EforcesIncment incment = (EforcesIncment)request.getAttribute("inc");
        return appointmentDubboService.alterStatus2AddOrder(userInfo,incment ,id);
    }

    @RequestMapping("updateByPrimaryKeySelective")
    @CrossOrigin
    public ResultVo doUpdateByPrimaryKeySelective(EforcesAppointment record){
        return appointmentDubboService.updateByPrimaryKeySelective(record);
    }
    
    @RequestMapping("getAppointmentById")
    @CrossOrigin
    public ResultVo getAppointmentById(Integer id){
    	try {
    		EforcesAppointment appointment =  service.selectAppointmentByPrimaryKey(id);
        	if(appointment != null ) {
        		return ResultUtil.exec(true, "获得待接单信息成功！", appointment);
        	}
        	return ResultUtil.exec(false, "获得待接单信息为空！", null);
    	}catch(Exception e ) {
    		e.printStackTrace();
    		return ResultUtil.exec(false, "查询异常！", null);
    	}
    	
    }
}
