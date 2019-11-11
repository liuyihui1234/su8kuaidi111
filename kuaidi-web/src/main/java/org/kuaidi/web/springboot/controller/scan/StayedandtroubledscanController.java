package org.kuaidi.web.springboot.controller.scan;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.kuaidi.bean.domain.EforcesIncment;
import org.kuaidi.bean.domain.EforcesOrder;
import org.kuaidi.bean.domain.EforcesStayedandtroubledscan;
import org.kuaidi.bean.domain.EforcesUser;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesOrderService;
import org.kuaidi.iservice.IEforcesStayedandtroubledscanService;
import org.kuaidi.web.springboot.core.authorization.NeedUserInfo;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/web/stayedandtroubledscan/")
public class StayedandtroubledscanController {
	
    @Reference(version = "1.0.0")
    IEforcesStayedandtroubledscanService scanService;
    
    @Reference(version = "1.0.0")
    IEforcesOrderService  orderService; 
    
    @GetMapping("scan")
    @CrossOrigin
    @NeedUserInfo
    public PageVo getAll(QueryPageVo page, HttpServletRequest request) {
        try {
            EforcesIncment inc = (EforcesIncment) request.getAttribute("inc");
            PageInfo<EforcesStayedandtroubledscan> eforcesUsers = scanService.getAllIssue(page.getPage(), page.getLimit(), null);
            return ResultUtil.exec(eforcesUsers.getPageNum(), eforcesUsers.getSize(), eforcesUsers.getTotal(), eforcesUsers.getList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(1, 10, 0, null);
        }
    }

    @RequestMapping("getById")
    @CrossOrigin
    public ResultVo getById(Integer id) {
        try {
            EforcesStayedandtroubledscan eforcesStayedandtroubledscan = scanService.selectById(id);
            return ResultUtil.exec(true, "查询成功", eforcesStayedandtroubledscan);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(true, "查询失败", null);
        }
    }

    /**
     * 添加
     * @param record
     * @return
     */
    @PostMapping("scan")
    @CrossOrigin
    @NeedUserInfo
    public ResultVo addWeighingScan(EforcesStayedandtroubledscan record, HttpServletRequest request) {
        try {
            EforcesUser user = (EforcesUser) request.getAttribute("user");
            EforcesIncment inc = (EforcesIncment) request.getAttribute("inc");
            String billsnumber = record.getBillsnumber();
            String[] split = {};
            if(StringUtils.isNotEmpty(billsnumber)){
               split = billsnumber.split("\\s+");
            }else {
                return ResultUtil.exec(false, "运单号不能为空", null);
            }
            Set<String> set = new HashSet<String>();
            for (String str : split) {
                if(StringUtils.isNotEmpty(str)){
                    set.add(str);
                }
            }
            //判断订单里面有没有无效的订单            
            List<String> billNumList = new ArrayList<String>();
            for(String billNumber:set) {
            	billNumList.add(billNumber);
            }
            List<EforcesOrder> billList = orderService.getAllNumberMsg(billNumList);
            Map<String , EforcesOrder> billMap = new HashMap<String , EforcesOrder>();
            for(EforcesOrder  billInfo : billList) {
            	billMap.put(billInfo.getNumber(), billInfo);
            }
            boolean flage = false ; 
            for(String billNumber:set) {
            	if(!billMap.containsKey(billNumber)) {
            		flage = true ;
            		break;
            	}
            }
            if(flage) {
            	return ResultUtil.exec(false, "订单中有不正确的订单号，请确定！", null);
            }
            //判断是否有已经添加过的问题订单。
            List<EforcesStayedandtroubledscan> scanList = scanService.getScanInfoByBillsNumbers(billNumList);
            if(scanList != null && scanList.size() > 0 ) {
            	return ResultUtil.exec(false, "问题件中包含录入的编号，请确定！", null);
            }
            List<EforcesStayedandtroubledscan> list = new ArrayList<>();
            for (String str1 : set) {
                EforcesStayedandtroubledscan scan = new EforcesStayedandtroubledscan();
                scan.setScantype("问题件扫描");
                scan.setSacnners(user.getName());
                scan.setFlightsnumber(record.getFlightsnumber());
                scan.setBillsnumber(str1);
                scan.setSacnnerid(user.getNumber());
                scan.setIncname(user.getIncname());
                scan.setTroubledtype(record.getTroubledtype());
                scan.setIncid(user.getIncid());
                list.add(scan);
            }
            int i = scanService.insertList(list);
            return ResultUtil.exec(true, "添加成功", set.size());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(true, "添加失败", null);
        }
    }

    /**
     * 修改
     *
     * @return
     */

    @PutMapping("scan")
    @CrossOrigin
    public ResultVo updateScan(EforcesStayedandtroubledscan record) {
        try {
            int i = scanService.updateByPrimaryKeySelective(record);
            return ResultUtil.exec(true, "修改成功", i);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(true, "修改失败", null);
        }
    }

    /**
     * 删除
     *
     * @return
     */
    @DeleteMapping("scan")
    @CrossOrigin
    public ResultVo deleteScan(@RequestBody List<Integer> list) {
        try {
            int i = scanService.deleteByid(list);
            return ResultUtil.exec(true, "删除成功", i);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(true, "删除失败", null);
        }
    }

}

