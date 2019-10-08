package org.kuaidi.web.springboot.webcontroller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.kuaidi.bean.domain.EforcesIncment;
import org.kuaidi.bean.domain.EforcesRectoOrder;
import org.kuaidi.bean.domain.EforcesUser;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesRectoOrderService;
import org.kuaidi.web.springboot.core.authorization.NeedUserInfo;
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
	@NeedUserInfo
	public PageVo getAll(HttpServletRequest request , QueryPageVo page) {
		EforcesUser userInfo =  (EforcesUser)request.getAttribute("user");
		String incNumber = userInfo.getIncid();
		PageVo rst = rectoorderDubboService.getAll(page,incNumber);
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
	@NeedUserInfo
	public ResultVo addRectoOrder(HttpServletRequest request,EforcesRectoOrder record) { 
		try {
			EforcesUser userInfo = (EforcesUser)request.getAttribute("user");
			EforcesIncment  incMent = (EforcesIncment)request.getAttribute("inc");
			 record.setNum(1);
			 record.setScantypename("收件交单");
			 record.setScantypeid(0);
			 record.setGoodsid(0);
			 record.setScanman(userInfo.getName());
			 record.setScanmanid(userInfo.getNumber());
			 record.setCreateid(userInfo.getNumber());
			 record.setCreatename(userInfo.getName());
			 record.setDepartid(incMent.getNumber());
			 record.setDepartname(incMent.getName());
			 
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
