package org.kuaidi.web.springboot.controller.weixin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.kuaidi.bean.domain.EforcesOrder;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;

@RestController
@RequestMapping("/weixin/order/")
public class WeixinOrderController {
	
	Logger logger = LoggerFactory.getLogger(WeixinOrderController.class);
	
	@Reference(version="1.0.0")
	private IEforcesOrderService  orderService; 
	
    @ResponseBody
    @PostMapping("/getUserSendOrder")
    public ResultVo getUserSendOrder(@RequestBody  String openId){
		try {
			List<EforcesOrder> list =  orderService.getNumberByOpenId("oa8tD0aijnZprPCG759mI-H0mcJE");
			if(list != null && list.size() > 0 ) {
				return ResultUtil.exec(true, "获得用户寄出订单成功！", list);
			}
			return ResultUtil.exec(true, "用户寄出的订单为空！", null);
		}catch(Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return ResultUtil.exec(false, "查找用户寄出的订单异常！", null);
		}
    }
    
    @ResponseBody
    @PostMapping("/getNumberSendOrder")
    public ResultVo getNumberSendOrder(@RequestBody Map<String, Object> params ){
		try {
			if(params.get("number")==null) {
				return ResultUtil.exec(false, "运单号查询为空！", null);
			}
			String number=params.get("number").toString();
			EforcesOrder order = orderService.getOrderMsg(number);
			if(order==null) {
				return ResultUtil.exec(true, "运单号查询为空！", null);
			}
			List<EforcesOrder> list=new ArrayList<>();
			list.add(order);
			if(list != null && list.size() > 0 ) {
				return ResultUtil.exec(true, "运单号查询成功！", list);
			}
			return ResultUtil.exec(false, "运单号查询为空！", null);
		}catch(Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return ResultUtil.exec(false, "运单号查询异常！", null);
		}
    }
    
    @ResponseBody
    @PostMapping("/delUserSendOrder")
    public ResultVo delUserSendOrder(@RequestBody Map<String, Object> params ){
		try {
			
			if(params.get("orderId")==null) {
				return ResultUtil.exec(false, "运单编号有误！", null);
			}
			Integer id=Integer.parseInt(params.get("orderId").toString());
			EforcesOrder order = orderService.getByid(id);
			order.setIsDelete(1);
			orderService.updateByPrimaryKeySelective(order);
			return ResultUtil.exec(true, "订单删除成功！", null);
		}catch(Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return ResultUtil.exec(false, "查找用户寄出的订单异常！", null);
		}
    }
}
