package com.weiran.form.config;

import com.weiran.form.common.AuthExceptionEntryPoint;
import com.weiran.form.handler.CustomAccessDeniedHandler;
import com.weiran.form.handler.MyAuthenticationFailHandler;
import com.weiran.form.handler.MyAuthenticationSuccessHandler;
import com.weiran.form.handler.MyLogoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import javax.servlet.http.HttpServletResponse;

/**
 *  资源服务配置
 * ResourceServerConfig 用于保护 oauth 相关的 endpoints，
 * 同时主要作用于用户的登录(form login,Basic auth)
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailHandler myAuthenticationFailHandler;

//    @Autowired
//    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Autowired
    private MyLogoutHandler myLogoutHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //表单登录 方式
        http.formLogin()
                //登录需要经过的url请求
                .loginProcessingUrl("/login/form")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailHandler)
                .and()
                .exceptionHandling().authenticationEntryPoint(new AuthExceptionEntryPoint());

        http.logout().logoutUrl("/logout")
                .logoutSuccessHandler(myLogoutHandler);

        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .authorizeRequests() // 请求权限设置
                .antMatchers("/test/**"
                )
                .permitAll()
                .anyRequest().authenticated()  // 所有请求都进行权限验证
//                .and()
//                .httpBasic() // 配置弹出框登录
                .and()
                .exceptionHandling().authenticationEntryPoint(new AuthExceptionEntryPoint());
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.authenticationEntryPoint(new AuthExceptionEntryPoint())
                .accessDeniedHandler(customAccessDeniedHandler);
    }
}
