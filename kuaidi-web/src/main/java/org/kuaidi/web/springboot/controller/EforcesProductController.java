package org.kuaidi.web.springboot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.kuaidi.bean.domain.EforcesProduct;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/web/Product/")
public class EforcesProductController {

    @Reference(version = "1.0.0")
    IEforcesProductService productService;

    /**
     * 查询物料品名维护信息
     * @param page
     * @return
     */
    @GetMapping("Product")
    @ResponseBody
    @CrossOrigin
    public PageVo getListMsg (QueryPageVo page){
        try {
            EforcesProduct product = new EforcesProduct();
            if(StringUtils.isNotEmpty(page.getInfo1())){
                product.setName(page.getInfo1());
            }
            if(StringUtils.isNotEmpty(page.getInfo2())){
                product.setNumber(page.getInfo2());
            }

            PageInfo<EforcesProduct> pageInfo = productService.getlist(page.getPage(),page.getLimit(),product);
            return ResultUtil.exec(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getList());
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(page.getPage(),page.getLimit(),0,null);
        }
    }

    /**
     * 添加物料品名维护信息
     * @param product
     * @return
     */
    @PostMapping("Product")
    @ResponseBody
    @CrossOrigin
    public ResultVo addProduct(EforcesProduct product){
        try {
            int result = productService.insertSelective(product);
            return ResultUtil.exec(true,"添加成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"添加失败",0);
        }
    }
    /**
     * 修改物料品名维护信息
     * @param product
     * @return
     */
    @PutMapping("Product")
    @ResponseBody
    @CrossOrigin
    public ResultVo updateProduct(EforcesProduct product){
        try {
            int result = productService.updateByPrimaryKeySelective(product);
            return ResultUtil.exec(true,"修改成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"修改失败",0);
        }
    }

/*    *//**
     * 单个删除
     * @param id
     * @return
     *//*
    @RequestMapping("removeDeleteProfuct")
    @ResponseBody
    @CrossOrigin
    public ResultVo removeUpdate(Integer id){
        try {
            Integer[] array ={id};
            int result = productService.removeUpdate(array);
            return ResultUtil.exec(true,"删除成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"删除失败",0);
        }
    }*/


    /**
     * 批量删除
     * @param id
     * @return
     */
    @DeleteMapping("Product")
    @ResponseBody
    @CrossOrigin
    public ResultVo removeUpdateS(@RequestBody Integer[] id){
        try {
            int result = productService.removeUpdate(id);
            return ResultUtil.exec(true,"删除成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"删除失败",0);
        }
    }



    /**
     * 查询要修改的数据
     * @param id
     * @return
     */
    @RequestMapping("showProduct")
    @ResponseBody
    @CrossOrigin
    public ResultVo showProduct(Integer id){
        try {
            EforcesProduct result = productService.selectByPrimaryKey(id);
            return ResultUtil.exec(true,"查询数据成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"查询数据失败",0);
        }
    }

}
