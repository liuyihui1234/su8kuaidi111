package org.kuaidi.web.springboot.controller.scan;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesTransportedscan;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesTransportedscanService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2019/8/7 15:11
 */
@RestController
@RequestMapping("/web/Transportedscan/")
public class TransportedscanController {
    @Reference(version = "1.0.0")
    IEforcesTransportedscanService transportedscanService;

    @RequestMapping("insert")
    @CrossOrigin
    public ResultVo doInsertSelective(EforcesTransportedscan record){
        try {
            EforcesTransportedscan transportedscan = transportedscanService.selectByBillsnumber(record.getBillsnumber());
            if(transportedscan != null){
                int a = transportedscanService.insertSelective(record);
                if (a > 0){
                    return ResultUtil.exec(true,"添加转运记录成功",null);
                }
                return ResultUtil.exec(false,"添加转运记失败",null);
            }
            return ResultUtil.exec(false,"该订单已转运",null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false,"操作失败",null);
        }
    }

    @RequestMapping("selectAll")
    @CrossOrigin
    public PageVo doSelectAll(QueryPageVo page){
        try {
            PageInfo<EforcesTransportedscan> pageInfo = transportedscanService.selectByIncid(page.getPage(), page.getLimit(), page.getId());
            return ResultUtil.exec(pageInfo.getPageNum(),pageInfo.getSize(),pageInfo.getTotal(),pageInfo.getList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(1,10,0,null);
        }
    }

    @RequestMapping("selectOne")
    @CrossOrigin
    public ResultVo doSelectOne(Integer id){
        try {
            EforcesTransportedscan transportedscan = transportedscanService.selectByPrimaryKey(id);
            if(transportedscan != null){
                return ResultUtil.exec(true,"查询详情成功",transportedscan);
            }
            return ResultUtil.exec(false,"查询详情失败",null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false,"操作失败",null);
        }
    }

    @RequestMapping("update")
    @CrossOrigin
    public ResultVo doUpdate(EforcesTransportedscan record){
        try {
            int a = transportedscanService.updateByPrimaryKeySelective(record);
            if (a > 0){
                return ResultUtil.exec(true,"修改成功",null);
            }
            return ResultUtil.exec(false,"修改失败",null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false,"操作失败",null);
        }
    }
}
