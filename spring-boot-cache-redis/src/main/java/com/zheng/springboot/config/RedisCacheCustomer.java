package com.zheng.springboot.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

/**
 * redis做缓存
 * @Author zhenglian
 * @Date 2018/5/19 13:48
 */
@Configuration
@EnableCaching
public class RedisCacheCustomer extends CachingConfigurerSupport {
    @Autowired
    private RedisCacheProperties redisCacheProperties;
    @Resource(name = "standaloneRedisTemplate")
    RedisTemplate redisTemplate;
    
    private RedisSerializer redisJsonSerializer() {
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        return jackson2JsonRedisSerializer;
    }
    
    private RedisCacheConfiguration redisCacheConfiguration() {
        Duration ttl = Duration.of(redisCacheProperties.getTtl(), ChronoUnit.SECONDS);
        Boolean cacheNullValues = redisCacheProperties.getCacheNullValues();
        cacheNullValues = Optional.ofNullable(cacheNullValues).isPresent() ? cacheNullValues : false;

        RedisSerializer valueSerializer = redisJsonSerializer();
        RedisSerializationContext.SerializationPair<String> valuePair = RedisSerializationContext.SerializationPair
                .fromSerializer(valueSerializer);
        
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(ttl);
        
        if (Optional.ofNullable(cacheNullValues).isPresent()
                && !cacheNullValues) {
            configuration = configuration.disableCachingNullValues();
        }
        
        return configuration.serializeValuesWith(valuePair);
    }
    
    @Bean
    public RedisCacheManager redisCacheManager() {
        RedisConnectionFactory connectionFactory = redisTemplate.getConnectionFactory();
        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(connectionFactory);
        builder.cacheDefaults(redisCacheConfiguration());
        RedisCacheManager cacheManager = builder.build();
        cacheManager.afterPropertiesSet();
        return cacheManager;
    }
    
}
