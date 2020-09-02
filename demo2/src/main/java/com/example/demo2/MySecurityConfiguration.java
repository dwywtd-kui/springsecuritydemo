package com.example.demo2;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class MySecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/api/user/**").hasAnyRole("user")  //user 角色访问/api/user/开头的路由
                .antMatchers("/api/admin/**").hasAnyRole("admin") //admin 角色访问/api/admin/开头的路由
                .antMatchers("/api/public/**").permitAll()                 //允许所有可以访问/api/public/开头的路由
                .and()
            .formLogin();
    }
    /**基于内存的多用户支持*/
//    @Bean
//    public UserDetailsService userDetailsService(){
//        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//
//        //创建用户user01,密码user01,角色user
//        userDetailsManager.createUser(User.withDefaultPasswordEncoder().username("user01").password("user01").roles("user").build());
//        //创建用户admin01,密码admin01,角色admin
//        userDetailsManager.createUser(User.withDefaultPasswordEncoder().username("admin01").password("admin01").roles("admin").build());
//
//        return userDetailsManager;
//    }

    /**基于默认数据库模型的认证于授权*/
//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource){
//        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager();
//        userDetailsManager.setDataSource(dataSource);
//
//        //创建用户user01,密码user01,角色user
//        if (!userDetailsManager.userExists("user01")) { //判断user01是否存在
//            userDetailsManager.createUser(User.withDefaultPasswordEncoder().username("user01").password("user01").roles("user").build());
//        }
//        //创建用户admin01,密码admin01,角色admin
//        if (!userDetailsManager.userExists("admin01")) {//判断admin01是否存在
//            userDetailsManager.createUser(User.withDefaultPasswordEncoder().username("admin01").password("admin01").roles("admin").build());
//        }
//        return userDetailsManager;
//    }

    /**使用AuthenticationManagerBuilder 来支持多用户*/
//    @Autowired
//    private DataSource dataSource;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("user01")
//                .password(new BCryptPasswordEncoder().encode("user01"))
//                .roles("user")
//                .and()
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("admin01")
//                .password(new BCryptPasswordEncoder().encode("admin01"))
//                .roles("admin");
//    }

}
