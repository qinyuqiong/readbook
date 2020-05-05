package com.wenhua;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ReadbookUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReadbookUserApplication.class, args);
    }

}
