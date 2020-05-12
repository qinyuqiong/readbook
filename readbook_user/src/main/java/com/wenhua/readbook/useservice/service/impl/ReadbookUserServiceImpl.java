package com.wenhua.readbook.useservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wenhua.readbook.useservice.entity.ReadbookUser;
import com.wenhua.readbook.useservice.handler.UserException;
import com.wenhua.readbook.useservice.mapper.ReadbookUserMapper;
import com.wenhua.readbook.useservice.service.ReadbookUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenhua.readbook_common.JwtUtils;
import com.wenhua.readbook_common.MD5;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Override
    public String login(ReadbookUser user) {
        //获取登录手机号和密码
        Integer telphone = user.getTelphone();
        String password = user.getPassword();
        //手机和密码非空判断
        if (StringUtils.isEmpty(telphone) || StringUtils.isEmpty(password)){
            throw new UserException(20001,"登录失败");
        }
        //判断手机号正确
        QueryWrapper<ReadbookUser> queryWrapper = new QueryWrapper();
        queryWrapper.eq("telphone",telphone);
        ReadbookUser readbookUser = baseMapper.selectOne(queryWrapper);
        //判断查询对象是否为空
        if (StringUtils.isEmpty(readbookUser)){
            throw new UserException(20001,"用户不存在");
        }
        //判断密码
        //因为存储到数据库的密码坑定是加密的
        //把输入的密码进行加密，再和数据库密码进行比较
        //加密方式MD5
        if (!MD5.encrypt(password).equals(readbookUser.getPassword())){
            throw new UserException(20001,"密码错误");
        }
        //注册成功生成token字符串，使用jwt工具类
        String jwtToken = JwtUtils.getJwtToken(readbookUser.getId().toString(), readbookUser.getUsername());
        return jwtToken;
    }
}
