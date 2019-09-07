package org.kuaidi.web.springboot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesLogisticStracking;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforceslogisticstrackingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/web/logisticstracking/")
public class LogisticstrackingController {
    @Reference(version ="1.0.0")
    IEforceslogisticstrackingService service;

    /**
     * 物流跟踪记录
     * @param page
     * @return
     */
    @RequestMapping("getlogisticstracking")
    @ResponseBody
    @CrossOrigin
    public PageVo logisticstracking(QueryPageVo page) {
        try {
            PageInfo<EforcesLogisticStracking> result = service.getListStracking(page.getPage(),page.getLimit());
            return ResultUtil.exec(result.getPageNum(),result.getSize(),result.getTotal(),result.getList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(page.getPage(),page.getLimit(),0,null);
        }
    }

    /**
     * 删除物流跟踪记录
     * @param id
     * @return
     */
    @RequestMapping("deleteLogistics")
    @CrossOrigin
    public ResultVo deleteDistributedScan(Integer id){
        try {
            Integer[] array = {id};
            int result = service.deleteLogisticStraking(array);
            return ResultUtil.exec(true,"删除成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"删除失败",null);
        }
    }


    @RequestMapping("insertLogisticSelective")
    @CrossOrigin
    public ResultVo insertLogisticSelective(EforcesLogisticStracking record){
        try {
            int i = service.insertLogisticSelective(record);
            return ResultUtil.exec(true,"添加成功",null);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"添加失败",null);
        }
    }

    /**
     * 删除物流跟踪记录
     * @param array
     * @return
     */
    @RequestMapping("deleteLogisticss")
    @CrossOrigin
    public ResultVo deleteDistributedScans(@RequestBody Integer[] array){
        try {
            int result = service.deleteLogisticStraking(array);
            return ResultUtil.exec(true,"删除成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"删除失败",null);
        }
    }


    /**
     * 根据多个运单编号查询
     * @param billsNumber
     * @return
     */
    @RequestMapping("getlistbillsNumber")
    @CrossOrigin
    public ResultVo getlistbillsNumber(Integer billsNumber){
        try {
            Integer[] array = {billsNumber};
            List<EforcesLogisticStracking> result = service.getListBillsNumber(array);
            return ResultUtil.exec(true,"查询成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"查询失败",null);
        }
    }



}
