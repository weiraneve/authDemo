package com.weiran.satoken.basic.manager.impl;

import com.weiran.satoken.basic.model.RolePermissionDO;
import com.weiran.satoken.basic.mapper.RolePermissionMapper;
import com.weiran.satoken.basic.manager.RolePermissionManager;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色权限中间表 服务实现类
 * </p>
 *
 * @author Weiran
 * @since 2021-10-10
 */
@Service
public class RolePermissionManagerImpl extends ServiceImpl<RolePermissionMapper, RolePermissionDO> implements RolePermissionManager {

}
