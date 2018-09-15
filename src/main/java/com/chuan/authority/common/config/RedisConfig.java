package com.chuan.authority.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * <p>
 *
 * </p>
 *
 * @author JingChuan
 * @since 2018/9/6 22:32
 */
@Configuration
public class RedisConfig {
    @Value("${redis.host}")
    private String host = "127.0.0.1";

    @Value("${redis.port}")
    private int port = 6379;

    // 0 - never expire
    private int expire = 0;

    //timeout for jedis try to connect to redis server, not expire time! In milliseconds
    @Value("${redis.timeout}")
    private int timeout = 0;

    @Value("${redis.password}")
    private String password = "";

    @Bean("jedisPool")
    public JedisPool jedisPool(){
        JedisPool jedisPool=null;
        //设置jedis相关参数
        if (jedisPool == null) {
            if (password != null && !"".equals(password)) {
                jedisPool = new JedisPool(new JedisPoolConfig(), host, port, timeout, password);
            } else if (timeout != 0) {
                jedisPool = new JedisPool(new JedisPoolConfig(), host, port, timeout);
            } else {
                jedisPool = new JedisPool(new JedisPoolConfig(), host, port);
            }
        }
        return jedisPool;
    }
}
