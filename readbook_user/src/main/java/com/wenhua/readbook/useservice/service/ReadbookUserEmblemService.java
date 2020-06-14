package com.wenhua.readbook.useservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wenhua.readbook.useservice.entity.ReadbookUserEmblem;

/**
 * <p>
 * 用户徽章关联表 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-06-11
 */
public interface ReadbookUserEmblemService extends IService<ReadbookUserEmblem> {

    boolean saveByReadbookUser(Integer readbookUserId);

    Integer selectById(Integer readbookUserId);
}
