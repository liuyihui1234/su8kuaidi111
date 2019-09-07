package org.kuaidi.web.springboot.testcontrller;

import org.apache.commons.lang3.StringUtils;
import org.kuaidi.bean.Config;
import org.kuaidi.bean.domain.EforcesUser;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.utils.SendPhoneCode;
import org.kuaidi.web.springboot.util.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sendSMS/")
public class TestController {
	
	@Autowired
	private RedisUtil  redisUtil;

    /**
            * 短信验证码测试
     * @param telephone
     */
    @RequestMapping("SendPhone")
    @ResponseBody
    public ResultVo testSms(String telephone,Integer userId){
        // 根据userid  user  table 查询记录
        // 传的手机号和保存的手机号是否一致。
        SendPhoneCode phoneCode = new SendPhoneCode();
        String verCode = "";
        try {
        	// 验证一下用户信息。
        	verCode = phoneCode.sendCode(telephone);
        } catch (com.aliyuncs.exceptions.ClientException e) {
            e.printStackTrace();
        }
        // 十分钟有效
        if(StringUtils.isNotEmpty(verCode)) {
        	redisUtil.set(Config.redisPhonePrex + telephone, verCode, 10*60);
        }
        return ResultUtil.exec(true, "发送验证码成功！", verCode);
    }

    @RequestMapping("regOtherUser")
    @ResponseBody
    public void regOtherUser(EforcesUser user, String smsCode){
        user.getMobile() ;
        //从user表取出手机号 去redis取出验证码 判断与前端传回来的是否一致

        //不一致， 提示验证码错误


        //正确， 走以前的逻辑。


        //

    }
}
