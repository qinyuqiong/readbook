package com.wenhua.readbook.useservice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wenhua.readbook.useservice.entity.ReadbookUser;
import com.wenhua.readbook.useservice.entity.RegisterVo;
import com.wenhua.readbook.useservice.entity.UserWithEmblem;
import com.wenhua.readbook.useservice.entity.query.QueryBookUser;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-03-13
 */
public interface ReadbookUserService extends IService<ReadbookUser> {

    String login(ReadbookUser user);

    void register(RegisterVo registerVo);

    Integer InquireUserId(ReadbookUser readbookUser);

    UserWithEmblem selectUserWithEmble(Integer userid);

    IPage<ReadbookUser> pageListCondition(Page<ReadbookUser> eduBookPage, QueryBookUser readbookUser);
}
