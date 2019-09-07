package org.kuaidi.web.springboot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.kuaidi.bean.maintainance.EforcesSupplier;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesSupplierService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/web/Supplier/")
public class EforcesSupplierController {
    @Reference(version = "1.0.0")
    IEforcesSupplierService supplierServic;

    /**
     * 获取供应商列表信息
     * @return
     */
    @GetMapping("Supplier")
    @CrossOrigin
    @ResponseBody
    public PageVo getlistSupplier(QueryPageVo page){
        EforcesSupplier supplier = new EforcesSupplier();
        try {
            if(StringUtils.isNotEmpty(page.getInfo2())){
                supplier.setNumber(page.getInfo2());
            }
            if(StringUtils.isNotEmpty(page.getInfo1())){
                supplier.setName(page.getInfo1());
            }
            PageInfo<EforcesSupplier> resultList = supplierServic.getlistSupplier(page.getPage(),page.getLimit(),supplier);

            return ResultUtil.exec(resultList.getPageNum(),resultList.getSize(),resultList.getTotal(),resultList.getList());
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtil.exec(page.getPage(),page.getLimit(),0,null);
    }


    /**
     * 批量删除
     * @param id
     * @return
     */
    @DeleteMapping("Supplier")
    @CrossOrigin
    @ResponseBody
    public ResultVo removeUpdate (@RequestBody Integer[] id){
        try {
            int result = supplierServic.removeUpdate(id);
            return ResultUtil.exec(true,"删除成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"删除失败",0);
        }
    }

/*    *//**
     * 单个删除
     * @param id
     * @return
     *//*
    @RequestMapping("removeUpdates")
    @ResponseBody
    @CrossOrigin
    public ResultVo removeUpdates(Integer id){
        try {
            Integer[] array ={id};
            int result = supplierServic.removeUpdate(array);
            return ResultUtil.exec(true,"删除成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"删除失败",0);
        }
    }*/


    /**
     * 增加
     * @param supplier
     * @return
     */
    @PostMapping("Supplier")
    @CrossOrigin
    @ResponseBody
    public ResultVo addSupplier(EforcesSupplier supplier){
        try {
            int result = supplierServic.insertSelective(supplier);
            return ResultUtil.exec(true,"增加信息成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"增加信息失败",0);
        }
    }
    /**
     * 修改
     * @param supplier
     * @return
     */
    @PutMapping("Supplier")
    @CrossOrigin
    @ResponseBody
    public ResultVo updateSupplier(EforcesSupplier supplier){
        try {
            int result = supplierServic.updateByPrimaryKeySelective(supplier);
            return ResultUtil.exec(true,"修改信息成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"修改信息失败",0);
        }
    }

/*    *//**
     * 查询需要修改的数据
     * @param id
     * @return
     *//*
    @GetMapping("Supplier")
    @CrossOrigin
    @ResponseBody
    public ResultVo showSupplier(Integer id){
        try {
            EforcesSupplier result = supplierServic.selectByPrimaryKey(id);
            return ResultUtil.exec(true,"查询成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"查询失败",null);
        }
    }*/

}
