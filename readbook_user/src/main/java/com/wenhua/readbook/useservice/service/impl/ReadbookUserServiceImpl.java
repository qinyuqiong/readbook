package com.wenhua.readbook.useservice.service.impl;

import com.wenhua.readbook.useservice.entity.ReadbookUser;
import com.wenhua.readbook.useservice.mapper.ReadbookUserMapper;
import com.wenhua.readbook.useservice.service.ReadbookUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-03-13
 */
@Service
public class ReadbookUserServiceImpl extends ServiceImpl<ReadbookUserMapper, ReadbookUser> implements ReadbookUserService {

}
