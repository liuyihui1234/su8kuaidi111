package org.kuaidi.web.springboot.controller.weixin;

import java.util.List;
import org.kuaidi.bean.domain.EforcesOrder;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;

@RestController
@RequestMapping("/weixin/order/")
public class WeixinOrderController {
	
	Logger logger = LoggerFactory.getLogger(WeixinOrderController.class);
	
	@Reference(version="1.0.0")
	private IEforcesOrderService  orderService; 
	
	@RequestMapping("getUserSendOrder")
    @CrossOrigin
    public ResultVo getUserSendOrder(String openId){
		try {
			List<EforcesOrder> list =  orderService.getNumberByOpenId(openId);
			if(list != null && list.size() > 0 ) {
				return ResultUtil.exec(true, "获得用户寄出订单成功！", list);
			}
			return ResultUtil.exec(false, "用户寄出的订单为空！", null);
		}catch(Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return ResultUtil.exec(false, "查找用户寄出的订单异常！", null);
		}
    }

}
