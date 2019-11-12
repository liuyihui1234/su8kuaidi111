package org.kuaidi.web.springboot.controller.scan;

import org.kuaidi.bean.domain.EforcesBaggingScan;
import org.kuaidi.bean.domain.EforcesIncment;
import org.kuaidi.bean.domain.EforcesUser;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesBiggingScanService;
import org.kuaidi.web.springboot.core.authorization.NeedUserInfo;
import org.kuaidi.web.springboot.dubboservice.BaggingScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.alibaba.dubbo.config.annotation.Reference;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/web/baggingScan/")
public class BaggingScanController {
	@Autowired
	BaggingScanService doubboScanService;
	
	@Reference(version = "1.0.0")
	IEforcesBiggingScanService  scanService;

	@GetMapping("scan")
	@CrossOrigin
	@NeedUserInfo
	public PageVo getAll(QueryPageVo page, HttpServletRequest request) {
		String authorization = request.getHeader("Authorization");
		EforcesIncment inc = (EforcesIncment) request.getAttribute("inc");
		PageVo rst = null;
		System.err.println(inc);
		rst = doubboScanService.getAll(page,inc.getNumber());
		return rst;
	}

	@RequestMapping("getById")
	@CrossOrigin
	public ResultVo getById(Integer id) {
		try {
			EforcesBaggingScan result = scanService.getById(id);
			return ResultUtil.exec(true, "查询成功", result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false, "查询失败", null);
		}
	}

	@DeleteMapping("scan")
	@CrossOrigin
	public ResultVo deleteByIds(@RequestBody List<Integer> array) {
		try {
			int i=scanService.deleteById(array);
			if(i>0){
				return ResultUtil.exec(true, "删除成功", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false,"删除失败",null);
		}
		return ResultUtil.exec(false, "删除失败！", null);
	}
	

	
	@PutMapping("scan")
	@CrossOrigin
	public ResultVo updateById(EforcesBaggingScan record) {
		try {
			
			scanService.updateById(record);
			return ResultUtil.exec(true, "修改成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false, "修改失败", null);
		}
	}
	
	@PostMapping("scan")
	@CrossOrigin
	@NeedUserInfo
	public ResultVo addRecord(EforcesBaggingScan record, HttpServletRequest request) {

			EforcesUser user = (EforcesUser) request.getAttribute("user");
			EforcesIncment inc = (EforcesIncment) request.getAttribute("inc");
			ResultVo rst=doubboScanService.webMakeBagScan(record,inc,user);
			return rst;

	}
	
}
