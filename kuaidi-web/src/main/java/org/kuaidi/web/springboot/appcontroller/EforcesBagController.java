package org.kuaidi.web.springboot.appcontroller;

import javax.servlet.http.HttpServletRequest;

import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesCorp;
import org.kuaidi.web.springboot.core.authorization.NeedUserInfo;
import org.kuaidi.web.springboot.service.HandlingOrdersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;

@RestController
@RequestMapping("/web/bagInfo/")
public class EforcesBagController {
	Logger logger = LoggerFactory.getLogger(EforcesBagController.class);
	
	@Autowired
	private HandlingOrdersService  orderService; 
	
	@RequestMapping("makeBagScan")
    @ResponseBody
    public ResultVo makeBagScan(HttpServletRequest request, String billsNum, String bagName){
		String token = request.getHeader("token");
		return orderService.makeBagScan(token ,  billsNum, bagName);
		
	}
	
	
	@RequestMapping("removeBagScan")
    @ResponseBody
    public ResultVo removeBagScan(HttpServletRequest request, String bagNumber){
		String token = request.getHeader("token");
		return orderService.removeBagScan(token , bagNumber);
		
	}
	
	/*
	 * 发送包
	 */
//	@RequestMapping("sentBagScan")
//    @ResponseBody
//	@CrossOrigin
//	@NeedUserInfo
//    public ResultVo sentBagScan(HttpServletRequest request, String bagNumber){
//		String token = request.getHeader("Authorization");
//		return orderService.sendBagScan(token , bagNumber);
//	}
	
	
	/*
	 * 接收送包
	 */
//	@RequestMapping("receiveBagScan")
//    @ResponseBody
//    public ResultVo receiveBagScan(HttpServletRequest request, String bagNumber){
//		String token = request.getHeader("token");
//		return orderService.receiveBag(token , bagNumber);
//		
//	}
}
