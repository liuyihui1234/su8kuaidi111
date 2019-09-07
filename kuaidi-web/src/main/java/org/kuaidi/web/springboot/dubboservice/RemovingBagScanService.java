package org.kuaidi.web.springboot.dubboservice;

import org.kuaidi.bean.domain.EforcesRemovingBagScan;
import org.kuaidi.bean.domain.EforcesWeighingScan;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.iservice.IEforcesRemovingBagScanService;
import org.kuaidi.iservice.IEforcesWeighingScanService;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;

@Component
public class RemovingBagScanService {
	@Reference(version = "1.0.0")
    IEforcesRemovingBagScanService scanService;
	public PageVo<EforcesRemovingBagScan> getAll(QueryPageVo page) {
        try {
            PageInfo<EforcesRemovingBagScan> eforcesUsers = scanService.getAll(page.getPage(),page.getLimit(),page.getId());
            
            return ResultUtil.exec(eforcesUsers.getPageNum(), eforcesUsers.getSize(),eforcesUsers.getTotal(), eforcesUsers.getList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(1, 10 ,0, null);
        }
    }
}
