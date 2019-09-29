package org.kuaidi.web.springboot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesDepartment;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesDepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/web/Department/")
@RestController
public class EforcesDepartmentController {
    @Reference(version = "1.0.0")
    IEforcesDepartmentService Department;


    /**
     * 显示企业部门管理
     * @param page
     * @return
     */
    @GetMapping("Department")
    @ResponseBody
    @CrossOrigin
    public PageVo getlist(QueryPageVo page){
        try {
            PageInfo<EforcesDepartment> pageInfo = Department.getList(page.getPage(),page.getLimit());
            return ResultUtil.exec(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getList());
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtil.exec(page.getPage(),page.getLimit(),0,null);
    }

    /**
     *  不分页
     *
     */
    @RequestMapping("getListTwo")
    @ResponseBody
    @CrossOrigin
    public ResultVo getListTwo(){
        try {
            List<EforcesDepartment> list=Department.getListTwo();
            return ResultUtil.exec(true, "查询成功",list);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false, "查询失败",null);
        }
    }


    /**
     * 添加企业部门管理
     * @param department
     * @return
     */
    @PostMapping("Department")
    @ResponseBody
    @CrossOrigin
    public ResultVo addDepartment(EforcesDepartment department){
        try {
            int result = Department.insertSelective(department);
            return ResultUtil.exec(true,"添加数据成功",result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtil.exec(false,"添加数据失败",0);
    }

    /**
     * 修改企业部门
     * @param department
     * @return
     */
    @PutMapping("Department")
    @ResponseBody
    @CrossOrigin
    public ResultVo updateDepartment(EforcesDepartment department){
        try {
            int result = Department.updateByPrimaryKeySelective(department);
            return ResultUtil.exec(true,"修改成功",result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtil.exec(false,"修改失败",0);
    }

    /**
     * 批量删除企业部门
     * @param id
     * @return
     */
    @DeleteMapping("Department")
    @CrossOrigin
    public ResultVo removeUpdate(@RequestBody Integer[] id){
        try {
            int result = Department.removeUpdate(id);
            return ResultUtil.exec(true,"删除成功",result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtil.exec(false,"删除失败",0);
    }

    /**
     * 查询修改企业部门管理数据
     * @param id
     * @return
     */
    @RequestMapping("showDepartment")
    @CrossOrigin
    @ResponseBody
    public ResultVo showDepartment(Integer id){
        try {
            EforcesDepartment result = Department.selectByPrimaryKey(id);
            return ResultUtil.exec(true,"查询成功",result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtil.exec(false,"查询失败",null);
    }


    /**
     * 单条删除企业部门
     * @param id
     * @return
     */
    @RequestMapping("removeUpdates")
    @CrossOrigin
    public ResultVo removeUpdate(Integer id){
        try {
            Integer[] array = {id};
            int result = Department.removeUpdate(array);
            return ResultUtil.exec(true,"删除成功",result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtil.exec(false,"删除失败",0);
    }

}
