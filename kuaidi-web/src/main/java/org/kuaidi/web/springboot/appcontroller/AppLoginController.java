package org.kuaidi.web.springboot.appcontroller;

import org.apache.commons.lang.StringUtils;
import org.kuaidi.bean.Config;
import org.kuaidi.bean.domain.EforcesUser;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.UserService;
import org.kuaidi.utils.SendPhoneCode;
import org.kuaidi.web.springboot.dubboservice.UserLoginService;
import org.kuaidi.web.springboot.util.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.aliyuncs.exceptions.ClientException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/app/appLogin/")
public class AppLoginController {

	@Autowired
    RedisUtil redisUtil;
	
	@Reference(version = "1.0.0")
    private UserService userService;
	
	@Autowired
    private UserLoginService userLoginService;
	
	@RequestMapping("loginOut")
    @CrossOrigin
    @ResponseBody
    public ResultVo LoginOut(HttpServletRequest request) {
		try {
			EforcesUser  userInfo = (EforcesUser)request.getAttribute("user");
	    	String token = userInfo.getToken();
	        redisUtil.del(Config.REDISAPPLOGINPREX+token);
	        return ResultUtil.exec(true, "退出成功！", null);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ResultUtil.exec(true, "退出成功！", null);
    }
	
	@RequestMapping("SendPhone")
    @CrossOrigin
    @ResponseBody
    public ResultVo  SendPhone(HttpServletRequest  request, String telephone ,Integer type){
    	// 如果实名制认证为空或者为 1 的时候， 
		if(telephone == null || telephone.length() != 11) {
    		return ResultUtil.exec(false, "手机号错误，请确定！", "") ;
    	}
    	if(redisUtil.get(Config.redisPhonePrex+telephone) != null ) {
			return ResultUtil.exec(false, "十分钟之内不能重复的获得验证码！", "") ;
		}
    	if(type == null  || type == 1 ) {
    		// 表示已经登录了
    		EforcesUser  userInfo = (EforcesUser)request.getAttribute("user");
    		if(userInfo == null ) {
    			return ResultUtil.exec(false, "请用户先登录！", null);
    		}
    		if(StringUtils.equals(userInfo.getMobile(), telephone)) {
    			SendPhoneCode  sendPhone = new SendPhoneCode();
    			String validateCode = null;
				try {
					validateCode = sendPhone.sendCode(telephone);
					redisUtil.set(Config.redisPhonePrex + telephone, validateCode, 10*60);
	    			return ResultUtil.exec(true, "发送验证码成功！", validateCode);
				} catch (ClientException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return ResultUtil.exec(false, "验证码发送失败！", null);
				}
    		}else {
    			return ResultUtil.exec(false, "请输入正确的手机号！", null);
    		}
    	}else if(type == 2 ){
    		List<EforcesUser> userList = userService.selectUserByPhone(telephone);
    		if(userList != null && userList.size() > 0 ) {
    			return ResultUtil.exec(false, "手机号已经存在，不能重复设置！", null);
    		}
    		SendPhoneCode  sendPhone = new SendPhoneCode();
    		String validateCode = null;
			try {
				validateCode = sendPhone.sendCode(telephone);
				redisUtil.set(Config.redisPhonePrex + telephone, validateCode, 12000);
				return ResultUtil.exec(true, "发送验证码成功！", validateCode);
			} catch (ClientException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ResultUtil.exec(false, "验证码发送失败！", null);
			}
    	}
    	return ResultUtil.exec(false, "参数错误！", null);
    }
	
	/**
               * 开通小号 获取验证码，判断手机号是否已经被注册，如果是则不能再注册、并把验证码和手机号存到Reids里面
     * @param mobile
     * @return 返回值为1表示成功，返回值为0则失败
     * @throws ClientException
     */
    @RequestMapping("openTrumpetsmsCode")
    @ResponseBody
    public ResultVo openTrumpetsmsCode(String mobile) throws ClientException {
        return userLoginService.openTrumpetsmsCode(mobile);
    }

}
