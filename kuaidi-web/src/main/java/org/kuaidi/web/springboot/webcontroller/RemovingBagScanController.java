package org.kuaidi.web.springboot.webcontroller;




import java.util.Date;
import java.util.List;

import org.kuaidi.bean.domain.EforcesRemovingBagScan;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesRemovingBagScanService;
import org.kuaidi.web.springboot.dubboservice.RemovingBagScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;

@RestController
@RequestMapping("/web/removingBagScan/")
public class RemovingBagScanController {
	@Reference(version = "1.0.0")
	IEforcesRemovingBagScanService scanService;

	@Autowired
	RemovingBagScanService dubboScanService;
	@RequestMapping("getAll")
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



	@RequestMapping("addRecord")
	@CrossOrigin public ResultVo addRecord(EforcesRemovingBagScan record) {
		try { 
			record.setScantime(new Date());
			scanService.addWeighingScan(record); 
			return ResultUtil.exec(true, "添加成功", null); 
		}
		catch (Exception e) { 
			e.printStackTrace(); 
			return ResultUtil.exec(true, "添加失败", null);
		} 
	}



	@RequestMapping("deleteById")

	@CrossOrigin 
	public ResultVo deleteById(Integer id) {
		try 
		{ Integer[] array={id};
		scanService.deleteById(array);
		return ResultUtil.exec(true, "删除成功",null); 
		} 
		catch (Exception e) {
			e.printStackTrace();
			return  ResultUtil.exec(false,"删除失败",null);
		} 
	}



	@RequestMapping("deleteByIds")	  
	@CrossOrigin
	public ResultVo deleteByIds(@RequestBody Integer[] array) {
		try{ 
			scanService.deleteById(array); 
			return ResultUtil.exec(true, "删除成功", null);
		} catch (Exception e) { 
			e.printStackTrace(); 
			return ResultUtil.exec(false,"删除失败",null);
		} 

	}
	
	@RequestMapping("setById")	  
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
