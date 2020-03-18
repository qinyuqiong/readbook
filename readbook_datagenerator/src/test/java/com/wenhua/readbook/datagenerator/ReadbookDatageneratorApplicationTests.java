package com.wenhua.readbook.datagenerator;

import com.wenhua.readbook.datagenerator.handler.RandomArray;
import com.wenhua.readbook.datagenerator.handler.RandomData;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ReadbookDatageneratorApplicationTests {

    @Autowired
    private RandomArray randomArray;

    @Test
    void contextLoads() {

    }

}
