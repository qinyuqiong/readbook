package com.wenhua.readbook.datagenerator.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @description:
 * @author: yuqiong
 * @createDate: 2020/3/18
 * @version: 1.0
 */
@EnableTransactionManagement
@Configuration
@MapperScan("com.wenhua.readbook.datagenerator.mapper")
public class AllConfig {
}
