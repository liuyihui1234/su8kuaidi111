package org.kuaidi.utils;

import com.alipay.api.AlipayApiException;

/**
 * 测试支付main方法
 */
public class AliPayReq {
    public static void main(String[] args) throws AlipayApiException {



/**
         * 下单接口测试 电脑网站
         * outTradeNo:商户网站唯一订单号
         * totalAmount:该笔订单的资金总额，单位为RMB-Yuan。取值范围为[0.01，100000000.00]，精确到小数点后两位
         * subject:商品的标题/交易标题/订单标题/订单关键字等
         * body:对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body
         */


        String str = AliPayUtil.alipay("111228823331","0.01","vivo","vivo手机X系列");
        System.out.println("************************"+ str +"************************");



/**
         * 退款接口测试 电脑网站
         * outTradeNo:订单支付时传入的商户订单号,不能和 trade_no同时为空 特殊可选
         * tradeNo:支付宝交易号，和商户订单号不能同时为空 特殊可选
         * refundAmount:需要退款的金额，该金额不能大于订单金额,单位为元，支持两位小数 必选
         * refundReason:退款的原因说明 可选
         * 订单支付时传入的商户订单号,不能和 trade_no同时为空 特殊可选
         */

        String st = AliPayUtil.aliRefund("111222333","211111111","2121","正常退款","211");
        System.out.println("************************"+ st +"************************");

        /**
         * App下单接口
         * outTradeNo:订单支付时传入的商户订单号,不能和 trade_no同时为空 特殊可选
         * body:标题
         * subject:描述
         */
        String appPay = AliPayUtil.appaliPay("0.01","玩具","迪迦奥特曼",OrderNumUtil.getOrderNum());
        System.out.println("************************"+ appPay + "************************");
    }
}
