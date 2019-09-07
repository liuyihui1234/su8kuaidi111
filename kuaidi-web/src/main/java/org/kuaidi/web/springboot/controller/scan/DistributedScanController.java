package org.kuaidi.web.springboot.controller.scan;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesCustomerSign;
import org.kuaidi.bean.domain.EforcesDistributedScan;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesCustomerSignService;
import org.kuaidi.iservice.IEforcesDistributedScanService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web/distributedScan/")
public class DistributedScanController {
    @Reference(version = "1.0.0")
    IEforcesDistributedScanService scanService;

    @RequestMapping("getAll")
    @CrossOrigin
    public PageVo getAll(QueryPageVo page) {
        try {
            PageInfo<EforcesDistributedScan> alldistribute = scanService.getAlldistribute(page.getPage(), page.getLimit(), page.getId());
            return ResultUtil.exec(alldistribute.getPageNum(), alldistribute.getSize(), alldistribute.getTotal(), alldistribute.getList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(1, 10, 0, null);
        }
    }


    @RequestMapping("getById")
    @CrossOrigin
    public ResultVo getById(Integer id) {
        try {
            EforcesDistributedScan eforcesDistributedScan = scanService.selectById(id);
            return ResultUtil.exec(true, "查询成功", eforcesDistributedScan);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(true, "查询失败", null);
        }
    }

    @RequestMapping("addDistributedScan")
    @CrossOrigin
    public ResultVo addDistributedScan(EforcesDistributedScan record) {
        try {
            int i = scanService.insertSelective(record);
            return ResultUtil.exec(true, "添加成功", i);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(true, "添加失败", null);
        }
    }

    @RequestMapping("updateDistributedScan")
    @CrossOrigin
    public ResultVo updateDistributedScan(EforcesDistributedScan record) {
        try {
            int i = scanService.updateByPrimaryKeySelective(record);
            return ResultUtil.exec(true, "修改成功", i);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(true, "修改失败", null);
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
