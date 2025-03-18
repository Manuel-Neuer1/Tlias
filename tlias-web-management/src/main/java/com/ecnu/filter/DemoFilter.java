package com.ecnu.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;


//@WebFilter(urlPatterns = "/*") // 拦截所有请求
@Slf4j
public class DemoFilter implements Filter {

    //初始化方法，web服务器启动的时候执行，只执行一次
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("DemoFilter init 初始化方法成功运行 ......");
    }

    //拦截到请求之后，会执行多次
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("DemoFilter doFilter 过滤方法开始运行 ......");
        // 将拦截到的请求放行
        filterChain.doFilter(servletRequest, servletResponse);
    }

    //销毁方法，web服务器关闭的时候执行，只执行一次
    @Override
    public void destroy() {
        log.info("DemoFilter destroy 销毁方法成功运行 ......");
    }
}
