package com.wenhua.readbook.bookservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 *  书籍操作模块
 * @author LENOVO
 * @version 1.0 2020/6/12
 */
@SpringBootApplication
@EnableCaching
@EnableEurekaClient
public class ReadbookBookApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReadbookBookApplication.class, args);
    }
}
