package com.tigerjoys.shark.miai.service.test;

import java.util.Date;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.shark.miai.common.constant.CommonConst;
import org.shark.miai.common.util.TimeTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.shark.miai.RedisCacheConst;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

/**
 * 登录注册的时候讲用户的注册时间存储到redis的zset集合中
 * @author liuman
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/applicationContext.xml"})
public class RedisZsetTest extends BaseTestConfig {
	
	@Autowired
	@Qualifier(RedisCacheConst.REDIS_PUBLIC_CACHE)
	private CacheRedis cacheRedis;
	
	@Test
	public void testZSetAdd() {
		long userId = 116L;
		Date date = new Date();
		long dateMillis = date.getTime();
		cacheRedis.zadd(CommonConst.registerUser, dateMillis, String.valueOf(userId));
	}
	
	@Test
	public void testZSetFetch() {
		Date currentDate = new Date();
		//当前时间
		double max = currentDate.getTime();
		//五分钟前的时间
		double min = TimeTools.getTimeByMinute(-60);
		//获得是member的值(也就是userId)
		Set<String> result = cacheRedis.zrangeByScore(CommonConst.registerUser, min, max);
		System.out.println("json of result:" + JsonHelper.toJson(result));
	}

}
