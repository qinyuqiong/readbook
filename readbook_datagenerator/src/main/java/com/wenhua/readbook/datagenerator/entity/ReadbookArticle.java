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
 * 文章表
 * </p>
 *
 * @author testjava
 * @since 2020-03-18
 */
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

    @ApiModelProperty(value = "文章摘要")
    private String summary;

    @ApiModelProperty(value = "文章内容 纯文本")
    private String contentText;

    @ApiModelProperty(value = "所属文章id")
    private Integer bookId;

    @ApiModelProperty(value = "所在页数")
    private Integer page;

    @ApiModelProperty(value = "文章内容 html")
    private String content;

    @ApiModelProperty(value = "文章查看次数")
    private Integer view;

    @ApiModelProperty(value = "文章喜欢次数")
    private Integer like;

    @ApiModelProperty(value = "文章创建时间")
    private Date createTime;

    @ApiModelProperty(value = "文章更新时间")
    private Date updateTime;


}
