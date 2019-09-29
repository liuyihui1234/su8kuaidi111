package org.kuaidi.web.springboot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;

import java.util.List;

import org.kuaidi.bean.maintainance.EforcesTransportmode;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesTransportmodeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/web/transport/")
public class EforcesTransportmodeController {

    @Reference(version = "1.0.0")
    IEforcesTransportmodeService modeService;


    /**
     * 显示运输方式信息
     * @param page
     * @return
     */
    @GetMapping("Transport")
    @CrossOrigin
    public PageVo getlistTransportmode(QueryPageVo page){
        try {
            PageInfo<EforcesTransportmode> resulltList = modeService.getListTran(page.getPage(),page.getLimit());
            return ResultUtil.exec(resulltList.getPageNum(),resulltList.getPageSize(),resulltList.getTotal(),resulltList.getList());
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(page.getPage(),page.getLimit(),0,null);
        }
    }

    /**
             * 批量删除运输方式信息
     * @param id
     * @return
     */
    @DeleteMapping("Transport")
    @CrossOrigin
    public ResultVo removeTransportmode(@RequestBody Integer[] id){
        try {
           int result = modeService.removeUpdate(id);
           return ResultUtil.exec(true,"删除成功",result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtil.exec(false,"删除失败",null);
    }

/*    *//**
     * 单条删除运输方式信息
     * @param id
     * @return
     *//*
    @RequestMapping("deleteTransportmodes")
    @ResponseBody
    @CrossOrigin
    public ResultVo removeTransportmode(Integer id){
        try {
            Integer[] array = {id};
            int result = modeService.removeUpdate(array);
            return ResultUtil.exec(true,"删除成功",result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtil.exec(false,"删除失败",null);
    }*/

    /**
     * 添加运输方式信息
     * @param tranmode
     * @return
     */
    @CrossOrigin
    @PostMapping("Transport")
    public ResultVo addTransportmode(EforcesTransportmode tranmode){
        try {
            int result = modeService.insertSelective(tranmode);
            return ResultUtil.exec(true,"添加成功",result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtil.exec(false,"添加失败",null);

    }

    /**
     * 修改运输方式
     * @param tranmode
     * @return
     */
    @PutMapping("Transport")
    @CrossOrigin
    public ResultVo updateTransportmode(EforcesTransportmode tranmode){
        try {
            int result = modeService.updateByPrimaryKeySelective(tranmode);
            return ResultUtil.exec(true,"修改成功",result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtil.exec(false,"修改失败",null);
    }

/*    *//**
     * 查询修改运输方式
     * @param id
     * @return
     *//*
    @RequestMapping("getTransport")
    @CrossOrigin
    @ResponseBody
    public ResultVo getTransport(Integer id){
        try {
            EforcesTransportmode result = modeService.selectByPrimaryKey(id);
            return ResultUtil.exec(true,"查询成功",result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtil.exec(false,"查询失败",null);
    }*/
    
    /**
             * 查询所有的运输方式
     * @param page
     * @return
     */
    @RequestMapping("getAllTransportmode")
    @CrossOrigin
    public ResultVo getAllTransportmode(){
        try {
            List<EforcesTransportmode> resulltList = modeService.getAllListTran();
            return ResultUtil.exec(true,"查询所有的运输方式成功！",resulltList);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"查询所有运输方式失败！",null);
        }
    }

}
