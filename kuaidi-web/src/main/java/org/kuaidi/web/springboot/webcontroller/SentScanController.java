package org.kuaidi.web.springboot.webcontroller;


import org.kuaidi.bean.domain.EforcesSentScan;
import org.kuaidi.bean.vo.*;
import org.kuaidi.iservice.IEforcesSentscanService;
import org.kuaidi.web.springboot.dubboservice.SentScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alibaba.dubbo.config.annotation.Reference;

import java.util.List;


@RestController
@RequestMapping("/web/sentScan/")
public class SentScanController {
	
	@Autowired
	SentScanService dubboSentscanService;
	
	@Reference(version = "1.0.0")
	IEforcesSentscanService   sentScanService;
	
	@GetMapping("scan")
	@CrossOrigin
	public PageVo getAll(QueryPageVo page) {
		System.err.println("page:"+page);
		PageVo rst = null;
		rst = dubboSentscanService.getAll(page);
		return rst;
	}

	@DeleteMapping("scan")
	@CrossOrigin
	public ResultVo deleteByIds(@RequestBody List<Integer> array) {
		try {
			System.err.println("删除方法");
			dubboSentscanService.deleteMenusByID(array);
			return ResultUtil.exec(true, "删除成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false,"删除失败",null);
		}
	}

	
	
	@RequestMapping("getById")
	@CrossOrigin
	public ResultVo getById(Integer id) {
		try {
			EforcesSentScan result=sentScanService.getById(id);
			return ResultUtil.exec(true, "删除成功", result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false,"删除失败",null);
		}
	}
	
	@PutMapping("scan")
	@CrossOrigin
	public ResultVo setById(EforcesSentScan sentScan) {
		try {
			sentScanService.setById(sentScan);
			return ResultUtil.exec(true, "修改成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false,"修改失败",null);
		}
	}
	
	@PostMapping("scan")
	@CrossOrigin
	public ResultVo addSentScan(EforcesSentScan sentScan) {
		try {

			DubboMsgVO   msgVo=sentScanService.addSentScan(sentScan,null);
			if(msgVo  != null && msgVo.isRstFlage()) {
				return ResultUtil.exec(true,"添加成功！",null);
			}else {
				return ResultUtil.exec(false,"添加失败！",null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false,"添加异常",null);
		}
	}
}
