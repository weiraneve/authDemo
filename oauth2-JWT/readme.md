# 文档
关于名为oauth2-JWT的demo文档。

## 前言
```
Spring Cloud Security 为构建安全的SpringBoot应用提供了一系列解决方案，
结合Oauth2还可以实现更多功能，比如使用JWT令牌存储信息，刷新令牌功能
```

## 使用
[JWT的原理可以看这里](https://www.ruanyifeng.com/blog/2018/07/json_web_token-tutorial.html)

自己在使用上，跟参考文章不一的是，实际使用要在POST的授权里的Oauth2中的Access Token处，填入JWT令牌才能访问受保护的资源

## 理解
- 值得讲的东西不多，一是使用JWT来存储令牌（另一种主流是redis+token）；二是扩展JWT中存储的内容。

## 底层
等待更新

---

[更多细节参考文章](http://www.macrozheng.com/#/cloud/oauth2_jwt)