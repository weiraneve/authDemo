package com.weiran.form.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败返回处理器
 */
@Slf4j
@Component
public class MyAuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        logger.info("登录失败");
        //设置状态码
        response.setStatus(200);
        response.setContentType("application/json;charset=UTF-8");
        if("该账号已被禁用".equals(exception.getMessage())) {
            //将 登录失败 信息打包成json格式返回
            response.getWriter().write("{\"code\":422,\"msg\":\"该账号已被禁用\",\"data\":{\"error\": {\"username\": \"该账号已被禁用\"}}}");
        } else {
            //将 登录失败 信息打包成json格式返回
            response.getWriter().write("{\"code\":400,\"msg\":\"用户名或密码错误\",\"data\":{\"error\": {\"username\": \"用户名或密码错误\"}}}");
        }

    }
}
