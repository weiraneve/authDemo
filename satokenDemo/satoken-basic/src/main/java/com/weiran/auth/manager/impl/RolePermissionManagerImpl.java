package com.weiran.auth.manager.impl;

import com.weiran.auth.model.RolePermissionDO;
import com.weiran.auth.mapper.RolePermissionMapper;
import com.weiran.auth.manager.RolePermissionManager;
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
