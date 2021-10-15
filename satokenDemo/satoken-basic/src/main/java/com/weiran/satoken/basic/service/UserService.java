package com.weiran.satoken.basic.service;

import com.weiran.satoken.basic.util.AjaxResult;

public interface UserService {

    AjaxResult login(String username, String password);
}
