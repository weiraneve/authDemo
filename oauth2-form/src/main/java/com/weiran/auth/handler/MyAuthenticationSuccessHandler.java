package com.weiran.auth.handler;

import com.alibaba.fastjson.JSON;
import com.weiran.auth.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功返回处理器
 */
@Slf4j
@Component
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("登录成功");

        String header = request.getHeader("Authorization");
        String clientId;
        String clientSecret;
        if (header == null || !header.startsWith("Basic")) {
            //PC页面登录
            clientId = "jouav_base";
            clientSecret = "Jouav1234";
        } else { //app端登录（主要在于token过期时间不同）
            String[] tokens = this.extractAndDecodeHeader(header, request);
            assert tokens.length == 2;
            //获取clientId 和 clientSecret
            clientId = tokens[0];
            clientSecret = tokens[1];
        }

        //获取 ClientDetails
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);

        if (clientDetails == null){
            throw new UnapprovedClientAuthenticationException("clientId 不存在"+clientId);
            //判断  方言  是否一致
        }else if (!StringUtils.equals(clientDetails.getClientSecret(),clientSecret)){
            throw new UnapprovedClientAuthenticationException("clientSecret 不匹配"+clientId);
        }
        //密码授权 模式, 组建 authentication
        TokenRequest tokenRequest = new TokenRequest(null,clientId,clientDetails.getScope(),"password");

        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request,authentication);

        OAuth2AccessToken token = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
        String Authorization = token.getTokenType() + " " + token.getValue();
        Result result = new Result();
        result.addData("Authorization", Authorization);
        result.addData("refresh_token", token.getRefreshToken().getValue());
        result.addData("expires_in", token.getExpiresIn() * 1000L);

        //将 authention 信息打包成json格式返回
        response.setContentType("application/json;charset=UTF-8");

        //TODO 插入登录日志
//        UserAuthBO userBO = (UserAuthBO)authentication.getPrincipal();
//        UserLoginLogDO userLoginLogDO = new UserLoginLogDO();
//        userLoginLogDO.setLoginIp(CommonUtil.getIpAddr(request));
//        userLoginLogDO.setUserId(userBO.getUserId());
//        userLoginLogService.addUserLoginLog(userLoginLogDO);

        response.getWriter().write(JSON.toJSONString(result));
    }


    /**
     * 解码请求头
     */
    private String[] extractAndDecodeHeader(String header, HttpServletRequest request) throws IOException {
        byte[] base64Token = header.substring(6).getBytes("UTF-8");

        byte[] decoded;
        try {
            decoded = Base64.decode(base64Token);
        } catch (IllegalArgumentException var7) {
            throw new BadCredentialsException("Failed to decode basic authentication token");
        }

        String token = new String(decoded, "UTF-8");
        int delim = token.indexOf(":");
        if (delim == -1) {
            throw new BadCredentialsException("Invalid basic authentication token");
        } else {
            return new String[]{token.substring(0, delim), token.substring(delim + 1)};
        }
    }
}
