package com.wenhua.readbook.ekservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * springcould
 * @version 1.0 2020/5/5
 * @auther LENOVO
 */
@SpringBootApplication
@EnableEurekaServer//加入此注解
public class EkServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EkServerApplication.class);
    }
}
