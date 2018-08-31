package com.chuan.authority.exception;

import lombok.Data;

/**
 * <p>
 *  自定义数据库异常
 * </p>
 *
 * @author JingChuan
 * @since 2018/8/30 0:12
 */
@Data
public class DBException extends RuntimeException{
    private Integer code=500;
    private String message;

    public DBException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public DBException(String message) {
        super(message);
        this.message = message;
    }

    public DBException(String message,Throwable e) {
        super(message,e);
        this.message = message;
    }

    public DBException(Integer code,String message,Throwable e) {
        super(message,e);
        this.code=code;
        this.message = message;
    }
}
