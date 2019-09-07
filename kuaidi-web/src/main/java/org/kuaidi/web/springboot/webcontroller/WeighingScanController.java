package org.kuaidi.web.springboot.webcontroller;




import java.util.List;
import org.kuaidi.bean.domain.EforcesWeighingScan;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesWeighingScanService;
import org.kuaidi.web.springboot.dubboservice.WeighingScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.alibaba.dubbo.config.annotation.Reference;

@RestController
@RequestMapping("/web/weighingScan/")
public class WeighingScanController {
	@Reference(version = "1.0.0")
	IEforcesWeighingScanService scanService;
	
	@Autowired
	WeighingScanService dubboScanService;
	@GetMapping("scan")
	@CrossOrigin
	public PageVo getAll(QueryPageVo page) {
		PageVo rst = null;
		rst = dubboScanService.getAll(page);
		return rst;
	}
	
	
	@RequestMapping("getById")
	@CrossOrigin
	public ResultVo getById(Integer id) {
	try {
		EforcesWeighingScan result = scanService.getById(id);
		return ResultUtil.exec(true, "查询成功", result);
	} catch (Exception e) {
		e.printStackTrace();
		return ResultUtil.exec(true, "查询 失败", null);
	}
	}
	
	
	@PostMapping("scan")
	@CrossOrigin
	public ResultVo addWeighingScan(EforcesWeighingScan record) {
	try {
		//record.setCreatetime(new Date());
		scanService.addWeighingScan(record);
		return ResultUtil.exec(true, "添加成功", null);
	} catch (Exception e) {
		e.printStackTrace();
		return ResultUtil.exec(true, "添加失败", null);
	}
	}


	@DeleteMapping("scan")
	@CrossOrigin
	public ResultVo deleteByIds(@RequestBody List<Integer> array) {
		try {
			System.err.println("删除方法");
			dubboScanService.deleteById(array);
			return ResultUtil.exec(true, "删除成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false,"删除失败",null);
		}
	}

	

	
	@PutMapping("scan")
	@CrossOrigin
	public ResultVo setById(EforcesWeighingScan record) {
		try {
			scanService.setById(record);
			return ResultUtil.exec(true, "修改成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false,"修改失败",null);
		}
	}
	
	
	
	
}
