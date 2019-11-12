package org.kuaidi.web.springboot.controller.scan;




import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.kuaidi.bean.domain.EforcesIncment;
import org.kuaidi.bean.domain.EforcesOrder;
import org.kuaidi.bean.domain.EforcesUser;
import org.kuaidi.bean.domain.EforcesWeighingScan;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesOrderService;
import org.kuaidi.iservice.IEforcesWeighingScanService;
import org.kuaidi.web.springboot.core.authorization.NeedUserInfo;
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
	
	@Reference(version = "1.0.0")
	IEforcesOrderService orderDetailOrder; 
	
	@GetMapping("scan")
	@CrossOrigin
	@NeedUserInfo
	public PageVo getAll(HttpServletRequest request, QueryPageVo page) {
		PageVo rst = null;
		EforcesUser userInfo = (EforcesUser)request.getAttribute("user");
		rst = dubboScanService.getAll(page, userInfo.getIncid());
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
	@NeedUserInfo
	public ResultVo addWeighingScan(HttpServletRequest request ,EforcesWeighingScan record) {
	try {
		
		
		String billNum = record.getBillsnumber();
		if(billNum == null || StringUtils.equals(billNum, "") ) {
			return ResultUtil.exec(false, "订单号不能为空！", null);
		}
		
		List <EforcesOrder> orderList = orderDetailOrder.getByNumber(billNum);
		if(orderList == null || orderList.size() == 0 ) {
			return ResultUtil.exec(false, "订单号不正确，请确定！", null);
		}
		
		EforcesIncment  incment = (EforcesIncment)request.getAttribute("inc");
		
		EforcesUser user = (EforcesUser)request.getAttribute("user");
		
		String incNum = null ; 
		if(user != null) {
			incNum = user.getIncid();
		}
		if(incNum == null ||  billNum == null ) {
			return ResultUtil.exec(false, "参数错误，请确定！", null);
		}
		record.setIncid(user.getIncid());
		// 判断是否称重过
		List<EforcesWeighingScan> list = scanService.getWeightScanByParam(incNum, billNum);
		if(list != null && list.size() > 0 ) {
			return ResultUtil.exec(false, "订单已经称重过了！", null);
		}
		record.setIncid(incNum);
		record.setIncname(incment.getName());
		record.setScantype("收件称重扫描");
		record.setCreatetime(new Date());
		record.setScantime(new Date());
		record.setScanners(user.getName());
		record.setScannerid(user.getNumber());
		scanService.addWeighingScan(record);
		return ResultUtil.exec(true, "添加成功", null);
	} catch (Exception e) {
		e.printStackTrace();
		return ResultUtil.exec(false, "添加失败", null);
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
