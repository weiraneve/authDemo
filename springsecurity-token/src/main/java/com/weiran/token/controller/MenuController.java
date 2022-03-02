package com.weiran.token.controller;

import com.weiran.token.entity.PermissionMenuDTO;
import com.weiran.token.response.ResultVO;
import com.weiran.token.service.AdminUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {


    private final AdminUserService adminUserService;

    /**
     * 获取管理员权限菜单
     */
    @GetMapping
    public ResultVO findByMenus(Principal principal) {
        List<PermissionMenuDTO> menus = adminUserService.findByMenus(principal.getName());
        return ResultVO.success(menus);
    }

}
