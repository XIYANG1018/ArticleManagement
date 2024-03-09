package org.example.interceptor;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.pojo.Result;
import org.example.utils.JwtUtil;
import org.example.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.expression.spel.ast.OpEQ;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component // 把当前拦截器注入IOC容器
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // get token
        String token = request.getHeader("Authorization");
        // verify token
        try {
            // 从redis中获取相同的token

            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            String redisToken = operations.get(token);

            if (redisToken==null) {
                // token失效
                throw new RuntimeException();  //被catch到，给出response 401
            }


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
