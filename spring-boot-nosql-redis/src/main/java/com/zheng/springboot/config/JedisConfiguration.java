package com.zheng.springboot.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

/**
 * @Author zhenglian
 * @Date 2018/5/18 10:25
 */
@Configuration
public class JedisConfiguration {

    @Autowired
    private JedisPooledConfig pooledConfig;
    
    @Autowired
    private JedisStandaloneConfig jedisStandaloneConfig;

    /**
     * 连接redis集群需要做的配置
     * @return
     */
    @Autowired
    private JedisClusterConfig jedisClusterConfig;

    /**
     * redis集群
     * @return
     */
    public JedisConnectionFactory convertJedisClusterConnectionFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(pooledConfig.getMaxTotal());
        jedisPoolConfig.setMaxIdle(pooledConfig.getMaxIdle());
        jedisPoolConfig.setMinIdle(pooledConfig.getMinIdle());
        jedisPoolConfig.setMaxWaitMillis(pooledConfig.getMaxWait());
        jedisPoolConfig.setTestOnBorrow(pooledConfig.isTestOnBorrow());
        jedisPoolConfig.setTestOnReturn(pooledConfig.isTestOnReturn());
        jedisPoolConfig.setTestWhileIdle(pooledConfig.isTestWhileIdle());

        RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration();
        clusterConfiguration.setPassword(RedisPassword.of(jedisClusterConfig.getPassword()));
        List<RedisNode> nodes = parseRedisNodes(jedisClusterConfig.getNodes());
        clusterConfiguration.setClusterNodes(nodes);
        Integer maxRedirects = jedisClusterConfig.getMaxRedirects();
        clusterConfiguration.setMaxRedirects(Optional.ofNullable(maxRedirects).orElse(1));
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(clusterConfiguration, jedisPoolConfig);
        jedisConnectionFactory.afterPropertiesSet();
        return jedisConnectionFactory;
    }

    /**
     * redis单节点
     * @return
     */
    public JedisConnectionFactory convertJedisStandaloneConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(jedisStandaloneConfig.getHostname());
        config.setPassword(RedisPassword.of(jedisStandaloneConfig.getPassword()));
        config.setDatabase(jedisStandaloneConfig.getDatabase());
        config.setPort(jedisStandaloneConfig.getPort());
        
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(config);
        jedisConnectionFactory.afterPropertiesSet();
        return jedisConnectionFactory;
    }
    
    
    @Bean(name = "standaloneRedisTemplate")
    public RedisTemplate standaloneRedisTemplate() {
        // 单节点连接工厂
        JedisConnectionFactory factory = convertJedisStandaloneConnectionFactory();
        StringRedisTemplate template = new StringRedisTemplate(factory);
        return template;
    }

//    @Bean(name = "clusterRedisTemplate")
    public RedisTemplate clusterRedisTemplate() {
        // 集群连接工厂
        JedisConnectionFactory factory = convertJedisClusterConnectionFactory();
        StringRedisTemplate template = new StringRedisTemplate(factory);
        return template;
    }
    

    /**
     * 从配置文件中解析出集群的节点
     * @param nodes
     * @return
     */
    private List<RedisNode> parseRedisNodes(String nodes) {
        List<RedisNode> redisNodes = new ArrayList<>();
        if (StringUtils.isEmpty(nodes)) {
            RedisNode node = new RedisNode("127.0.0.1", 6789);
            redisNodes.add(node);
            return redisNodes;
        }

        StringTokenizer tokenizer = new StringTokenizer(nodes, ",");
        String nodeStr;
        String[] hostAndPort;
        String host;
        Integer port;
        RedisNode node;
        while (tokenizer.hasMoreTokens()) {
            nodeStr = tokenizer.nextToken();
            if (StringUtils.isEmpty(nodeStr)) {
                continue;
            }
            hostAndPort = nodeStr.split(":");
            host = hostAndPort[0];
            if (hostAndPort.length != 2) {
                port = 6879;
            }else {
                port = Integer.parseInt(hostAndPort[1]);
            }
            
            node = new RedisNode(host, port);
            redisNodes.add(node);
        }
        return redisNodes;
    }

}
