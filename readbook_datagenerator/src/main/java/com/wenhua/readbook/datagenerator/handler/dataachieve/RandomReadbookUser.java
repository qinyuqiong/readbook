package com.wenhua.readbook.datagenerator.handler.dataachieve;

import com.wenhua.readbook.datagenerator.entity.ReadbookUser;
import com.wenhua.readbook.datagenerator.handler.RandomArray;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description:实现ReadbookUser的数据设置
 * @author: yuqiong
 * @createDate: 2020/3/19
 * @version: 1.0
 */
public class RandomReadbookUser {
    @Autowired
    private RandomArray randomArray;

    private ReadbookUser readbookUser;

    //随机生成名字
    public void RandomUsername(){
        ReadbookUser username = this.readbookUser
                .setUsername(randomArray.dataLowercaseLetters(4))
                .setAge(randomArray.dataNumber(2))
                .setAvatar(randomArray.dataLowercaseLetters(12));

    }

}
