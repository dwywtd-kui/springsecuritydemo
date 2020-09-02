package com.example.customfilterdemo;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author hanliukui
 * @Date 2020/9/2 16:38
 */
public class MySecurityFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // 非登录请求，不处理
        if("/login".equals(httpServletRequest.getRequestURI())&&httpServletRequest.getMethod().equals(HttpMethod.POST.name())) {
            String username = httpServletRequest.getParameter("username");
            String password = httpServletRequest.getParameter("password");
            System.out.println("username:" + username);
            System.out.println("password:" + password);
        }else {
            System.out.println("非登录处理！");
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
