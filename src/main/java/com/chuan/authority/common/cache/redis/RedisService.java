package com.chuan.authority.common.cache.redis;

import com.chuan.authority.common.exception.CacheException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * <p>
 *     jedis管理
 * </p>
 *
 * @author JingChuan
 * @since 2018/9/3 17:04
 */
@Slf4j
@Service
public class RedisService {

    @Autowired
    private JedisPool jedisPool;

    public  void excute(Consumer<Jedis> consumer){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            consumer.accept(jedis);
        }catch (Exception e){
            log.error("redis 执行失败",e);
            throw new CacheException("redis 执行失败");
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }

    public  <R> R callRedis(Function<Jedis,? extends R> function){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            return function.apply(jedis);
        }catch (Exception e){
            log.error("redis 执行失败",e);
            throw new CacheException("redis 执行失败");
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }
}
