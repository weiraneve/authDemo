package com.weiran.auth.service;

import com.weiran.auth.bo.UserAuthBO;
import com.weiran.auth.myexception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 实现UserDetailsService接口，用于加载用户信息
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     *  使用到的UserAuthBO继承UserDetails类，所以这里如果我们要写入密码，必须通过Spring Security的加密
     **/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //获取用户基本信息
        if(!username.startsWith("jou")) {
            throw new MyException(400, "用户名或密码错误");
        }
        UserAuthBO userAuthBO = new UserAuthBO();
        // 写死
        userAuthBO.setUserId(1000L);
        userAuthBO.setUsername("表单自定义登录账号");
        userAuthBO.setPassword(passwordEncoder.encode("jouav1234"));
        // 可以在此获得获取用户权限
        Set<String> roles = new HashSet<>();
//        List<String> userRole = userRoleService.getUserRoleAuthCodeList(userDO.getUserId());
//        List<String> userEnterpriseRole = userRoleService.getUserEnterpriseRoleAuthCodeList(userDO.getUserId());
//        roles.addAll(userRole);
//        roles.addAll(userEnterpriseRole);
        userAuthBO.setRoles(roles);
        return userAuthBO;
    }
}
