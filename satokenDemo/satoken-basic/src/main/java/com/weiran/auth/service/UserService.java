package com.weiran.auth.service;

import com.weiran.auth.util.AjaxResult;

public interface UserService {

    AjaxResult login(String username, String password);
}
