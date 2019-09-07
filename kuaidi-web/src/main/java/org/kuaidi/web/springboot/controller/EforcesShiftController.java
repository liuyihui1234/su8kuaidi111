package org.kuaidi.web.springboot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.maintainance.EforcesShift;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesShiftService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/web/Shift/")
public class EforcesShiftController {

    @Reference(version = "1.0.0")
    IEforcesShiftService service;

    /**
     * 查询班次管理
     * @param page
     * @return
     */
    @RequestMapping("getListShift")
    @ResponseBody
    @CrossOrigin
    public PageVo getListShift(QueryPageVo page) {
        try {
            PageInfo<EforcesShift> listShift = service.selectByShiftlist(page.getPage(),page.getLimit());
            return ResultUtil.exec(listShift.getPageNum(),listShift.getPageSize(),listShift.getTotal(),listShift.getList());
        } catch (Exception e) {
            e.printStackTrace();
            return  ResultUtil.exec(page.getPage(),page.getLimit(),0,null);
        }
    }

    /**
     * 批量删除班次管理
     * @param id
     * @return
     */
    @DeleteMapping("Shift")
    @CrossOrigin
    public ResultVo removeUpdate (@RequestBody Integer[] id){
        try {
            int result = service.removeByShift(id);
            return ResultUtil.exec(true,"删除成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"删除失败",0);
        }
    }

/*    *//**
     * 单个删除班次管理
     * @param id
     * @return
     *//*
    @DeleteMapping("removeUpdates")
    @CrossOrigin
    public ResultVo removeUpdates (Integer id){
        try {
            Integer[] array = {id};
            int result = service.removeByShift(array);
            return ResultUtil.exec(true,"删除成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"删除失败",0);
        }
    }*/

    /**
     * 添加班次管理
     * @param Shift
     * @return
     */
    @PostMapping("Shift")
    @ResponseBody
    @CrossOrigin
    public ResultVo addShift(EforcesShift Shift){
     try {
         int shiftResult = service.insertSelective(Shift);
         return ResultUtil.exec(true,"添加班次管理成功",shiftResult);
     }catch (Exception e){
         e.printStackTrace();
         return ResultUtil.exec(false,"添加班次管理失败",0);
     }
    }

    /**
     * 修改班次管理
     * @param Shift
     * @return
     */
    @PutMapping("Shift")
    @ResponseBody
    @CrossOrigin
    public ResultVo updateShift(EforcesShift Shift){
        try {
            int result = service.updateByPrimaryKeySelective(Shift);
        return ResultUtil.exec(true,"修改班次管理成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"修改班次管理失败",0);
        }
    }

    /**
     * 根据id查询数据修改班次管理
     * @param id
     * @return
     */
    @RequestMapping("ShowSift")
    @ResponseBody
    @CrossOrigin
    public ResultVo showShift(Integer id){
        try {
            EforcesShift result = service.selectByPrimaryKey(id);
            return ResultUtil.exec(true,"查询成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"查询失败",null);
        }
    }
}
