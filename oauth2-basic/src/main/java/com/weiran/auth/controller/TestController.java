package com.weiran.auth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/show")
    public Object getCurrentTest(Authentication authentication) {
        return authentication.getPrincipal();
    }
}
