package com.wenhua.readbook.bookservice.config;

/**
 * @author LENOVO
 * @version 1.0 2020/10/6
 */

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.nio.charset.StandardCharsets;

/**
 * spring boot 解决中文乱码
 */
@Configuration
public class CharacterEncodingFilterConfig {
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        //用于注册过滤器
        FilterRegistrationBean frb = new FilterRegistrationBean();
        // 使用spring 提供的字符编码过滤器，不用自己写过滤器
        CharacterEncodingFilter cef = new CharacterEncodingFilter(StandardCharsets.UTF_8.toString(), true);
        frb.setFilter(cef);
        return frb;
    }
}

