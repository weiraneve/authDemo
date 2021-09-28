package com.weiran.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableOAuth2Sso
@SpringBootApplication
public class OauthSsoClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(OauthSsoClientApplication.class, args);
    }
}
