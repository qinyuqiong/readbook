package com.wenhua.readbook.bookservice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wenhua.readbook.bookservice.entity.ReadbookBook;
import com.wenhua.readbook.bookservice.entity.ReadbookBookType;
import com.wenhua.readbook.bookservice.entity.ReadbookType;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <p>
 * 书籍类型关联表 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-06-14
 */
public interface ReadbookBookTypeService extends IService<ReadbookBookType> {

    int updateByBook(ReadbookBookType readbookBookType);

    IPage<ReadbookBookType> pageListCondition(Page<ReadbookBookType> eduBookPage, ReadbookBookType readbookBookType);

    int selectTypeidByBookid(Integer bookid);


    }

