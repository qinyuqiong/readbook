package com.wenhua.readbook_common;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:定义具体的数据返回格式
 * @author: yuqiong
 * @createDate: 2020/3/16
 * @version: 1.0
 */
@Data//生成getter,setter
@NoArgsConstructor//生成无参构造函数
public class StatusReturn {
    private Integer code;

    private Boolean success;

    private  String message;

    private Map<String,Object> data = new HashMap<>();

    /**
     * 操作成功，调用此方法，返回成功的数据
     * @return
     */
    public static StatusReturn success(){
        StatusReturn statusReturn = new StatusReturn();
        statusReturn.setSuccess(true);
        statusReturn.setCode(ResultCode.SUCCESS);
        statusReturn.setMessage("操作成功");
        return statusReturn;
    }

    /**
     * 操作失败，调用此方法，返回失败的数据
     * @return
     */
    public static StatusReturn error(){
        StatusReturn statusReturn = new StatusReturn();
        statusReturn.setSuccess(false);
        statusReturn.setCode(ResultCode.ERROR);
        statusReturn.setMessage("操作失败");
        return statusReturn;
    }


    /**
     * 以下方法是为了链式编程
     * @param success
     * @return
     */
    public StatusReturn success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public StatusReturn message(String message){
        this.setMessage(message);
        return this;
    }

    public StatusReturn code(Integer code){
        this.setCode(code);
        return this;
    }

    public StatusReturn data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public StatusReturn data(Map<String, Object> map){
        this.setData(map);
        return this;
    }

}
