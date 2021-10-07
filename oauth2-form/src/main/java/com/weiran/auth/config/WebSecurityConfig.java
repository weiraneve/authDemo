package com.weiran.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * discription: Security 配置
 * ResourceServerConfig 是比 SecurityConfig 的优先级低
 * SecurityConfig 用于保护 oauth 要开放的资源，
 * 同时主要作用于client端以及token的认证(bearer auth)
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //不定义没有password grant_type,密码模式需要AuthenticationManager支持
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().
                anyRequest().authenticated()
                .and()
                .csrf().disable(); // 取消“跨站防护”
        http.headers().disable(); //取消登录时请求头

    }
}
