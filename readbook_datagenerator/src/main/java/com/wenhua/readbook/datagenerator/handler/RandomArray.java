package com.wenhua.readbook.datagenerator.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @description:根据随机数生成随机数组
 * @author: yuqiong
 * @createDate: 2020/3/18
 * @version: 1.0
 */
@Component
@ComponentScan
public class RandomArray {

    @Autowired
    public RandomData randomData;

    /**
     * 随机生成对应长度的数组
     * 如果重复，退回去重新生成随机数
     * @param length
     * @return
     */
    public Integer dataNumber(int length){
        int data = 0;
        for (int i=1; i <= length ; i++){
            data += randomData.getRANDOM_NUMBER()*Math.pow(10, length-i);//随机生成某一位的数字并加到原本数字中
            //如果生成完毕，但是生成的随机数字为0，则重新执行直到不满足条件
            if (i == 1 && data == 0){
                dataNumber(length);
            }
            }
        return (Integer)data;
    }

    /**
     * 随机生成对应长度的大写字符串数组
     * @param length
     * @return
     */
    public String dataUppercaseLetters(int length){
        char[] dataChars = new char[length];
        for (int i=0; i < dataChars.length ; i++){
            dataChars[i] =randomData.getRANDOM_UPPERCASE_LETTERS();
        }
        //char类型转为string类型
        String data = String.copyValueOf(dataChars);
        return data;
    }


    /**
     * 随机生成对应长度的小写字符串数组
     * @param length
     * @return
     */
    public String dataLowercaseLetters(int length){
        char[] dataChars = new char[length];
        for (int i=0; i < dataChars.length ; i++){
            dataChars[i] = randomData.getRANDOM_LOWERCASE_LETTERS();
        }
        String data = String.copyValueOf(dataChars);
        return data;
    }

}
