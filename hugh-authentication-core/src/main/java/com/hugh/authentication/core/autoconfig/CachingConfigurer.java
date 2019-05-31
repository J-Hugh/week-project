package com.hugh.authentication.core.autoconfig;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * 缓存层相关配置
 * 1.序列化使用Jackson
 * 2.缓存默认为5分钟失效
 * 3.redis失效后所有请求都会直接访问底层数据库。不会阻塞
 * @author luoyulin1
 */
@Configuration
@EnableCaching(proxyTargetClass = true)
@Slf4j
public class CachingConfigurer extends CachingConfigurerSupport {

    /**
     * 创建json序列化器对象
     * @return
     */
    @Bean
    public Jackson2JsonRedisSerializer getSerializer() {
        log.info("初始化redis序列化器...");
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, Visibility.ANY);
        om.enableDefaultTyping(DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        return jackson2JsonRedisSerializer;
    }

    /**
     *
     * @param redisConnectionFactory
     * @param serializer
     * @return
     */
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory, Jackson2JsonRedisSerializer serializer) {
        log.info("初始化reids模板...");
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setValueSerializer(serializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * 缓存默认配置
     * @param serializer
     * @return
     */
    @Bean("defaultCacheConfiguration")
    public RedisCacheConfiguration redisCacheConfiguration(Jackson2JsonRedisSerializer serializer){
        log.info("初始化reids缓存默认配置...");
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
        configuration = configuration.serializeValuesWith(SerializationPair.fromSerializer(serializer)).entryTtl(Duration.ofSeconds(300));
        return configuration;
    }

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory,@Qualifier("defaultCacheConfiguration") RedisCacheConfiguration cacheConfiguration) {
        log.info("初始化reids缓存管理器...");
        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();

        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(cacheConfiguration)
                .withInitialCacheConfigurations(cacheConfigurations)
                .transactionAware()
                .build();
    }

    @Bean
    @Override
    public CacheErrorHandler errorHandler() {
       return new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
                log.error("获取缓存数据失败 缓存名:{},键:{}", cache.getName(), key, exception);
            }

            @Override
            public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
                log.error("设置缓存数据失败 缓存名:{},键:{}", cache.getName(), key, exception);
            }

            @Override
            public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
                log.error("删除缓存数据失败 缓存名:{},键:{}", cache.getName(), key,exception);
            }

            @Override
            public void handleCacheClearError(RuntimeException exception, Cache cache) {
                log.error("清空缓存数据失败 缓存名:{}", cache.getName(),exception);
            }
        };
    }

}
