package org.kuaidi.web.springboot.controller.weixin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.kuaidi.web.springboot.util.HttpGet;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sf.json.JSONObject;

@RestController
@RequestMapping("/weixin/wechatUser/")
public class WeiXinUserController {
	
	//根据用户的openId获得用户的详情，并插入数据库
	
	@RequestMapping("addUserInfo")
    @CrossOrigin
    public ResultVo saveUserInfo(String openId) {
		
		return ResultUtil.exec(false, "添加失败！", null);
	}
	
	@RequestMapping("getOpenId")
    @CrossOrigin
    public ResultVo getOpenId(HttpServletRequest request) {
		System.out.println(">>>>>>>>>>>>start >>>>>>>>>>>>");
		String code = request.getParameter("code");
		
		Map params = new HashMap();
		params.put("secret", "wx3e938056214367ec");
		params.put("appid", "548a67191b1a1a3c34aefc3b3f0804d6");
		params.put("grant_type", "authorization_code");
		params.put("code", code);
		String result = HttpGet.get("http://open.weixin.qq.com/connect/oauth2/authorize",params);
		
		JSONObject jsonObject = JSONObject.fromObject(result);
		String openid = jsonObject.get("openid").toString();
		System.out.println(openid);
		return ResultUtil.exec(false, "添加失败！", null);
	}
	
}
