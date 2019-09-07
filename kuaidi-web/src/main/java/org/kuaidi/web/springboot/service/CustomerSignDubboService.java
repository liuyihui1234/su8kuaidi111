package org.kuaidi.web.springboot.service;

import com.alibaba.dubbo.config.annotation.Reference;
import org.kuaidi.bean.domain.EforcesCustomerSign;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesCustomerSignService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2019/7/27 14:59
 */
@Component
public class CustomerSignDubboService {

    @Reference(version = "1.0.0")
    IEforcesCustomerSignService customerSignService;

    /**
     * 根据快递员员工号查询该员工已送达的订单
     * g
     * @param operatorid
     * @return
     */
    public ResultVo findCustomerByOperatorId(String operatorid){
        try {
            List<EforcesCustomerSign> list = customerSignService.selectByOperatorId(operatorid);
            if(list != null && list.size() > 0){
                return ResultUtil.exec(true,"查询已签收成功",list);
            }
            return ResultUtil.exec(false,"暂无已签收订单",null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false,"查询操作失败",null);
        }
    }

    /**
     * 根据订单号查询该签收订单的详情
     * g
     * @param number
     * @return
     */
    public ResultVo findByNumber(String number){
        try {
            EforcesCustomerSign customerSign = customerSignService.selectByNumber(number);
            if(customerSign != null){
                return ResultUtil.exec(true,"查找成功",customerSign);
            }
            return ResultUtil.exec(false,"查找失败",null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false,"操作失败",null);
        }
    }

//    /**
//     * 动态添加一条签收记录
//     * g
//     * @param customerSign
//     * @return
//     */
//    public ResultVo addCustomerSign(EforcesCustomerSign customerSign){
//        try {
//            int data = customerSignService.insertCustomerSign(customerSign);
//            if(data>0){
//                return ResultUtil.exec(true,"添加成功",null);
//            }
//            return ResultUtil.exec(false,"添加失败",null);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResultUtil.exec(false,"操作失败",null);
//        }
//    }
}
