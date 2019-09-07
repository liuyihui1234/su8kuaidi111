package org.kuaidi.web.springboot.dubboservice;

import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.kuaidi.bean.domain.EforcesDefaultBankInfo;
import org.kuaidi.bean.domain.EforcesDistributedScan;
import org.kuaidi.bean.domain.EforcesIncDefaultPrice;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IDictionaryService;
import org.kuaidi.iservice.IEforcesDistributedScanService;
import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Reference;

@Component
public class DictionaryService {
	
	@Reference(version = "1.0.0")
	IDictionaryService inetsignService;


	public ResultVo getdefaultPrice(String province ,String city, String county, String network) {
		Integer priceId = 0 ;
		if(StringUtils.isNotEmpty(network)) {
			priceId = 4;
		}
		if(priceId == 0 && StringUtils.isNotEmpty(county)) {
			priceId = 3;
		}
		if(priceId == 0 && StringUtils.isNotEmpty(city)) {
			priceId = 2;
		}
		if(priceId == 0 && StringUtils.isNotEmpty(province)) {
			priceId = 1;
		}
		EforcesIncDefaultPrice defaultPrice =  inetsignService.getDefaultPriceById(priceId);
		if(defaultPrice == null) {
			return ResultUtil.exec(false, "默认费用记录不存在", null);
		}
		return ResultUtil.exec(true, "查找数据成功！", defaultPrice);
	}
	
	
	public ResultVo defaultBankInfo() {
		try {
			List<EforcesDefaultBankInfo> bankInfo =  inetsignService.getAllBankInfo();
			if(bankInfo != null && bankInfo.size() > 0 ) {
				return ResultUtil.exec(true, "查找数据成功！", bankInfo.get(0));
			}else {
				return ResultUtil.exec(false, "没有找到对应的记录！", null);
			}
		}catch(Exception e) {
			return ResultUtil.exec(false, "查找数据异常！", null);
		}
	}

}
