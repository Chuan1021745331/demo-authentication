package com.chuan.authority.common.exception;

import lombok.Data;

/**
 * <p>
 *  参数异常
 * </p>
 *
 * @author JingChuan
 * @since 2018/8/29 22:48
 */
@Data
public class ParamException extends RuntimeException{
    private Integer code=500;
    private String message;

    public ParamException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public ParamException(String message) {
        super(message);
        this.message = message;
    }

    public ParamException(String message,Throwable e) {
        super(message,e);
        this.message = message;
    }

    public ParamException(Integer code,String message,Throwable e) {
        super(message,e);
        this.code=code;
        this.message = message;
    }
}
