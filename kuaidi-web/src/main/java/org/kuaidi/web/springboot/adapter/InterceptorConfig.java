package org.kuaidi.web.springboot.adapter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	
	@Bean
    public AuthorizationInterceptor getTokenInterceptor(){
        return new AuthorizationInterceptor();
    }
	
	 @Override
     public void addInterceptors(InterceptorRegistry registry) {
       	 registry.addInterceptor(getTokenInterceptor()).addPathPatterns("/**");
     }

}
