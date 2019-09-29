package org.kuaidi.web.springboot.appcontroller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.Config;
import org.kuaidi.bean.domain.EforcesLeaveword;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.iservice.IEforcesLeaveWordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/app/Leave/")
public class AppLeaveWordController {

    @Reference(version = "1.0.0")
    IEforcesLeaveWordService leaveWordService;
    
    Logger logger = LoggerFactory.getLogger(AppLeaveWordController.class);

    @RequestMapping("getListLeaveMsg")
    @ResponseBody
    public PageVo getList (String incNumber,Integer pageNum,Integer pageSize){
        try {
            if(pageSize == null ||  pageSize <= 0 ){
                pageSize = Config.pageSize;
            }
            if(pageNum == null || pageNum <= 0 ){
                pageNum = 1;
            }
            PageInfo<EforcesLeaveword> pageInfo = leaveWordService.listMsg(incNumber,pageNum,pageSize);
            List<EforcesLeaveword> resultDate= pageInfo.getList();
            //循环得到集合里的状态转换 0:未处理 1:已处理
            String type;
            for (int i = 0;i < resultDate.size(); i++){
                if(resultDate.get(i).getStatus().equals("0")){
                    type = "未处理";
                }else{
                    type = "已处理";
                }
                resultDate.get(i).setStatus(type);
            }
            return ResultUtil.exec(pageNum,pageSize,pageInfo.getTotal(),resultDate);
        }catch (Exception e){
        	logger.error(e.getMessage());
            return ResultUtil.exec(pageNum,pageSize,0,null);
        }
    }
}
