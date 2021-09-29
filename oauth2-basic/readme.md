# 文档
关于名为oauth2-basic的demo文档。

## 前言
```
Spring Cloud Security 
为构建安全的SpringBoot应用提供了一系列解决方案，
结合Oauth2可以实现单点登录、令牌中继、令牌交换等功能，
本文档仅就Oauth2做简单的使用与解析
```

## 使用
这里介绍最常用的授权码模式与密码模式的使用。大多数网址的跳转自己使用``` Postman```测试。
授权码模式中：
- 首先，后端引导构造出地址为请求类型为```GET```的网址。如
http://localhost:{port}/oauth/authorize?response_type=code&client_id={clientId}&redirect_uri={被保护的资源目标访问网址}&scope=all
- 然后输入账号密码进行登录操作，操作成功后进行授权，或者直接跳转目标重定向网址，这时候网址带着code授权码。
- 在postman里，使用Basic认证通过client_id和client_secret构造一个Authorization头信息，在body中添加grant_type,code,client_id,redirect_url,scope参数信息，通过POST请求获取访问令牌。grant_type填入authorization_code
- 获得访问令牌后，在资源保护的网址访问中，在请求头中```Authorization```添加访问令牌bearer + {access_token}，便可以访问

密码模式更加简单：
- 首先postman中，POST请求形式访问地址：http://localhost:8088/oauth/token  ， 授权中Username与Password填入对应信息，
请求body里填入grant_type,code,username,password,redirect_url,scope参数信息，grant_type填入password。
- 然后请求可以获得访问令牌，用在访问资源保护的网址的请求头中即可。

## 理解
- 密码模式比授权码模式更简洁，少了跳转网址获取code的这一步骤，更适合简单地、没有第三方等授权的权限对应的开发。
- 并且在这个模块中，我已经配置了用redis来存储token令牌的功能，这样不会出现重启服务后，内存中的令牌失效的情况，
而主流的验证方式也是，redis+token或者JWT。

## 底层
等待更新

---

[更多细节参考文章](http://www.macrozheng.com/#/cloud/oauth2)