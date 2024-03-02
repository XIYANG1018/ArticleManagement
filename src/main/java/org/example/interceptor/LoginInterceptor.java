package org.example.interceptor;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.pojo.Result;
import org.example.utils.JwtUtil;
import org.example.utils.ThreadLocalUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component // 把当前拦截器注入IOC容器
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // get token
        String token = request.getHeader("Authorization");
        // verify token
        try {
            Map<String, Object> claims = JwtUtil.parseToken(token);

            // store the service data into threadLocal
            ThreadLocalUtil.set(claims);
            return true;

        } catch (Exception e) {
            // http status code 401
            response.setStatus(401);
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);

        // clear data in thread local, this function is called automatically after completing request
        ThreadLocalUtil.remove();
    }
}
