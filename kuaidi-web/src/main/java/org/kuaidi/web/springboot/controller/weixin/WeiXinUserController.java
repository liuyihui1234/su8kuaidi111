package org.kuaidi.web.springboot.controller.weixin;

import java.util.Map;

import org.kuaidi.bean.domain.EforcesWechatUser;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.EforcesWechatUserService;
import org.kuaidi.web.springboot.config.WxMaProperties;
import org.kuaidi.web.springboot.util.HttpGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;

import net.sf.json.JSONObject;

@RestController
@RequestMapping("/weixin/wechatUser")
public class WeiXinUserController {
	@Autowired
    private WxMaProperties properties;
	@Reference(version = "1.0.0")
	private EforcesWechatUserService eforcesWechatUserService;
	
	//根据用户的openId获得用户的详情，并插入数据库
	
	//@ResponseBody
    //@PostMapping("/login")
    public ResultVo saveUserInfo(String openId) {
		EforcesWechatUser user = eforcesWechatUserService.selectByPrimaryKey(openId);//查询是否已经是老用户
		if(user==null) {//新用户授权
			
			eforcesWechatUserService.insert(user);
		}
		return ResultUtil.exec(false, "添加失败！", null);
	}
	
    @ResponseBody
    @PostMapping("/auth")
    public ResultVo getOpenId(@RequestBody Map<String,String> params) {
		System.out.println(">>>>>>>>>>>>start >>>>>>>>>>>>");
		String code=params.get("code")!=null?params.get("code").toString():"";
        if (StringUtils.isBlank(code)) {
        	return ResultUtil.exec(false, "添加失败！", null);
        }
        params.clear();
        params.put("js_code", code);
        params.put("grant_type", "authorization_code");
		params.put("secret",properties.getConfigs().get(0).getSecret());
		params.put("appid",properties.getConfigs().get(0).getAppid());
		
		
		String result = HttpGet.get("https://api.weixin.qq.com/sns/jscode2session",params);
		
		JSONObject jsonObject = JSONObject.fromObject(result);
		String openid = jsonObject.get("openid").toString();
		System.out.println(openid);
		return ResultUtil.exec(false, "添加失败！", null);
	}
	
}
