package org.kuaidi.web.springboot.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import org.kuaidi.bean.domain.EforcesPaydetai;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesPaydedaiService;
import org.kuaidi.utils.AliPayUtil;
import org.kuaidi.utils.OrderNumUtil;
import org.kuaidi.utils.RegexUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aliPay/")
public class AliPayController {
    private static Logger logger = LoggerFactory.getLogger(AliPayReturnController.class);

    @Reference(version = "1.0.0")
    IEforcesPaydedaiService paydedaiService;

    /**
     * 电脑网站支付
     * @param totalAmount 金额
     * @return
     */
    @PostMapping("PayInfo")
    @ResponseBody
    public ResultVo getPayInfo(String totalAmount){
        try {
            String str = AliPayUtil.alipay("111228823331", totalAmount, "vivo", "vivo手机X系列");
            return ResultUtil.exec(true, str, null );
        }catch (Exception e){
            return ResultUtil.exec(false, "支付失败！", null );
        }
    }

    /**
     * APP支付与前端数据库打交道
     * @param totalAmount 金额
     * @param body 主题
     * @param subject 描述
     * @param netSignId 与网签信息关联
     * @return
     */
    @PostMapping("AppPay")
    @ResponseBody
    public ResultVo aliPayApp(String totalAmount,String body,String subject, Integer netSignId){
        try {
            String outtradeno = OrderNumUtil.getOrderNum(); //获得生成订单号
            String str = AliPayUtil.appaliPay(totalAmount,body,subject,outtradeno); //调用APP支c付下单接口
            //插入数据 状态:正在支付
            EforcesPaydetai paydetai = new EforcesPaydetai();
            paydetai.setTotalAmount(totalAmount);
            paydetai.setNetsignid(netSignId);
            paydetai.setOutTradeNo(outtradeno);
            paydetai.setStatus(1);
            paydedaiService.insertPayinfo(paydetai);
            logger.info("增加订单信息**********金额:"+ paydetai.getTotalAmount() + "网签信息id:"+ paydetai.getNetsignid() +"订单号:"+ paydetai.getOutTradeNo()+"**********");
            return ResultUtil.exec(true,"成功",str);
        } catch (Exception e){
            return ResultUtil.exec(false,"失败",null);
        }
    }
}
