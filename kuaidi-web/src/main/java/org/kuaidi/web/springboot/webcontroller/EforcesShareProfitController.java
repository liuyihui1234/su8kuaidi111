package org.kuaidi.web.springboot.webcontroller;

import java.util.Map;

import org.kuaidi.bean.Config;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.iservice.IEforcesIncmentProfitService;
import org.kuaidi.web.springboot.util.ProfitShare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/web/shareprofit/")
public class EforcesShareProfitController {
	
	@Reference(version = "1.0.0")
	private IEforcesIncmentProfitService  profitService; 
	
	@Autowired
	private ProfitShare profitShare; 
	
	
	@ResponseBody
    @RequestMapping("getProfitByParam")
    @CrossOrigin
    public PageVo doFindIncmentByNumber(QueryPageVo page){
        try {
        	Integer pages = page.getPage();
        	if(pages == null ) {
        		pages = 1 ; 
        	}
        	Integer pageSize = page.getLimit();
        	if(pageSize == null) {
        		pageSize = Config.pageSize;
        	}
        	String parentId = page.getInfo1();
        	String incName = page.getInfo2();
        	PageInfo<Map<String, Object>> pageInfo = profitService.getIncmentProfitByPage(pages, pageSize, parentId, incName);
        	return ResultUtil.exec(pageInfo.getPageNum(),pageInfo.getSize(),pageInfo.getTotal(),pageInfo.getList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(1,10,0,null);
        }
    }
	
	@ResponseBody
    @RequestMapping("shareProfitDetail")
    @CrossOrigin
    public PageVo shareProfitDetail(String  billsNums){
		profitShare.shareProfit(billsNums);
		return null; 
	}

}
