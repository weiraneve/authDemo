package com.weiran.satoken.oauth2;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SaOauth2Application {

    public static void main(String[] args) {
        SpringApplication.run(SaOauth2Application.class, args);
        System.out.println("\nSa-Token-OAuth Server端启动成功");
    }

}

