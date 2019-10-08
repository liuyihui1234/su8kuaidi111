package org.kuaidi.web.springboot.controller.scan;

import org.kuaidi.bean.domain.EforcesAppointment;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.iservice.AppointmentService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/web/reservation/")
public class WebAppointmentController {
	
	@Reference(version = "1.0.0")
	private AppointmentService  appointService;
	
	/*
	   * 查询所有的订单。
	 */
	@GetMapping("getAppointment")
    @CrossOrigin
	public PageVo getAppointment(QueryPageVo page,EforcesAppointment Eforces){
		try {
			PageHelper.startPage(page.getPage(),page.getLimit());
			PageInfo<EforcesAppointment> pageInfo = appointService.getlistAllMsg(Eforces);
			return ResultUtil.exec(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getList());
		}catch (Exception e){
			e.printStackTrace();
			return ResultUtil.exec(page.getPage(),page.getLimit(),0,null);
		}
	}
	
}
