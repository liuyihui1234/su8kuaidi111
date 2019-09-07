package org.kuaidi.web.springboot.webcontroller;


import java.util.List;

import org.kuaidi.bean.domain.EforcesRectoOrder;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesRectoOrderService;
import org.kuaidi.web.springboot.dubboservice.RectoorderDubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;

@RestController
@RequestMapping("/web/rectoorder/")
public class RectoorderController {

	@Reference(version = "1.0.0")
	IEforcesRectoOrderService orderService;
	@Autowired
	RectoorderDubboService rectoorderDubboService;

	//    @RequestMapping("addRectoorderService")
	//    @CrossOrigin
	//    public ResultVo doAddRectoorderService(String number){
	//        return rectoorderDubboService.addRectoorderService(number);
	//    }

	@GetMapping("scan")
	@CrossOrigin
	public PageVo getAll(QueryPageVo page) {
		System.err.println("2222222");
		PageVo rst = null;
		rst = rectoorderDubboService.getAll(page);
		return rst;
	}

	@RequestMapping("getById")
	@CrossOrigin
	public ResultVo getById(Integer  id) {
		try {
			EforcesRectoOrder result = orderService.getById(id);
			return ResultUtil.exec(true, "查询成功", result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false,"查询失败",null);
		}
	}
	
	
	
	@DeleteMapping("scan")
	@CrossOrigin
	public ResultVo deleteByIds(@RequestBody List<Integer> array) {
		try {
			System.err.println("删除方法");
			 orderService.deleteById(array);
			return ResultUtil.exec(true, "删除成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false,"删除失败",null);
		}
	}
	
	@PostMapping("scan")
	@CrossOrigin
	public ResultVo addRectoOrder(EforcesRectoOrder record) { 
		try {
			System.err.println(record);
			 orderService.addRectoOrder(record);
			return ResultUtil.exec(true, "添加成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false,"添加失败",null);
		}
	}
	
	@PutMapping("scan")
	@CrossOrigin
	public ResultVo setById(EforcesRectoOrder record) {
		try {
			System.err.println("修改方法");
			 orderService.setById(record);
			return ResultUtil.exec(true, "修改成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false,"修改失败",null);
		}
	}


}
