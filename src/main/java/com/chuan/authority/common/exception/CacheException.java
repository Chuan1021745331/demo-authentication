package com.chuan.authority.common.exception;

import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author JingChuan
 * @since 2018/9/3 17:17
 */
@Data
public class CacheException extends RuntimeException {
    private Integer code=500;
    private String message;

    public CacheException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public CacheException(String message) {
        super(message);
        this.message = message;
    }

    public CacheException(String message,Throwable e) {
        super(message,e);
        this.message = message;
    }

    public CacheException(Integer code,String message,Throwable e) {
        super(message,e);
        this.code=code;
        this.message = message;
    }
}
