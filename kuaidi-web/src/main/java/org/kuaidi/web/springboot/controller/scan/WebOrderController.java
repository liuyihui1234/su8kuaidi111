package org.kuaidi.web.springboot.controller.scan;

import javax.servlet.http.HttpServletRequest;

import org.kuaidi.bean.domain.EforcesOrder;
import org.kuaidi.bean.domain.EforcesUser;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.iservice.IEforcesOrderService;
import org.kuaidi.web.springboot.core.authorization.NeedUserInfo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/web/order/")
public class WebOrderController {
	
	@Reference(version = "1.0.0")
	private IEforcesOrderService  orderService;
	
	/**
	   * 寄/派件运单管理
	 * @param page
	 * @return 
	 */
	@GetMapping("order")
	@ResponseBody
	@CrossOrigin
	@NeedUserInfo
	public PageVo getListMsg(HttpServletRequest request,Integer page, Integer limit, EforcesOrder order){
		try {
//			EforcesUser user = (EforcesUser)request.getAttribute("user");
//			String incNum = user.getIncnumber();
			PageInfo<EforcesOrder> pageInfo = orderService.getAllMsg(page,limit,order);
			return ResultUtil.exec(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getList());
		}catch (Exception e){
			e.printStackTrace();
			return ResultUtil.exec(page,limit,0,null);
		}
	}

}
