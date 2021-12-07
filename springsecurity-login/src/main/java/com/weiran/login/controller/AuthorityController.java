package com.weiran.login.controller;

import com.weiran.login.obj.Msg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class AuthorityController {

    @GetMapping("/user/login/success")
    @ResponseBody
    public Msg loginSuccess() {
        log.info("登陆成功");
        return Msg.success();
    }

    @GetMapping("/user/login/fail")
    @ResponseBody
    public Msg loginFail() {
        log.info("登陆失败");
        return Msg.fail();
    }

    /**
     * 获取登录用户名
     */
    @GetMapping("/user/getUsername")
    @ResponseBody
    public Msg getUserName(HttpServletRequest request) {
        return Msg.success().add("msg", request.getSession().getAttribute("loginUsername"));
    }

}

