package org.kuaidi.web.springboot.adapter;

import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.kuaidi.bean.Config;
import org.kuaidi.bean.domain.EforcesIncment;
import org.kuaidi.bean.domain.EforcesUser;
import org.kuaidi.web.springboot.core.authorization.Authorization;
import org.kuaidi.web.springboot.core.authorization.NeedUserInfo;
import org.kuaidi.web.springboot.util.redis.RedisUtil;
import org.kuaidi.web.springboot.util.redis.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;


public class WebLoginInterceptor implements HandlerInterceptor {

    // 存放鉴权信息的Header名称，默认是token
    private String httpHeaderName = "Authorization";

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

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 从header中得到token
        String token = request.getHeader(httpHeaderName);
        if (token != null && token.startsWith(httpHeaderPrefix) && token.length() > 0) {
            String rst = manager.checkKey(URI, token);
            // 验证token
            if (StringUtils.isEmpty(rst)) {
                JSONObject data = new JSONObject();
                data.put("msg", "用户登录超时，请重新登录！");
                data.put("data", null);
                response.setStatus(402);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.getWriter().write(data.toString());
                response.getWriter().flush();
                return false;
            }else{
                if (method.getAnnotation(NeedUserInfo.class) != null) {
                    JSONObject data = JSONObject.fromObject(rst);
                    JSONObject userInfo = data.getJSONObject("userInfo");
                    EforcesUser eforcesUser = (EforcesUser) JSONObject.toBean(userInfo, EforcesUser.class);
                    JSONObject incInfo = data.getJSONObject("incInfo");
                    EforcesIncment eforcesIncment = (EforcesIncment)JSONObject.toBean(incInfo, EforcesIncment.class);
                    request.setAttribute("user",eforcesUser);
                    request.setAttribute("inc",eforcesIncment);
                }
            }
            return true;
        }else {
            JSONObject data = new JSONObject();
            data.put("msg", "请登录后再进行操作");
            data.put("data", null);
            response.setStatus(401);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(data.toString());
            response.getWriter().flush();
            return false;
        }
    }
}
