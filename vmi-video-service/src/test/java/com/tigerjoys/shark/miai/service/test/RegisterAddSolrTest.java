package com.tigerjoys.shark.miai.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.shark.miai.RedisCacheConst;
import com.tigerjoys.shark.miai.agent.service.IValidCodeService;
import com.tigerjoys.shark.miai.inter.contract.IUserContract;
import com.tigerjoys.shark.miai.inter.entity.UserEntity;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;


/**
 * 用户注册成功后将用户信息加入到solr索引中
 * @author liuman
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/applicationContext.xml"})
public class RegisterAddSolrTest extends BaseTestConfig {
	
    private static final Logger logger = LoggerFactory.getLogger(RegisterAddSolrTest.class);	
	
	@Autowired
	@Qualifier(RedisCacheConst.REDIS_PUBLIC_CACHE)
	private CacheRedis cacheRedis;
	
	@Autowired
	private IValidCodeService validCodeService;
	
	@Autowired
	private IUserContract userContract;
	
	/**
	 * 将用户信息加入到solr索引中去
	 * @throws Exception 
	 */
	@Test
	public void testAddUserInfoToSolr() throws Exception {
		//获取验证码
		String validateCode = validCodeService.createValidCode("13051717084");
	    //TODO 组织创建用户接口输入参数
//		meetStrangerAgent.insert(user);
	}
	
	@Test
	public void testGetUserById() throws Exception {
		long id = 24423932341846272L;
		UserEntity user = userContract.findById(id);
		logger.info("testBirthday:{}", user.getBirthday());
	}
	
}
