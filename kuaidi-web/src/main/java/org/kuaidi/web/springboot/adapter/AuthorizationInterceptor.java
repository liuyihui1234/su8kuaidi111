package org.kuaidi.web.springboot.adapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;
import org.kuaidi.web.springboot.core.authorization.Authorization;
import org.kuaidi.web.springboot.util.redis.RedisUtil;
import org.kuaidi.web.springboot.util.redis.TokenManager;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import net.sf.json.JSONObject;


public class AuthorizationInterceptor implements HandlerInterceptor  {
	
	// 存放鉴权信息的Header名称，默认是token
	private String httpHeaderName = "token";

	// 鉴权信息的无用前缀，默认为空
	private String httpHeaderPrefix = "";
	
	@Autowired
	private TokenManager manager;
	
	@Autowired
	private RedisUtil redisUtil;
	
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		request.setCharacterEncoding("UTF-8");
		// 如果不是映射到方法直接通过
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		String URI = request.getRequestURI();
		System.out.println(URI);
		if(StringUtils.isNotEmpty(URI) && URI.startsWith("\\web\\")) {
			return true; 
		}
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		// 从header中得到token
		
		if (method.getAnnotation(Authorization.class) != null // 查看方法上是否有注解
				|| handlerMethod.getBeanType().getAnnotation(Authorization.class) != null) {
			JSONObject  data = new JSONObject();
			data.put("code", 401);
			data.put("msg", "请先登录！");
			data.put("data", null);
			response.setStatus(200);
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.getWriter().write(data.toString());
			response.getWriter().flush();
			return false; 
		}else {
			String token = request.getHeader(httpHeaderName);
			if (token != null && token.startsWith(httpHeaderPrefix) && token.length() > 0) {
				token = token.substring(httpHeaderPrefix.length());
				boolean rst =  manager.checkKey(URI, token);
				// 验证token
				if(!rst) {
					JSONObject  data = new JSONObject();
					data.put("code", 401);
					data.put("msg", "用户登录超时，请重新登录！");
					data.put("data", null);
					response.setStatus(200);
					response.setContentType(MediaType.APPLICATION_JSON_VALUE);
					response.getWriter().write(data.toString());
					response.getWriter().flush();
					return false; 
				}
				return true;
			}
		}
		// 如果验证token失败，并且方法注明了Authorization，返回401错误
		return true;
	}

}
