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
    @GetMapping("logisticstracking")
    @ResponseBody
    @CrossOrigin
    public PageVo logisticstracking(QueryPageVo page,String billsnumber) {
        try {
            PageInfo<EforcesLogisticStracking> result = service.getListStracking(page.getPage(),page.getLimit(),billsnumber);
            return ResultUtil.exec(result.getPageNum(),result.getSize(),result.getTotal(),result.getList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(page.getPage(),page.getLimit(),0,null);
        }
    }

/*    *//**
     * 删除物流跟踪记录
     * @param id
     * @return
     *//*
    @DeleteMapping("logisticstracking")
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
    }*/


    /**
     * 添加物流跟踪
     * @param record
     * @return
     */
    @PostMapping("logisticstracking")
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
     * @param id
     * @return
     */
    @DeleteMapping("logisticstracking")
    @CrossOrigin
    public ResultVo deleteDistributedScans(@RequestBody Integer[] id){
        try {
            int result = service.deleteLogisticStraking(id);
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
     *
     */
    @RequestMapping("logisticstracking")
    @CrossOrigin
    public ResultVo getlistbillsNumber(@RequestBody String billsNumber){
        try {
            String[] split = billsNumber.split("\\s+");
            List<EforcesLogisticStracking> result = service.getListBillsNumber(split);
            return ResultUtil.exec(true,"查询成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"查询失败",null);
        }
    }



}
