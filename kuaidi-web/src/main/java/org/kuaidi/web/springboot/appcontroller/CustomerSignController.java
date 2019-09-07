package org.kuaidi.web.springboot.appcontroller;

import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.web.springboot.service.CustomerSignDubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2019/7/27 15:06
 */
@RestController
@RequestMapping("/app/customerSign/")
public class CustomerSignController {
    @Autowired
    CustomerSignDubboService customerSignDubboService;

    @RequestMapping("findCustomerByOperatorId")
    @CrossOrigin
    public ResultVo doFindCustomerByOperatorId(String operatorid){
        return customerSignDubboService.findCustomerByOperatorId(operatorid);
    }

    @RequestMapping("findByNumber")
    @CrossOrigin
    public ResultVo doFindByNumber(String number){
        return customerSignDubboService.findByNumber(number);
    }
}
