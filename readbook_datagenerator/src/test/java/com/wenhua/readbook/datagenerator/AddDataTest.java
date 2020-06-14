package com.wenhua.readbook.datagenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: yuqiong
 * @createDate: 2020/3/19
 * @version: 1.0
 */
@ComponentScan
@Component
public class AddDataTest {
    @Autowired
    AddData addData;

    public void test(int Quantity){
        for(int i = 0 ; i < Quantity ; i++){
        addData.AddUser();
        //addData.AddReadbookBook();
        //addData.AddReadbookMenu();
        //addData.AddReadbookType();
        addData.AddReadbookEmblem();
        //addData.AddReadbookComment();
        //addData.AddReadbookArticle();
        //addData.AddReadbookInfo();
        //addData.AddReadbookBookrack();
        //addData.AddReadbookBookType();
        //addData.AddReadbookRoleMenu();
        addData.AddReadbookUserEmblem();
        //addData.AddReadbookCommentArticle();
        }
    }
}
