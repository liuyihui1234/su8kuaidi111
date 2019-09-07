package org.kuaidi.web.springboot.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.kuaidi.bean.Config;
import org.kuaidi.bean.domain.EforcesCorp;
import org.kuaidi.bean.domain.EforcesIncment;
import org.kuaidi.bean.domain.EforcesLogisticStracking;
import org.kuaidi.bean.domain.EforcesTransportedscan;
import org.kuaidi.bean.domain.EforcesUser;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesCorp;
import org.kuaidi.iservice.IEforcesOrderService;
import org.kuaidi.iservice.IEforcesTransportedscanService;
import org.kuaidi.iservice.UserService;
import org.kuaidi.web.springboot.util.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Reference;

import net.sf.json.JSONObject;

@Component
public class TransportScanService {
	
	@Reference(version = "1.0.0")
	private IEforcesTransportedscanService  transportedService; 
	
	@Reference(version = "1.0.0")
	private UserService  userService; 
	
	@Reference(version = "1.0.0")
	private IEforcesOrderService orderService; 
	
	@Reference(version = "1.0.0")
	private IEforcesCorp  corpService ; 
	
	@Autowired
	private HandlingOrdersService handlingService; 
	
	@Autowired
	private RedisUtil  redisUtil; 
	
	/*
	 * 添加转运记录。
	 */
	public ResultVo addTransportScan(String token , String billsNumber, String nextNumber, Integer corpId) {
		EforcesTransportedscan record = new EforcesTransportedscan();
		try {
			String userData = redisUtil.get(Config.REDISAPPLOGINPREX + token);
			JSONObject data = JSONObject.fromObject(userData);
			JSONObject userInfo = data.getJSONObject("userInfo");
			EforcesUser eforcesUser =   (EforcesUser) JSONObject.toBean(userInfo, EforcesUser.class);
			JSONObject incInfo = data.getJSONObject("incInfo");
			EforcesIncment eforcesIncment = (EforcesIncment)JSONObject.toBean(incInfo, EforcesIncment.class);
			
			record.setBillsnumber(billsNumber);
			record.setNextnumber(nextNumber);
			record.setScantype("转运扫描");
			record.setFlag(0);
			record.setBz("");
			EforcesCorp corpInfo =  corpService.getEforcesCorpById(corpId);
			if(corpInfo == null ) {
				ResultUtil.exec(false, "物流公司信息错误！", null);
			}
			record.setNextcorpid(corpId);
			record.setNextcorpname(corpInfo.getName());
			record.setScanners(eforcesUser.getName());
			record.setScannerid(eforcesUser.getNumber());
			record.setAmount(0);
			record.setIncid(eforcesIncment.getNumber());
			// 登录后应该知道对应网点的名字。
			record.setIncname(eforcesIncment.getName());
			//封装物流信息
			String description = "快件在【%s】由【%s】扫描委托【%s】派件,，派件单号【%s】";
			description = String.format(description, eforcesIncment.getName(), eforcesUser.getName(), corpInfo.getName(),nextNumber);
			EforcesLogisticStracking  strackingInfo =  handlingService.getLogisticstracking(billsNumber,description, eforcesUser.getName(), 
					eforcesIncment.getName(), eforcesIncment.getNumber(), 3);
			
			int rst = transportedService.insertSelective(record,  strackingInfo);
			if(rst > 0 ) {
				// 插入转运记录。
				return ResultUtil.exec(true, "转运订单成功！", null);
			}
			return ResultUtil.exec(false, "转运订单失败！", null);
		}catch(Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false, "转运订单异常！", null);
		}
	}

}
