package org.kuaidi.web.springboot.appcontroller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.kuaidi.bean.Config;
import org.kuaidi.bean.domain.EforcesIncment;
import org.kuaidi.bean.domain.EforcesOrder;
import org.kuaidi.bean.domain.EforcesStayedandtroubledscan;
import org.kuaidi.bean.domain.EforcesUser;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesOrderService;
import org.kuaidi.iservice.IEforcesStayedandtroubledscanService;
import org.kuaidi.web.springboot.core.authorization.Authorization;
import org.kuaidi.web.springboot.service.HandlingOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/app/Stayedandtrouble/")
public class AppStayedandtroubledscanController {

    @Reference(version = "1.0.0")
    IEforcesStayedandtroubledscanService stayedandtroubledscanService;
    
    @Reference(version = "1.0.0")
    IEforcesOrderService orderService; 
    
    @Autowired
	private HandlingOrdersService  handlingService ;

    /**
     * 分页查询问题快递件列表！
     * @param pageNum
     * @param pageSize
     * @param incid
     * @param type （1 ： 留仓件（11， 7 ， 10 ）， 2 ： 已损件： （4）， 3 ： 问题件（其他））
     * @return
     */
    @RequestMapping("Lookproblems")
    @ResponseBody
    public PageVo GetProblem(Integer pageNum, Integer pageSize, String incid , Integer type ){
        try {
            if(pageSize ==null || pageSize<=0){
                pageSize = Config.pageSize;
            }
            if(pageNum == null || pageNum<=0){
                pageNum =1;
            }
            List<Integer> causeIds = new ArrayList<Integer>();
            if(type == null || type == 3) {
            	causeIds.add(1);
            	causeIds.add(2);
            	causeIds.add(3);
            	causeIds.add(5);
            	causeIds.add(6);
            	causeIds.add(8);
            	causeIds.add(9);
            	causeIds.add(12);
            }else if(type != null && type == 2 ) {
            	causeIds.add(4);
            }else if(type != null && type == 1) {
            	causeIds.add(7);
            	causeIds.add(10);
            	causeIds.add(11);
            }
            PageInfo<EforcesStayedandtroubledscan> data = stayedandtroubledscanService.getIssue(pageNum,pageSize,incid,causeIds);
            return ResultUtil.exec(pageNum,pageSize,data.getTotal(),"成功",data.getList());
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(pageNum,pageSize,0,"失败",null);
        }
    }
    
    /**
     * 分页查询问题快递件详细信息！(问题件（）)
     * @param pageNum
     * @param pageSize
     * @param incid
     * @return
     */
    @RequestMapping("getProblemPackage")
    @ResponseBody
    public PageVo getProblemPackage(Integer pageNum, Integer pageSize, String incid){
        try {
            if(pageSize ==null || pageSize<=0){
                pageSize = Config.pageSize;
            }
            if(pageNum == null || pageNum<=0){
                pageNum =1;
            }
            List<Integer> causeIds = new ArrayList<Integer>();
        	causeIds.add(1);
        	causeIds.add(2);
        	causeIds.add(3);
        	causeIds.add(5);
        	causeIds.add(6);
        	causeIds.add(8);
        	causeIds.add(9);
        	causeIds.add(12);
            PageInfo<EforcesStayedandtroubledscan> data = stayedandtroubledscanService.getIssue(pageNum,pageSize,incid,causeIds);
            List<EforcesStayedandtroubledscan> rstList = data.getList();
            JSONArray  rstData = new JSONArray();
            for(int i = 0 ; i < rstList.size() ; i++) {
            	EforcesStayedandtroubledscan  problemItem = rstList.get(i);
            	if(problemItem != null ) {
            		JSONObject item = new JSONObject();
            		item.put("scanType", problemItem.getScantype());
            		item.put("troubledtype", problemItem.getTroubledtype());
            		item.put("id", problemItem.getId());
            		if(problemItem.getOrder() != null ) {
            			EforcesOrder order = problemItem.getOrder();
            			item.put("address", order.getFromaddress());
            			item.put("fromName",  order.getFromname());
            			item.put("mobile",  order.getFromtel());
            			item.put("billNum",  order.getNumber());
            		}else {
            			item.put("address","");
            			item.put("fromName",  "");
            			item.put("mobile", "");
            			item.put("billNum",  "");
            		}
            		rstData.add(item);
            	}
            }
            if(rstData.size() > 0 ) {
            	return ResultUtil.exec(pageNum,pageSize,data.getTotal(),"成功",rstData);
            }else {
            	return ResultUtil.exec(pageNum,pageSize,rstData.size(),"成功",rstData);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(pageNum,pageSize,0,"失败",null);
        }
    }
    
    /**
     * 分页查询问题快递件详细信息！(留仓件（）)
     * @param pageNum
     * @param pageSize
     * @param incid
     * @param type (留仓件（1）， 已损件（2）)
     * @return
     */
    @RequestMapping("getDestoryedPackage")
    @ResponseBody
    public PageVo getDestoryedPackage(Integer pageNum, Integer pageSize, String incid, Integer type){
        try {
            if(pageSize ==null || pageSize<=0){
                pageSize = Config.pageSize;
            }
            if(pageNum == null || pageNum<=0){
                pageNum =1;
            }
            List<Integer> causeIds = new ArrayList<Integer>();
            if(type != null && type == 2 ) {
            	causeIds.add(4);
            }else if(type != null && type == 1) {
            	causeIds.add(7);            	
            	causeIds.add(10);
            	causeIds.add(11);
            }
            PageInfo<EforcesOrder> data = orderService.getDestoryedBill(pageNum,pageSize,incid,causeIds);
            List<EforcesOrder> rstList = data.getList();
            return ResultUtil.exec(pageNum,pageSize,data.getTotal(),"成功",rstList);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(pageNum,pageSize,0,"失败",null);
        }
    }
    
    /*
     * @param  userId ,用户id ， incNum
     * 派送留仓件。
     */
    @RequestMapping("sentDestoryedPackage")
    @ResponseBody
    @Authorization
    public ResultVo sentDestoryedPackage(HttpServletRequest  request,String billNumber){
        try {
        	EforcesUser userInfo = (EforcesUser)request.getAttribute("user");
    		EforcesIncment  incMent = (EforcesIncment)request.getAttribute("inc");
            List<Integer> causeIds = new ArrayList<Integer>();
            ResultVo rst = handlingService.sentOrder(billNumber, userInfo, incMent);
            // 删除掉destory表中的数据。
            if(rst.getCode() == 1) {
            	int rmCount = stayedandtroubledscanService.deleteDestoryBill(billNumber);
            	if(rmCount > 0 ) {
            		return ResultUtil.exec(true,"留仓件派件成功！",null);
            	}
            }
            return ResultUtil.exec(false,"留仓件派件成功！",null);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"失败",null);
        }
    }
    
}
