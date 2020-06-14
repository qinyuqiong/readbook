package com.wenhua.readbook.useservice.controller;


import com.wenhua.readbook.useservice.service.ReadbookUserEmblemService;
import com.wenhua.readbook_common.StatusReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户徽章关联表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-06-11
 */
@CrossOrigin
@RestController
@RequestMapping("/useservice/readbookuseremblem")
public class ReadbookUserEmblemController {

    //注入service
    @Autowired
    private ReadbookUserEmblemService readbookUserEmblemService;

    /**
     * 为useid添加徽章
     * 初始徽章，注册的新用户分配的初级徽章
     */
    @GetMapping("InitialBadge/{readbookUserId}")
    public boolean InitialBadge(@PathVariable Integer readbookUserId){
        boolean save = readbookUserEmblemService.saveByReadbookUser(readbookUserId);
        return save;
    }

    /**
     * 根据userid查询徽章id
     * @param readbookUserId
     */
    @GetMapping("SelectById/{readbookUserId}")
    public StatusReturn SelectById(@PathVariable Integer readbookUserId){
        Integer emblemId = readbookUserEmblemService.selectById(readbookUserId);
        return StatusReturn.success().data("emblemId",emblemId);
    }


}

