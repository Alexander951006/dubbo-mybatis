package com.example.dubbomybatisprovider.cache;

import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@MapperScan("com.example.dubbomybatisprovider.mapper")
@EnableTransactionManagement
@Configuration
public class MybatisPlusConfig {
    @Bean
    public OptimisticLockerInnerInterceptor optimisticLockerInnerInterceptor(){
       return new OptimisticLockerInnerInterceptor();
    }


    //分页查询插件配置
    @Bean
    public PaginationInnerInterceptor paginationInnerInterceptor(){
        return new PaginationInnerInterceptor();
    }


}
