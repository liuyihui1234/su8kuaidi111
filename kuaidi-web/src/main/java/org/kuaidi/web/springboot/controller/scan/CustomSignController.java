package org.kuaidi.web.springboot.controller.scan;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.kuaidi.bean.domain.EforcesCustomerSign;
import org.kuaidi.bean.domain.EforcesIncment;
import org.kuaidi.bean.domain.EforcesUser;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesCustomerSignService;
import org.kuaidi.iservice.IEforceslogisticstrackingService;
import org.kuaidi.web.springboot.core.authorization.NeedUserInfo;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/web/sign/")
public class CustomSignController {
    
	@Reference(version = "1.0.0")
    IEforcesCustomerSignService scanService;
    
    @Reference(version = "1.0.0")
    IEforceslogisticstrackingService logisticstrackingService;

    @GetMapping("customsign")
    @CrossOrigin
    @NeedUserInfo
    public PageVo getAll(QueryPageVo page,HttpServletRequest request) {
        try {
            EforcesIncment inc = (EforcesIncment) request.getAttribute("inc");
            PageInfo<EforcesCustomerSign> allSign = scanService.getAllSign(page.getPage(), page.getLimit(),"44");
            return ResultUtil.exec(allSign.getPageNum(), allSign.getSize(), allSign.getTotal(), allSign.getList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(1, 10, 0, null);
        }
    }

    /**
     * 添加用户签收
     * @param record
     * @return
     */
    @PostMapping("customsign")
    @CrossOrigin
    @NeedUserInfo
    public ResultVo addScan(EforcesCustomerSign record, HttpServletRequest request) {
        try {
            EforcesUser user = (EforcesUser) request.getAttribute("user");
            EforcesIncment inc = (EforcesIncment) request.getAttribute("inc");
            //判断当前单号是否已经是到件后的状态
            String s = logisticstrackingService.selectMaxMark(record.getNumber());
            System.out.println(s);
            if(StringUtils.isEmpty(s)){
                return ResultUtil.exec(false, "单号错误", null);
            }else if(Integer.parseInt(s)<4){
                return ResultUtil.exec(false, "运单状态不正确", null);
            }
            /*
                                        * 判断订单是否被签收过
             * */
            EforcesCustomerSign  customerSign =  scanService.selectByNumber(record.getNumber());
            if(customerSign != null) {
            	return ResultUtil.exec(false, "运单已经签收过了，请确定！", null);
            }
            record.setIncname(inc.getName());
            record.setIncid(user.getIncid());
            
            int i = scanService.insertCustomer(record);
            return ResultUtil.exec(true, "添加成功", i);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false, "添加失败", null);
        }
    }


    @PutMapping("customsign")
    @CrossOrigin
    public ResultVo pdateScan(EforcesCustomerSign record) {
        try {
            int i = scanService.updateByPrimaryKeySelective(record);
            return ResultUtil.exec(true, "修改成功", i);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false, "修改失败", null);
        }
    }

    @DeleteMapping("customsign")
    @CrossOrigin
    public ResultVo deleteScan(@RequestBody List<Integer> list) {
        try {
            int i = scanService.DeleteById(list);
            return ResultUtil.exec(true, "删除成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false, "删除失败", null);
        }
    }


}
