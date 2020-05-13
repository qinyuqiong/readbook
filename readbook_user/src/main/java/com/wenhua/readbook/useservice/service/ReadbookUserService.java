package com.wenhua.readbook.useservice.service;

import com.wenhua.readbook.useservice.entity.ReadbookUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wenhua.readbook.useservice.entity.RegisterVo;

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
}
