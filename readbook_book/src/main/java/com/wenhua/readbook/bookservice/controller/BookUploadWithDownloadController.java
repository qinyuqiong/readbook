package com.wenhua.readbook.bookservice.controller;

import com.wenhua.readbook.bookservice.entity.query.QueryBook;
import com.wenhua.readbook.bookservice.service.ReadbookBookService;
import com.wenhua.readbook_common.StatusReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 1.获取上传时服务器所需要的信息
 * 2.获取文件名，拼接文件名，拼接将要存储的路径
 * 3.通过io流获取完整的路径
 * 4.调用上传
 * 5.关闭上传
 * 6.返回访问的完整路径
 * @author LENOVO
 * @version 1.0 2020/10/5
 */
@RestController
@RequestMapping("/bookservice/oss")
@CrossOrigin
public class BookUploadWithDownloadController {

    @Autowired
    private ReadbookBookService readbookBookService;

    //上传图书文件
    @PostMapping("upload")
    public StatusReturn uploadBookFile(@RequestParam("file") MultipartFile file){

        String url = readbookBookService.uploadBookFile(file);
        if (url.isEmpty()||url.equals("")){
            return StatusReturn.error();
        }
        return StatusReturn.success().data("url",url);
    }


    /**
     * 下载图书
     * @return
     */
    @PostMapping("download")
    public StatusReturn downloadBookFile(@RequestBody QueryBook queryBook){

        String path = readbookBookService.downloadBookFile(queryBook);
        if (path.isEmpty()||path.equals("")){
            return StatusReturn.error();
        }
        return StatusReturn.success().data("path",path);
    }

}
