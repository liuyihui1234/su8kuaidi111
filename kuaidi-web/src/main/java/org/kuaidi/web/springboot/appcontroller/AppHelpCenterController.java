package org.kuaidi.web.springboot.appcontroller;

import org.kuaidi.bean.Config;
import org.kuaidi.bean.domain.EforcesHelpcente;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.iservice.EforcesHelpcenteService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/app/Helpcenter/")
public class AppHelpCenterController {
	
	@Reference(version = "1.0.0")
    EforcesHelpcenteService helpcenteService;
	
	@RequestMapping("getListByPage")
    @CrossOrigin
    public PageVo<EforcesHelpcente> getListByPage(Integer pageNum, Integer pageSize){
        try {
        	if(pageNum == null || pageNum < 1 ) {
        		pageNum = 1 ;
        	}
        	if(pageSize == null || pageSize < 1) {
        		pageSize = Config.pageSize;
        	}
           PageInfo<EforcesHelpcente> result = helpcenteService.getCountdata(pageNum,pageSize);
           return ResultUtil.exec(result.getPageNum(),result.getSize(),result.getTotal(),"查询数据成功！",result.getList());
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(pageNum,pageSize,0 ,"查询帮助中心操作失败！",null);
        }
    }

}
