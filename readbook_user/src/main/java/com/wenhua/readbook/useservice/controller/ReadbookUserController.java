package com.wenhua.readbook.useservice.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wenhua.readbook.useservice.entity.ReadbookUser;
import com.wenhua.readbook.useservice.entity.RegisterVo;
import com.wenhua.readbook.useservice.entity.UserWithEmblem;
import com.wenhua.readbook.useservice.entity.query.QueryBookUser;
import com.wenhua.readbook.useservice.service.ReadbookUserService;
import com.wenhua.readbook_common.StatusReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户所有操作
 * </p>
 *
 * @author testjava
 * @since 2020-03-13
 */
@CrossOrigin
@RestController
@RequestMapping("/useservice/readbookuser")
public class ReadbookUserController {

    //注入service
    @Autowired
    private ReadbookUserService readbookUserService;

    //注册
    @PostMapping("register")
    public StatusReturn registterUser(@RequestBody RegisterVo registerVo){
        readbookUserService.register(registerVo);
        return StatusReturn.success();
    }

    /**
     *  通过手机号，密码登录
     * @param user
     * @return
     */
    @PostMapping("login")
    public StatusReturn loginUser(@RequestBody ReadbookUser user){
        //member对象封装手机号和密码
        //调用service方法实现登陆
        //返回token值，使用jwt生成
        String token = readbookUserService.login(user);
        return StatusReturn.success().data("token",token);
    }

    /**
     * 根据id删除用户,同时删除
     * 书架表记录
     * 用户徽章表记录
     * 书籍评价表记录
     * 统计表
     */
    @DeleteMapping("{id}")
    public StatusReturn deleteUserById(@PathVariable Integer id){
        boolean b = readbookUserService.removeById(id);
        if (b == true){
            return StatusReturn.success();
        }
        return StatusReturn.error();
    }

    /**
     *  多条件组合查询带分页
     * @param page
     * @param limit
     * @param readbookUser
     * @return
     */
    @PostMapping("moreCondtionPageList/{page}/{limit}")
    //@RequestBody(required = false) 传值，fasle数据不必全写，使用此方式必须使用post请求
    public StatusReturn getMoreCondtionPageList(@PathVariable Long page, @PathVariable Long limit,
                                     @RequestBody(required = false) QueryBookUser readbookUser){
        //创建page对象，传递参数
        Page<ReadbookUser> eduBookPage = new Page<>(page,limit);
        //调用sevice的方法实现条件查询带分页
        IPage<ReadbookUser> readbookUserIPage = readbookUserService.pageListCondition(eduBookPage, readbookUser);
        //调用方法，获取分页数据
        long total = eduBookPage.getTotal();
        List<ReadbookUser> records = eduBookPage.getRecords();
        return StatusReturn.success().data("total",total).data("records",records).data("readbookUserIPage",readbookUserIPage);
    }

    /**
     * 查询分页所有用户
     */
    @GetMapping("pageList/{page}/{limit}")
    public StatusReturn getAllUserList(@PathVariable Long page,@PathVariable Long limit){
        //创建page对象，传递两个参数
        Page<ReadbookUser> readbookUserPage = new Page<>(page,limit);
        //调用分页插件
        readbookUserService.page(readbookUserPage,null);
        long total = readbookUserPage.getTotal();
        List<ReadbookUser> records = readbookUserPage.getRecords();
        return StatusReturn.success().data("total",total).data("record",records);
    }

    /**
     * 根据用户名和电话查询用户ID
     * @return
     */
    @PostMapping("InquireUserId")
    public StatusReturn getInquireUserId(@RequestBody  ReadbookUser readbookUser){
        Integer userId = readbookUserService.InquireUserId(readbookUser);
        return StatusReturn.success().data("ID",userId);
    }

    /**
     * 修改用户信息
     * @return
     */
    @PostMapping("UpdateInformation")
    public StatusReturn UpdateInformation(@RequestBody ReadbookUser readbookUser){
        boolean b = readbookUserService.updateById(readbookUser);
        if (b == true){
            return StatusReturn.success();
        }
        return StatusReturn.error();
    }

    /**
     *  根据用户id查询个人信息与徽章等级
     * @return
     */
    @GetMapping("SelectUserWithEmble/{userid}")
    public  StatusReturn SelectUserWithEmble(@PathVariable Integer userid){
        UserWithEmblem userWithEmblem = readbookUserService.selectUserWithEmble(userid);
        return StatusReturn.success().data("userWithEmblem",userWithEmblem);
    }
}

