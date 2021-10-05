# 文档
关于名为oauth2-GJN的demo文档。

## 前言
```
理想的解决方案应该是这样的，认证服务负责认证，网关负责校验认证和鉴权，其他API服务负责处理自己的业务逻辑。
安全相关的逻辑只存在于认证服务和网关服务中，其他服务只是单纯地提供服务而没有任何安全相关逻辑。
这里采用Nacos作为注册中心，Gateway作为网关，使用nimbus-jose-jwtJWT库操作JWT令牌。
```

## 使用
相关服务划分：
- oauth2-gateway：网关服务，负责请求转发和鉴权功能，整合Spring Security+Oauth2；
- oauth2-auth：Oauth2认证服务，负责对登录用户进行认证，整合Spring Security+Oauth2；
- oauth2-api：受保护的API服务，用户鉴权通过后可以访问该服务，不整合Spring Security+Oauth2。

## 理解


## 底层
等待更新

---
