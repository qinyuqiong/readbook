package com.wenhua.readbook.bookservice.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wenhua.readbook.bookservice.entity.ReadbookBookType;
import com.wenhua.readbook.bookservice.service.ReadbookBookTypeService;
import com.wenhua.readbook_common.StatusReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 书籍类型关联表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-06-14
 */
@CrossOrigin
@RestController
@RequestMapping("/bookservice/readbook-book-type")
public class ReadbookBookTypeController {
    @Autowired
    private ReadbookBookTypeService readbookBookTypeService;

    @PostMapping("update")
    public  StatusReturn updateBookType(@RequestBody ReadbookBookType readbookBookType){
        int i =readbookBookTypeService.updateByBook(readbookBookType);
        return StatusReturn.success().data("i",i);
    }

    @PostMapping
    public StatusReturn addBookType(@RequestBody ReadbookBookType readbookBookType){
        readbookBookTypeService.save(readbookBookType);
        return StatusReturn.success();
    }

    //多条件查询
    @PostMapping("getBookTypeMoreCondtionPageList/{page}/{limit}")
    //@RequestBody(required = false) 传值，fasle数据不必全写，使用此方式必须使用post请求
    public StatusReturn getBookTypeMoreCondtionPageList(@PathVariable Long page, @PathVariable Long limit,
                                                @RequestBody(required = false) ReadbookBookType readbookBookType){
        //创建page对象，传递参数
        Page<ReadbookBookType> eduBookPage = new Page<>(page,limit);
        //调用sevice的方法实现条件查询带分页
        IPage<ReadbookBookType> readbookBookTypeIPage = readbookBookTypeService.pageListCondition(eduBookPage, readbookBookType);
        return StatusReturn.success().data("readbookBookTypeIPage",readbookBookTypeIPage);
    }
}

