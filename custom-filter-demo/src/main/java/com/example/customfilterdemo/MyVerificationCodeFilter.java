package com.example.customfilterdemo;

import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author hanliukui
 * @Date 2020/9/2 17:51
 */
public class MyVerificationCodeFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 只处理登录请求
        if("/login".equals(request.getRequestURI())&&request.getMethod().equals(HttpMethod.POST.name())) {
            if(this.verificationCode(request, response)){
                filterChain.doFilter(request, response);
            }else {
                response.getWriter().write("verification code check failure!");
//                throw new RuntimeException("验证码校验失败！");
            }
        }else {
            filterChain.doFilter(request, response);
        }
    }


    private Boolean verificationCode(HttpServletRequest request,HttpServletResponse response){
        // 从session中获取正确的验证码
        HttpSession session = request.getSession();
        String kaptcha = (String) session.getAttribute("kaptcha");

        // 从参数中获取用户输入的验证码
        String code = request.getParameter("code");
        if (StringUtils.isEmpty(code)){
            // 清空session中的验证码，让用户重新获取
            session.removeAttribute("kaptcha");
            return false;
        }
        // 验证码校验
        if (!code.equals(kaptcha)){
            return false;
        }
        return true;
    }
}
