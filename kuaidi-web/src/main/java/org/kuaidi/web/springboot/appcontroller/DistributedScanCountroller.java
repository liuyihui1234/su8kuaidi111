package org.kuaidi.web.springboot.appcontroller;

import org.kuaidi.bean.domain.EforcesDistributedScan;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.web.springboot.service.DistributedScanDubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2019/7/27 11:06
 */
@RestController
@RequestMapping("/app/distributed/")
public class DistributedScanCountroller {

    @Autowired
    DistributedScanDubboService distributedDubbo;

    @RequestMapping("findDisByPostmanId")
    @CrossOrigin
    public PageVo<EforcesDistributedScan> dofindDisByPostmanId(Integer pageNum, Integer pageSize,String postmanid){
        return distributedDubbo.findDisByPostmanId(pageNum,pageSize,postmanid);
    }

    @RequestMapping("alterDistributed")
    @CrossOrigin
    public ResultVo doalterDistributed(String number,Integer id, String num,String touserSignature,String fannex,String description){
        return distributedDubbo.alterDistributed(number,id,num,touserSignature,fannex,description);
    }
}
