package com.weiran.auth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/test")
    public Object getCurrentUser(Authentication authentication) {
        return authentication.getPrincipal();
    }
}
