package com.lyu.csdndemo;

import com.lyu.csdndemo.common.util.redis.RedisCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CsdndemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(CsdndemoApplication.class, args);
  }

  @Bean
  public RedisCache redisCache() {
    return new RedisCache();
  }
}
