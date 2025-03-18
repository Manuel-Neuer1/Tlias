package com.ecnu.filter;

import com.ecnu.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        //1 获取请求路径
        String requestURI = httpServletRequest.getRequestURI();
        //2 判断是否是登录请求，如果路径中包含/login，放行
        if(requestURI.contains("/login")){
            log.info("登录请求，放行");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        //3 从请求头中获取token
        String token = httpServletRequest.getHeader("token");
        //4 判断token是否存在，如果不存在，返回错误结果（未登录401）
        if(token == null || token.isEmpty()){
          log.info("请求头中无token，返回未登录结果401");
          httpServletResponse.setStatus(401);
          return;
        }
        //5 如果token存在，解析token，如果解析失败，返回错误结果（未登录401）
        try{
            JwtUtils.parseJWT(token);
        } catch (Exception e){
            log.info("请求头中token非法，返回未登录结果401");
            httpServletResponse.setStatus(401);
            return;
        }
        //6 如果token解析成功，放行
        log.info("token合法，放行");
        filterChain.doFilter(servletRequest, servletResponse);
        return;
    }
}
