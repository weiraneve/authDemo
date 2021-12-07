package com.weiran.login.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 平时大家做的登录，都会先经过控制层，而SpringSecurity则不会，你把提交的接口改为和配置类处理的接口相同，
 * 它就会自动的去执行Service层的loadUserByUsername方法，然后就是判断规则，注意密码，上面给大家提到了怎么存密文，
 * 如下面的代码，就能得到一个密文串，当然如果大家要注册功能的话，只需要在注册提交之后，在后端拿到明文密码再加密，最后存储即可
 */
public class RegisterUtil {

        public static void main(String[] args) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            System.out.println(bCryptPasswordEncoder.encode("123"));
        }
    }