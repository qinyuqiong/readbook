package com.wenhua.readbook.useservice.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用来返回用户与徽章信息
 * @author LENOVO
 * @version 1.0 2020/6/12
 */
@Data
public class UserWithEmblem {
    @ApiModelProperty(value = "用户 id")
    private Integer id;

    @ApiModelProperty(value = "昵称")
    private String username;

    @ApiModelProperty(value = "电话")
    private String telphone;

    @ApiModelProperty(value = "邮箱")
    private String emial;

    @ApiModelProperty(value = "性别, 1 表示男，0 表示女")
    private Boolean sex;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "用户头像url")
    private String avatar;

    @ApiModelProperty(value = "徽章名")
    private String EmblemName;

    @ApiModelProperty(value = "介绍")
    private String EmblemIntroduce;
}
