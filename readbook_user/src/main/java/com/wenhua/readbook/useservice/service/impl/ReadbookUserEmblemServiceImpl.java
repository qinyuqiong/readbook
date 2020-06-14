package com.wenhua.readbook.useservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenhua.readbook.useservice.entity.ReadbookUserEmblem;
import com.wenhua.readbook.useservice.mapper.ReadbookUserEmblemMapper;
import com.wenhua.readbook.useservice.service.ReadbookUserEmblemService;
import com.wenhua.readbook.useservice.service.ReadbookUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户徽章关联表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-06-11
 */
@Service
public class ReadbookUserEmblemServiceImpl extends ServiceImpl<ReadbookUserEmblemMapper, ReadbookUserEmblem> implements ReadbookUserEmblemService {

    @Autowired
    private ReadbookUserService readbookUserService;

    /**
     * 根据用户id添加徽章id
     * @param readbookUserId
     * @return
     */
    @Override
    public boolean saveByReadbookUser(Integer readbookUserId) {
        ReadbookUserEmblem readbookUserEmblem = new ReadbookUserEmblem();
        readbookUserEmblem.setUserId(readbookUserId);
        readbookUserEmblem.setEmblemId(1);
        int insert = baseMapper.insert(readbookUserEmblem);
        return insert == 0;
    }

    /**
     * 根据用户id返回徽章id
     * @param readbookUserId
     * @return
     */
    @Override
    public Integer selectById(Integer readbookUserId) {
        QueryWrapper<ReadbookUserEmblem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",readbookUserId);
        ReadbookUserEmblem readbookUserEmblem = baseMapper.selectOne(queryWrapper);
        return readbookUserEmblem.getEmblemId();
    }
}
