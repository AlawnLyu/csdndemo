package com.lyu.csdndemo.common.config.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.lyu.csdndemo.*.dao")
public class MyBatisConfig {
}
