package org.kuaidi.web.springboot.controller.scan;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesStayedandtroubledscan;
import org.kuaidi.bean.domain.EforcesWeighingScan;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesStayedandtroubledscanService;
import org.kuaidi.iservice.IEforcesWeighingScanService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/web/stayedandtroubledscan/")
public class StayedandtroubledscanController {
    @Reference(version = "1.0.0")
    IEforcesStayedandtroubledscanService scanService;

    @GetMapping("scan")
    @CrossOrigin
    public PageVo getAll(QueryPageVo page) {
        try {
            PageInfo<EforcesStayedandtroubledscan> eforcesUsers = scanService.getAllIssue(page.getPage(), page.getLimit(), page.getId());
            System.out.println(eforcesUsers);
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
     *
     * @param record
     * @return
     */

    @PostMapping("scan")
    @CrossOrigin
    public ResultVo addWeighingScan(EforcesStayedandtroubledscan record) {
        try {
            int i = scanService.insertSelective(record);
            return ResultUtil.exec(true, "添加成功", i);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(true, "添加失败", null);
        }
    }

    /**
     *修改
     *
     * @return
     */

    @PutMapping("scan")
    @CrossOrigin
    public ResultVo updateScan(EforcesStayedandtroubledscan record) {
        try {
            int i = scanService.updateByPrimaryKeySelective(record);
            return ResultUtil.exec(true, "添加成功", i);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(true, "添加失败", null);
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
            int i = scanService.deleteByid(list);
            return ResultUtil.exec(true, "删除成功", i);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(true, "删除失败", null);
        }
    }




}

