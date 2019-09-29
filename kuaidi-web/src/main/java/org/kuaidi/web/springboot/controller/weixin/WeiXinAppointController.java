package org.kuaidi.web.springboot.controller.weixin;

import org.kuaidi.bean.domain.EforcesAppointment;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.web.springboot.service.AppointmentDubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weixin/appoitment/")
public class WeiXinAppointController {
	
	@Autowired
    AppointmentDubboService appointmentDubboService;
	
	@RequestMapping("addAppointment")
    @CrossOrigin
    public ResultVo doAddAppointment(EforcesAppointment eforcesAppointment){
        return appointmentDubboService.addAppointment(eforcesAppointment);
    }
	
	
	
}
