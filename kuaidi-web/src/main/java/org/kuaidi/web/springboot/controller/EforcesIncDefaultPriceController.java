package org.kuaidi.web.springboot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.kuaidi.bean.domain.EforcesIncDefaultPrice;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesIncDefaultPriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/incdefault/")
public class EforcesIncDefaultPriceController {

    private static Logger logger = LoggerFactory.getLogger(AliPayReturnController.class);

    @Reference(version = "1.0.0")
    private IEforcesIncDefaultPriceService faultPriceService;

    @RequestMapping("getList")
    @CrossOrigin
    @ResponseBody
    public ResultVo getList(Integer id){
       List<EforcesIncDefaultPrice> value = faultPriceService.getList(id);
        return ResultUtil.exec(true,"查询成功",value);
    }
    @RequestMapping("update")
    @CrossOrigin
    @ResponseBody
    public ResultVo update(String joinprice, String remark, String checkRemark, int id){
        int result = faultPriceService.updateIncDefalutPrice(joinprice,remark,checkRemark,id);
        if(result>0){
            return ResultUtil.exec(true,"更新数据成功",result);
         }else {
            return  ResultUtil.exec(false,"更新数据失败",result);
        }
    }
}
