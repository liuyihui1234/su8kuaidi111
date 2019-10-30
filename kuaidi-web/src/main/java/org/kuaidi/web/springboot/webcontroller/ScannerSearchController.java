package org.kuaidi.web.springboot.webcontroller;

import javax.servlet.http.HttpServletRequest;
import org.kuaidi.bean.domain.EforcesUser;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.web.springboot.core.authorization.NeedUserInfo;
import org.kuaidi.web.springboot.dubboservice.ScannerSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/web/scansearch/")
public class ScannerSearchController {
	
	
	@Autowired
	ScannerSearchService  scanSearchService; 
	
	@RequestMapping("search")
	@CrossOrigin
	@NeedUserInfo
	public PageVo getAll(HttpServletRequest request,QueryPageVo page, Integer scanType) {
		EforcesUser  userInfo = (EforcesUser)request.getAttribute("user");
		if(scanType == null || scanType < 1) {
			scanType = 1; 
		}
		System.err.println("page:"+page);
		PageVo rst =  scanSearchService.getAll(page, userInfo.getIncid(),  scanType);
		return rst;
	}

}
