package org.kuaidi.web.springboot.dubboservice;

import org.kuaidi.bean.domain.EforcesBaggingScan;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.iservice.IEforcesBiggingScanService;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;

@Component
public class BaggingScanService {
	@Reference(version = "1.0.0")
	IEforcesBiggingScanService scanService;
	public PageVo<EforcesBaggingScan> getAll(QueryPageVo page) {
        try {
            PageInfo<EforcesBaggingScan> eforcesUsers = scanService.getAll(page.getPage(),page.getLimit(),page.getId());
            
            return ResultUtil.exec(eforcesUsers.getPageNum(), eforcesUsers.getSize(),eforcesUsers.getTotal(), eforcesUsers.getList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(1, 10 ,0, null);
        }
    }
}
