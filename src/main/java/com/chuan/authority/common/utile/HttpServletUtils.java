package com.chuan.authority.common.utile;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @类名:
 * @包名: com.chuan.authority.utile
 * @描述: (HttpServlet相关工具类)
 * @日期: 2018/8/28 21:08
 */
public class HttpServletUtils {

    private static List<String> proxyServerHeaderList =new ArrayList<>();
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

    /**
     * 获取真实ip请求地址
     * @param request
     * @return
     */
    public static String getIPAddress(HttpServletRequest request){
        if(request==null){
            return "";
        }
        /** Squid 服务代理  */
        String ip = request.getHeader("x-forwarded-for");

        /** apache 服务代理*/
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        /** weblogic 服务代理  */
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        /** 有些代理服务器*/
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        /** nginx服务代理*/
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        ip=ip.split(",")[0];
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }
}
