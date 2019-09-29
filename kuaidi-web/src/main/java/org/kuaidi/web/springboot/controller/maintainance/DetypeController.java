package org.kuaidi.web.springboot.controller.maintainance;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;

import java.util.List;

import org.kuaidi.bean.maintainance.EforcesDetype;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.maintainance.IEforcesDetypeService;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2019/8/10 13:55
 */
@RestController
@RequestMapping("/web/Detype/")
public class DetypeController {
    @Reference(version = "1.0.0")
    IEforcesDetypeService detypeService;

    @GetMapping("Detype")
    @ResponseBody
    @CrossOrigin
    public PageVo<EforcesDetype> selectAll(QueryPageVo page){
        try {
            PageInfo<EforcesDetype> pageInfo = detypeService.selectAll(page.getPage(), page.getLimit());
            return ResultUtil.exec(pageInfo.getPageNum(), pageInfo.getSize(), pageInfo.getTotal(), pageInfo.getList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(1, 10, 0, null);
        }
    }

    @RequestMapping("selectOne")
    @ResponseBody
    @CrossOrigin
    public ResultVo selectOne(Integer id){
        try {
            EforcesDetype eforcesDetype = detypeService.selectOne(id);
            if(eforcesDetype != null){
                return ResultUtil.exec(true,"查找成功",eforcesDetype);
            }
            return ResultUtil.exec(false,"查找失败",null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false,"操作失败",null);
        }
    }

    @PostMapping("Detype")
    @ResponseBody
    @CrossOrigin
    public ResultVo insertOne(EforcesDetype detype){
        try {
            int a = detypeService.insertOne(detype);
            if (a > 0){
                return ResultUtil.exec(true,"添加成功",null);
            }
            return ResultUtil.exec(false,"添加失败",null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false,"操作失败",null);
        }
    }

    @PutMapping("Detype")
    @ResponseBody
    @CrossOrigin
    public ResultVo updateOne(EforcesDetype detype){
        try {
            EforcesDetype d = detypeService.selectOne(detype.getId());
            if(d != null){
                int a = detypeService.updateOne(detype);
                if (a > 0){
                    return ResultUtil.exec(true,"修改成功",null);
                }
                return ResultUtil.exec(false,"修改失败",null);
            }
            return ResultUtil.exec(false,"传入参数有误，该id无对应信息",null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false,"操作失败",null);
        }
    }

    @RequestMapping("updateDelete")
    @ResponseBody
    @CrossOrigin
    public ResultVo updateDelete(Integer id){
        try {
            EforcesDetype detype = detypeService.selectOne(id);
            if(detype != null){
                Integer[] array= {id};
                int a = detypeService.updateDelete(array);
                if (a > 0){
                    return ResultUtil.exec(true,"删除成功",null);
                }
                return ResultUtil.exec(false,"删除失败",null);
            }
            return ResultUtil.exec(false,"传入参数有误，该id无对应信息",null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false,"操作失败",null);
        }
    }

    @DeleteMapping("Detype")
    @ResponseBody
    @CrossOrigin
    public ResultVo updateDelete(@RequestBody Integer[] ids){
        try {
            int a = detypeService.updateDelete(ids);
            if (a > 0){
                return ResultUtil.exec(true,"删除成功",null);
            }
            return ResultUtil.exec(false,"删除失败",null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false,"操作失败",null);
        }
    }
    
    @RequestMapping("getAllRecord")
    @ResponseBody
    @CrossOrigin
    public ResultVo getAllRecord(QueryPageVo page){
        try {
            List<EforcesDetype> detyList = detypeService.selectAll();
            return ResultUtil.exec(true,"查询物品类型成功！",detyList );
        }catch(Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false,"查询物品类型失败！",null);
        }
    }
}
