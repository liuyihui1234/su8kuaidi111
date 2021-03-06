package org.kuaidi.web.springboot.controller.scan;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesIncment;
import org.kuaidi.bean.domain.EforcesReceivedScan;
import org.kuaidi.bean.domain.EforcesUser;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesReceivedscanService;
import org.kuaidi.web.springboot.core.authorization.NeedUserInfo;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2019/8/6 15:36
 */
@RestController
@RequestMapping("/web/Receivedscan/")
public class ReceivedscanController {
    @Reference(version = "1.0.0")
    IEforcesReceivedscanService receivedscanService;

    @GetMapping("Receivedscan")
    @ResponseBody
    @CrossOrigin
    public PageVo doGetAllOrderSelective(QueryPageVo page){
        try {
            PageInfo<EforcesReceivedScan> pageInfo = receivedscanService.getAllOrderSelective(page.getPage(), page.getLimit(), null);
            return ResultUtil.exec(pageInfo.getPageNum(),pageInfo.getSize(),pageInfo.getTotal(),pageInfo.getList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(1,10,0,null);
        }
    }

    @PutMapping("Receivedscan")
    @ResponseBody
    @CrossOrigin
    public ResultVo doUpdateByPrimaryKeySelective(EforcesReceivedScan receivedScan){
        try {
            int a = receivedscanService.updateByPrimaryKeySelective(receivedScan);
            if(a>0){
                return ResultUtil.exec(true,"修改成功",null);
            }
            return ResultUtil.exec(false,"修改失败",null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false,"操作失败",null);
        }
    }

    @PostMapping("Receivedscan")
    @ResponseBody
    @CrossOrigin
    @NeedUserInfo
    public ResultVo newSelective(EforcesReceivedScan receivedScan, HttpServletRequest request){
        ResultVo rst;
        EforcesUser user = (EforcesUser) request.getAttribute("user");
        EforcesIncment inc = (EforcesIncment) request.getAttribute("inc");
        rst=receivedscanService.receiveOrder(receivedScan,user,inc);
        return rst;
    }
    
    @DeleteMapping("Receivedscan")
    @ResponseBody
    @CrossOrigin
    @NeedUserInfo
    public ResultVo deleteByIds(@RequestBody List<Integer> array){
       int rst=receivedscanService.deleteById(array);
       if(rst > 0 ) {
    	   return ResultUtil.exec(true, "删除收件信息成功！", null);
       }
       return ResultUtil.exec(false, "删除收件信息失败！", null);
    }

    @RequestMapping("selectOne")
    @ResponseBody
    @CrossOrigin
    public ResultVo doSelectByPrimaryKey(Integer id){
        try {
            EforcesReceivedScan receivedScan = receivedscanService.selectByPrimaryKey(id);
            if(receivedScan != null){
                return ResultUtil.exec(true,"查询成功",receivedScan);
            }
            return ResultUtil.exec(false,"查询失败",null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false,"操作失败",null);
        }
    }


    /**
     * 寄/派件运单管理 详细显示
     * @param number
     * @return
     */
    @RequestMapping("selectreceiveOrder")
    @ResponseBody
    @CrossOrigin
            public ResultVo getreceiveOrder(String number){
                try {
                    List<HashMap> receivedScan = receivedscanService.getreceiveOrder(number);
                    return ResultUtil.exec(true,"查询成功",receivedScan);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false,"查询失败",null);
        }
    }

}
