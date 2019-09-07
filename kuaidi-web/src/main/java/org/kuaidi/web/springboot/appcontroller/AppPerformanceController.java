package org.kuaidi.web.springboot.appcontroller;

import java.io.IOException;

import org.kuaidi.bean.Config;
import org.kuaidi.bean.domain.EforcesOrder;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.iservice.IEforcesCustomerSignService;
import org.kuaidi.iservice.IEforcesOrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/app/performance/")
public class AppPerformanceController {
	
	@Reference(version = "1.0.0")
	IEforcesOrderService  orderService;
	
	@Reference(version = "1.0.0")
	IEforcesCustomerSignService signService;
	
	/*
	 * 快递员收到的快递
	 */
	@RequestMapping("getByCreateUserId")
	@ResponseBody
	public PageVo<EforcesOrder> getByCreateUserId(Integer pageNum, Integer  pageSize, String  createUserId) throws IOException {
		try {
			if(pageSize == null ||  pageSize <= 0 ){
				pageSize = Config.pageSize;
			}
			if(pageNum == null || pageNum <= 0 ){
				pageNum = 1;
			}
			PageInfo<EforcesOrder> pageInfo = orderService.getByCreateUserId(pageNum, pageSize, createUserId);
			return ResultUtil.exec(pageNum,pageSize,pageInfo.getTotal(), pageInfo.getList());
		}catch (Exception e){
			e.printStackTrace();
			return ResultUtil.exec(pageNum, pageSize, 0,null);
		}
	}
	
	/*
	 * 快递员已经投递的快递
	 */
	@RequestMapping("getDeliveryByUserId")
	@ResponseBody
	public PageVo<EforcesOrder> getDeliveryByUserId(Integer pageNum, Integer  pageSize, String  deliveryUserId) throws IOException {
		try {
			if(pageSize == null ||  pageSize <= 0 ){
				pageSize = Config.pageSize;
			}
			if(pageNum == null || pageNum <= 0 ){
				pageNum = 1;
			}
			PageInfo<EforcesOrder> pageInfo = orderService.getDeliveryByCreateUserId(pageNum, pageSize, deliveryUserId);
			return ResultUtil.exec(pageNum,pageSize,pageInfo.getTotal(), pageInfo.getList());
		}catch (Exception e){
			e.printStackTrace();
			return ResultUtil.exec(pageNum, pageSize, 0,null);
		}
	}
	
}
