package cn.sichu.graduatemall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 * <p>
 * After 3.0:
 * <p>
 * "cn.sichu.graduatemall.dao"
 * 
 * @author sichu
 * @date 2022/03/26
 */
@Configuration
@MapperScan({"cn.sichu.graduatemall.mbg.mapper", "cn.sichu.graduatemall.dao"})
public class MyBatisConfig {}
