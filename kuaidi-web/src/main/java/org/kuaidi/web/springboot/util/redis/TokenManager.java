package org.kuaidi.web.springboot.util.redis;

import org.apache.commons.lang3.StringUtils;
import org.kuaidi.bean.Config;
import org.kuaidi.bean.domain.EforcesIncment;
import org.kuaidi.bean.domain.EforcesUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.sf.json.JSONObject;

@Component
public class TokenManager {
	
	@Autowired
	private RedisUtil redisUtil;
	
	/*
	 * 将redis中保存的用户信息用JSON格式保存
	 */
	public String checkKey(String uri , String token) {
		if(StringUtils.isNotEmpty(uri) && uri.startsWith("/web/") ) {
			String userInfo = redisUtil.get(Config.REDISWEBLOGINPREX + token);
			if(StringUtils.isNotEmpty(userInfo)) {
				redisUtil.setExpire(Config.REDISWEBLOGINPREX + token,Config.EXPIRETIME);
				return userInfo;
			}else {
				return null;
			}
		}else {
			String userInfo = redisUtil.get(Config.REDISAPPLOGINPREX + token);
			if(StringUtils.isNotEmpty(userInfo)) {
				redisUtil.setExpire(Config.REDISAPPLOGINPREX + token, 2*3600);
				return userInfo;
			}else {
				return null;
			}
		} 
	}
	
}
