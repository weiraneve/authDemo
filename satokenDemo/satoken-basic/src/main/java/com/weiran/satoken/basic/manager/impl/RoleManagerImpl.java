package com.weiran.satoken.basic.manager.impl;

import com.weiran.satoken.basic.model.RoleDO;
import com.weiran.satoken.basic.mapper.RoleMapper;
import com.weiran.satoken.basic.manager.RoleManager;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author Weiran
 * @since 2021-10-10
 */
@Service
public class RoleManagerImpl extends ServiceImpl<RoleMapper, RoleDO> implements RoleManager {

}
