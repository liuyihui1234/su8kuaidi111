package org.kuaidi.utils;

/* *
 *类名：AlipayConfig
 *功能：支付宝基础配置类
 *详细：设置帐户有关信息及返回路径 电脑网站和APP支付都有调用
 *修改日期：2019-07-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 *
 */


import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2019070465728546";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCCmEmwaRWHAPFg21Dqq0gzWFqBH3g46JoGhG8OWAKZfhAW0X4PORQkjh66NDSPur51Ri6aFnqFoYuz9sQYyF8iAhCOMkuC7PywyKjH+/6fFOLT/o8Jaop/Whel/ecl080NIaHEZBGAhmPSMLmOzT9gaLCuwRm9hTwGpCK6S81lT2T3nC2K0efiUVpJ8lFtn4kRYiOIOg6h9Y7p8x2NcBGotaC5O1iHS0TzrlhEr479/CvF1UvBEb80ICMWRR7g3uuiSlw+AOJt+t3rW/gPKxtnEgr3RhTL0Ejex8cU1DBBTnuOQ9PDx9Yd5aaMUsoPjk2HOAfAbgs4pX3cJclM0u0lAgMBAAECggEAaayfHD+8KrUpnO166ZlV4C5jdc6oMqyNTi7Fyr6Ow/ONQ1mbfuf6E6Zo2NW7nn5G9ZCnzwnXdOCGskZA4ajpyhQPn3C1R6lKxCZq5QBVRhbZgR+gUMdEcM7PCgONkj9z0mdfhhJPU0fr6D6DODl/Sh0MsywQDxqLuvgAHe2Os6XEkaq/asaHlWHW3zPjhRAZxgChfufs3wiL2mHVH7gFfnAJn8RUSlkJuAer9J5Ixz7iCmhwDtW3GTI8dYmZD6gL2XHzeXnNLjEkcL27KQWvngLIUfLYyKmcYR1i6fiy8Dg1LWz+D/SMQSyOu4gKeLGMuOZzAjK/uTSDFQ9EC1oYVQKBgQDK7PK62uDjk8AKfhH18UdZyPcBxKJTQzUKKbZpM3ilYPBDUgFmlYSHeWOehzjr+EZNT99Im7BuE9ZFR3TUL3MJ1QyimGojDSYhWMYylICP8O3oVS9BaG/vX0zp991aPbYmfM8l4jdC6I/bVYkmeIJ66WpnOIPYLKovfZ0wkkV1iwKBgQCkwGB7Yjdg9roSn7KPvf+bsMZGZDijg3PU4rJFxLBuQx6gmx2s/yp1bc+vjFHXOQ5QiOuqPTbE0bY/TWuxAMSvDdjBo44h4+vfAbq+oplRUT0k7qozn8PJzZj1FsSKRKwLm2vKKPpTvU71bcvsH1n/+OAfA7CNJ5VZzXNEz8heDwKBgBU85U6PF9hZn1C5zxFpdik8cjWaKc8DCqsMoI20GF1enua8z264GU3Ac5+k0V1I97qhsGG+AaITq2pGx0Bp0uVlidP+laUjfCkjTGMAzc/CzHNzbuWuk4B+/eLC0UTfjo/Kojd5zITRKDUxZ5BCLo3EniE9dfcK/ajIwcnSlThvAoGAFb4RvZbLqi9FhTmwLgosFpbww89sLiAmBD1au5xmkFapTxbJhtAyAUdZtlg1YMr12akIOPJVLQpkG9ySzsxEKLoSFhQIqO5UbZzj0U7SHwoyoJIiwidehl5sxkOX8ot3jRyY0BgyWSTNq9Quql3E2eQ1Z8Jr5WUI784OXVFypv8CgYAiiisWMuO4uFPTcqfsbmm3L/5+NgQg+QrUz4b4nCkmGH1cb9ZCFcSSE5mK3zEURqcH2kW2iRBY2/EWINBirTfkZegotulAfWls/m0qxlQfGA3zeLbPipvcJzuv6jh/czA1Df6Ba8KKXobXivNUjcXsrotgbCe4YBmxmfIRJ3+77g==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxDlVcNsZAIj1lBExh0Ff1fUEpA+uTdRyZqwWlBg8dP9UCTJKxtd8O2HeMrt+BL1T6dwv0B7Z7Mqgpss8QlDf4NQZriqcq/h6sM35G3ZixHDQtqJf5Llo9As0PXaaohLH608MsoJoc3Mx6CZmvDxXnB3mERL+g2JvLcwdOOx2M5OM50ZellIdxdOksrcJG+AgYXcP01KeFR6+f6Tfu7FR3rGjP4o5XpEsVR4K5ExEEAeEoJbuUGD/FhqUpZsd7aPunYZC3DvQAU2qVJz5vniBkQ3i3f92WRe3NXuU8qvTkVgqPoTQZ3+GIawXzYApHIFld4+uOqqkw8honwMp6zcx3QIDAQAB";

    // 服务器异步通知页面路径  需htt
    // p://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 不能重定向
    public static String notify_url = "http://sd.kuai8.com.cn/returnPay/appaliReturnPay";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 不能重定向
    public static String return_url = "http://sd.kuai8.com.cn/returnPay/appaliReturnPay";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";

    // 支付宝打印日志存放路径
    public static String log_path = "E:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord,String order,String trade) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
            writer.write(order);
            writer.write(trade);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}