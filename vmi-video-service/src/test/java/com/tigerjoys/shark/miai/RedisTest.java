package com.tigerjoys.shark.miai;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Transaction;

import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.Converter;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

public class RedisTest extends BaseTestConfig {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier(AgentRedisCacheConst.REDIS_PUBLIC_CACHE)
	private CacheRedis cacheRedis;
	
	@Test
	public void pipelineWatchTest(){
		logger.info("测试redis的pipeline watch方法！");
		
		try {
			cacheRedis.jedisCommand(jedis -> {
				jedis.watch("cctv");
				if(!jedis.sismember("cctv", "aa")) {
					jedis.unwatch();
					logger.info("redis not cctv aa!");
					return;
				}
				
				try {
					Thread.sleep(10000L);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				Pipeline tx = jedis.pipelined();
				tx.zadd("bb", 10, "aa");
				tx.srem("cctv", "aa");
				List<Object> list = tx.syncAndReturnAll();
				
				logger.info("return list : " + JsonHelper.toJson(list));
			});
		} catch (Exception e) {
			logger.error(e.getMessage() , e);
		}
	}
	
	@Test
	public void multiWatchTest(){
		logger.info("测试redis的pipeline watch方法！");
		try {
			cacheRedis.jedisCommand(jedis -> {
				jedis.watch("cctv");
				if(!jedis.sismember("cctv", "aa")) {
					jedis.unwatch();
					logger.info("redis not cctv aa!");
					return;
				}
				
				try {
					Thread.sleep(10000L);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				Transaction tx = jedis.multi();
				tx.zadd("bb", 10, "aa");
				tx.srem("cctv", "aa");
				List<Object> list = tx.exec();
				
				logger.info("return list : " + JsonHelper.toJson(list));
			});
		} catch (Exception e) {
			logger.error(e.getMessage() , e);
		}
	}
	
	@Test
	public void convertTest(){
		logger.info("测试转换函数");
		
		Converter<String, Integer> converter = this::parseInt;
		int i = converter.convert("1326");
		logger.info("i======"+i);
	}
	
	private int parseInt(String s){
		return Tools.parseInt(s);
	}

}
