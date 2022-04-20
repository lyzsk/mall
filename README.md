# JAVA-mall
- [JAVA miaosha](#java-mall)
- [Screenshoots](#运行截图)
- [Intro](#Intro)
- [Installation](#Installation)
- [Dev Enviorment](#Dev-Enviorment)
- [How to use](#how-to-use)

# Screenshoots
![Image text]()

- [(Back to top)](#JAVA-mall)

# Intro
- 施工中
- Swagger-UI
    - Implement online API documents: use for testing
- Redis
    - Implement cache: 
- SpringSecurity + JWT
    - admin && member has different permission
        - member_limitPermission:
        ![Image text]()

        - admin_allPermission:
        ![Image text]()
    - admin can get access to all object with Spring injection
        ```shell
        ##@PreAuthorize("hasAuthority('pms:brand:read')")
        ```
    - springsecurity_limitPermission
    ![Image text]()
- Elasticsearch:
    - Implement product search:
    ![Image text]()
- Mongodb:
    - Implement `create`, `delete`, `list` in `product member read history`:
    ![Image text]()
- SpringTask:
    - Start a scheduled task, check every 10 min, if there is an order that has not been paid, auto cancel the order:
    ![Image text]()
    
- [(Back to top)](#JAVA-mall)

# Installation
- [(Back to top)](#JAVA-mall)

# Dev Enviorment
- JDK 1.8
- SpringBoot 2.1.3.RELEASE
- pagehelper 1.2.10
- druid 1.1.10
- mybatis 1.4.1
- mysql 8.0.28
- swagger2 2.7.0
- jjwt 0.9.0
- elasticsearch
- mongodb
- redis
- lombok
- aliyun-sdk-oss
- DBeaver 可视化数据库管理，用于代替Navicat
- [(Back to top)](#JAVA-mall)

# How to use
- !施工中!
- [(Back to top)](#JAVA-mall)