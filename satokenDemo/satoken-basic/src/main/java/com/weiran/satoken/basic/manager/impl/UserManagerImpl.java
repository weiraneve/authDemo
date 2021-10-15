package com.weiran.satoken.basic.manager.impl;

import com.weiran.satoken.basic.model.UserDO;
import com.weiran.satoken.basic.mapper.UserMapper;
import com.weiran.satoken.basic.manager.UserManager;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表
 服务实现类
 * </p>
 *
 * @author Weiran
 * @since 2021-10-10
 */
@Service
public class UserManagerImpl extends ServiceImpl<UserMapper, UserDO> implements UserManager {

}
