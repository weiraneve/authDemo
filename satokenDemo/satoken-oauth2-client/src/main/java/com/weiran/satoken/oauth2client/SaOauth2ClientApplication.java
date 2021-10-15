package com.weiran.satoken.oauth2client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SaOauth2ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaOauth2ClientApplication.class, args);
        System.out.println("\nSa-Token-OAuth Client端启动成功\n");
    }
}
