package com.chuan.authority.common.config;

import com.chuan.authority.common.exception.DBException;
import com.chuan.authority.common.exception.ParamException;
import com.chuan.authority.common.utile.HttpServletUtils;
import com.chuan.authority.common.utile.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @类名:
 * @包名: com.chuan.authority.config
 * @描述: (全局异常处理器)
 * @日期: 2018/8/27 23:15
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({ParamException.class})
    public Object handleParamException(ParamException e, HttpServletRequest request, Model model){
        log.error(e.getMessage(), e);
        //判断request是否是ajax请求
        if(HttpServletUtils.isAjax(request)){
            return R.failed(e.getCode(),e.getMessage());
        }
        model.addAttribute("errorMessage",e.getMessage());
        return new ModelAndView("error/500");
    }

    @ExceptionHandler({DBException.class})
    public Object handleDBException(DBException e, HttpServletRequest request, Model model){
        log.error(e.getMessage(), e);
        //判断request是否是ajax请求
        if(HttpServletUtils.isAjax(request)){
            return R.failed(e.getCode(),e.getMessage());
        }
        model.addAttribute("errorMessage",e.getMessage());
        return new ModelAndView("error/500");
    }

    @ExceptionHandler(Exception.class)
    public Object exception(Exception e, HttpServletRequest request){
        log.error(e.getMessage(), e);
        //判断request是否是ajax请求
        if(HttpServletUtils.isAjax(request)){
            return R.failed(HttpStatus.INTERNAL_SERVER_ERROR.value(),"服务器错误,请联系管理员");
        }
        return new ModelAndView("error/500");
    }
}
