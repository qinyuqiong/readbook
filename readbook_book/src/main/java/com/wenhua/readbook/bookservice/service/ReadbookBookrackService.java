package com.wenhua.readbook.bookservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wenhua.readbook.bookservice.entity.ReadbookBookrack;

import java.util.Map;

/**
 * <p>
 * 书架表 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-06-13
 */
public interface ReadbookBookrackService extends IService<ReadbookBookrack> {

    int delectBookToUser(ReadbookBookrack readbookBookrack);

    Map<String,Object> pageById(Integer userid, Long page, Long limit);
}
