package com.tigerjoys.shark.miai.service.test;

import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;


/**
 * @author shimng
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/applicationContext.xml"})
public class RedisLuaTest extends BaseTestConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(RedisLuaTest.class);
	
	//脚本
	private final String LUA = "local user1,user2 = KEYS[1],KEYS[2]; local ex1 = redis.call('exists',user1); local ex2 = redis.call('exists',user2); if(ex1 and tonumber(ex1) == 0 and ex2 and tonumber(ex2) == 0) then redis.call('set',user1,user1); redis.call('pexpire',user1,35000); redis.call('set',user2,user2); redis.call('pexpire',user2,35000); return 1; else return 0;end";
	
	@Autowired
	@Qualifier(AgentRedisCacheConst.REDIS_PUBLIC_CACHE)
	private CacheRedis cacheRedis; 
	
	@Test
	public void testRedisLua() throws Exception {
		String result = String.valueOf(cacheRedis.eval(LUA, 2, "vchat_user_order_555", "vchat_user_order_666"));
		logger.info("result of json:{}", JsonHelper.toJson(result));
	}
	
	@Test
	public void testRedisLuaTime() throws Exception {
		for(int i=0; i<50000;i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					int user1 = getRandomNumber(100,140);
					int user2 = getRandomNumber(200,10000);
					long startTime = System.currentTimeMillis();
					String result = String.valueOf(cacheRedis.eval(LUA, 2, "vchat_user_order_"+user1, "vchat_user_order_"+user2));
					//logger.info("result of json:{}", JsonHelper.toJson(result));
					long endTime = System.currentTimeMillis();
					logger.info("进行一次需要消耗的时间值：" + (endTime - startTime) + "毫秒");
				}
			}).start();
		}
	}
	
	public int getRandomNumber(int min, int max) {
		Random random = new Random();  
		int randomNumber =  random.nextInt(max)%(max-min+1) + min; 
		return randomNumber;
	}
	
}
