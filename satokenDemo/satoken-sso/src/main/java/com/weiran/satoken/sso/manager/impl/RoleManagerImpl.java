package com.weiran.satoken.sso.manager.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weiran.satoken.sso.manager.RoleManager;
import com.weiran.satoken.sso.mapper.RoleMapper;
import com.weiran.satoken.sso.model.RoleDO;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author Weiran
 * @since 2021-09-10
 */
@Service
public class RoleManagerImpl extends ServiceImpl<RoleMapper, RoleDO> implements RoleManager {

}
