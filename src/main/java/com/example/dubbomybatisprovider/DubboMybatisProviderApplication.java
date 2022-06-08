package com.example.dubbomybatisprovider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableDubbo
@EnableCaching
@MapperScan(basePackages ="com.example.dubbomybatisprovider.mapper")
public class DubboMybatisProviderApplication {
    public static void main(String[] args) {

        SpringApplication.run(DubboMybatisProviderApplication.class, args);
    }

}
