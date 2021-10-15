package com.weiran.satoken.sso.manager.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weiran.satoken.sso.manager.UserManager;
import com.weiran.satoken.sso.mapper.UserMapper;
import com.weiran.satoken.sso.model.UserDO;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表
 服务实现类
 * </p>
 *
 * @author Weiran
 * @since 2021-09-10
 */
@Service
public class UserManagerImpl extends ServiceImpl<UserMapper, UserDO> implements UserManager {

}
