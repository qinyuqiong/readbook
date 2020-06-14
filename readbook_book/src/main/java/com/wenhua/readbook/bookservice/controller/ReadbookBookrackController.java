package com.wenhua.readbook.bookservice.controller;


import com.wenhua.readbook.bookservice.entity.ReadbookBookrack;
import com.wenhua.readbook.bookservice.service.ReadbookBookrackService;
import com.wenhua.readbook_common.StatusReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 书架表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-06-13
 */
@CrossOrigin
@RestController
@RequestMapping("/bookservice/bookrack")
public class ReadbookBookrackController {

    @Autowired
    private ReadbookBookrackService readbookBookrackService;

    //1.分页查看书架书籍

    /**
     * @param userid
     * @param page 多少页
     * @param limit 每页个数
     * @return
     */
    @GetMapping("pageList/{userid}/{page}/{limit}")
    public StatusReturn getAllBookList(@PathVariable Integer userid , @PathVariable Long page , @PathVariable Long limit){
        Map<String,Object> bookrackPage = readbookBookrackService.pageById(userid,page,limit);
        //long total = bookrackPage.getTotal();
        //List<ReadbookBookrack> records = bookrackPage.getRecords();
        return StatusReturn.success().data("bookrackPage",bookrackPage);
    }

    //2.添加书籍到用户
    @GetMapping("{userid}/{bookid}")
    public StatusReturn AddBookToUser(@PathVariable Integer userid , @PathVariable Integer bookid){
        ReadbookBookrack readbookBookrack = new ReadbookBookrack();
        readbookBookrack.setUserId(userid);
        readbookBookrack.setBookId(bookid);
        boolean save = readbookBookrackService.save(readbookBookrack);
        if (save == true){
            return StatusReturn.success();
        }
        return StatusReturn.error();
    }

    //3.删除用户书架的书籍
    @DeleteMapping("{userid}/{bookid}")
    public StatusReturn DeleteBookToUser(@PathVariable Integer userid , @PathVariable Integer bookid){
        ReadbookBookrack readbookBookrack = new ReadbookBookrack();
        readbookBookrack.setUserId(userid);
        readbookBookrack.setBookId(bookid);
        int delect = readbookBookrackService.delectBookToUser(readbookBookrack);
        if (delect > 0){
            return StatusReturn.success();
        }
        return StatusReturn.error();
    }


}

