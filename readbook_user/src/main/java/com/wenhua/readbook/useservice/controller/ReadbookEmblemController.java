package com.wenhua.readbook.useservice.controller;


import com.wenhua.readbook.useservice.entity.ReadbookEmblem;
import com.wenhua.readbook.useservice.service.ReadbookEmblemService;
import com.wenhua.readbook_common.StatusReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 徽章表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-06-11
 */
@CrossOrigin
@RestController
@RequestMapping("/useservice/readbook-emblem")
public class ReadbookEmblemController {
    //注入service
    @Autowired
    private ReadbookEmblemService readbookEmblemService;

    /**
     *  添加徽章规则
     * @param readbookEmblem
     * @return
     */
    @PostMapping("add")
    public StatusReturn AddType(@RequestBody ReadbookEmblem readbookEmblem){
        boolean save = readbookEmblemService.save(readbookEmblem);
        if (save == true){
            return StatusReturn.success();
        }
        return StatusReturn.error();
    }

    /**
     * 更改徽章规则
     * @param readbookEmblem
     * @return
     */
    @PostMapping("update")
    public StatusReturn UpdateType(@RequestBody ReadbookEmblem readbookEmblem){
        boolean updateById = readbookEmblemService.updateById(readbookEmblem);
        if (updateById == true){
            return StatusReturn.success();
        }
        return StatusReturn.error();
    }

    /**
     * 删除徽章规则
     * 当有用户是此徽章时不可删除
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public StatusReturn DeleteType(@PathVariable Integer id){
        boolean removeById = readbookEmblemService.removeById(id);
        if (removeById == true){
            return StatusReturn.success();
        }
        return StatusReturn.error();
    }

    /**
     * 返回所有规则
     * @return
     */
    @GetMapping
    public StatusReturn ListType(){
        List<ReadbookEmblem> readbookEmblemList = readbookEmblemService.list(null);
        return StatusReturn.success().data("readbookEmblemList",readbookEmblemList);
    }

    /**
     * 根据徽章id查询徽章信息
     * @return
     */
    @GetMapping("{id}")
    public StatusReturn SelectEmblemById(@PathVariable Integer id){
        ReadbookEmblem readbookEmblemServiceById = readbookEmblemService.getById(id);
        return StatusReturn.success().data("readbookEmblem",readbookEmblemServiceById);
    }
}

