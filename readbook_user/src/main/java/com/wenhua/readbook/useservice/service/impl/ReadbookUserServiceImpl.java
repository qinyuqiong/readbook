package com.wenhua.readbook.useservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenhua.readbook.useservice.entity.ReadbookEmblem;
import com.wenhua.readbook.useservice.entity.ReadbookUser;
import com.wenhua.readbook.useservice.entity.query.RegisterVo;
import com.wenhua.readbook.useservice.entity.UserWithEmblem;
import com.wenhua.readbook.useservice.entity.query.QueryBookUser;
import com.wenhua.readbook.useservice.handler.UserException;
import com.wenhua.readbook.useservice.mapper.ReadbookUserMapper;
import com.wenhua.readbook.useservice.service.ReadbookEmblemService;
import com.wenhua.readbook.useservice.service.ReadbookUserEmblemService;
import com.wenhua.readbook.useservice.service.ReadbookUserService;
import com.wenhua.readbook_common.JwtUtils;
import com.wenhua.readbook_common.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

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

    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Autowired
    private ReadbookUserEmblemService readbookUserEmblemService;
    @Autowired
    private ReadbookEmblemService readbookEmblemService;

    /**
     * 登录，使用电话，密码
     * @param user
     * @return
     */
    @Override
    public String login(ReadbookUser user) {
        //获取登录手机号和密码
        String telphone = user.getTelphone();
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

    /**
     * 注册，获取用户名，密码，手机号，验证码
     * @param registerVo
     */
    @Override
    public void register(RegisterVo registerVo) {
        //1.获取数据
        String username = registerVo.getUsername();
        String password = registerVo.getPassword();
        String telphone = registerVo.getTelphone();
        String code = registerVo.getCode();
        //2.判断是否为空
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || StringUtils.isEmpty(telphone) || StringUtils.isEmpty(code)){
            throw new UserException(20001,"没有获取到");
        }
        //4.获取验证码，是否相同
        String redisCode = redisTemplate.opsForValue().get(telphone);
        System.out.println("-----------------------"+redisCode);
        if (!redisCode.equals(code)){
            throw new UserException(20001,"验证码有误");
        }

        //3.判断电话是否已经存在
        QueryWrapper<ReadbookUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("telphone",telphone);
        Integer count = baseMapper.selectCount(queryWrapper);
        if (count<0){
            throw new UserException(20001,"手机号已存在");
        }

        //5.添加用户名，手机号，密码，默认性别，到数据库
        ReadbookUser readbookUser = new ReadbookUser();
        readbookUser.setUsername(username);
        readbookUser.setTelphone(telphone);
        readbookUser.setPassword(MD5.encrypt(password));
        //readbookUser.setRoleId(1);
        readbookUser.setSex(true);
        baseMapper.insert(readbookUser);

        //6.根据用户名，电话。查询用户ID
        Integer userId = InquireUserId(readbookUser);

        //7.添加初始徽章
        readbookUserEmblemService.saveByReadbookUser(userId);
    }

    /**
     * 根据用户名，电话。查询用户ID
     * @param readbookUser
     * @return
     */
    @Override
    public Integer InquireUserId(ReadbookUser readbookUser) {
        //5.查询添加用户的id
        QueryWrapper<ReadbookUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",readbookUser.getUsername());
        queryWrapper.eq("telphone",readbookUser.getTelphone());
        ReadbookUser readbookUser1 = baseMapper.selectOne(queryWrapper);
        return readbookUser1.getId();
    }

    /**
     * 根据用户id查询个人信息与徽章等级
     * @param userid
     * @return
     */
    @Override
    public UserWithEmblem selectUserWithEmble(Integer userid) {
        //1.根据id查询用户信息
        ReadbookUser readbookUser = baseMapper.selectById(userid);
        //2.根据id查询徽章id
        Integer selectById = readbookUserEmblemService.selectById(userid);
        //3.根据徽章id查询徽章名称与介绍
        ReadbookEmblem readbookEmblemServiceById = readbookEmblemService.getById(selectById);
        //4.返回所有信息
        UserWithEmblem userWithEmblem = new UserWithEmblem();
        userWithEmblem.setId(userid);
        userWithEmblem.setUsername(readbookUser.getUsername());
        userWithEmblem.setTelphone(readbookUser.getTelphone());
        userWithEmblem.setEmial(readbookUser.getEmial());
        userWithEmblem.setSex(readbookUser.getSex());
        userWithEmblem.setAge(readbookUser.getAge());
        userWithEmblem.setAvatar(readbookUser.getAvatar());
        userWithEmblem.setEmblemName(readbookEmblemServiceById.getName());
        userWithEmblem.setEmblemIntroduce(readbookEmblemServiceById.getIntroduce());

        return userWithEmblem;
    }

    /**
     * 多条件分页查询
     * @param eduUserPage
     * @param readbookUser
     */
    @Override
    public IPage<ReadbookUser> pageListCondition(Page<ReadbookUser> eduUserPage, QueryBookUser readbookUser) {
        //1.判断readbookuser是否为空
        if (StringUtils.isEmpty(readbookUser)) {
            IPage<ReadbookUser> readbookUserIPage = baseMapper.selectPage(eduUserPage, null);
            return readbookUserIPage;
        }
        //2.不为空，获取条件值
        Integer id = readbookUser.getId();
        String username = readbookUser.getUsername();
        String telphone = readbookUser.getTelphone();
        String emial = readbookUser.getEmial();
        Boolean sex = readbookUser.getSex();
        Integer miniAge = readbookUser.getMiniAge();
        Integer maxAge = readbookUser.getMaxAge();
        Integer maxRoleId = readbookUser.getMaxRoleId();
        Integer miniRoleId = readbookUser.getMiniRoleId();
        Date firstCreateTime = readbookUser.getFirstCreateTime();
        Date lastCreateTime = readbookUser.getLastCreateTime();
        Date firstUpdateTime = readbookUser.getFirstUpdateTime();
        Date lastUpdateTime = readbookUser.getLastUpdateTime();

        //创建条件构造器
        QueryWrapper<ReadbookUser> queryWrapper = new QueryWrapper<>();

        //先判断条件是否为空，再加入构造器
        if (!StringUtils.isEmpty(id)){
            //id
            queryWrapper.eq("id",id);
        }
        if (!StringUtils.isEmpty(username)){
            //username
            queryWrapper.like("username",username);
        }
        if (!StringUtils.isEmpty(telphone)){
            //telphone
            queryWrapper.eq("telphone",telphone);
        }
        if (!StringUtils.isEmpty(emial)){
            //emial
            queryWrapper.eq("emial",emial);
        }
        if (!StringUtils.isEmpty(sex)){
            //sex
            queryWrapper.eq("sex",sex);
        }
        if (!StringUtils.isEmpty(maxAge)){
            //age
            queryWrapper.le("age",maxAge);
        }
        if (!StringUtils.isEmpty(miniAge)){
            //age
            queryWrapper.ge("age",miniAge);
        }
        if (!StringUtils.isEmpty(maxRoleId)){
            //role_id
            queryWrapper.le("role_id",maxRoleId);
        }
        if (!StringUtils.isEmpty(miniRoleId)){
            //role_id
            queryWrapper.ge("role_id",miniRoleId);
        }
        if (!StringUtils.isEmpty(firstCreateTime)){
            //create_time
            queryWrapper.ge("create_time",firstCreateTime);
        }
        if (!StringUtils.isEmpty(lastCreateTime)){
            //create_time
            queryWrapper.le("create_time",lastCreateTime);
        }
        if (!StringUtils.isEmpty(firstUpdateTime)){
            //update_time
            queryWrapper.ge("update_time",firstUpdateTime);
        }
        if (!StringUtils.isEmpty(lastUpdateTime)){
            //update_time
            queryWrapper.le("update_time",lastUpdateTime);
        }

        IPage<ReadbookUser> readbookUserIPage = baseMapper.selectPage(eduUserPage, queryWrapper);
        return readbookUserIPage;
    }
}
