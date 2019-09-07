package org.kuaidi.web.springboot.util.redis;

import org.apache.commons.lang3.StringUtils;
import org.kuaidi.bean.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenManager {
	
	@Autowired
	private RedisUtil redisUtil;
	
	/*
	 * 将redis中保存的用户信息用JSON格式保存
	 */
	public boolean checkKey(String uri , String token) {
		if(StringUtils.isNotEmpty(uri) && uri.startsWith("/web/") ) {
			String userInfo = redisUtil.get(Config.REDISWEBLOGINPREX + token);
			if(StringUtils.isNotEmpty(userInfo)) {
				redisUtil.setExpire(Config.REDISWEBLOGINPREX + token,Config.EXPIRETIME);
				return true;
			}else {
				return false;
			}
		}else {
			String userInfo = redisUtil.get(Config.REDISAPPLOGINPREX + token);
			if(StringUtils.isNotEmpty(userInfo)) {
				redisUtil.set(Config.REDISAPPLOGINPREX + token, userInfo, 2*3600);
				return true; 
			}else {
				return false;
			}
		} 
	}
	
}
