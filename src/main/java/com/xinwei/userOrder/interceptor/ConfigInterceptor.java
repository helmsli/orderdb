package com.xinwei.userOrder.interceptor;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ConfigInterceptor extends WebMvcConfigurerAdapter{
	
	/*
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       // registry.addInterceptor(tokenInterceptor).addPathPatterns("/**");
    }
    */
    /**
     * 支持Ajax的跨域请求
     */
    @Override  
    public void addCorsMappings(CorsRegistry registry) {  
        registry.addMapping("/**")  
                .allowedOrigins("*")  
                .allowCredentials(true)  
                .allowedMethods("GET", "POST", "DELETE", "PUT")  
                .maxAge(3600);  
    }  
}