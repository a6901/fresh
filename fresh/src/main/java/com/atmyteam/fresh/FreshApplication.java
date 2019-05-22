package com.atmyteam.fresh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@MapperScan(value = "com.atmyteam.fresh.Mapper")
@SpringBootApplication
@EnableCaching
@EnableAsync
@EnableRedisHttpSession(maxInactiveIntervalInSeconds= 3600)
public class FreshApplication {

    public static void main(String[] args) {
        SpringApplication.run(FreshApplication.class, args);
    }

}
