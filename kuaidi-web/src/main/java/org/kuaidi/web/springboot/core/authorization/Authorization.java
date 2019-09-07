package org.kuaidi.web.springboot.core.authorization;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *APP接口身份验证用户进行身份验证，验证失败返回401错误
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Authorization {
	boolean login() default true;/*是否登录*/
}