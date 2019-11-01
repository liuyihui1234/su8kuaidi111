package org.kuaidi.web.springboot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.kuaidi.bean.Config;
import org.kuaidi.bean.domain.EforcesIncment;
import org.kuaidi.bean.domain.EforcesUser;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.bean.vo.TokenVo;
import org.kuaidi.iservice.IEforcesIncmentService;
import org.kuaidi.iservice.UserService;
import org.kuaidi.utils.Md5Util;
import org.kuaidi.utils.SendPhoneCode;
import org.kuaidi.web.springboot.core.authorization.Authorization;
import org.kuaidi.web.springboot.dubboservice.UserLoginService;
import org.kuaidi.web.springboot.util.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/login/")
@CrossOrigin
public class UserLoginController {

    @Reference(version = "1.0.0")
    private UserService userService;

    @Reference(version = "1.0.0")
    private IEforcesIncmentService incentService;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    private UserLoginService userLoginService;

    @RequestMapping("dologin")
    @CrossOrigin
    @ResponseBody
    public ResultVo doLogin(EforcesUser user, Integer type) {
        return userLoginService.UserdoLogin(user, type );
    }


    /**
     * web端页面首页首次打开判断token是否有效
     * @return
     */
    @RequestMapping("checktoken")
    @CrossOrigin
    @ResponseBody
    public ResultVo Login(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        boolean exist = redisUtil.isExist(Config.REDISWEBLOGINPREX + token);
        String str = exist ? "验证通过" : "登录过期,请重新登录";
        return ResultUtil.exec(exist, str, null);
    }


    /**
     * web端修改密码
     * @return
     */
    @RequestMapping("modifywebpsw")
    @CrossOrigin
    @ResponseBody
    public ResultVo modify(String number, String psw, String newpsw) {
        EforcesUser eforcesUser = userService.selectUser(number);
        if (!eforcesUser.getPassword().equals(Md5Util.encode(psw))) {
            return ResultUtil.exec(false, "原密码错误", null);
        }
        EforcesUser user = new EforcesUser();
        user.setPassword(Md5Util.encode(newpsw));
        user.setId(eforcesUser.getId());
        Integer info = userService.updateUserInfo(user);
        if(info==0){
            ResultUtil.exec(false, "修改密码失败", null);
        }
        return ResultUtil.exec(true, "修改密码成功", null);
    }

    /**
     * web端登录
     * @param user
     * @return
     */
    @RequestMapping("login")
    @CrossOrigin
    @ResponseBody
    public ResultVo Login(EforcesUser user) {
        TokenVo tokenVo = new TokenVo();
        //校验验证码
        String s = redisUtil.get(Config.WEBCODE + user.getAccess_token());
        if (StringUtils.isEmpty(s)) {
            return ResultUtil.exec(false, "验证码过期！", null);
        }
        if (!user.getCode().equalsIgnoreCase(s)) {
            return ResultUtil.exec(false, "验证码错误！", null);
        }
        String password = Md5Util.encode(user.getPassword());
        EforcesUser user1 = userService.selectUser(user.getNumber());
        if (user1 == null) {
            List<EforcesUser> userList = userService.selectUserByPhone(user.getNumber());
            if (userList != null && userList.size() > 0) {
                user1 = userList.get(0);
            }
        }
        if (user1 == null) {
            return ResultUtil.exec(false, "账户不存在！", null);
        } else {
            if (user1.getPassword() != null && StringUtils.equals(user1.getPassword(), password)) {
                String encode = Md5Util.encode(user.getNumber() + user.getId());
                user1.setAccess_token(encode);
                JSONObject userInfo = JSONObject.fromObject(user1);
                JSONObject data = new JSONObject();
                data.put("userInfo", userInfo);
                EforcesIncment incment = incentService.selectByNumber(user1.getIncnumber());
                if (StringUtils.isNotEmpty(incment.getLevel() + "")) {
                    incment.setNumber(incment.getNumber().substring(0, incment.getLevel() * 2));
                }
                JSONObject incInfo = JSONObject.fromObject(incment);
                data.put("incInfo", incInfo);
                redisUtil.set(Config.REDISWEBLOGINPREX + encode, data.toString(), Config.EXPIRETIME);
                return ResultUtil.exec(true, "登录成功！", user1);
            } else {
                return ResultUtil.exec(false, "密码错误！", null);
            }
        }
    }

    @RequestMapping("modifyPwd")
    @ResponseBody
    public ResultVo addUserInfo(String phoneNum, String smsCode, String newPwd) {
        try {
            if (StringUtils.isEmpty(smsCode)) {
                return ResultUtil.exec(false, "验证码不能为空！", null);
            }
            String validateCode = redisUtil.get(Config.redisPhonePrex + phoneNum);
            if (StringUtils.isEmpty(validateCode) || !StringUtils.equals(validateCode, smsCode)) {
                return ResultUtil.exec(false, "验证码错误！", null);
            }
            List<EforcesUser> userList = userService.selectUserByPhone(phoneNum);
            if (userList == null || userList.size() == 0) {
                return ResultUtil.exec(false, "用户信息不存在！", null);
            }
            EforcesUser userInfo = userList.get(0);
            if(userInfo != null && StringUtils.equals(userInfo.getPassword(), Md5Util.encode(newPwd))) {
            	return ResultUtil.exec(false, "新密码不能和旧密码一样。", null);
            }
            userInfo.setPassword(Md5Util.encode(newPwd));
            Integer rst = userService.updateUserInfo(userInfo);
            if (rst > 0) {
                return ResultUtil.exec(true, "修改密码成功！", null);
            }
            return ResultUtil.exec(false, "修改密码异常！", null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false, "修改密码异常！", null);
        }
    }

    @RequestMapping("addUserInfo")
    @ResponseBody
    public ResultVo addUserInfo(Integer netSignId, String userName, String password) {
        return userLoginService.addUserInfo(netSignId, userName, password);
    }

    @RequestMapping("addUserName")
    @ResponseBody
    public ResultVo addUserName(String realName, String phone, String address) {
        return userLoginService.addUserInfo(realName, phone, address);
    }

    @RequestMapping("updateUserInfo")
    @ResponseBody
    public ResultVo updateUserInfo(Integer userId, String userName, String password) {
        return userLoginService.updateUserInfo(userId, userName, password);
    }

    @RequestMapping("crtUserNumber")
    @ResponseBody
    public ResultVo crtUserNumber(Integer userId) {
        return userLoginService.crtUserNUmber(userId);
    }

    /**
     * 开通小号
     *
     * @param userId   用户
     * @param mobile   手机号
     * @param password 密码
     * @param smsCode  验证码
     * @return
     */
    @RequestMapping("openTrumpet")
    @ResponseBody
    public ResultVo openTrumpet(Integer userId, String mobile, String password, String smsCode) {
        return userLoginService.openTrumpet(userId, mobile, password, smsCode);
    }


    @RequestMapping("findUserById")
    @ResponseBody
    public ResultVo doFindUserById(Integer id) {
        return userLoginService.findUserById(id);
    }

    /**
     * 修改用户名、手机号
     * @return
     */
    @RequestMapping("updateUserIdentity")
    @ResponseBody
    public ResultVo updateUserIdentity(HttpServletRequest request, String name, String portraitpath, String identitynum,
                                       String identityfontpath, String identitybackpath, String smsCode) {
        String token = request.getHeader("token");
        String userData = redisUtil.get(Config.REDISAPPLOGINPREX + token);
        if (StringUtils.isEmpty(userData)) {
            return ResultUtil.exec(false, "用户请先登录！", null);
        }
        JSONObject data = JSONObject.fromObject(userData);
        JSONObject userInfo = data.getJSONObject("userInfo");
        EforcesUser eforcesUser = (EforcesUser) JSONObject.toBean(userInfo, EforcesUser.class);
        String mobile = eforcesUser.getMobile();
        String oldSmsCode = redisUtil.get(Config.redisPhonePrex + mobile);
        if (oldSmsCode == null || !StringUtils.equals(oldSmsCode, smsCode)) {
            return ResultUtil.exec(false, "验证码错误！", null);
        }
        return userLoginService.updateUseMsg(eforcesUser, name, portraitpath, identitynum, identityfontpath, identitybackpath);
    }


    @RequestMapping("updateUserMsg")
    @ResponseBody
    public ResultVo updateUserMsg(int id, String Name, String Mobile, String portraitpath) {
        return userLoginService.updateMsg(id, Name, Mobile, portraitpath);
    }

    /**
               * 短信验证码测试
     *(实名制认证的时候用)
     *
     * @param telephone
     */
    @RequestMapping("modifyPWD/sendSMS")
    @ResponseBody
    public ResultVo SendPhone1(String telephone) {
    	if(telephone == null || telephone.length() != 11) {
    		return ResultUtil.exec(false, "手机号错误，请确定！", "") ;
    	}
    	if(redisUtil.get(Config.redisPhonePrex+telephone) != null ) {
			return ResultUtil.exec(false, "三分钟之内不能重复的获得验证码！", "") ;
		}
        // 根据userid  user  table 查询记录
        // 传的手机号和保存的手机号是否一致。
        SendPhoneCode phoneCode = new SendPhoneCode();
        String verCode = "";
        try {
            List<EforcesUser> userList = userService.selectUserByPhone(telephone);
            if (userList == null || userList.size() == 0) {
                return ResultUtil.exec(false, "用户信息不存在！", null);
            }
            EforcesUser userInfo = userList.get(0);
            if (userInfo != null) {
                verCode = phoneCode.sendCode(telephone);
            }
            // 十分钟有效
            if (StringUtils.isNotEmpty(verCode)) {
                redisUtil.set(Config.redisPhonePrex + telephone, verCode, Config.YZMVALIDATETIME * 60);
            }
            // 验证一下用户信息。
        } catch (com.aliyuncs.exceptions.ClientException e) {
            e.printStackTrace();
            return ResultUtil.exec(false, "发送验证码失败！", "");
        }
        return ResultUtil.exec(true, "发送验证码成功！", verCode);
    }
    
    @RequestMapping("appLoginOut")
    @CrossOrigin
    @ResponseBody
    @Authorization
    public ResultVo appLoginOut(HttpServletRequest request) {
    	EforcesUser  userInfo = (EforcesUser)request.getAttribute("user");
    	String token = userInfo.getToken();
        redisUtil.del(Config.REDISAPPLOGINPREX+token);
        return ResultUtil.exec(true, "退出成功！", null);
    }

}
