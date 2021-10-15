package com.weiran.satoken.sso;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.weiran.satoken.sso.mapper")
public class SaSsoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaSsoApplication.class, args);
        System.out.println("\nSa-Token-SSO 认证中心启动成功");
    }

}

