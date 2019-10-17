package org.kuaidi.web.springboot.controller.weixin;

import java.util.HashMap;
import java.util.Map;

import org.kuaidi.bean.domain.EforcesWechatUser;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.EforcesWechatUserService;
import org.kuaidi.web.springboot.config.WxMaProperties;
import org.kuaidi.web.springboot.util.AesCbcUtil;
import org.kuaidi.web.springboot.util.HttpGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;

@RestController
@RequestMapping("/weixin/wechatUser")
public class WeiXinUserController {
	@Autowired
    private WxMaProperties properties;
	@Reference(version = "1.0.0")
	private EforcesWechatUserService eforcesWechatUserService;

	
	//根据用户的openId获得用户的详情，并插入数据库
	
	@RequestMapping("addUser")
    @CrossOrigin
    public ResultVo saveUserInfo(EforcesWechatUser user) {
			if(eforcesWechatUserService.insert(user)>0) {
				return ResultUtil.exec(true, "添加成功！", user);
			}else {
				return ResultUtil.exec(false, "添加失败！", null);
			}
		
		
	}
	
    @RequestMapping("auth")
    @CrossOrigin
    public ResultVo getOpenId(@RequestBody Map<String, Object> map) {
		System.out.println(">>>>>>>>>>>>start >>>>>>>>>>>>");
		String code=map.get("code")==null?"":map.get("code").toString();
		String encryptedData=map.get("encryptedData")==null?"":map.get("encryptedData").toString();
		String iv=map.get("iv")==null?"":map.get("iv").toString();
        if (StringUtils.isBlank(code)) {
        	return ResultUtil.exec(false, "添加失败！", null);
        }
        Map<String,String> params=new HashMap<String, String>();
        params.clear();
        params.put("js_code", code);
        params.put("grant_type", "authorization_code");
		params.put("secret",properties.getConfigs().get(0).getSecret());
		params.put("appid",properties.getConfigs().get(0).getAppid());
		String result = HttpGet.get("https://api.weixin.qq.com/sns/jscode2session",params);
		System.out.println(result);
		 
		JSONObject jsonObject = JSONObject.parseObject(result);
		String session_key = jsonObject.getString("session_key").replace(" ","+");
		try {
			String decrypts = AesCbcUtil.decrypt(encryptedData, session_key,
					iv, "UTF-8");
			JSONObject jsons = JSONObject.parseObject(decrypts);
			String openid = jsons.get("openId").toString();
			EforcesWechatUser user = eforcesWechatUserService.selectByPrimaryKey(openid);
			if(user==null) {//新用户
				EforcesWechatUser userDo=new EforcesWechatUser();
				userDo.setCity(jsons.get("city").toString());
				userDo.setCountry(jsons.get("country").toString());
				userDo.setHeadimgurl(jsons.get("avatarUrl").toString());
				userDo.setOpenid(openid);
				userDo.setProvince(jsons.get("province").toString());
				userDo.setNickname(jsons.get("nickName").toString());
               String gender = jsons.get("gender").toString();
                if("2".equals(gender)){
                	userDo.setSex(2);
                }else {
                	userDo.setSex(1);
                }
                if(eforcesWechatUserService.insertSelective(userDo)>0) {
                	return ResultUtil.exec(true, "用户授权成功", userDo);
                }else {
                	return ResultUtil.exec(true, "用户授权失败", null);
                }
				
			}else {//老用户
				return ResultUtil.exec(true, "登录成功", user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(true, "新用户授权失败", null);
		}
		
	}
	
}
