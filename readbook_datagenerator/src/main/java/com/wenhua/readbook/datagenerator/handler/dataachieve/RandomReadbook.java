package com.wenhua.readbook.datagenerator.handler.dataachieve;

import com.wenhua.readbook.datagenerator.handler.RandomArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @description:按照条件设置数据库各项的数据
 * @author: yuqiong
 * @createDate: 2020/3/19
 * @version: 1.0
 */
@ComponentScan
@Component
public class RandomReadbook {
    @Autowired
    private RandomArray randomArray;

    //随机生成名字,用于各种name
    public String RandomName(){
        String name =
                randomArray.dataUppercaseLetters(1)
                        +randomArray.dataLowercaseLetters(3);
        return name;
    }

    //随机数字
    public Integer RandomNumber(){
        Integer number = randomArray.dataNumber(10);
        return number;
    }

    //随机0-99数字，用于年龄等
    public Integer RandomHundredNumber(){
        Integer number = randomArray.dataNumber(2);
        return number;
    }

    //生成邮箱
    public String RandomEmail(){
        String email = randomArray.dataUppercaseLetters(1)
                +randomArray.dataLowercaseLetters(3)
                +randomArray.dataUppercaseLetters(2)
                +randomArray.dataLowercaseLetters(3)+"@qq.com";
        return email;
    }

    //生成string，字符串
    public String RandomString(){
        String string = randomArray.dataUppercaseLetters(1)
                +randomArray.dataLowercaseLetters(1)
                +randomArray.dataUppercaseLetters(2)
                +randomArray.dataLowercaseLetters(2)
                +randomArray.dataNumber(2);
        return string;
    }
}
