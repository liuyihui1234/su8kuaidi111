package org.kuaidi.web.springboot.webcontroller;


import org.kuaidi.bean.domain.EforcesCusinformation;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesCusinformationService;
import org.kuaidi.web.springboot.dubboservice.CusinformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;

@RestController
@RequestMapping("/web/cusinformation/")
public class CusinformationController {
	@Autowired
	CusinformationService doubboService;
	
	@Reference(version = "1.0.0")
	IEforcesCusinformationService mationService;
	
	@RequestMapping("getAll")
	@CrossOrigin
	public PageVo getAll(QueryPageVo page) {
		PageVo rst = null;
		rst = doubboService.getAll(page);
		return rst;
	}
	
	@RequestMapping("getById")
	@CrossOrigin
	public ResultVo getById(Integer id) {
		try {
			EforcesCusinformation result = mationService.getById(id);
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
			mationService.deleteById(id);
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
			mationService.deleteById(array);
			return ResultUtil.exec(true, "删除成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false, "删除失败", null);
		}
	}
	
	@RequestMapping("updateById")
	@CrossOrigin
	public ResultVo updateById(EforcesCusinformation record) {
		try {
			mationService.updateById(record);
			return ResultUtil.exec(true, "修改成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false, "修改失败", null);
		}
	}
	
	@RequestMapping("addRecord")
	@CrossOrigin
	public ResultVo addRecord(EforcesCusinformation record) {
		try {
			//record.setScantime(new Date());
			mationService.addRecord(record);
			return ResultUtil.exec(true, "添加成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false, "添加失败", null);
		}
	}
	
}
