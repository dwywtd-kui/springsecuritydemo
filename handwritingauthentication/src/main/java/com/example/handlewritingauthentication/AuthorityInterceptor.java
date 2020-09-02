package com.example.handlewritingauthentication;

import org.aopalliance.intercept.Interceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * @author hanliukui
 */

@Component
public class AuthorityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("utf-8");
        UserDetail userDetail = (UserDetail) request.getSession().getAttribute(UserDetail.SESSION_KEY);
        if (userDetail==null){
            response.getWriter().write("请登录！");
            return false;
        }
        String requestURI = request.getRequestURI();
        Set<String> authorities = userDetail.getAuthorities();

        if (authorities!=null) {
            if (authorities.contains("p1") && requestURI.contains("/r/1")) {
                return true;
            }
            if (authorities.contains("p2") && requestURI.contains("/r/2")) {
                return true;
            }
        }
        response.getWriter().write("未授权");
        return false;
    }
}
