package com.chuan.authority.common.utile;

import lombok.Data;

/**
 * @类名:
 * @包名: com.chuan.authority.utile
 * @描述: (接口返回格式)
 * @日期: 2018/8/27 23:17
 */
@Data
public class R<T> {
    private Integer code;
    private String  message;
    private T data;

    private R(){
        this.code=0;
        this.message="";
    }

    private R(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static R ok(){
        return new R();
    }

    public static <T> R<T> ok(T data){
        return new R(0,"",data);
    }

    public static <T> R<T> ok(String msg,T data){
        return new R(0,msg,data);
    }

    public static R failed(){
       return new R(1,"failed",null);
    }

    public static R failed(String msg){
        return new R(1,msg,null);
    }

    public static R failed(Integer code,String msg){
        return new R(code,msg,null);
    }

    public static <T> R<T> failed(String msg,T data){
        return new R(1,msg,data);
    }
}
