package com.wenhua.readbook.bookservice.entity.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 传输实现类
 * @author LENOVO
 * @version 1.0 2020/6/14
 */
@Data
public class QueryBook {

    @ApiModelProperty(value = "书籍id")
    private Integer bookId;

    @ApiModelProperty(value = "书籍名称")
    private String name;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "简介")
    private String intro;

    @ApiModelProperty(value = "url路径")
    private String url;

    @ApiModelProperty(value = "出版社名")
    private String publisher;

    @ApiModelProperty(value = "类型id")
    private Integer typeId;

    @ApiModelProperty(value = "类型名称")
    private String TypeName;


}
