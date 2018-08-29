package com.chuan.authority.utile;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @类名:
 * @包名: com.chuan.authority.utile
 * @描述: (HttpServlet相关工具类)
 * @日期: 2018/8/28 21:08
 */
public class HttpServletUtils {
    /**
     * 判断是否是ajax请求
     * @param request
     * @return
     */
    public static boolean isAjax(HttpServletRequest request){
        if(request==null){
            return false;
        }
        if(StringUtils.isBlank(request.getHeader("x-requested-with"))){
            return false;
        }

        return request.getHeader("x-requested-with").equals("XMLHttpRequest");
    }
}
