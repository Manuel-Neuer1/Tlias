package com.ecnu.interceptor;

import com.ecnu.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1 获取请求路径
        String requestURI = request.getRequestURI();
        //2 判断是否是登录请求，如果路径中包含/login，放行
        if(requestURI.contains("/login")){
            log.info("登录请求，放行");
            return true;
        }
        //3 从请求头中获取token
        String token = request.getHeader("token");
        //4 判断token是否存在，如果不存在，返回错误结果（未登录401）
        if(token == null || token.isEmpty()){
            log.info("请求头中无token，返回未登录结果401状态码");
            response.setStatus(401);
            return false;
        }
        //5 如果token存在，解析token，如果解析失败，返回错误结果（未登录401）
        try{
            JwtUtils.parseJWT(token);
        } catch (Exception e){
            log.info("请求头中token非法，返回未登录结果401状态码");
            response.setStatus(401);
            return false;
        }
        //6 如果token解析成功，放行
        log.info("token合法，放行");

        return true;
    }

}
