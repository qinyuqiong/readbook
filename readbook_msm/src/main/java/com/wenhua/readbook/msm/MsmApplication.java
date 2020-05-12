package com.wenhua.readbook.msm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

/**
 * 阿里云短信服务
 * @version 1.0 2020/5/12
 * @auther LENOVO
 */
@EnableCaching//整合redis
@ComponentScan({"com.wenhua"})
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MsmApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsmApplication.class);
    }
}
