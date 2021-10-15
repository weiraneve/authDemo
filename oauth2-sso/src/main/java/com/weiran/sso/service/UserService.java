package com.weiran.sso.service;

import com.weiran.sso.myexception.TokenException;
import com.weiran.sso.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 实现UserDetailsService接口，用于加载用户信息
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserBO userBO = new UserBO();
        if (username.startsWith("jou")) {
            userBO.setUserId(123L);
            userBO.setUsername(username);
            userBO.setPassword(passwordEncoder.encode("jouav1234"));
        } else {
            throw new TokenException(400, "用户不存在!!!");
        }
        return userBO;
    }
}
