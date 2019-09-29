package org.kuaidi.web.springboot.webcontroller;

import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.web.springboot.service.AppointmentDubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping("web/Reservation/Reservation")
public class ReservationController {

    @Autowired
    AppointmentDubboService appointmentDubboService;

    /**
     * 预约单管理
     * @param
     * @return
     */
    @GetMapping("Reservation")
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
    @DeleteMapping("Reservation")
    @CrossOrigin
    public ResultVo removeUpdates(@RequestBody Integer[] array){
        return appointmentDubboService.deleteappointments(array);
    }
}
