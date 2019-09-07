package org.kuaidi.web.springboot.dubboservice;

import org.kuaidi.bean.domain.EforcesSettlementquotation;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.iservice.IEforcesSettlementquotationService;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;

@Component
public class SettlementquotationService {
	@Reference(version = "1.0.0")
	IEforcesSettlementquotationService   quotationService;
	public PageVo<EforcesSettlementquotation> getAll(QueryPageVo page) {
        try {
            PageInfo<EforcesSettlementquotation> eforcesUsers = quotationService.getAll(page.getPage(),page.getLimit(),page.getRecipientname(),page.getDestinationname());
            
            return ResultUtil.exec(eforcesUsers.getPageNum(), eforcesUsers.getSize(),eforcesUsers.getTotal(), eforcesUsers.getList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(1, 10 ,0, null);
        }
    }
}
