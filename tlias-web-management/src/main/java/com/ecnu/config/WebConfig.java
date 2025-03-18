package com.ecnu.config;

import com.ecnu.interceptor.DemoInterceptor;
import com.ecnu.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

//    @Autowired
//    private DemoInterceptor demoInterceptor;
    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**") //拦截所有请求
                .excludePathPatterns("/login"); //不拦截哪些请求
        /*
        *             /* 一级路径 能匹配/depts /emps /login，不能匹配 /depts/1
        *             /** 任意级路径 能匹配/depts /emps /login /depts/1 /depts/1/emps
        *             /depts/* /depts下的一级路径 能匹配/depts/1，不能匹配 /depts/1/emps
        *             /depts/** /depts下任意级路径
        * */
    }
}
