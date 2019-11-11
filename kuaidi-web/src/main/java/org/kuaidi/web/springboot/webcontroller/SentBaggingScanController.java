package org.kuaidi.web.springboot.webcontroller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.kuaidi.bean.domain.EforcesIncment;
import org.kuaidi.bean.domain.EforcesSentScan;
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
		EforcesIncment inc = (EforcesIncment) request.getAttribute("inc");
		PageVo<Map<String, Object>> rst = doubboScanService.getAllSentBagging(page,inc.getNumber());
		return rst;
	}

//	@RequestMapping("getById")
//	@CrossOrigin
//	public ResultVo getById(Integer id) {
//		try {
//			EforcesBaggingScan result = scanService.getById(id);
//			return ResultUtil.exec(true, "查询成功", result);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return ResultUtil.exec(false, "查询失败", null);
//		}
//	}

//	@DeleteMapping("scan")
//	@CrossOrigin
//	public ResultVo deleteByIds(@RequestBody List<Integer> array) {
//		try {
//			int i=scanService.deleteById(array);
//			if(i>0){
//				return ResultUtil.exec(true, "删除成功", null);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return ResultUtil.exec(false,"删除失败",null);
//		}
//		return ResultUtil.exec(false, "删除失败！", null);
//	}
	
//	@PutMapping("scan")
//	@CrossOrigin
//	public ResultVo updateById(EforcesBaggingScan record) {
//		try {
//			scanService.updateById(record);
//			return ResultUtil.exec(true, "修改成功", null);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return ResultUtil.exec(false, "修改失败", null);
//		}
//	}
	
	@PostMapping("scan")
	@CrossOrigin
	@NeedUserInfo
	public ResultVo addRecord(HttpServletRequest request , String bagNumber,EforcesSentScan  sentScan) {
		return orderService.sendBagScan(request, bagNumber,sentScan);
	}
}
