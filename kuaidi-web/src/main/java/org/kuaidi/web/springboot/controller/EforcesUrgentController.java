package org.kuaidi.web.springboot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.maintainance.EforcesUrgent;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesUrgentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/web/Urgent/")
public class EforcesUrgentController {

    @Reference(version = "1.0.0")
    IEforcesUrgentService urgentService;

    /**
     * 显示业务类型
     * @param page
     * @return
     */
    @GetMapping("Urgent")
    @CrossOrigin
    public PageVo getlist(QueryPageVo page){
        try {
            PageInfo<EforcesUrgent> listResult = urgentService.listResult(page.getPage(),page.getLimit());
            return ResultUtil.exec(listResult.getPageNum(),listResult.getPageSize(),listResult.getTotal(),listResult.getList());
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtil.exec(page.getPage(),page.getLimit(),0,null);
    }


    /**
     * 修改业务类型
     * @param urgent
     * @return
     */
    @PutMapping("Urgent")
    @CrossOrigin
    public ResultVo updateUrgent(EforcesUrgent urgent){
        try{
            int result = urgentService.updateByPrimaryKeySelective(urgent);
            return ResultUtil.exec(true,"修改业务类型成功",result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtil.exec(false,"修改业务类型失败",null);
    }
    /**
     * 添加业务类型
     * @param urgent
     * @return
     */
    @PostMapping("Urgent")
    @CrossOrigin
    public ResultVo addUrgent(EforcesUrgent urgent){
        try{
            int result = urgentService.insertSelective(urgent);
            return ResultUtil.exec(true,"添加业务类型成功",result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtil.exec(false,"添加业务类型失败",null);
    }
    /**
     * 批量删除业务类型
     * @param id
     * @return
     */
    @DeleteMapping("Urgent")
    @CrossOrigin
    public ResultVo removeUpdateUrgent(@RequestBody Integer[] id){
        try{
            int result = urgentService.removeUpdate(id);
            return ResultUtil.exec(true,"删除业务类型成功",result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtil.exec(false,"删除业务类型失败",null);
    }

/*    *//**
     * 单条删除业务类型
     * @param id
     * @return
     *//*
    @RequestMapping("removedeletes")
    @CrossOrigin
    @ResponseBody
    public ResultVo removeUpdateUrgents(Integer id){
        try{
            Integer[] array = {id};
            int result = urgentService.removeUpdate(array);
            return ResultUtil.exec(true,"删除业务类型成功",result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtil.exec(false,"删除业务类型失败",null);
    }*/


/*    *//**
     * 查询修改的数据
     * @param id
     * @return
     *//*
    @GetMapping("Urgent")
    @CrossOrigin
    public ResultVo showUrgent(Integer id){
        try {
            EforcesUrgent result = urgentService.selectByPrimaryKey(id);
            return ResultUtil.exec(true,"查询成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"查询失败",null);
        }
    }*/

}
