package com.wenhua.readbook.bookservice.entity.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

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

    @ApiModelProperty(value = "出版社名")
    private String publisher;

    @ApiModelProperty(value = "类型id")
    private Integer typeId;

    @ApiModelProperty(value = "创建最早时间")
    private Date firstCreateTime;

    @ApiModelProperty(value = "创建最晚时间")
    private Date lastCreateTime;

    @ApiModelProperty(value = "类型名称")
    private String TypeName;

    @ApiModelProperty(value = "别名")
    private String TypeNickname;
}
