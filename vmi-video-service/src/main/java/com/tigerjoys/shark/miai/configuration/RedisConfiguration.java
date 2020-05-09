package com.tigerjoys.shark.miai.configuration;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.cache.RedisPoolConfig;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.RedisCacheConst;

/**
 * redis配置
 * @author chengang
 *
 */
@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfiguration {
	
	@Autowired
	private RedisProperties properties;
	
	/**
     * 创建Redis客户端
     * @return CacheRedis
     * @throws SQLException
     */
    @Bean(name= {RedisCacheConst.REDIS_PUBLIC_CACHE,RedisCacheConst.PUBLIC_MYBATIS_SPRING_BEAN_NAME})
    public CacheRedis cacheRedis() {
    	RedisPoolConfig config = new RedisPoolConfig();
    	config.setHost(properties.getHost());
    	config.setPort(properties.getPort());
    	config.setDatabase(properties.getDb());
    	config.setTimeout(properties.getTimeout());
    	config.setMaxTotal(properties.getActive());
    	config.setMaxIdle(properties.getIdle());
    	config.setMaxWaitMillis(properties.getWaitMillis());
    	config.setTestOnBorrow(properties.isBorrowCheck());
    	config.setTestOnReturn(properties.isReturnCheck());
    	config.setPassword(Tools.isNull(properties.getPassword()) ? null : properties.getPassword());
    	
    	return new CacheRedis(config);
    }

}
