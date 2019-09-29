package org.kuaidi.web.springboot.appcontroller;

import java.io.IOException;

import org.kuaidi.bean.Config;
import org.kuaidi.bean.domain.EforcesCustomerSign;
import org.kuaidi.bean.domain.EforcesOrder;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.iservice.IEforcesCustomerSignService;
import org.kuaidi.iservice.IEforcesOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;

/*
 * 业绩查询
 **/ 
@RestController
@RequestMapping("/app/achievement/")
public class AppAchievementController {
	
	@Reference(version = "1.0.0")
	IEforcesOrderService  orderService;
	
	private static final Logger logger = LoggerFactory.getLogger(AppAchievementController.class);
	
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
			logger.debug(e.getMessage());
			return ResultUtil.exec(pageNum, pageSize, 0,null);
		}
	}
	@RequestMapping("getByDeliveryUserId")
	@ResponseBody
	public PageVo<EforcesOrder> getByDeliveryUserId(Integer pageNum, Integer  pageSize, String  deliveryUserId) throws IOException {

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
			logger.debug(e.getMessage());
			return ResultUtil.exec(pageNum, pageSize, 0,null);
		}
	}
	
}
