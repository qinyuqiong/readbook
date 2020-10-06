package com.wenhua.readbook.bookservice.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 书籍信息表
 * </p>
 *
 * @author testjava
 * @since 2020-06-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ReadbookBook对象", description="书籍信息表")
public class ReadbookBook implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "书籍id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
