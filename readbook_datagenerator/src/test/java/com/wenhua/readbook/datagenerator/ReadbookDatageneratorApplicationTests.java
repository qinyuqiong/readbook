package com.wenhua.readbook.datagenerator;

import com.wenhua.readbook.datagenerator.handler.RandomArray;
import com.wenhua.readbook.datagenerator.handler.dataachieve.RandomReadbook;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class ReadbookDatageneratorApplicationTests {

@Autowired
AddDataTest addDataTest;

    @Test
    void contextLoads() {
        addDataTest.test(50);
    }

}
