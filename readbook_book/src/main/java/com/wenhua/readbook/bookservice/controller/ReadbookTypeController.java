package com.wenhua.readbook.bookservice.controller;


import com.wenhua.readbook.bookservice.entity.ReadbookType;
import com.wenhua.readbook.bookservice.service.ReadbookTypeService;
import com.wenhua.readbook_common.StatusReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 规则表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-06-13
 */
@RestController
@RequestMapping("/bookservice/readbook-type")
public class ReadbookTypeController {

    @Autowired
    private ReadbookTypeService readbookTypeService;

    /**
     *  添加规则
     * @param readbookType
     * @return
     */
    @PostMapping("add")
    public StatusReturn AddType(@RequestBody ReadbookType readbookType){
        boolean save = readbookTypeService.save(readbookType);
        if (save == true){
            return StatusReturn.success();
        }
        return StatusReturn.error();
}

    /**
     * 更改规则
     * @param readbookType
     * @return
     */
    @PostMapping("update")
    public StatusReturn UpdateType(@RequestBody ReadbookType readbookType){
        boolean updateById = readbookTypeService.updateById(readbookType);
        if (updateById == true){
            return StatusReturn.success();
        }
        return StatusReturn.error();
    }

    /**
     * 删除规则
     *当规则仍有书籍时删除失败
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public StatusReturn DeleteType(@PathVariable Integer id){
        boolean removeById = readbookTypeService.removeById(id);
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
        List<ReadbookType> readbookTypeList = readbookTypeService.list(null);
        return StatusReturn.success().data("readbookTypeList",readbookTypeList);
    }

    /**
     * 根据信息返回规则id
     * @param readbookType
     * @return
     */
    @PostMapping("selectTypeId")
    public StatusReturn SelectTypeId(@RequestBody ReadbookType readbookType){
        Integer id = readbookTypeService.selectTypeId(readbookType);
        return StatusReturn.success().data("id",id);
    }
}

