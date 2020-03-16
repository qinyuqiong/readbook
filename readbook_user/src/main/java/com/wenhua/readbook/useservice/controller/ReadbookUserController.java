package com.wenhua.readbook.useservice.controller;


import com.wenhua.readbook.useservice.entity.ReadbookUser;
import com.wenhua.readbook.useservice.service.ReadbookUserService;
import com.wenhua.readbook_common.StatusReturn;
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

    //注入service
    @Autowired
    private ReadbookUserService readbookUserService;

    /**
     * 根据id删除用户
     */


    /**
     * 查询所有用户
     */
    @GetMapping
    public StatusReturn getAllUserList(){
        List<ReadbookUser> list = readbookUserService.list(null);
        System.out.println(list);
        return StatusReturn.success().data("items",list);
    }

}

