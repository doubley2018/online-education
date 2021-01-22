package com.angi.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author angi
 * @date 2021/1/22 19:27
 */
@Configuration
@MapperScan("com.angi.mapper")
public class MybatisPlusConfig {
}
