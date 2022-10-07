**[简体中文](./README.CN.md) | English**

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

This project is developed using **SpringBoot + MyBatis** frameworks, provide mall project RESTful API.

It is currently under development, you can access all the API with [Swagger-UI] after running the project.

> :star: **_If you like this project or it helps you in some way, don't forget to star._** :star:

# Quick start

1. clone the project into your local disk.

    ```
    git clone git@github.com:lyzsk/mall.git
    ```

2. create MySQL database `mall`, and import `mall.sql` script.
3. prepare **Redis**, **RabbitMQ**, **MongoDB**, **Elasticsearch**, and modify related settings in `application.yml`.

    ```
    <!-- start redis -->
    redis-server.exe redis.windows.conf

    <!-- start MQ -->
    rabbitmq-plugins enable rabbitmq_management

    <!-- start MongoDB service -->
    net start MongoDB
    ```

4. start SpringBoot application, by running `MallApplication.java`
5. open browser, and visit http://localhost:8080/swagger-ui.html

# Key features

## SpringSecurity + JWT login

Implement **Ums(User management system)** user login with authentication + authorization by using SpringSecurity + JWT.

-   when user **POST** login info to API, firstly, authenticate username - password
-   if password correct, return 200, generate JWT token with header: Bearer
-   authorize the login with header + token, and **GET** authorization with userId.

### Preview

-   Example login with {username: test, password: 123456}:

    ![preview-01]

## Redis caching SMS verification code

When new user want to register new account in **Ums**, it will need phone number for verification.

Implement API get verification code according to the phone number.

Implement API verify the verification code.

Custom Redis Key-Value set, with phone number as KEY, verification code as VALUE, and set 120s timeout.

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

Implement `OmsPortalOrderController` and `OmsPortalOrderService`, If user places an order for a product:

1. lock products in inventory.
2. generate order with orderId.
3. SpringTask set order timeout in 60 minutes (`@Scheduled(cron = "0 0/10 * ? * ?")`)
4. If order timeout, send delayed message to MQ, with `delayTimes` 30s, cancel order.
5. If user didn't pay in time, process cancel order, release locked products.

### Preview

-   Example of auto cancel order:

    ![preview-03]

## Product search with Elasitcsearch and support Chinese word segmentation:

1. Use Spring Data Elasticsearch to simplify the code, by using annotations: `@interface Document`, `@interface Id`, `@interface Field`
2. Besides traditional keywords search, implement `findByNameOrSubTitleOrKeywords` method, provid derived query for keywords:

    ```java
    @Query("{"bool" : {"must" : {"field" : {"name" : "?0"}}}}")
    Page<EsProduct> findByName(String name,Pageable pageable);
    ```

3. use `@Field(analyzer = "ik_max_word",type = FieldType.Text)` annotation to set the word that will be need Chinese word segmentation.

### Preview

-   Example of search product:

    ![preview-04]

## Use MongoDB create, delete, list API for Member Read History

1. When user browse products, can use this API, to create member read history, and store into MongoDB.
2. They could also delete the history, or get all history.
3. When user list the history, it will list by create time in Descending order:

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
