package com.tigerjoys.shark.miai.agent.service.impl;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;
import com.tigerjoys.shark.miai.agent.constant.AlibabaSmsConst;
import com.tigerjoys.shark.miai.agent.service.IValidCodeService;

/**
 * 用户验证码
 * @author lipeng
 *
 */
@Service
public class ValidCodeServiceImpl implements IValidCodeService{
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier(AgentRedisCacheConst.VALID_CODE_CACHE)
	private CacheRedis validCodeCacheRedis;
	
	
	/**
	 * 随机类
	 */
	private Random random = new Random();
	
	/**
	 * 生成短信验证码
	 * @param mobile - 手机号码
	 * @return String
	 */
	@Override
	public String createValidCode(String mobile){
		String randomCode = getRandom(AlibabaSmsConst.valid_code_length);
		logger.info(mobile + "您的短信验证码为："+randomCode);
		
		validCodeCacheRedis.setex(getMobileValidateCode(mobile), AgentRedisCacheConst.REDIS_MOBILE_SEND_CODE_EXPIRE,randomCode);
		
		return randomCode;
	}
	
	/**
	 * 获取短信验证码
	 * @param mobile - 手机号码
	 * @return String
	 */
	public String getValidCode(String mobile) {
		return validCodeCacheRedis.get(getMobileValidateCode(mobile));
	}
	
	/**
	 * 删除掉验证码信息
	 * @param mobile - 手机号码
	 */
	public void delValidCode(String mobile) {
		validCodeCacheRedis.del(getMobileValidateCode(mobile));
	}
	
	/**
	 * 生成指定位数的数字
	 * @param n
	 * @return String
	 */
	private String getRandom(int n) {
		if(n<= 0) return "0";
		
		StringBuilder buf = new StringBuilder();
		for(int i=0;i<n;i++) {
			buf.append(random.nextInt(10));
		}
		
		return buf.toString();
	}
	
	/**
	 * 获得验证码的key
	 * @param mobile - 手机号码
	 * @return String
	 */
	private String getMobileValidateCode(String mobile) {
		return AgentRedisCacheConst.VALID_CODE_KEY+mobile;
	}
	
}
