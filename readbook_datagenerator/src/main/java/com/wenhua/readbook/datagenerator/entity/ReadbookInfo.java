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
 * 统计信息表
 * </p>
 *
 * @author testjava
 * @since 2020-09-29
 */
@Service
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ReadbookInfo对象", description="统计信息表")
public class ReadbookInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "统计信息id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "书籍id")
    private Integer bookId;

    @ApiModelProperty(value = "文章id")
    private Integer articleId;

    @ApiModelProperty(value = "当前文章进度,0-100")
    private Integer schedule;

    @ApiModelProperty(value = "阅读时间")
    private Date readTime;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)//自动填充标识
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
