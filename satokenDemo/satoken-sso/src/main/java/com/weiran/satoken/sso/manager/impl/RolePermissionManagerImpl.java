package com.weiran.satoken.sso.manager.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weiran.satoken.sso.manager.RolePermissionManager;
import com.weiran.satoken.sso.mapper.RolePermissionMapper;
import com.weiran.satoken.sso.model.RolePermissionDO;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色权限中间表 服务实现类
 * </p>
 *
 * @author Weiran
 * @since 2021-09-10
 */
@Service
public class RolePermissionManagerImpl extends ServiceImpl<RolePermissionMapper, RolePermissionDO> implements RolePermissionManager {

}
