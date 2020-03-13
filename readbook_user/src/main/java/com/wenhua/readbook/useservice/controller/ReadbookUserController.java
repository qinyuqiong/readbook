package com.wenhua.readbook.useservice.controller;


import com.wenhua.readbook.useservice.entity.ReadbookUser;
import com.wenhua.readbook.useservice.service.ReadbookUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-03-13
 */
@RestController
@RequestMapping("/useservice/readbookuser")
public class ReadbookUserController {

    @Autowired
    private ReadbookUserService readbookUserService;

    @GetMapping
    public List<ReadbookUser> getAllUserList(){
        List<ReadbookUser> list = readbookUserService.list(null);
        System.out.println(list);
        return list;
    }

}

