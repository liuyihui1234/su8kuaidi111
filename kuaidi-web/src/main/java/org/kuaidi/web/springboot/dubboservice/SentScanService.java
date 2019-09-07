package org.kuaidi.web.springboot.dubboservice;

import org.kuaidi.bean.domain.EforcesSentScan;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesSentscanService;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;

import java.util.List;

@Component
public class SentScanService {
	
	@Reference(version = "1.0.0")
	IEforcesSentscanService sentscanService;
	
	public PageVo<EforcesSentScan> getAll(QueryPageVo page) {
        try {
            PageInfo<EforcesSentScan> eforcesUsers = sentscanService.getAll(page.getPage(),page.getLimit(),page.getId());
            return ResultUtil.exec(eforcesUsers.getPageNum(), eforcesUsers.getSize(),eforcesUsers.getTotal(), eforcesUsers.getList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(1, 10 ,0, null);
        }
    }
    public ResultVo deleteMenusByID(List<Integer> list) {
        try {
            int i = sentscanService.deleteByIds(list);
            if(i>0){
                return ResultUtil.exec(true, "删除成功", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false, "删除失败！", null);
        }
        return ResultUtil.exec(false, "删除失败！", null);
    }
}
