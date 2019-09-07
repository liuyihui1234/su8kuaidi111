package org.kuaidi.web.springboot.controller.scan;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesCustomerSign;
import org.kuaidi.bean.domain.EforcesStayedandtroubledscan;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesCustomerSignService;
import org.kuaidi.iservice.IEforcesStayedandtroubledscanService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/web/CustomSign/")
public class CustomSignController {
    @Reference(version = "1.0.0")
    IEforcesCustomerSignService scanService;

    @RequestMapping("getAll")
    @CrossOrigin
    public PageVo getAll(QueryPageVo page) {
        try {
            PageInfo<EforcesCustomerSign> allSign = scanService.getAllSign(page.getPage(), page.getLimit(), page.getId());
            return ResultUtil.exec(allSign.getPageNum(), allSign.getSize(), allSign.getTotal(), allSign.getList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(1, 10, 0, null);
        }
    }


    @RequestMapping("getById")
    @CrossOrigin
    public ResultVo getById(Integer id) {
        try {
            EforcesCustomerSign eforcesCustomerSign = scanService.selectByPrimaryKey(id);
            return ResultUtil.exec(true, "查询成功", eforcesCustomerSign);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(true, "查询失败", null);
        }
    }

    @RequestMapping("addCustomSign")
    @CrossOrigin
    public ResultVo addWeighingScan(EforcesCustomerSign record) {
        try {
            int i = scanService.insertCustomer(record);
            return ResultUtil.exec(true, "添加成功", i);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(true, "添加失败", null);
        }
    }

//
//
//
//
//    @RequestMapping("addstayedandtroubledscan")
//    @CrossOrigin
//    public ResultVo addWeighingScan(EforcesStayedandtroubledscan record) {
//        try {
//            int i = scanService.insertSelective(record);
//            return ResultUtil.exec(true, "添加成功", i);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResultUtil.exec(true, "添加失败", null);
//        }
//    }

}
