package org.kuaidi.web.springboot.controller.scan;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.kuaidi.bean.domain.EforcesIncment;
import org.kuaidi.bean.domain.EforcesLogisticStracking;
import org.kuaidi.bean.domain.EforcesOrder;
import org.kuaidi.bean.domain.EforcesRectoOrder;
import org.kuaidi.bean.domain.EforcesUser;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesOrderService;
import org.kuaidi.iservice.IEforcesRectoOrderService;
import org.kuaidi.web.springboot.core.authorization.NeedUserInfo;
import org.kuaidi.web.springboot.dubboservice.RectoorderDubboService;
import org.kuaidi.web.springboot.util.LogisticStracking;
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
	
	@Reference(version = "1.0.0")
	IEforcesOrderService orderDetailOrder; 
	
	@Autowired
	RectoorderDubboService rectoorderDubboService;

	@GetMapping("scan")
	@CrossOrigin
	public PageVo getAll(QueryPageVo page) {
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
	@NeedUserInfo
	public ResultVo addRectoOrder(HttpServletRequest request,EforcesRectoOrder record) { 
		try {
			String billNum = record.getNumber();
			if(billNum == null || StringUtils.equals(billNum, "") ) {
				return ResultUtil.exec(false, "订单号不能为空！", null);
			}
			String[] billSection = billNum.split("\\s+");
			List<String> numberList = new ArrayList<String>();
			if(billSection.length > 0 ) {
				for (String str : billSection) {
		            if(StringUtils.isNotEmpty(str)){
		                System.err.println(str);
		                numberList.add(str.trim());
		            }
		        }
			}
			List <EforcesOrder> orderList = orderDetailOrder.getAllNumberMsg(numberList);
			if(orderList == null || orderList.size() == 0 ) {
				return ResultUtil.exec(false, "订单号不正确，请确定！", null);
			}
			EforcesIncment  incment = (EforcesIncment)request.getAttribute("inc");
			if(incment != null) {
				record.setDepartname(incment.getName());
			}
			String incNum = null ; 
			EforcesUser user = (EforcesUser) request.getAttribute("user");
			if(user != null ) {
				record.setDepartid(user.getIncid());
				record.setCreateid(user.getNumber());
				record.setCreatename(user.getName());
				record.setScanmanid(user.getNumber());
				record.setScanman(user.getName());
				incNum = user.getIncid();
			}
			if(incNum == null ||  billNum == null ) {
				return ResultUtil.exec(false, "参数错误，请确定！", null);
			}
			
			List <EforcesRectoOrder> list = orderService.getRectoOrderByNumber(incNum, numberList);
			
			if(list != null && list.size() > 0  ) {
//				return ResultUtil.exec(false, "已经收件交单过了！", null);
				for(int i = numberList.size()-1 ; i >= 0 ; i-- ) {
					String numberItem = numberList.get(i);
					for(int j = 0 ; j < list.size(); j++) {
						EforcesRectoOrder  rectoOrder = list.get(j);
						if(rectoOrder != null && StringUtils.equals(rectoOrder.getNumber(), numberItem) ) {
							numberList.remove(i);
						}
					}
				}
			}
			if(record.getNum() == null) {
				record.setNum(1);
			}
			if(record.getScantypeid() == null) {
				record.setScantypeid(0);
			}
			if(record.getScantypename() == null) {
				record.setScantypename("收件交单");
			}
			// 封装物流信息
			String description = "%s已收件,扫描员是【%s】";
        	description = String.format(description, incment.getName(), user.getName());
			EforcesLogisticStracking  logisticStracking = 
					LogisticStracking.createStrackingInfo(user, incment, description, record.getNumber(), 1);
			//生成多条记录进行插入。
			
			orderService.addRectoOrder(record,logisticStracking,numberList);
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
