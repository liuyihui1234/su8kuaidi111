package org.kuaidi.web.springboot.controller.scan;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.kuaidi.bean.domain.EforcesIncment;
import org.kuaidi.bean.domain.EforcesLogisticStracking;
import org.kuaidi.bean.domain.EforcesOrder;
import org.kuaidi.bean.domain.EforcesTransportedscan;
import org.kuaidi.bean.domain.EforcesUser;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesOrderService;
import org.kuaidi.iservice.IEforcesTransportedscanService;
import org.kuaidi.web.springboot.core.authorization.NeedUserInfo;
import org.kuaidi.web.springboot.util.LogisticStracking;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2019/8/7 15:11
 */
@RestController
@RequestMapping("/web/Transportedscan/")
public class TransportedscanController {
	
    @Reference(version = "1.0.0")
    IEforcesTransportedscanService transportedscanService;
    
    @Reference(version = "1.0.0")
    IEforcesOrderService  orderService; 

    @GetMapping("scan")
	@CrossOrigin
    public PageVo doSelectAll(QueryPageVo page){
        try {
            PageInfo<EforcesTransportedscan> pageInfo = transportedscanService.selectByIncid(page.getPage(), page.getLimit(), page.getId());
            return ResultUtil.exec(pageInfo.getPageNum(),pageInfo.getSize(),pageInfo.getTotal(),pageInfo.getList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(1,10,0,null);
        }
    }
    
    @PostMapping("scan")
    @CrossOrigin
    @NeedUserInfo
    public ResultVo doInsertSelective(HttpServletRequest request,EforcesTransportedscan record){
        try {
        	//判断订单是否存在
        	if(record.getBillsnumber() == null || StringUtils.equals(
        				record.getBillsnumber(),"")) {
        		return ResultUtil.exec(false,"订单号不能为空！",null);
        	}
        	EforcesOrder orderInfoList = orderService.getOrderMsg(record.getBillsnumber());
        	if(orderInfoList == null) {
        		return ResultUtil.exec(false,"订单号存在！",null);
        	}
        	EforcesTransportedscan historyTransport = transportedscanService
        				.selectByBillsnumber(record.getBillsnumber());
        	if(historyTransport != null) {
        		return ResultUtil.exec(false,"订单已经转运过，不能重复的操作！",null);
        	}
            EforcesTransportedscan transportedscan = transportedscanService.selectByBillsnumber(record.getBillsnumber());
            if(transportedscan == null){
            	EforcesUser userInfo = (EforcesUser)request.getAttribute("user");
            	EforcesIncment incment = (EforcesIncment) request.getAttribute("inc");
            	// 添加转运相关的数据
            	record.setAmount(0);
            	record.setScantype("转运扫描");
            	record.setScannerid(userInfo.getNumber());
            	record.setScanners(userInfo.getName());
            	record.setIncname(incment.getName());
            	record.setIncid(userInfo.getIncid());
            	String description = "快件在【%s】由【%s】扫描委托【%s】派件,，派件单号【%s】";
            	description = String.format(description, incment.getName(), userInfo.getName(),
            			record.getNextcorpname(), record.getNextnumber());
            	// 添加物流信息。
            	EforcesLogisticStracking strackingInfo = 
            			LogisticStracking.createStrackingInfo(userInfo, incment, description, record.getBillsnumber(), 3);
                int a = transportedscanService.insertSelective(record,strackingInfo);
                if (a > 0){
                    return ResultUtil.exec(true,"添加转运记录成功",null);
                }
                return ResultUtil.exec(false,"添加转运记失败",null);
            }
            return ResultUtil.exec(false,"该订单已转运",null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false,"操作失败",null);
        }
    }

    @RequestMapping("getById")
	@CrossOrigin
    public ResultVo doSelectOne(Integer id){
        try {
            EforcesTransportedscan transportedscan = transportedscanService.selectByPrimaryKey(id);
            if(transportedscan != null){
                return ResultUtil.exec(true,"查询详情成功",transportedscan);
            }
            return ResultUtil.exec(false,"查询详情失败",null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false,"操作失败",null);
        }
    }

    @PutMapping("scan")
	@CrossOrigin
    public ResultVo doUpdate(EforcesTransportedscan record){
        try {
            int a = transportedscanService.updateByPrimaryKeySelective(record);
            if (a > 0){
                return ResultUtil.exec(true,"修改成功",null);
            }
            return ResultUtil.exec(false,"修改失败",null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false,"操作失败",null);
        }
    }
    
    /**
     * 删除
     * @return
     */
    @DeleteMapping("scan")
    @CrossOrigin
    public ResultVo deleteScan(@RequestBody List<Integer> list) {
        try {
            int i = transportedscanService.deleteByid(list);
            return ResultUtil.exec(true, "删除成功", i);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(true, "删除失败", null);
        }
    }
}
