**简体中文 | [English](./README.md)**

<p align="center">
    <a href="https://github.com/lyzsk/mall/blob/master/LICENSE">
        <img src="https://img.shields.io/github/license/lyzsk/mall.svg?style=plastic&logo=github" />
    </a>
    <a href="https://github.com/lyzsk/mall/members">
        <img src="https://img.shields.io/github/forks/lyzsk/mall.svg?style=plastic&logo=github" />
    </a>
    <a href="https://github.com/lyzsk/mall/stargazers">
        <img src="https://img.shields.io/github/stars/lyzsk/mall.svg?style=plastic&logo=github" />
    </a>
</p>

<p align="center">
    <img src="https://img.shields.io/badge/Language-Java-3C415C?style=plastic&logo=openjdk&logoColor=FFFFFF" />
    <img src="https://img.shields.io/badge/IDE-IDEA-3C415C?style=plastic&logo=intellijidea&logoColor=FFFFFF" />
    <img src="https://img.shields.io/badge/Framework-Spring-3C415C?style=plastic&logo=spring&logoColor=6DB33F" />
<img src="https://img.shields.io/badge/Framework-SpringBoot-3C415C?style=plastic&logo=springboot&logoColor=6DB33F" />
    <img src="https://img.shields.io/badge/Framework-SpringSecurity-3C415C?style=plastic&logo=springsecurity&logoColor=6DB33F" />
</p>

<p align="center">
    <img src="https://img.shields.io/badge/-Redis-3C415C?style=plastic&logo=redis&logoColor=DC382D" />
    <img src="https://img.shields.io/badge/-MySQL-3C415C?style=plastic&logo=mysql&logoColor=4479A1" />
    <img src="https://img.shields.io/badge/-MongoDB-3C415C?style=plastic&logo=mongodb&logoColor=47A248" />
    <img src="https://img.shields.io/badge/-Elasticsearch-3C415C?style=plastic&logo=elasticsearch&logoColor=005571" />
    <img src="https://img.shields.io/badge/-RabbitMQ-3C415C?style=plastic&logo=rabbitmq&logoColor=FF6600" />
</p>

# Introduction

这个项目使用 **SpringBoot + MyBatis** 框架开发, 为 mall 项目提供 RESTful API 接口.

目前还在开发中, 运行项目后可以通过 [Swagger-UI] 访问接口.

> :star: **_如果你喜欢这个项目, 或者这个项目对你有帮助, 别忘了点赞哦_** :star:

# Quick start

1. clone 项目到本地

    ```
    git clone git@github.com:lyzsk/mall.git
    ```

2. 创建 MySQL 数据库 `mall`, 通过脚本 `mall.sql` 导入数据.
3. 准备 **Redis**, **RabbitMQ**, **MongoDB**, **Elasticsearch**, 并在 `application.yml` 修改相应配置.

    ```
    <!-- start redis -->
    redis-server.exe redis.windows.conf

    <!-- start MQ -->
    rabbitmq-plugins enable rabbitmq_management

    <!-- start MongoDB service -->
    net start MongoDB
    ```

4. 运行 `MallApplication.java` 启动 SpringBoot APP
5. 打开浏览器访问: http://localhost:8080/swagger-ui.html

# Key features

## SpringSecurity + JWT login

SpringSecurity + JWT 实现 **Ums(User management system)** 用户登录 验证 + 授权.

-   当用户 **POST** 登录请求到 API, 第一步, 验证 用户名-密码
-   如果密码正确, return 200, 生成 JWT token 和 header: Bearer
-   通过 header + token 授权登录, 并 **GET** 授权的 userId.

### Preview

-   Example login with {username: test, password: 123456}:

    ![preview-01]

## Redis caching SMS verification code

当用户在 **Ums** 注册新账户, 需要手机验证码.

实现 API 根据 手机号码 获取 验证码.

实现 API 验证验证码.

自定义 Redis Key-Value set, KEY 是手机号码, VALUE 是验证码, 设置 120s 超时.

### Preview

`yml` configuration:

```yaml
redis:
    key:
        prefix:
            authCode: "portal:authCode:"
        expire:
            authCode: 120 # 验证码超期时间
```

key java code:

```java
        //验证码绑定手机号并存储到redis
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE + telephone, sb.toString());
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE + telephone, AUTH_CODE_EXPIRE_SECONDS);
        return CommonResult.success(sb.toString(), "获取验证码成功");
```

## SpringTask auto cancel order and RabbitMQ delay message

实现 `OmsPortalOrderController` and `OmsPortalOrderService`, 如果用户下单:

1. 锁定产品的库存.
2. 生成订单和 orderId.
3. SpringTask 设置 60 分钟超时 (`@Scheduled(cron = "0 0/10 * ? * ?")`)
4. 如果超时, 向 MQ 发送延迟消息, with `delayTimes` 30s, 取消订单.
5. 如果用户没有按时支付, 处理取消订单, 释放锁定产品的库存.

### Preview

-   Example of auto cancel order:

    ![preview-03]

## Product search with Elasitcsearch and support Chinese word segmentation

1. 用 Spring Data Elasticsearch 通过注解简化代码: `@interface Document`, `@interface Id`, `@interface Field`
2. 除了传统的关键词搜索, 实现 `findByNameOrSubTitleOrKeywords` 方法, 提供衍生查询:

    ```java
    @Query("{"bool" : {"must" : {"field" : {"name" : "?0"}}}}")
    Page<EsProduct> findByName(String name,Pageable pageable);
    ```

3. 用 `@Field(analyzer = "ik_max_word",type = FieldType.Text)` 注解设置中文分词.

### Preview

-   Example of search product:

    ![preview-04]

## Use MongoDB create, delete, list API for Member Read History

1. 用户浏览产品, 调用 API 存储 Member Read History into MongoDB.
2. 可以添加, 删除, list 产品浏览记录.
3. 根据时间降序 list 浏览记录:

    ```java
    List<MemberReadHistory> findByMemberIdOrderByCreateTimeDesc(Long memberId);
    ```

### Preview

-   Example of create, delete, list Member Read History:
    ![preview-05]

[swagger-ui]: http://localhost:8080/swagger-ui.html
[preview-01]: https://github.com/lyzsk/support-repo/blob/master/mall/previews/UmsLogin_with_test_123456.gif?raw=true
[preview-03]: https://github.com/lyzsk/support-repo/blob/master/mall/previews/SpringTask_cancelOrder.png?raw=true
[preview-04]: https://github.com/lyzsk/support-repo/blob/master/mall/previews/ESProduct_search_by_keywords.gif?raw=true
[preview-05]: https://github.com/lyzsk/support-repo/blob/master/mall/previews/MemberReadHistory_create_delete_list.gif?raw=true
