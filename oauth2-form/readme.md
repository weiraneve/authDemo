# 文档
关于名为oauth2-form的demo文档。

## 前言
```
自定义Oauth2登录认证成功和失败的返回结果；以及完成自定义表单登录；
```

## 使用
- 在UserService中的loadUserByUsername方法里，如果使用了继承UserDetails的Bo类，那么在setPassword的时候，其中的密码不论是否写死还是从数据库中取，都要通过通过Spring Security的加密；在这里也可以加入用户权限。
- 在授权服务配置类中，在配置方法中``` void configure(ClientDetailsServiceConfigurer clients) ```里，因为项目里使用表单登录，并且使用了自己写的认证处理器，处理登录成功和失败的情况，所以在授权服务配置类的配置方法中不需要加密。自然这里也变相屏蔽了原本的oauth/token接口

## 理解
- 这里的自定义认证成功处理器和失败处理器还有一些接口句柄处都是用自定义的返回结构体代替了默认返回，增加了后端调试的方便性。
- 自己写的认证处理器用了对请求头中的解码算法，做到了自定义处理client_id && client_secret ，所以也不需要加密。

## 底层
等待更新

---
