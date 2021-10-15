package com.weiran.form.handler;

import com.alibaba.fastjson.JSON;
import com.weiran.form.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
@Slf4j
@Component
public class MyLogoutHandler implements LogoutSuccessHandler {
    @Autowired
    @Qualifier("consumerTokenServices")
    ConsumerTokenServices consumerTokenServices;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        log.info("退出登录");
        String header = httpServletRequest.getHeader("Authorization");
        log.info("header = {}", header);
        header = header.substring(7);
        log.info("header = {}", header);
        consumerTokenServices.revokeToken(header);
        Result result = new Result();
        httpServletResponse.setContentType("application/json;charset=UTF-8");

        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}
