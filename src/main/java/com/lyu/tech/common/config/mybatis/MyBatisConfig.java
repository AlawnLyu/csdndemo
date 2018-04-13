package com.lyu.tech.common.config.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.lyu.tech.*.dao")
public class MyBatisConfig {
}
