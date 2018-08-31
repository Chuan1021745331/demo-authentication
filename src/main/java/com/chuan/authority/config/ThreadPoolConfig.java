package com.chuan.authority.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * <p>
 *  线程池配置
 * </p>
 *
 * @author JingChuan
 * @since 2018/8/29 20:13
 */
@Configuration
public class ThreadPoolConfig {

    @Bean("consumerThreadPool")
    public ExecutorService createExecutorService(){
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        return executorService;
    }
}
