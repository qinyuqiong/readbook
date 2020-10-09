package com.wenhua.readbook.bookservice.service;

import com.wenhua.readbook.bookservice.entity.ReadbookType;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 规则表 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-06-13
 */
public interface ReadbookTypeService extends IService<ReadbookType> {

    Integer selectTypeId(ReadbookType readbookType);

    ReadbookType selectBookTypeByTypeid(Integer typeid );
}
