package org.mindcapture.Interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindcapture.Utils.JWTUtils;
import org.mindcapture.Utils.ThreadLocalUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求头中的token
        String token = request.getHeader("Authorization");
        
        // 检查token是否存在
        if (!StringUtils.hasLength(token)) {
            response.setStatus(401);
            return false;
        }
        
        // 如果token以Bearer 开头，去掉这个前缀
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        
        try {
            // 解析JWT令牌
            Map<String, Object> claims = JWTUtils.parseJWT(token);
            
            // 将用户信息存入ThreadLocal
            ThreadLocalUtils.set(claims);
            
            return true;
        } catch (Exception e) {
            // JWT验证失败
            response.setStatus(401);
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 请求结束后，清除ThreadLocal中的数据，防止内存泄漏
        ThreadLocalUtils.remove();
    }
}