package com.example.demo3;

import com.example.demo3.persisttoken.MyPersistentTokenRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@EnableWebSecurity
public class MyWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailService myUserDetailService;

    @Autowired
    private MyPersistentTokenRepositoryImpl persistentTokenRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/user/**").hasRole("user")  //user 角色访问/api/user/开头的路由
                .antMatchers("/api/admin/**").hasRole("admin") //admin 角色访问/api/admin/开头的路由
                .antMatchers("/api/public/**").permitAll()                 //允许所有可以访问/api/public/开头的路由
            .and()
                .formLogin()
            .and()
                .rememberMe().userDetailsService(myUserDetailService)
//                .key("testhan")
//                .rememberMeCookieName("my-cookie"); //指明cookie name
                .tokenRepository(persistentTokenRepository) //需要使用持久化令牌方案，传入 tokenRepository。
            .and()
                .logout()
//                .logoutUrl("/mylogout") //接收注销登录的url请求，默认是 /logout
//                .logoutSuccessUrl("/login") //设置注销登录成功后的重定向url,默认是 /
//                .logoutSuccessHandler(new LogoutSuccessHandler() {  //注销登录成功后的处理器，可以设置干什么
//                    @Override
//                    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//                        String username = authentication.getName();
//                        System.out.println(username+"已注销！111111111111");
//                    }
//                })
//                .invalidateHttpSession(true)    //使用户的HttpSession失效
//                .deleteCookies("remember-me")      //注销成功，删除指定的cookie
//                .addLogoutHandler(new LogoutHandler() { //添加注销的一些处理，其实可以用logoutSuccessHandler实现
//                    @Override
//                    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
//                        String username = authentication.getName();
//                        System.out.println(username+"已注销！22222222222222");
//                    }
//                })
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

}
