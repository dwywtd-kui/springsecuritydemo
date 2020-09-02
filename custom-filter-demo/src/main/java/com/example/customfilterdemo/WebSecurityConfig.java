package com.example.customfilterdemo;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Properties;

/**
 * @author hanliukui
 * @Date 2020/9/2 16:29
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        http
                .authorizeRequests()
                .antMatchers("/kaptcha.jpg").permitAll()    // 放开验证码获取的访问地址
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")           // 自定义登录页URL
                .loginProcessingUrl("/login")       // 自定义登陆处理请求地址
                .permitAll();
        // 将自定义的过滤器添加到Spring Security 过滤器链中
        http
                .addFilterBefore(new MyVerificationCodeFilter(),UsernamePasswordAuthenticationFilter.class);
    }


    @Bean
    public DefaultKaptcha getDDefaultKaptcha() {
        DefaultKaptcha dk = new DefaultKaptcha();
        Properties properties = new Properties();
        // 图片边框
        properties.setProperty("kaptcha.border", "yes");
        // 边框颜色
        properties.setProperty("kaptcha.border.color", "105,179,90");
        // 字体颜色
        properties.setProperty("kaptcha.textproducer.font.color", "red");
        // 图片宽
        properties.setProperty("kaptcha.image.width", "110");
        // 图片高
        properties.setProperty("kaptcha.image.height", "40");
        // 字体大小
        properties.setProperty("kaptcha.textproducer.font.size", "30");
        // session key
        properties.setProperty("kaptcha.session.key", "code");
        // 验证码长度
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        // 字体
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        Config config = new Config(properties);
        dk.setConfig(config);

        return dk;
    }
}
