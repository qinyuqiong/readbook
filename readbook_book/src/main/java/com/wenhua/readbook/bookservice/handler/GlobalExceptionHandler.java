package com.wenhua.readbook.bookservice.handler;

import com.wenhua.readbook_common.StatusReturn;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.NestedServletException;

/**
 * @description:统一异常处理
 * @author: yuqiong
 * @createDate: 2020/3/16
 * @version: 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 对所有异常进行相同处理
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public StatusReturn error(Exception exception){
        exception.printStackTrace();
        return StatusReturn.error().message("出现异常");
    }

    /**
     * 对特定异常进行处理
     */
    @ExceptionHandler(NestedServletException.class)
    @ResponseBody
    public StatusReturn error(NestedServletException NsException){
        NsException.printStackTrace();
        return StatusReturn.error().message("数据嵌套出现异常");
    }

    /**
     * 对特定异常进行处理
     */
    @ExceptionHandler(BookException.class)
    @ResponseBody
    public StatusReturn error(BookException bookException){
        bookException.printStackTrace();
        return StatusReturn.error().message(bookException.getMessage()).code(bookException.getCode());
    }
}
