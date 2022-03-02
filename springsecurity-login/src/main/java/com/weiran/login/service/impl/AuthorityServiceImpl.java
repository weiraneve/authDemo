package com.weiran.login.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.weiran.login.dao.AuthorityDao;
import com.weiran.login.model.Authority;
import com.weiran.login.service.AuthorityService;
import com.weiran.login.utils.GetSessionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

    // 注入dao层接口
    private final AuthorityDao authorityDao;

    // 重写UserDetailsService中的方法
    @Override
    public UserDetails loadUserByUsername(String username) {
        QueryWrapper<Authority>  queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        Authority authority = authorityDao.selectOne(queryWrapper);
        if (authority == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        // 把用户名存入session，存入session是方便获取到登录的用户名
        GetSessionUtil.getSession().setAttribute("loginUsername", authority.getUsername());
        // 这个User是SpringSecurity提供的，而不是自己写的，只需要传入账号密码，和权限，SpringSecurity会自己判断账号密码，密码是加密过后的密码，而不是明文
        return new User(authority.getUsername(), authority.getPassword(), getAuthority());

//        return new User(authority.getUsername(),authority.getPassword(),
//                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_" + authority.getAuthority()));
        // 这里authority的Authority属性给去掉了，如果需要权限属性可以加上
        // 这个位置的 ROLE_ 这个并不是一定要加的，只是在配置类权限对比的时候，使用 hasAnyRole 和 hasAnyAuthorites 的不同，如果加 ROLE_ ，则配置类就用 hasAnyRole，否则就用第二个
    }

    // 指定一个权限，拥有该权限的才能登录，这里是写死，即使没有设定权限不影响登陆
    private List<GrantedAuthority> getAuthority() {
        return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN");

    }

}


