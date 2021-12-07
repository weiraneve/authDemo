package com.weiran.login.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;


@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(getPassword());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                // 指定前端input字段的名字，即name，不指定默认为username和password
                .passwordParameter("username")
                .passwordParameter("password")
                // 自定义登录界面的地址
                .loginPage("/login.html")
                // 登录请求的处理的url，即ajax的url地址
                .loginProcessingUrl("/user/login")
                // 登录失败之后的处理接口，你也可以自定义handler处理
                .failureUrl("/user/login/fail")
                // 登录成功后默认的请求接口(controller中的接口)
                .defaultSuccessUrl("/user/login/success")
                .and().authorizeRequests()
                // 设置 user/login/** 接口谁都可以访问，否则没办法登录
                .antMatchers("/user/login/**").permitAll()
                //   拥有ADMIN的权限，可以肆意妄为，什么操作都能做
                .antMatchers("/**").hasAnyRole("ADMIN")
                .and().httpBasic()
                // 跨域保护禁用掉
                .and().csrf().disable();
                // 关闭禁用frame框架，不关闭的话，不允许嵌套页面的出现，这个地方困扰了我很久
        http.headers().frameOptions().disable();
                // 退出登录的接口地址，以及退出登录之后，返回到哪个页面
        http.logout().logoutUrl("/user/logout").logoutSuccessUrl("/login.html");
    }

    @Bean
    HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    // 密码加密
    @Bean
    public BCryptPasswordEncoder getPassword() {
        return new BCryptPasswordEncoder();
    }

    // 静态资源的放行规则，放行所有静态资源，并且放行登录页面
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**","/img/**",
                "/js/**","/layui/**","/login.html");
    }

}


