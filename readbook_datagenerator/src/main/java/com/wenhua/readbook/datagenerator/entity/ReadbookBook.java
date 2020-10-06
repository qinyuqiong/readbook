package com.wenhua.readbook.datagenerator.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 书籍信息表
 * </p>
 *
 * @author testjava
 * @since 2020-09-29
 */
@Service
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

    @ApiModelProperty(value = "介绍详情 text")
    private String presentationText;

    @ApiModelProperty(value = "介绍详情 html")
    private String presentationHtml;

    @ApiModelProperty(value = "书籍价格")
    private Integer money;

    @ApiModelProperty(value = "出版社名")
    private String publisher;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)//自动填充标识
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
