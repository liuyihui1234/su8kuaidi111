package org.kuaidi.web.springboot.appcontroller;

import javax.servlet.http.HttpServletRequest;

import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.web.springboot.service.TransportScanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/app/transport/")
public class EforcesTransportScanController {
	
	Logger logger = LoggerFactory.getLogger(EforcesTransportScanController.class);
	
	@Autowired
	private TransportScanService transportScanService; 
	
	@RequestMapping("addTransportScan")
    @ResponseBody
    public ResultVo addTransportScan(HttpServletRequest request,String billsNumber, String nextNumber , Integer corpId){
		try {
			String token = request.getHeader("token");
			return transportScanService.addTransportScan(token , billsNumber, nextNumber, corpId);
		}catch(Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
			return ResultUtil.exec(false, "添加转运记录异常！", null);
		}
	}
		

}
