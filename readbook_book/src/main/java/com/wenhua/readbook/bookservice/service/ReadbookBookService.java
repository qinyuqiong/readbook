package com.wenhua.readbook.bookservice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wenhua.readbook.bookservice.entity.ReadbookBook;
import com.wenhua.readbook.bookservice.entity.query.QueryBook;

/**
 * <p>
 * 书籍信息表 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-06-12
 */
public interface ReadbookBookService extends IService<ReadbookBook> {

    boolean addBook(QueryBook queryBook);

    boolean updateBook(QueryBook queryBook);

    IPage<ReadbookBook> pageListCondition( Long page, Long limit , QueryBook readbook);
}