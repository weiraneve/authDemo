package com.weiran.satoken.sso.manager.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weiran.satoken.sso.manager.PermissionManager;
import com.weiran.satoken.sso.mapper.PermissionMapper;
import com.weiran.satoken.sso.model.PermissionDO;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author Weiran
 * @since 2021-09-10
 */
@Service
public class PermissionManagerImpl extends ServiceImpl<PermissionMapper, PermissionDO> implements PermissionManager {

}
