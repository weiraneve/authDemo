package com.weiran.auth.service;

import com.weiran.auth.bo.UserBO;
import com.weiran.auth.myexception.TokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
