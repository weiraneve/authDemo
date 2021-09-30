package com.weiran.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequestMapping("/test")
@RestController
public class TestController {

    @GetMapping("/show")
    public String getShow() {
        return "Form模块登录成功！！！";
    }

    @GetMapping("/info")
    public Principal user(Principal user) {
        return user;
    }

}
