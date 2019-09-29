package org.kuaidi.web.springboot.webcontroller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesPrice;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesPrice;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("web/price/")
public class EforcesPricesController {

    @Reference(version="1.0.0")
    IEforcesPrice price;

    /**
     * 获取所有地区所有价格信息
     * @param page
     * @return
     */
    @GetMapping("price")
    @CrossOrigin
    public PageVo getByPrice(QueryPageVo page){
        try {
            PageInfo<EforcesPrice> result = price.getByPrice(page.getPage(),page.getLimit());
            return ResultUtil.exec(result.getPageNum(),result.getPageSize(),result.getTotal(),result.getList());
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(page.getPage(),page.getLimit(),0,null);
        }
    }


    /**
     * 添加地区价格信息
     * @param priceObject
     * @return
     */
    @PostMapping("price")
    @CrossOrigin
    public ResultVo addPrice(EforcesPrice priceObject){
        try {
            int result = price.insertSelective(priceObject);
            return ResultUtil.exec(true,"添加成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"添加失败",0);
        }
    }


    /**
     * 删除地区价格信息
     * @param id
     * @return
     */
    @DeleteMapping("price")
    @CrossOrigin
    public ResultVo deletePrice (@RequestBody Integer[] id){
        try {
            int result = price.removeByPrice(id);
            return ResultUtil.exec(true,"删除成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"删除失败",0);
        }
    }

    /**
     * 修改地区价格信息
     * @param priceObject
     * @return
     */
    @PutMapping("price")
    @CrossOrigin
    public ResultVo updatePrice(EforcesPrice priceObject){
        try {
            int result = price.updateByPrimaryKeySelective(priceObject);
            return ResultUtil.exec(true,"修改成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"修改失败",0);
        }
    }

}
