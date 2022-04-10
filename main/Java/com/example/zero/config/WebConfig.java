package com.example.zero.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.zero.interceptor.AdminInterceptor;

@Configuration  //将以配置的方式交给Spring
public class WebConfig implements WebMvcConfigurer{
	
   @Autowired
   private AdminInterceptor adminIntercepor;
   
   //配置拦截器
   @Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(adminIntercepor)
		.addPathPatterns("/**") // 拦截所有路径
		.excludePathPatterns("/admin/login","/admin/vercode",
				"/layui/**","/images/**","/admin/add"); //放行路径
	}
   
}  
