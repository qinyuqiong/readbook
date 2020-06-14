package com.wenhua;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 用户操作模块
 * @author LENOVO
 */
@SpringBootApplication
@EnableCaching
@EnableEurekaClient
public class ReadbookUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReadbookUserApplication.class, args);
    }

}
