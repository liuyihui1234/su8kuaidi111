package org.kuaidi.web.springboot.appcontroller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.kuaidi.bean.domain.EforcesHomepagepic;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesHomepagepicService;
import org.kuaidi.web.springboot.controller.AliPayReturnController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Homepage/")
public class EforcesHomepagepricesController {
    private static Logger logger = LoggerFactory.getLogger(AliPayReturnController.class);

    @Reference(version = "1.0.0")
    IEforcesHomepagepicService homepagepicService;

    @RequestMapping("checkHome")
    @ResponseBody
    public ResultVo HomepageCar(Integer id){
        List<EforcesHomepagepic> homepagePicList;
        try{
            homepagePicList = homepagepicService.selectByDivide(id);
            if (homepagePicList != null && !homepagePicList.isEmpty()){
                return ResultUtil.exec(true, "", homepagePicList);
            }else{
                return ResultUtil.exec(false,"查询失败",null);
            }
        }catch(Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"异常",e.getMessage());
        }
    }
}
