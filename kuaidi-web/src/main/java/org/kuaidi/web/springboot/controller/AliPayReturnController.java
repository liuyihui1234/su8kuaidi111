package org.kuaidi.web.springboot.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import org.kuaidi.bean.domain.AliReturnPayBean;
import org.kuaidi.bean.domain.EforcesNetsign;
import org.kuaidi.bean.domain.EforcesPaydetai;
import org.kuaidi.iservice.IEforcesPaydedaiService;
import org.kuaidi.iservice.NetSignInfo;
import org.kuaidi.utils.AliPayUtil;
import org.kuaidi.utils.AlipayConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/returnPay")
public class AliPayReturnController {

   private static Logger logger = LoggerFactory.getLogger(AliPayReturnController.class);
   
   @Reference(version = "1.0.0")
   IEforcesPaydedaiService paydedaiService;
    
   
   @Reference(version = "1.0.0")
   NetSignInfo  netSignInfo;

    /**
               *    支付宝回调的接口 电脑网站
     * @param
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/aliReturnPay", method = RequestMethod.POST)
    @ResponseBody
    public void returnPay(HttpServletResponse response, AliReturnPayBean returnPay, HttpServletRequest req)
            throws IOException {
        response.setContentType("type=text/html;charset=UTF-8");
        System.out.println(req+"11111111111111111111111111111111111");
        System.out.println(response+"11111111111111111111111111111111111");
        System.out.println(returnPay+"11111111111111111111111111111111111");
        logger.info("****************************************支付宝的回调函数被调用******************************");
        if (!AliPayUtil.checkSign(req)) {
            logger.info("****************************************验签失败*******************************************");
            response.getWriter().write("failture");
            return;
        }
        if (returnPay == null) {
            logger.info("支付宝的returnPay返回为空");
            response.getWriter().write("success");
            return;
        }
        logger.info("支付宝的returnPay" + returnPay.toString());
        if (returnPay.getTrade_status().equals("TRADE_SUCCESS")) {
            logger.info("支付宝的支付状态为TRADE_SUCCESS");
            /*paydedaiService.updatePaystatus(returnPay);*/
        }
        response.getWriter().write("success");
    }

    /**
     * App支付回调接口
     * @param request
     * @throws AlipayApiException
     */
    @RequestMapping("/appaliReturnPay")
    @ResponseBody
    public boolean rePay(HttpServletRequest request) throws AlipayApiException, UnsupportedEncodingException {
    //获取支付宝POST过来反馈信息
    Map<String,String> params = new HashMap<String,String>();
    Map requestParams = request.getParameterMap();
    for (
            Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
        String name = (String) iter.next();
        String[] values = (String[]) requestParams.get(name);
        String valueStr = "";
        for (int i = 0; i < values.length; i++) {
            valueStr = (i == values.length - 1) ? valueStr + values[i]
                    : valueStr + values[i] + ",";
        }
        //乱码解决，这段代码在出现乱码时使用。
        //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
        params.put(name, valueStr);
    }
    //切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
    //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)

    Iterator<String>  it =  params.keySet().iterator();
    while(it.hasNext()){
        String key = it.next();

        System.out.println("key : " + key +"  value :" + params.get(key));
    }

    //验签
    boolean signVerfied = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, "utf-8","RSA2");
        /**
         * 实际过程建议商户务必添加以下校验！
         * 1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
         * 2、判断total_amout是否确实为该订单的实际金额(即商户订单创建时的金额)
         * 3、校验通知中的seller_id(或者seller_email)是否为out_trade_no这笔单据的对应的操作方(有的时候，一个商户可能有多个seller_id/seller_email)
         * 4、验证app_id是否为该商户本身。
         */
    if(signVerfied){ //验证成功
        logger.info("*********************验签成功*********************");
        //商户订单号
        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
        logger.info("*********************商户订单号:"+ out_trade_no +"*********************");

        //支付宝交易号
        String trade_no = new String (request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
        logger.info("*********************支付宝交易号:"+ trade_no +"*********************");
        //交易状态
        String trade_Status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
        logger.info("*********************支付宝的支付状态是"+trade_Status +"*********************");
        if(trade_Status.equals("TRADE_FINISHED")){ //TRADE_FINISHED:交易结束，不可退款
            //判断该笔订单是否在商户网站中做过处理
            //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
            //如果有做过处理，不执行商户的业务程序
/*            String outTradeNo = out_trade_no;
            String tradeno = trade_no;*/
        logger.info("*********************交易结束，不可退款*********************");
            //更改订单状态
            //注意：
            //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
        }else if(trade_Status.equals("TRADE_SUCCESS")){ //TRADE_SUCCESS:交易支付成功
            //判断该笔订单是否在商户网站中已经做过处理
            //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
            //如果有做过处理，不执行商户的业务程序
            logger.info("*********************支付宝的支付状态为TRADE_SUCCESS*********************");
            logger.info("******支付成功商户订单号:"+ out_trade_no +"******" + "******支付成功支付宝交易号:"+ trade_no +"******");
            //修改状态
            paydedaiService.upderinfo(out_trade_no,trade_no);
            List<EforcesPaydetai> list = paydedaiService.selectByTradeno(out_trade_no);
            if(list != null && list.size() > 0 ) {
            	EforcesPaydetai  payDetail = list.get(0);
            	int netSignId = payDetail.getNetsignid() ;
            	float totalMoney = 0f;
            	String amount = payDetail.getTotalAmount();
            	try {
            		totalMoney = Float.parseFloat(amount);
            	}catch(Exception e) {
            		e.printStackTrace();
            	}
            	if(netSignId > 0 ) {
            		EforcesNetsign netSign = new EforcesNetsign();
            		netSign.setId(netSignId);		
            		netSign.setStatus(1);
            		netSign.setPaytype(1);
            		netSign.setPaymoney(totalMoney);
            		netSignInfo.updateNetsignSort(netSign);
            	}
            }
            //添加订单信息到日志
            Date date = new Date();
            SimpleDateFormat simpDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String strDate = simpDate.format(date);
            AlipayConfig.logResult("支付日期:"+strDate,"支付订单号:"+out_trade_no,"支付交易号:"+trade_no);
            //注意：
            //付款完成后，支付宝系统发送该交易状态通知
        }
        System.out.println("*********************回调成功*********************");
        return  signVerfied;
    }else { //验证失败
        logger.info("*********************验证失败*********************");
        return signVerfied ;
        }
    }
}