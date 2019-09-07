package org.kuaidi.web.springboot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.aliyuncs.exceptions.ClientException;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.kuaidi.bean.Config;
import org.kuaidi.bean.domain.EforcesIncment;
import org.kuaidi.bean.domain.EforcesUser;
import org.kuaidi.bean.domain.EforcesUser1;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.bean.vo.TokenVo;
import org.kuaidi.iservice.IEforcesIncmentService;
import org.kuaidi.iservice.UserService;
import org.kuaidi.utils.Md5Util;
import org.kuaidi.utils.UUIDUtil;
import org.kuaidi.web.springboot.dubboservice.UserLoginService;
import org.kuaidi.web.springboot.util.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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
    public ResultVo doLogin(EforcesUser user) {
        return userLoginService.UserdoLogin(user);
    }


    @RequestMapping("loginOut")
    @CrossOrigin
    @ResponseBody
    public ResultVo LoginOut(Integer uuid) {
        redisUtil.del(Config.REDISWEBLOGINPREX+uuid);
        return ResultUtil.exec(true, "退出成功！", null);
    }


    /**
     * web端登录
     *
     * @param user
     * @return
     */
    @RequestMapping("login")
    @CrossOrigin
    @ResponseBody
    public ResultVo Login(EforcesUser user) {
    	System.err.println(user);
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
        System.err.println(password);
        EforcesUser user1 = userService.selectUser(user.getNumber());
        System.err.println(user1);
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
                String uuid = UUIDUtil.getUUID();
                user1.setAccess_token(uuid);
                JSONObject userInfo = JSONObject.fromObject(user1);
                JSONObject data = new JSONObject();
                data.put("userInfo", userInfo);
                EforcesIncment incment =  incentService.selectByNumber(user1.getIncnumber());
                JSONObject incInfo = JSONObject.fromObject(incment);
                data.put("incInfo", incInfo);
                redisUtil.set(Config.REDISWEBLOGINPREX+uuid, data.toString(), Config.EXPIRETIME);
                return ResultUtil.exec(true, "登录成功！", user1);
            } else {
                return ResultUtil.exec(false, "密码错误！", null);
            }
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

    /**
     * 开通小号 获取验证码，判断手机号是否已经被注册，如果是则不能再注册、并把验证码和手机号存到Reids里面
     *
     * @param mobile
     * @return 返回值为1表示成功，返回值为0则失败
     * @throws ClientException
     */
    @RequestMapping("openTrumpetsmsCode")
    @ResponseBody
    public int openTrumpetsmsCode(String mobile) throws ClientException {
        return userLoginService.openTrumpetsmsCode(mobile);
    }

    @RequestMapping("findUserById")
    @ResponseBody
    public ResultVo doFindUserById(Integer id) {
        return userLoginService.findUserById(id);
    }

    /**
     * 修改用户名、手机号
     *
     * @param id 条件
     * @return
     */
    @RequestMapping("updateUserIdentity")
    @ResponseBody
    public ResultVo updateUserIdentity(int id, String name, String portraitpath, String identitynum,
                                       String identityfontpath, String identitybackpath) {
        return userLoginService.updateUseMsg(id, name, portraitpath, identitynum, identityfontpath, identitybackpath);
    }


    @RequestMapping("updateUserMsg")
    @ResponseBody
    public ResultVo updateUserMsg(int id, String Name, String Mobile, String portraitpath) {
        return userLoginService.updateMsg(id, Name, Mobile, portraitpath);
    }
}
