package org.kuaidi.web.springboot.appcontroller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.kuaidi.bean.domain.EforcesVersioninfo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesVersioninfoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Version/")
public class EforcesVersioninfoController {
    @Reference(version = "1.0.0")
    IEforcesVersioninfoService versioninfoService;

    @RequestMapping("getlist")
    @ResponseBody
    public ResultVo getlistDasy(int type){
     if(type>=0){
         List<EforcesVersioninfo> dispaly = versioninfoService.getlist(type);
         if(dispaly != null && dispaly.size() > 0 ) {
        	 return ResultUtil.exec(true,"查詢成功",dispaly.get(0));
         }
         
         return ResultUtil.exec(false,"没有找到对应类型的更新信息！",null);
     }else{
         return ResultUtil.exec(false,"查詢失敗",null);
     }
    }
}
