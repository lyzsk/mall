package cn.sichu.mall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author sichu
 * @date 2022/10/07
 **/
@Configuration
@MapperScan("cn.sichu.mall.mbg.mapper")
public class MyBatisConfig {
}

