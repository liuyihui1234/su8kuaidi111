package org.kuaidi.web.springboot.controller.scan;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.kuaidi.bean.domain.EforcesIncment;
import org.kuaidi.bean.domain.EforcesSentScan;
import org.kuaidi.bean.domain.EforcesUser;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesBiggingScanService;
import org.kuaidi.web.springboot.core.authorization.NeedUserInfo;
import org.kuaidi.web.springboot.dubboservice.BaggingScanService;
import org.kuaidi.web.springboot.service.HandlingOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;

@RestController
@RequestMapping("/web/sentBaggingScan/")
public class SentBaggingScanController {
	
	@Autowired
	BaggingScanService doubboScanService;
	
	@Reference(version = "1.0.0")
	IEforcesBiggingScanService  scanService;
	
	@Autowired
	HandlingOrdersService orderService; 

	@GetMapping("scan")
	@CrossOrigin
	@NeedUserInfo
	public PageVo getAll(QueryPageVo page, HttpServletRequest request) {
		EforcesUser userInfo = (EforcesUser) request.getAttribute("user");
		PageVo<Map<String, Object>> rst = doubboScanService.getAllSentBagging(page,userInfo.getIncid());
		return rst;
	}
	
	@PostMapping("scan")
	@CrossOrigin
	@NeedUserInfo
	public ResultVo addRecord(HttpServletRequest request , String bagNumber,EforcesSentScan  sentScan) {
		return orderService.sendBagScan(request, bagNumber,sentScan);
	}
}
