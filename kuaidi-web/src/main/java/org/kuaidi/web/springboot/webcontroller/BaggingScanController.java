package org.kuaidi.web.springboot.webcontroller;

import org.kuaidi.bean.domain.EforcesBaggingScan;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesBiggingScanService;
import org.kuaidi.web.springboot.dubboservice.BaggingScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;

@RestController
@RequestMapping("/web/baggingScan/")
public class BaggingScanController {
	@Autowired
	BaggingScanService doubboScanService;
	
	@Reference(version = "1.0.0")
	IEforcesBiggingScanService  scanService;

	@RequestMapping("getAll")
	@CrossOrigin
	public PageVo getAll(QueryPageVo page) {
		PageVo rst = null;
		rst = doubboScanService.getAll(page);
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
	
	@RequestMapping("deleteByIds")
	@CrossOrigin
	public ResultVo deleteByIds(@RequestBody Integer[] id) {
		try {
			scanService.deleteById(id);
			return ResultUtil.exec(true, "删除成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false, "删除失败", null);
		}
	}
	
	@RequestMapping("deleteById")
	@CrossOrigin
	public ResultVo deleteById(Integer id) {
		try {
			Integer[] array= {id};
			scanService.deleteById(array);
			return ResultUtil.exec(true, "删除成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false, "删除失败", null);
		}
	}
	
	@RequestMapping("updateById")
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
	
	@RequestMapping("addRecord")
	@CrossOrigin
	public ResultVo addRecord(EforcesBaggingScan record) {
		try {
			//record.setScantime(new Date());
			scanService.addRecord(record);
			return ResultUtil.exec(true, "添加成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false, "添加失败", null);
		}
	}
	
}
