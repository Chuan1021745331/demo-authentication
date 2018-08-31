package com.chuan.authority.utile;

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

    //代理服务器的头
    private static List<String> proxyServerHeaderList =new ArrayList<>();
    static{
        /** Squid 服务代理  */
        proxyServerHeaderList.add("X-Forwarded-For");

        /** apache 服务代理*/
        proxyServerHeaderList.add("Proxy-Client-IP");

        /** weblogic 服务代理*/
        proxyServerHeaderList.add("WL-Proxy-Client-IP");

        /** 有些代理服务器*/
        proxyServerHeaderList.add("HTTP_CLIENT_IP");

        /** nginx服务代理*/
        proxyServerHeaderList.add("X-Real-IP");
    }

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
        for(String proxyHeader:proxyServerHeaderList){
            String ipAddress = request.getHeader(proxyHeader);
            if(StringUtils.isBlank(ipAddress)||"unknown".equalsIgnoreCase(ipAddress)){
               continue;
            }
            //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
            return ipAddress.split(",")[0];
        }
        return request.getRemoteAddr();
    }
}
