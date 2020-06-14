package com.wenhua.readbook.useservice.entity.query;

import lombok.Data;

import java.util.Date;

/**
 * 用于封装条件查询的实现类
 * @author LENOVO
 * @version 1.0 2020/6/13
 */
@Data
public class QueryBookUser {
    private Integer id ;//用户id
    private String username;//用户名称
    private String telphone ;//用户手机
    private String emial ;//用户邮箱
    private Boolean sex ;//用户性别
    private Integer miniAge ;//用户最小年纪
    private Integer maxAge ;//用户最大年纪
    private Integer miniRoleId;//用户最下权限
    private Integer maxRoleId;//用户最大权限
    private Date firstCreateTime;//用户最早创建时间
    private Date lastCreateTime;//用户最晚创建时间
    private Date firstUpdateTime;//用户最早修改时间
    private Date lastUpdateTime;//用户最晚修改时间
}
