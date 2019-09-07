package org.kuaidi.web.springboot.controller.maintainance;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.maintainance.EforcesProblemcause;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.maintainance.IEforcesDetypeService;
import org.kuaidi.iservice.maintainance.IEforcesProblemcauseService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2019/8/10 17:42
 */
@RestController
@RequestMapping("/web/Problemcause/")
public class ProblemcauseController {

    @Reference(version = "1.0.0")
    IEforcesProblemcauseService problemcauseService;

    @RequestMapping("selectOne")
    @CrossOrigin
    public ResultVo selectOne(Integer id){
        try {
            EforcesProblemcause eforcesProblemcause = problemcauseService.selectOne(id);
            if(eforcesProblemcause != null){
                return ResultUtil.exec(true,"查找成功",eforcesProblemcause);
            }
            return ResultUtil.exec(false,"查找失败",null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false,"操作失败",null);
        }
    }

    @RequestMapping("selectAll")
    @CrossOrigin
    public PageVo<EforcesProblemcause> selectAll(QueryPageVo page){
        try {
            PageInfo<EforcesProblemcause> pageInfo = problemcauseService.selectAll(page.getPage(), page.getLimit());
            return ResultUtil.exec(pageInfo.getPageNum(), pageInfo.getSize(), pageInfo.getTotal(), pageInfo.getList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(1, 10, 0, null);
        }
    }

    @RequestMapping("insertOne")
    @CrossOrigin
    public ResultVo insertOne(EforcesProblemcause problemcause){
        try {
            int a = problemcauseService.insertOne(problemcause);
            if (a > 0){
                return ResultUtil.exec(true,"添加成功",null);
            }
            return ResultUtil.exec(false,"添加失败",null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false,"操作失败",null);
        }
    }

    @RequestMapping("updateOne")
    @CrossOrigin
    public ResultVo updateOne(EforcesProblemcause problemcause){
        try {
            EforcesProblemcause p = problemcauseService.selectOne(problemcause.getId());
            if(p != null){
                int a = problemcauseService.updateOne(problemcause);
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
    @CrossOrigin
    public ResultVo updateDelete(Integer id){
        try {
            EforcesProblemcause problemcause = problemcauseService.selectOne(id);
            if(problemcause != null){
                Integer[] array= {id};
                int a = problemcauseService.updateDelete(array);
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

    @RequestMapping("updateDeletes")
    @CrossOrigin
    public ResultVo updateDelete(@RequestBody Integer[] ids){
        try {
            int a = problemcauseService.updateDelete(ids);
            if (a > 0){
                return ResultUtil.exec(true,"删除成功",null);
            }
            return ResultUtil.exec(false,"删除失败",null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false,"操作失败",null);
        }
    }
}
