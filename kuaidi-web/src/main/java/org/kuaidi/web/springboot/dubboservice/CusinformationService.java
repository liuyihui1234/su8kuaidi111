package org.kuaidi.web.springboot.dubboservice;

import org.kuaidi.bean.domain.EforcesCusinformation;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.iservice.IEforcesCusinformationService;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;

@Component
public class CusinformationService {
	@Reference(version = "1.0.0")
	IEforcesCusinformationService mationService;
	public PageVo<EforcesCusinformation> getAll(QueryPageVo page) {
        try {
            PageInfo<EforcesCusinformation> eforcesUsers = mationService.getAll(page.getPage(),page.getLimit());
            
            return ResultUtil.exec(eforcesUsers.getPageNum(), eforcesUsers.getSize(),eforcesUsers.getTotal(), eforcesUsers.getList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(1, 10 ,0, null);
        }
    }
}
