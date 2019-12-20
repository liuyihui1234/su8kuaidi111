package org.kuaidi.utils;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 通用 下单 退款方法
 */
public class AliPayUtil {

    /**
     * 支付宝下单接口 电脑网站支付
     * @param outTradeNo 商户订单号，商户网站订单系统中唯一订单号，必填 。需要保证商户端唯一。
     * @param totalAmount 付款金额，必填
     * @param subject 商品的标题/交易标题/订单标题/订单关键字等
     * @param body 对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body
     * @return
     */
    public static String alipay (String outTradeNo, String totalAmount, String subject, String body){

        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        try {
            alipayRequest.setBizContent("{\"out_trade_no\":\""+ outTradeNo +"\","
                    + "\"total_amount\":\""+ totalAmount +"\","
                    + "\"subject\":\""+ subject +"\","
                    + "\"timeout_express\":\""+ AliPayConstants.TIMEOUT_EXPRESS +"\","
                    + "\"boby\":\""+ body +"\","
                    + "\"qr_pay_mode\":\""+ AliPayConstants.QR_PAY_MODE +"\","
                    + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
            String result;
            result = alipayClient.pageExecute(alipayRequest).getBody();
            System.out.println("***********************************\n返回结果为:"+ result);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 支付宝退款接口 电脑网站支付
     * @param outTradeNo 商户订单号，商户网站订单系统中唯一订单号，必填 。需要保证商户端唯一。
     * @param tradeNo 该交易在支付宝系统中的交易流水号。
     * @param refundAmount 需要退款的金额，该金额不能大于订单金额,单位为元，支持两位小数
     * @param refundReason 退款的原因说明
     * @param out_request_no  标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
     * @return
     */
    public static String aliRefund(String outTradeNo,String tradeNo,String refundAmount,String refundReason,String out_request_no){
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl,AlipayConfig.app_id,AlipayConfig.merchant_private_key,"json",AlipayConfig.charset,AlipayConfig.alipay_public_key,AlipayConfig.sign_type);
        //设置请求参数
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        try {
            alipayRequest.setBizContent("{\"out_trade_no\":\""+ outTradeNo +"\","
                    + "\"trade_no\":\""+ tradeNo +"\","
                    + "\"refund_amount\":\""+ refundAmount +"\","
                    + "\"refund_reason\":\""+ refundReason +"\","
                    + "\"out_request_no\":\""+ out_request_no +"\"}");
            //请求
            String result;

            //请求
            result =alipayClient.execute(alipayRequest).getBody();
            System.out.println("******************************\n返回结果为:"+ result);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 支付宝的验签方法 电脑网站支付验签方法 在支付成功回调的时候用 验证安全支付宝返回的信息是否被篡改
     * @param req
     * @return
     */
    public static boolean checkSign(HttpServletRequest req){
        Map<String,String[]> requestMap = req.getParameterMap();
        Map<String,String> paramsMap = new HashMap<>();
        requestMap.forEach((key, values) -> {
            String strs = "";
            for (String value : values){
                strs = strs + value;
            }
            System.out.println(("key值为"+key+"value为:"+strs));
            paramsMap.put(key, strs);
        });

        //调用SDK验证签名
        try {
            return AlipaySignature.rsaCheckV1(paramsMap,AlipayConfig.alipay_public_key,AlipayConfig.charset,AlipayConfig.sign_type);
        }catch (AlipayApiException e){
            e.printStackTrace();
            System.out.println("**************************验证失败**************************");
            return false;
        }
    }

    /**
     * APP 支付接口
     * @param totalAmount 金额
     * @param body 主题
     * @param subject 描述
     * @param order 订单号
     * @return
     */
    public static String appaliPay(String totalAmount,String body,String subject,String order){
        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, "RSA2");
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody(body);
        model.setSubject(subject);
        model.setOutTradeNo(order);
        model.setTimeoutExpress("30m");
        model.setTotalAmount(totalAmount);
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl("sd.kuai8.com.cn/returnPay/appaliReturnPay");
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
            return response.getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return "error";
        }
    }
    
}