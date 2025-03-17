package com.ecnu.exception;


import com.ecnu.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result handleException(Exception e) {
        log.error("程序出错了~", e);
        return Result.error("出错了，请联系管理员！");
    }

    @ExceptionHandler(value = DuplicateKeyException.class)
    public Result handleDuplicatenKeyException(DuplicateKeyException e){
        log.error("程序出错了~", e);
        String message = e.getMessage(); //获取出错信息
        int i = message.indexOf("Duplicate entry");
        String errMsg = message.substring(i);
        String[] arr = errMsg.split(" ");
        return Result.error(arr[2] + "已存在！");
    }
}
