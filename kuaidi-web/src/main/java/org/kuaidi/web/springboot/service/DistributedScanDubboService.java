package org.kuaidi.web.springboot.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.Config;
import org.kuaidi.bean.domain.EforcesCustomerSign;
import org.kuaidi.bean.domain.EforcesDistributedScan;
import org.kuaidi.bean.domain.EforcesIncment;
import org.kuaidi.bean.domain.EforcesLogisticStracking;
import org.kuaidi.bean.domain.EforcesUser;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesCustomerSignService;
import org.kuaidi.iservice.IEforcesDistributedScanService;
import org.kuaidi.iservice.IEforceslogisticstrackingService;
import org.kuaidi.iservice.UserService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2019/7/27 10:55
 */
@Component
public class DistributedScanDubboService {

    @Reference(version = "1.0.0")
    IEforcesDistributedScanService distributedScanService;

    @Reference(version = "1.0.0")
    IEforcesCustomerSignService customerSignService;

    @Reference(version = "1.0.0")
    UserService userService;

    @Reference(version = "1.0.0")
    IEforceslogisticstrackingService logisticstrackingService;

    /**
     * 根据快递员员工编号查询待派送订单
     * g
     * @param postmanid
     * @return
     */
    public PageVo<EforcesDistributedScan> findDisByPostmanId(Integer pageNum, Integer pageSize, String postmanid){
        try {
            List<EforcesCustomerSign> customerSigns = customerSignService.selectByOperatorId(postmanid);
            if(customerSigns != null && customerSigns.size() > 0){
                List<String> number = new ArrayList();
                for(int i =0;i < customerSigns.size();i++){
                    number.add(customerSigns.get(i).getNumber());
                }
                if(pageSize == null ||  pageSize <= 0 ){
                    pageSize = Config.pageSize;
                }
                if(pageNum == null || pageNum <= 0 ){
                    pageNum = 1;
                }
                PageInfo<EforcesDistributedScan> pageInfo = distributedScanService.selectDisByPostmanId(pageNum,pageSize,postmanid,number);
                return ResultUtil.exec(pageNum,pageSize,pageInfo.getTotal(), pageInfo.getList());
            }
            return ResultUtil.exec(pageNum, pageSize, 0,null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(pageNum, pageSize, 0,null);
        }
    }

    /**
     * 根据订单编号扫描签收订单
     * @param number
     * @param id
     * @param num
     * @param touserSignature
     * @param fannex
     * @return
     */
    public ResultVo alterDistributed(HttpServletRequest request, String number, String num,String touserSignature,String fannex){
        try {
            if(num.equals(number)){
                EforcesCustomerSign customer = customerSignService.selectByNumber(number);
                if(customer == null){
                	EforcesUser user = (EforcesUser)request.getAttribute("user");
                    EforcesIncment  incment = (EforcesIncment)request.getAttribute("inc");

                    EforcesCustomerSign customerSign = new EforcesCustomerSign();
                    customerSign.setNumber(num);
                    customerSign.setTousersignature(touserSignature);
                    customerSign.setDeliveryusername(user.getName());
                    customerSign.setOperators(user.getName());
                    customerSign.setDeliveryuserid(user.getNumber());
                    customerSign.setOperatorid(user.getNumber());
                    customerSign.setIncid(user.getIncid());
                    customerSign.setIncname(user.getIncname());
                    customerSign.setFannex(fannex);
                    String description = "派件已【签收】,签收人是【%s】,签收网点【%s】"; 
                    description =  String.format(description, touserSignature, incment.getName());
                    EforcesLogisticStracking logistic = new EforcesLogisticStracking();
                    logistic.setBillsnumber(num);
                    logistic.setDescription(description);
                    logistic.setOperators(user.getName());
                    logistic.setIncname(user.getIncname());
                    logistic.setIncid(user.getIncid());
                    logistic.setMark(6);

                    int data2 = customerSignService.insertCustomerSign(customerSign,logistic);
                    if(data2 > 0){
                        return ResultUtil.exec(true,"成功签收",null);
                    }
                    return ResultUtil.exec(false,"签收失败",null);
                }
                return ResultUtil.exec(false,"该订单已签收",null);
            }
            return ResultUtil.exec(false,"不是对应的包裹",null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false,"操作失败",null);
        }
    }
}
