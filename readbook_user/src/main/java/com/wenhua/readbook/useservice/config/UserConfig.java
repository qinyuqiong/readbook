package com.wenhua.readbook.useservice.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @description:配置文件
 * @author: yuqiong
 * @createDate: 2020/3/13
 * @version: 1.0
 */
@EnableTransactionManagement
@Configuration
@MapperScan("com.wenhua.readbook.useservice.mapper")
public class UserConfig {
    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
