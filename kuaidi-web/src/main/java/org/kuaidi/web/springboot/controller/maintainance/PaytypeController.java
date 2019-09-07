package org.kuaidi.web.springboot.controller.maintainance;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.maintainance.EforcesPaytype;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.maintainance.IEforcesPaytypeService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by Administrator on 2019/8/10 17:41
 */
@RestController
@RequestMapping("/web/Paytype/")
public class PaytypeController {
    @Reference(version = "1.0.0")
    IEforcesPaytypeService paytypeService;

    @RequestMapping("selectOne")
    @CrossOrigin
    public ResultVo selectOne(Integer id){
        try {
            EforcesPaytype eforcesPaytype = paytypeService.selectOne(id);
            if(eforcesPaytype != null){
                return ResultUtil.exec(true,"查找成功",eforcesPaytype);
            }
            return ResultUtil.exec(false,"查找失败",null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false,"操作失败",null);
        }
    }

    @RequestMapping("selectAll")
    @CrossOrigin
    public PageVo<EforcesPaytype> selectAll(QueryPageVo page){
        try {
            PageInfo<EforcesPaytype> pageInfo = paytypeService.selectAll(page.getPage(), page.getLimit());
            return ResultUtil.exec(pageInfo.getPageNum(), pageInfo.getSize(), pageInfo.getTotal(), pageInfo.getList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(1, 10, 0, null);
        }
    }

    @RequestMapping("insertOne")
    @CrossOrigin
    public ResultVo insertOne(EforcesPaytype paytype){
        try {
            int a = paytypeService.insertOne(paytype);
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
    public ResultVo updateOne(EforcesPaytype paytype){
        try {
            EforcesPaytype p = paytypeService.selectOne(paytype.getId());
            if(p != null){
                int a = paytypeService.updateOne(paytype);
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
            EforcesPaytype paytype = paytypeService.selectOne(id);
            if(paytype != null){
                Integer[] array= {id};
                int a = paytypeService.updateDelete(array);
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
            int a = paytypeService.updateDelete(ids);
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
