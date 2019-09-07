package org.kuaidi.web.springboot.dubboservice;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesWeighingScan;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesWeighingScanService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WeighingScanService {
	@Reference(version = "1.0.0")
    IEforcesWeighingScanService  weighingScanService;
	public PageVo<EforcesWeighingScan> getAll(QueryPageVo page) {
        try {
            PageInfo<EforcesWeighingScan> eforcesUsers = weighingScanService.getAll(page.getPage(),page.getLimit(),page.getId());
            
            return ResultUtil.exec(eforcesUsers.getPageNum(), eforcesUsers.getSize(),eforcesUsers.getTotal(), eforcesUsers.getList());
        } catch (Exception e) {
                e.printStackTrace();
                return ResultUtil.exec(1, 10 ,0, null);
        }
    }

    public ResultVo deleteById(List<Integer> list) {
        try {
            int i = weighingScanService.deleteById(list);
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
