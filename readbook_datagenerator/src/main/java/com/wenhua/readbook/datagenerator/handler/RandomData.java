package com.wenhua.readbook.datagenerator.handler;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

/**
 * @description:提供随机的字符，数字
 * @author: yuqiong
 * @createDate: 2020/3/18
 * @version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Component
@ComponentScan
public class RandomData {

    SecureRandom RANDOM = new SecureRandom();

    //随机0-9的数字
    private Integer RANDOM_NUMBER;

    //随机26个大写字母
    private char RANDOM_UPPERCASE_LETTERS;

    //随机26个小写字母
    private char RANDOM_LOWERCASE_LETTERS;

    public Integer getRANDOM_NUMBER() {
        RANDOM_NUMBER =RANDOM.nextInt(10);
        return RANDOM_NUMBER;
    }

    public char getRANDOM_UPPERCASE_LETTERS() {
        RANDOM_UPPERCASE_LETTERS = (char)(RANDOM.nextInt(25)+65);
        return RANDOM_UPPERCASE_LETTERS;
    }

    public char getRANDOM_LOWERCASE_LETTERS() {
        RANDOM_LOWERCASE_LETTERS = (char)(RANDOM.nextInt(25)+97);
        return RANDOM_LOWERCASE_LETTERS;
    }
}
