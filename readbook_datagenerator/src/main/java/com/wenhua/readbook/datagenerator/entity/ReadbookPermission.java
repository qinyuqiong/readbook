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
 * 许可表
 * </p>
 *
 * @author testjava
 * @since 2020-09-29
 */
@Service
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ReadbookPermission对象", description="许可表")
public class ReadbookPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "许可id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "父许可id")
    private Integer pid;

    @ApiModelProperty(value = "权限名")
    private String name;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)//自动填充标识
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
