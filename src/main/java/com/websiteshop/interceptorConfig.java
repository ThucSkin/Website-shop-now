package com.websiteshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.websiteshop.interceptor.GlobalInterceptor;

public class interceptorConfig implements WebMvcConfigurer{
    @Autowired
    GlobalInterceptor globalInterceptor;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(globalInterceptor)
        .addPathPatterns("/**")
        .excludePathPatterns("/rest/**","/admin/**","/assets/**");
    }

}
