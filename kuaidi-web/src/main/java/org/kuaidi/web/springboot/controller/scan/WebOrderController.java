package org.kuaidi.web.springboot.controller.scan;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.kuaidi.bean.domain.EforcesOrder;
import org.kuaidi.bean.vo.OrderInfoVO;
import org.kuaidi.bean.vo.PageVo;
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
	public PageVo getListMsg(HttpServletRequest request,Integer page, Integer limit, OrderInfoVO order){
		try {
//			EforcesUser user = (EforcesUser)request.getAttribute("user");
//			String incNum = user.getIncnumber();
			
			String billsNum = order.getBillsNum();
			System.out.println(billsNum);
			
			if(billsNum != null && billsNum.length() > 0 ) {
				String []section = billsNum.split("\n");
				if(section != null && section.length > 0) {
					List<String> billsNumList = order.getBillsNumberList();
					for(String sectionItem : section) {
						if(sectionItem != null && sectionItem.length() > 0 ) {
							billsNumList.add(sectionItem.trim());
						}
					}
				}
			}
			if(order != null && order.getSendTime() != null ) {
				String sendTime = order.getSendTime() ;
				if(sendTime.indexOf(" - ") > -1){
					String [] section = sendTime.split(" - ");
					if(section != null && section.length > 0) {
						String startSendTime =  section[0];
						if(StringUtils.isNotEmpty(startSendTime)) {
							order.setSendStartTime(startSendTime.trim());
						}
					}
					if(section != null && section.length > 1 ) {
						String endSendTime = section[1];
						if(endSendTime != null ) {
							order.setSendEndTime(endSendTime.trim());
						}
					}
					
				}
			}
			if(order != null && order.getInputTime() != null ) {
				String inputTime = order.getInputTime() ;
				if(inputTime.indexOf(" - ") > -1){
					String [] section = inputTime.split(" - ");
					if(section != null && section.length > 0) {
						String startInputTime =  section[0];
						if(StringUtils.isNotEmpty(startInputTime)) {
							order.setInputStartTime(startInputTime.trim());
						}
					}
					if(section != null && section.length > 1 ) {
						String endInputTime = section[1];
						if(endInputTime != null ) {
							order.setInputEndTime(endInputTime.trim());
						}
					}
				}
			}
			System.out.println(order.getRemark());
			System.out.println(order.getSendStartTime());
			System.out.println(order.getSendEndTime());
			System.out.println(order.getInputStartTime());
			System.out.println(order.getInputEndTime());
			PageInfo<EforcesOrder> pageInfo = orderService.getAllMsg(page,limit,order);
			return ResultUtil.exec(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getList());
		}catch (Exception e){
			e.printStackTrace();
			return ResultUtil.exec(page,limit,0,null);
		}
	}

}
