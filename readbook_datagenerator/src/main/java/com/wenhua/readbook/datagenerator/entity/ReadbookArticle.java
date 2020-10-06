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
 * 文章表
 * </p>
 *
 * @author testjava
 * @since 2020-09-29
 */
@Service
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ReadbookArticle对象", description="文章表")
public class ReadbookArticle implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文章id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "文章内容 html")
    private String content;

    @ApiModelProperty(value = "文章内容 纯文本")
    private String contentText;

    @ApiModelProperty(value = "所属文章id")
    private Integer bookId;

    @ApiModelProperty(value = "所在页数")
    private Integer page;

    @ApiModelProperty(value = "文章查看次数")
    private Integer view;

    @ApiModelProperty(value = "文章的赞次数")
    private Integer praise;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)//自动填充标识
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
