package com.wenhua.readbook.datagenerator.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 书籍类型关联表
 * </p>
 *
 * @author testjava
 * @since 2020-03-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ReadbookBookType对象", description="书籍类型关联表")
public class ReadbookBookType implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "书籍id")
    @TableId(value = "book_id", type = IdType.AUTO)
    private Integer bookId;

    @ApiModelProperty(value = "类型id")
    private Integer typeId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}
