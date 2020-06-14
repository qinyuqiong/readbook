package com.wenhua.readbook.bookservice.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wenhua.readbook.bookservice.entity.ReadbookBook;
import com.wenhua.readbook.bookservice.entity.query.QueryBook;
import com.wenhua.readbook.bookservice.service.ReadbookBookService;
import com.wenhua.readbook_common.StatusReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 书籍信息表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-06-12
 */
@CrossOrigin
@RestController
@RequestMapping("/bookservice/readbook-book")
public class ReadbookBookController {

    @Autowired
    private ReadbookBookService readbookBookService;

    //添加书籍，同时指定书籍类型
    @PostMapping("add")
    public StatusReturn addBook(@RequestBody QueryBook queryBook){
        boolean save = readbookBookService.addBook(queryBook);
        if (save == true){
            return StatusReturn.success();
        }
        return StatusReturn.error();
    }

    //更新书籍，同时可更新书籍类型
    @PostMapping("update")
    public StatusReturn updateBook(@RequestBody QueryBook queryBook){
        boolean save = readbookBookService.updateBook(queryBook);
        if (save == true){
            return StatusReturn.success();
        }
        return StatusReturn.error();
    }

    //删除，同时删除书籍类型，如果书架有不可删除
    @DeleteMapping("{bookid}")
    public StatusReturn deleteBook(@PathVariable Integer bookid){
        boolean b = readbookBookService.removeById(bookid);
        if (b == true){
            return StatusReturn.success();
        }
        return StatusReturn.error();
    }

    //多条件查询
    @PostMapping("moreCondtionPageList/{page}/{limit}")
    //@RequestBody(required = false) 传值，fasle数据不必全写，使用此方式必须使用post请求
    public StatusReturn getMoreCondtionPageList(@PathVariable Long page, @PathVariable Long limit,
                                                @RequestBody(required = false) QueryBook readbook){
        //创建page对象，传递参数
        Page<ReadbookBook> eduUserPage = new Page<>(page,limit);
        //调用sevice的方法实现条件查询带分页
        IPage<ReadbookBook> readbookUserIPage = readbookBookService.pageListCondition(page, limit , readbook);
        //调用方法，获取分页数据
        long total = eduUserPage.getTotal();
        List<ReadbookBook> records = eduUserPage.getRecords();
        return StatusReturn.success().data("total",total).data("records",records).data("readbookUserIPage",readbookUserIPage);
    }

    /**
     * 分页查询所有书籍
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("pageList/{page}/{limit}")
    public StatusReturn getAllBookList(@PathVariable Long page, @PathVariable Long limit){
        Page<ReadbookBook> readbookBookPage = new Page<>(page,limit);
        readbookBookService.page(readbookBookPage,null);
        List<ReadbookBook> records = readbookBookPage.getRecords();
        return StatusReturn.success().data("record",records);
    }

    /**
     * 根据书籍id查询书籍相关信息
     * @return
     */
    @GetMapping("{bookid}")
    public StatusReturn BookByIdList(@PathVariable Integer bookid ){
        ReadbookBook book = readbookBookService.getById(bookid);
        return StatusReturn.success().data("book",book);
    }

}

