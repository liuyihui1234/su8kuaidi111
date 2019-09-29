package org.kuaidi.web.springboot.webcontroller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.commons.lang3.StringUtils;
import org.kuaidi.bean.domain.EforcesOrder;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesOrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/web/order/")
public class OrderController {

    @Reference(version = "1.0.0")
    IEforcesOrderService orderService;

    /**
     * 详细查询
     * @param //number
     * @return
     */
    @CrossOrigin
    @RequestMapping("getOrderShow")
    public ResultVo getOrderShow(QueryPageVo page){
        try {
            if(StringUtils.isNotEmpty(page.getInfo1())){
               String id = page.getInfo1();
                Integer integerId = Integer.valueOf(id);
                EforcesOrder result = orderService.getByid(integerId);
            }
            return ResultUtil.exec(true,"查询成功",null);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"查询失败",null);
        }
    }

}
