package com.tigerjoys.shark.miai.agent.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.agent.IUserCharmAgent;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;
import com.tigerjoys.shark.miai.inter.contract.IUserCharmContract;
import com.tigerjoys.shark.miai.inter.entity.UserCharmEntity;

/**
 * 用户魅力值实现类
 * @author lipeng
 *
 */
@Service
public class UserCharmAgentImpl implements IUserCharmAgent {
	
	
	@Autowired
	private IUserCharmContract userCharmContract;
	
	@Autowired
	@Qualifier(AgentRedisCacheConst.USER_CHARM_CACHE)
	private CacheRedis userCharmCacheRedis;

	@Override
	public Integer getCharmByUserid(long userid) throws Exception {
		String key = AgentRedisCacheConst.USER_CHARM_KEY+userid;
		String charm = userCharmCacheRedis.get(key);
		if(charm != null) {
			return Tools.parseInt(charm);
		}
		UserCharmEntity userCharm = userCharmContract.findByProperty("userid", userid);
		if (Tools.isNotNull(userCharm)) {
			userCharmCacheRedis.set(key, userCharm.getCharm());
			return userCharm.getCharm();
		}
		return 0;
	}

	@Override
	public void addUserCharm(long userid) throws Exception {
		String key = AgentRedisCacheConst.USER_CHARM_KEY+userid;
		UserCharmEntity userCharm = userCharmContract.findByProperty("userid", userid);
		if (Tools.isNotNull(userCharm)) {
			userCharm.setCharm(userCharm.getCharm()+1);
			userCharmContract.update(userCharm);
			userCharmCacheRedis.set(key, userCharm.getCharm());
		}else{
			UserCharmEntity uc = new UserCharmEntity();
			Date currDate = new Date();
			uc.setCreate_time(currDate);
			uc.setUpdate_time(currDate);
			uc.setUserid(userid);
			uc.setCharm(1);
			userCharmContract.insert(uc);
			userCharmCacheRedis.set(key, 1);
		}
		
		
		
	}

}
