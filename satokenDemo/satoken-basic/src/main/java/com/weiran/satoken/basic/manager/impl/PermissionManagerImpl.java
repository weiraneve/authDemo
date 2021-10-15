package com.weiran.satoken.basic.manager.impl;

import com.weiran.satoken.basic.model.PermissionDO;
import com.weiran.satoken.basic.mapper.PermissionMapper;
import com.weiran.satoken.basic.manager.PermissionManager;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author Weiran
 * @since 2021-10-10
 */
@Service
public class PermissionManagerImpl extends ServiceImpl<PermissionMapper, PermissionDO> implements PermissionManager {

}
