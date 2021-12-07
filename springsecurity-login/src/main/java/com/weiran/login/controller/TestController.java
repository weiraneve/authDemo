package com.weiran.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    /**
     * 测试保护相关资源页面
     */
    @GetMapping("/index")
    public String getIndex() {
        return "index.html";
    }
}
