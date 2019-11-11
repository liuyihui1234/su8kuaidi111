package org.kuaidi.web.springboot.dubboservice;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.impl.cookie.DateParseException;
import org.kuaidi.bean.domain.EforcesCustomerSign;
import org.kuaidi.bean.domain.EforcesDistributedScan;
import org.kuaidi.bean.domain.EforcesReceivedScan;
import org.kuaidi.bean.domain.EforcesSentScan;
import org.kuaidi.bean.domain.EforcesWeighingScan;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ScanSearchVO;
import org.kuaidi.iservice.IEforcesCustomerSignService;
import org.kuaidi.iservice.IEforcesDistributedScanService;
import org.kuaidi.iservice.IEforcesOrderService;
import org.kuaidi.iservice.IEforcesReceivedscanService;
import org.kuaidi.iservice.IEforcesSentscanService;
import org.kuaidi.iservice.IEforcesWeighingScanService;
import org.kuaidi.web.springboot.util.DateUtil;
import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;

@Component
public class ScannerSearchService {
	
	@Reference(version = "1.0.0")
	private IEforcesSentscanService  sentScanService; 
	
	@Reference(version = "1.0.0")
	private IEforcesReceivedscanService  receiveScanService; 
	
	@Reference(version = "1.0.0")
	private IEforcesDistributedScanService  distributedScanService; 
	
	@Reference(version = "1.0.0")
	private IEforcesCustomerSignService signScanService; 
	
	@Reference(version = "1.0.0")
	private IEforcesWeighingScanService weightScanService; 
	
	@Reference(version = "1.0.0")
	private IEforcesOrderService  orderService ; 
	
	public PageVo getScanSeach(Integer page , Integer limit,  ScanSearchVO scanSearch) {
		try {
			PageInfo<Map<String, Object>> pageInfo = null; 
			if(StringUtils.isEmpty(scanSearch.getScanType())) {
				scanSearch.setScanType("发件");
			}
			if("发件".equals(scanSearch.getScanType())) {
				pageInfo =  orderService.getSendBillsByParam(page, limit, scanSearch);
			}else if("到件".equals(scanSearch.getScanType())) {
				 pageInfo =  orderService.getReceiveBillsByParam(page, limit, scanSearch);
			}else if("派件".equals(scanSearch.getScanType())) {
				pageInfo =  orderService.getDistributedBillsByParam(page, limit, scanSearch);
			}else if("签收".equals(scanSearch.getScanType())) {
				pageInfo =  orderService.getCustomerSignBillsByParam(page, limit, scanSearch);
			}else if("称重".equals(scanSearch.getScanType())) {
				pageInfo =  orderService.getWeightBillsByParam(page, limit, scanSearch);
			}
			/*
			 * 对时间进行转化
			 */
			if(pageInfo  != null && pageInfo.getList().size() > 0 ){
				List<Map<String, Object>> list = pageInfo.getList();
				for(int i = 0 ; i < list.size(); i++) {
					Map<String, Object>  scanItem = list.get(i);
					Date createTime = (Date)scanItem.get("scanTime");
					if(createTime != null) {
						try {
							String createTimeStr = DateUtil.sdf.format(createTime);
							scanItem.put("scanTime", createTimeStr);
						}catch(Exception e) {
							scanItem.put("scanTime", "");
						}
					}
				}
			}
			return ResultUtil.exec(page, limit, pageInfo.getSize(), "获得数据成功！", pageInfo.getList());
		}catch(Exception e ) {
			e.printStackTrace();
		}
		return ResultUtil.exec(page, limit,0, "查询失败",null); 
	}
	/*
	 * 1: 发件
	 * 2: 到件
	 * 3:派件
	 * 4:签收
	 * 5：称重
	 *     取件先不管。
	 */
	public PageVo getAll(QueryPageVo page, String incId , Integer scanType) {
		// TODO Auto-generated method stub
		//将查询到的结果封装到一个对象中。
		try {
			if(scanType == 1) {
				PageInfo<EforcesSentScan> pageInfo = sentScanService.getAll(page.getPage(), page.getLimit(), incId);
				if(pageInfo != null && pageInfo.getSize() > 0 ) {
					return ResultUtil.exec(page.getPage(), page.getLimit(), pageInfo.getTotal(), pageInfo.getList());
				}
			}else if(scanType == 2) {
				PageInfo<EforcesReceivedScan>  pageInfo = receiveScanService.getAllOrderSelective(page.getPage(), page.getLimit(), incId);
				if(pageInfo != null && pageInfo.getSize() > 0 ) {
					return ResultUtil.exec(page.getPage(), page.getLimit(), pageInfo.getTotal(), pageInfo.getList());
				}
			}else if(scanType == 3 ) {
				PageInfo<EforcesDistributedScan>  pageInfo = distributedScanService.getAlldistribute(page.getPage(), page.getLimit(), incId);
				if(pageInfo != null && pageInfo.getSize() > 0 ) {
					return ResultUtil.exec(page.getPage(), page.getLimit(), pageInfo.getTotal(), pageInfo.getList());
				}
			}else if(scanType == 4 ) {
				PageInfo<EforcesCustomerSign>  pageInfo = signScanService.getAllSign(page.getPage(), page.getLimit(), incId);
				if(pageInfo != null && pageInfo.getSize() > 0 ) {
					return ResultUtil.exec(page.getPage(), page.getLimit(), pageInfo.getTotal(), pageInfo.getList());
				}
			}else if(scanType == 5) {
				PageInfo<EforcesWeighingScan>  pageInfo = weightScanService.getAll(page.getPage(), page.getLimit(), incId);
				if(pageInfo != null && pageInfo.getSize() > 0 ) {
					return ResultUtil.exec(page.getPage(), page.getLimit(), pageInfo.getTotal(), pageInfo.getList());
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ResultUtil.exec(page.getPage(), page.getLimit(), 0, null);
	}
	
	
	
	
}
