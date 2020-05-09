package com.tigerjoys.shark.miai.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.shark.miai.RedisCacheConst;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;


/**
 * 服务评价测试
 * @author liuman
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/applicationContext.xml"})
public class ServiceCommentTest extends BaseTestConfig {
	
	@Autowired
	@Qualifier(RedisCacheConst.REDIS_PUBLIC_CACHE)
	private CacheRedis cacheRedis;
	
	/**
	 * 用户点击置顶
	 * @throws Exception 
	 */
	@Test
	public void testAddBussinessMessage() throws Exception {
		long userId = 10022588L;
	}
	
}
