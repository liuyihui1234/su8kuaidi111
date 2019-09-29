package org.kuaidi.web.springboot.controller.scan;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.kuaidi.bean.domain.EforcesCustomerSign;
import org.kuaidi.bean.domain.EforcesDistributedScan;
import org.kuaidi.bean.domain.EforcesIncment;
import org.kuaidi.bean.domain.EforcesUser;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesCustomerSignService;
import org.kuaidi.iservice.IEforcesDistributedScanService;
import org.kuaidi.iservice.IEforceslogisticstrackingService;
import org.kuaidi.web.springboot.core.authorization.NeedUserInfo;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/web/")
public class DistributedScanController {
    @Reference(version = "1.0.0")
    IEforcesDistributedScanService scanService;
    @Reference(version = "1.0.0")
    IEforceslogisticstrackingService logisticstrackingService;

    @GetMapping("distributedScan")
    @CrossOrigin
    @NeedUserInfo
    public PageVo getAll(QueryPageVo page, HttpServletRequest request) {
        try {
            EforcesIncment inc = (EforcesIncment) request.getAttribute("inc");
            PageInfo<EforcesDistributedScan> alldistribute = scanService.getAlldistribute(page.getPage(), page.getLimit(), inc.getNumber());
            return ResultUtil.exec(alldistribute.getPageNum(), alldistribute.getSize(), alldistribute.getTotal(), alldistribute.getList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(1, 10, 0, null);
        }
    }

    /**
     * 添加
     * @param record
     * @return
     */

    @PostMapping("distributedScan")
    @CrossOrigin
    @NeedUserInfo
    public ResultVo addDistributedScan(EforcesDistributedScan record,HttpServletRequest request) {
        try {

            EforcesUser user = (EforcesUser) request.getAttribute("user");
            EforcesIncment inc = (EforcesIncment) request.getAttribute("inc");
            //判断当前单号是否已经是到件后的状态
            String s = logisticstrackingService.selectMaxMark(record.getBillsnumber());
            if(StringUtils.isEmpty(s)){
                return ResultUtil.exec(false, "单号错误", null);
            }else if(Integer.parseInt(s)<4){
                return ResultUtil.exec(false, "运单状态不正确", null);
            }
            record.setScantype("派件扫描");
            record.setIncname(user.getIncname());
            record.setIncid(user.getIncid());


            int i = scanService.insertSelective(record);
            return ResultUtil.exec(true, "添加成功", i);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(true, "添加失败", null);
        }
    }

    /**
     * 修改
     * @param record
     * @return
     */
    @PutMapping("distributedScan")
    @CrossOrigin
    public ResultVo updateDistributedScan(EforcesDistributedScan record) {
        try {
            int i = scanService.updateByPrimaryKeySelective(record);
            return ResultUtil.exec(true, "修改成功", i);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(true, "修改失败", null);
        }
    }

    /**
     * 删除
     * @param list
     * @return
     */
    @DeleteMapping("distributedScan")
    @CrossOrigin
    public ResultVo deleteDistributedScan(@RequestBody List<Integer> list) {
        try {
            int i = scanService.deleteByid(list);
            return ResultUtil.exec(true, "删除成功", i);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(true, "删除失败", null);
        }
    }


}
