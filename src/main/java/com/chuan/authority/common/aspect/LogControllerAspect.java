package com.chuan.authority.common.aspect;

import com.alibaba.fastjson.JSON;
import com.chuan.authority.common.utile.HttpContextUtils;
import com.chuan.authority.common.utile.HttpServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * <p>
 *
 * </p>
 *
 * @author JingChuan
 * @since 2018/8/31 15:09
 */
@Aspect
@Component
@Slf4j
public class LogControllerAspect {

    @Pointcut("execution(* com.chuan.authority..controller.*.*(..))")
    public void logPointcut(){

    }

    @Before("logPointcut()")
    public void beforeInvoke(JoinPoint joinPoint){
        //记录请求ip,参数
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();

        // 记录下请求内容
        log.info("请求地址 : " + request.getRequestURL().toString());
        log.info("HTTP METHOD : " + request.getMethod());
        // 获取真实的ip地址
        log.info("IP : " + HttpServletUtils.getIPAddress(request));
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName());
        log.info("参数 : " + Arrays.toString(joinPoint.getArgs()));
        //
    }

    @AfterReturning(returning = "ret", pointcut = "logPointcut()")// returning的值和doAfterReturning的参数名一致
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容(返回值太复杂时，打印的是物理存储空间的地址)
        log.info("返回值 : " + JSON.toJSONString(ret));
    }

    @Around("logPointcut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object ob = pjp.proceed();// ob 为方法的返回值
        log.info("耗时 : " + (System.currentTimeMillis() - startTime));
        log.info("CLASS_METHOD : " + pjp.getSignature().getDeclaringTypeName() + "."
                + pjp.getSignature().getName());
        return ob;
    }
}
