package org.kuaidi.web.springboot.controller.scan;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ScanSearchVO;
import org.kuaidi.web.springboot.core.authorization.NeedUserInfo;
import org.kuaidi.web.springboot.dubboservice.ScannerSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/web/searchscan/")
public class SearchScanController {
	
//	@Reference(version = "1.0.0")
//	private IEforcesOrderService  orderService;
	
	@Autowired
	private ScannerSearchService  searchService;
	
	/**
	   * 寄/派件运单管理
	 * @param page
	 * @return 
	 */
	@RequestMapping("search")
	@ResponseBody
	@CrossOrigin
	@NeedUserInfo
	public PageVo getListMsg(HttpServletRequest request,Integer page, Integer limit, ScanSearchVO scanSearch){
		try {
//			EforcesUser user = (EforcesUser)request.getAttribute("user");
//			String incNum = user.getIncnumber();
			if(scanSearch == null || StringUtils.isEmpty(scanSearch.getScanType())) {
				return ResultUtil.exec(page, limit, 0, null);
			}
			String billsNum = scanSearch.getBillsNumber();
			System.out.println(billsNum);
			if(billsNum != null && billsNum.length() > 0 ) {
				String []section = billsNum.split("\n");
				if(section != null && section.length > 0) {
					List<String> billsNumList = scanSearch.getBillsNumberList();
					for(String sectionItem : section) {
						if(sectionItem != null && sectionItem.length() > 0 ) {
							billsNumList.add(sectionItem.trim());
						}
					}
				}
			}
			System.out.println(scanSearch.getScanTime());
			if(scanSearch != null && scanSearch.getScanTime() != null ) {
				String scanTime = scanSearch.getScanTime() ;
				if(scanTime.indexOf(" - ") > -1){
					String [] section = scanTime.split(" - ");
					if(section != null && section.length > 0) {
						String startSendTime =  section[0];
						if(StringUtils.isNotEmpty(startSendTime)) {
							scanSearch.setScanStartTime(startSendTime.trim());
						}
					}
					if(section != null && section.length > 1 ) {
						String endSendTime = section[1];
						if(endSendTime != null ) {
							scanSearch.setScanEndTime(endSendTime.trim());
						}
					}
				}
			}
			return searchService.getScanSeach(page, limit, scanSearch);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null ; 
	}

}
