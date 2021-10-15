package com.weiran.basic.config;

import com.weiran.basic.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * 认证服务器配置
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisConnectionFactory connectionFactory;

    /**
     *  oauth2 令牌存储在redis
     **/
    @Bean
    public TokenStore tokenStore() {
        RedisTokenStore redis = new RedisTokenStore(connectionFactory);
        return redis;
    }

    /**
     * 使用密码模式需要配置
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager)
                .tokenStore(tokenStore())
                .userDetailsService(userService);
    }


    /**
     *  用来配置客户端详情服务
     *  客户端详情信息在这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息
     **/
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                //用于标识用户ID
                .withClient("jouav_base")
                //授权范围
                .scopes("all")
                //客户端安全码
                .secret(passwordEncoder.encode("Jouav1234"))
                //配置redirect_uri，用于授权成功后跳转
                .redirectUris("https://www.baidu.com")
                //授权类型
                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
                //配置访问token的有效期
                .accessTokenValiditySeconds(60 * 60)
                //配置刷新token的有效期
                .refreshTokenValiditySeconds(60 * 60 * 24)
                .and()
                .withClient("jouav_month")
                .scopes("all")
                .redirectUris("https://www.baidu.com")
                .secret(passwordEncoder.encode("Jouav1234"))
                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
                .accessTokenValiditySeconds(60 * 60 * 24 * 30)
                .refreshTokenValiditySeconds(60 * 60 * 24 * 31)
                .and()
                .withClient("jouav_year")
                .redirectUris("https://www.baidu.com")
                .scopes("all")
                .secret(passwordEncoder.encode("Jouav1234"))
                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
                .accessTokenValiditySeconds(60 * 60 * 24 * 365)
                .refreshTokenValiditySeconds(60 * 60 * 24 * 400)
        ;
    }
}
