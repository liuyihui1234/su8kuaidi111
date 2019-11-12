package org.kuaidi.web.springboot.controller.scan;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.kuaidi.bean.domain.EforcesIncment;
import org.kuaidi.bean.domain.EforcesReceivedScan;
import org.kuaidi.bean.domain.EforcesUser;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesBiggingScanService;
import org.kuaidi.iservice.IEforcesReceivedscanService;
import org.kuaidi.web.springboot.core.authorization.NeedUserInfo;
import org.kuaidi.web.springboot.service.HandlingOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/web/receivedbagscan/")
public class ReceiveBagScanController {
	
	@Reference(version = "1.0.0")
	IEforcesBiggingScanService bagScanService;
	
	@Autowired
	HandlingOrdersService  orderService;
	
	@GetMapping("receivedbagscan")
    @ResponseBody
    @CrossOrigin
    @NeedUserInfo
    public PageVo doGetAllOrderSelective(QueryPageVo page,HttpServletRequest request){
        try {
        	EforcesUser userInfo = (EforcesUser)request.getAttribute("user");
            PageInfo<Map<String, Object>> pageInfo = bagScanService.getAllReceiveBaggingScan(page.getPage(), page.getLimit(), userInfo.getIncid());
            //修改时间。
            
            return ResultUtil.exec(pageInfo.getPageNum(),pageInfo.getSize(),pageInfo.getTotal(),pageInfo.getList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(1,10,0,null);
        }
    }
	
	
	@PostMapping("receivedbagscan")
    @ResponseBody
    @CrossOrigin
    @NeedUserInfo
    public ResultVo newSelective(EforcesReceivedScan receivedScan, String bagNumber,HttpServletRequest request){
		if(StringUtils.isEmpty(bagNumber)) {
			return ResultUtil.exec(false, "参数错误， 包号不能为空！", null);
		}
        ResultVo rst=orderService.receiveBag(request, bagNumber, receivedScan);
        return rst;
    }

}
