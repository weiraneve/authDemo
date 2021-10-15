package com.weiran.form.config;


import com.weiran.form.common.AuthExceptionEntryPoint;
import com.weiran.form.handler.CustomAccessDeniedHandler;
import com.weiran.form.redis.RedisTokenStore;
import com.weiran.form.service.UserService;
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
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

/**
 * 授权认证服务配置
 * 配置OAUth2授权服务器的便捷策略
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisConnectionFactory connectionFactory;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired(required = false)
    private WebResponseExceptionTranslator customWebResponseExceptionTranslator;

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    public RedisTokenStore tokenStore() {
        return new RedisTokenStore(connectionFactory);
    }

    //AuthorizationServerEndpointsConfigurer：用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .userDetailsService(userService)//若无，refresh_token会有UserDetailsService is required错误
                .tokenStore(tokenStore());
        endpoints.exceptionTranslator(customWebResponseExceptionTranslator);
    }

    /**
     * 认证服务器的安全配置
     * AuthorizationServerSecurityConfigurer：用来配置令牌端点(Token Endpoint)的安全约束
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .allowFormAuthenticationForClients() //允许表单认证
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()") //isAuthenticated():排除anonymous   isFullyAuthenticated():排除anonymous以及remember-me
                .authenticationEntryPoint(new AuthExceptionEntryPoint())
                .accessDeniedHandler(customAccessDeniedHandler);
    }


    /**
     *  ClientDetailsServiceConfigurer：用来配置客户端详情服务（UserService），客户端详情信息在这里进行初始化，
     *  你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息
     *  这里是通过自定义的MyAuthenticationSuccessHandler处理器来处理授权id与secret，所以不需要在这里使用加密
     **/
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                //用于标识用户ID
                .withClient("jouav_base")
                //授权范围
                .scopes("all")
                //客户端安全码
                .secret("Jouav1234")
                //授权方式
                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
                .accessTokenValiditySeconds(60 * 60)
                .refreshTokenValiditySeconds(60 * 60 * 24)
                .and()
                .withClient("jouav_month")
                .scopes("all")
                .secret("Jouav1234")
                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
                .accessTokenValiditySeconds(60 * 60 * 24 * 30)
                .refreshTokenValiditySeconds(60 * 60 * 24 * 31)
                .and()
                .withClient("jouav_year")
                .scopes("all")
                .secret("Jouav1234")
                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
                .accessTokenValiditySeconds(60 * 60 * 24 * 365)
                .refreshTokenValiditySeconds(60 * 60 * 24 * 400)
        ;
    }
}
