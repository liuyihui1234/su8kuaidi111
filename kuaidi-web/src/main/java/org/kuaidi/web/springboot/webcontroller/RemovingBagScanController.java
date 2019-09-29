package org.kuaidi.web.springboot.webcontroller;




import java.util.Date;
import java.util.List;

import org.kuaidi.bean.domain.EforcesIncment;
import org.kuaidi.bean.domain.EforcesRemovingBagScan;
import org.kuaidi.bean.domain.EforcesUser;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesRemovingBagScanService;
import org.kuaidi.web.springboot.core.authorization.NeedUserInfo;
import org.kuaidi.web.springboot.dubboservice.RemovingBagScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.alibaba.dubbo.config.annotation.Reference;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/web/removingBagScan/")
public class RemovingBagScanController {
	@Reference(version = "1.0.0")
	IEforcesRemovingBagScanService scanService;

	@Autowired
	RemovingBagScanService dubboScanService;
	@GetMapping("scan")
	@CrossOrigin
	public PageVo getAll(QueryPageVo page) {
		PageVo rst = null;
		rst = dubboScanService.getAll(page);
		return rst;
	}


	@RequestMapping("getById")
	@CrossOrigin public ResultVo getById(Integer id) { 
		try { 
			EforcesRemovingBagScan result = scanService.getById(id); 
			return ResultUtil.exec(true, "查询成功",result);
		}
		catch (Exception e) { 
			e.printStackTrace(); 
			return ResultUtil.exec(true, "查询 失败", null); 
		} 
	}



	@PostMapping("scan")
	@CrossOrigin
	@NeedUserInfo
	public ResultVo addRecord(EforcesRemovingBagScan record, HttpServletRequest request) {
		 ResultVo rst;
		EforcesUser user = (EforcesUser) request.getAttribute("user");
		EforcesIncment inc = (EforcesIncment) request.getAttribute("inc");
		System.err.println("user:"+user);
		System.err.println("inc:"+inc);
		rst=dubboScanService.removeBagScan(record,user,inc);
		return rst;
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
	public ResultVo setById(EforcesRemovingBagScan record) {
		try{ 
			scanService.setById(record);
			return ResultUtil.exec(true, "更改成功", null);
		} catch (Exception e) { 
			e.printStackTrace(); 
			return ResultUtil.exec(false,"更改失败",null);
		} 

	}




}
