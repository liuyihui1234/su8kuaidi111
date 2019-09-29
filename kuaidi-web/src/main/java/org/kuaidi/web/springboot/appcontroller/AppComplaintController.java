package org.kuaidi.web.springboot.appcontroller;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;

import org.kuaidi.bean.Config;
import org.kuaidi.bean.domain.EforcesComplaint;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.iservice.IEforcesComplaintService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/comPlaint/")
public class AppComplaintController {

    @Reference(version = "1.0.0")
    IEforcesComplaintService complaintService;
    /**
           * 投诉记录
     * @param incNumber
     * @return
     */
    @RequestMapping("getMsg")
    @ResponseBody
    public PageVo getComplaint(Integer pageNum , Integer pageSize , String incNumber){
        try {
        	if(pageNum == null || pageNum == 0 ) {
        		pageNum = 1 ; 
        	}
        	if(pageSize == null || pageSize < 1 ) {
        		pageSize = Config.pageSize;
        	}
        	PageInfo<EforcesComplaint> request = complaintService.getcomplaint(pageNum, pageSize , incNumber);
           if(request.getTotal() > 0 ) {
        	   return ResultUtil.exec(pageNum , pageSize ,request.getTotal(),"查询成功！",request.getList());
           }else {
        	   return ResultUtil.exec(pageNum , pageSize ,0,"暂时没有数据！",request.getList());
           }
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(pageNum , pageSize ,0,"查询失败",null);
        }
    }
}
