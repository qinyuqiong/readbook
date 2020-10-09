package com.wenhua.readbook.bookservice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wenhua.readbook.bookservice.entity.ReadbookBook;
import com.wenhua.readbook.bookservice.entity.query.QueryBook;
import com.wenhua.readbook_common.StatusReturn;
import org.springframework.web.bind.annotation.PathVariable;

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

    StatusReturn BookByIdList( Integer bookid );
}
