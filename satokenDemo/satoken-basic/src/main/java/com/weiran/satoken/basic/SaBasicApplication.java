package com.weiran.satoken.basic;

import cn.dev33.satoken.SaManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.weiran.satoken.basic.mapper")
@SpringBootApplication
public class SaBasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaBasicApplication.class, args);
        System.out.println("\n启动成功：Sa-Token配置如下：" + SaManager.getConfig());
    }

}
