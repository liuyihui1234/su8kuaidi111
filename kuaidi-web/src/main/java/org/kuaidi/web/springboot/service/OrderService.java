package org.kuaidi.web.springboot.service;


import java.util.List;
import org.kuaidi.bean.domain.EforcesDistributedScan;
import org.kuaidi.bean.domain.EforcesOrder;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesDistributedScanService;
import org.kuaidi.iservice.IEforcesOrderService;
import org.kuaidi.iservice.IEforcesRectoOrderService;
import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Reference;

import net.sf.json.JSONObject;

@Component
public class OrderService {
	@Reference(version = "1.0.0")
	private IEforcesRectoOrderService  rectoOrderService;
	
	@Reference(version = "1.0.0")
	private IEforcesOrderService  orderService;
	
	@Reference(version = "1.0.0")
	private IEforcesDistributedScanService  ScanService;
	
//	@Reference(version = "1.0.0")
//	private IEforceslogisticstrackingService  strackingService;
	
	 
	public ResultVo MySendAndReceiveOrder(String  userNum){
		try {
			JSONObject data = new JSONObject();
			int recvCount =  rectoOrderService.getUserRectoOrderCount(userNum);			
			int sendCount  = orderService.getUserOrderCount(userNum);
			data.put("sendCount", sendCount);
			data.put("recvCount", recvCount);
			return ResultUtil.exec(true, "查询操作成功！", data);
		}catch(Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false, "查询操作异常！", null);
		}
	}
	
	public ResultVo alreadySend (String number) {
		try {
			if (number == null) {
				return ResultUtil.exec(false, "提交参数不合法！", null);
			}
			JSONObject data = new JSONObject();
			List<EforcesDistributedScan> send = ScanService.selectByCreateincnumber(number);
			data.put("sendList", send);
			return ResultUtil.exec(true, "查询操作成功！", data);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false, "查询操作异常！", null);
		}
	}
	
	public ResultVo NoSend (String number) {
		try {
			if (number == null) {
				return ResultUtil.exec(false, "提交参数不合法！", null);
			}
			JSONObject data = new JSONObject();
			List<EforcesDistributedScan> send = ScanService.selectNoByCreateincnumber(number);
			data.put("sendList", send);
			return ResultUtil.exec(true, "查询操作成功！", data);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false, "查询操作异常！", null);
		}
	}
	
//	public  ResultVo getByBillsNumber(String number) {
//		try {
//			if (number == null) {
//				return ResultUtil.exec(false, "提交参数不合法！", null);
//			}
//			JSONObject data = new JSONObject();
//			List<EforcesLogisticStracking> result = strackingService.getByBillsNumber(number);
//			data.put("ResultList", result);
//			return ResultUtil.exec(true, "查询操作成功！", data);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return ResultUtil.exec(false, "查询操作异常！", null);
//		}
//	}

	public ResultVo addOrderSelective(EforcesOrder record){
		try {
			int data = orderService.insertSelective(record);
			if(data > 0){
				return ResultUtil.exec(true,"下单成功",null);
			}
			return ResultUtil.exec(false,"下单失败",null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false, "下单操作异常！", null);
		}
	}
	

	
}
