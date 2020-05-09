package com.tigerjoys.shark.miai.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.shark.miai.common.constant.CommonConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.RedisCacheConst;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

/**
 * redis常用api测试
 * @author liuman
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/applicationContext.xml"})
public class RedisCommonTest extends BaseTestConfig {
	
	@Autowired
	@Qualifier(RedisCacheConst.REDIS_PUBLIC_CACHE)
	private CacheRedis cacheRedis;
	
	@Test
	public void test1() {
		long userId = 11178L;
		cacheRedis.hincrBy(CommonConst.steal_red_package_count_key, String.valueOf(userId), 1);
		String countStr = cacheRedis.hget(CommonConst.steal_red_package_count_key, String.valueOf(userId));
		long count = Tools.getLong(countStr);
		System.out.println("count:" + count);
	}
	
	@Test
	public void testUserid() throws Exception {
		cacheRedis.del(AgentRedisCacheConst.USER_CACHE_KEY+25538902104277248L);
	}
	

}
