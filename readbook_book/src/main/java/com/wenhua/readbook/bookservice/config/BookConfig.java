package com.wenhua.readbook.bookservice.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author LENOVO
 * @version 1.0 2020/6/12
 */
@EnableTransactionManagement
@Configuration
@MapperScan("com.wenhua.readbook.bookservice.mapper")
public class BookConfig {
    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
