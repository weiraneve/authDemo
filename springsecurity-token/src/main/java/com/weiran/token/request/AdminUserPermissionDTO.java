package com.weiran.token.request;

import lombok.Data;


@Data
public class AdminUserPermissionDTO {

    private Integer id;

    private String[] permissionIds;
}
