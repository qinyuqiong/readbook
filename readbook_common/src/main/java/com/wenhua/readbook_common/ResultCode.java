package com.wenhua.readbook_common;

/**
 * @description:定义操作状态码
 * @author: yuqiong
 * @createDate: 2020/3/16
 * @version: 1.0
 */
public interface ResultCode {
    int SUCCESS = 200;//成功状态码
    int ERROR = 201;//失败状态码
    int AUTH  = 300;//没有操作权限状态码
}
