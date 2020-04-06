package com.wenhua.readbook.useservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wenhua.readbook.useservice.entity.ReadbookUser;
import com.wenhua.readbook.useservice.service.ReadbookUserService;
import com.wenhua.readbook_common.StatusReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-03-13
 */
@CrossOrigin
@RestController
@RequestMapping("/useservice/readbookuser")
public class ReadbookUserController {

    //注入service
    @Autowired
    private ReadbookUserService readbookUserService;


    /**
     * 根据id删除用户
     */
    @DeleteMapping("{id}")
    public boolean deleteUserById(@PathVariable Integer id){
        boolean b = readbookUserService.removeById(id);
        return b;
    }

    /**
     * 查询分页所有用户
     */
    @GetMapping("pageList/{page}/{limit}")
    public StatusReturn getAllUserList(@PathVariable Long page,@PathVariable Long limit){
        //创建page对象，传递两个参数
        Page<ReadbookUser> readbookUserPage = new Page<>(page,limit);
        //调用分页插件
        readbookUserService.page(readbookUserPage,null);
        long total = readbookUserPage.getTotal();
        List<ReadbookUser> records = readbookUserPage.getRecords();
        return StatusReturn.success().data("total",total).data("record",records);
    }

}

