package org.kuaidi.web.springboot.adapter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Bean
    public AuthorizationInterceptor getTokenInterceptor() {
        return new AuthorizationInterceptor();
    }

    @Bean
    public WebLoginInterceptor getLoginInterceptor() {
        return new WebLoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getTokenInterceptor()).addPathPatterns("/app/**").excludePathPatterns("/web/**", "/auth/**", "/login/**");
        registry.addInterceptor(getLoginInterceptor()).addPathPatterns("/web/**").excludePathPatterns("/app/**", "/auth/**", "/login/**");
    }

}
