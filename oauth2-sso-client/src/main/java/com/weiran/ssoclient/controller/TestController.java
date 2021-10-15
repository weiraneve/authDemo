package com.weiran.ssoclient.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/getCurrentTest")
    public Object getCurrentTest(Authentication authentication) {
        return authentication;
    }

    // 需要admin权限的接口
    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/ifAdmin")
    public Object adminAuth() {
        return "Has admin auth!";
    }

    @GetMapping("/show")
    public String getShow() {
        return "SSO登陆成功！！！";
    }

}
