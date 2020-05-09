package com.tigerjoys.shark.miai.agent.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.shark.miai.agent.IUserForegroundAgent;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;

/**
 * 用户是否处于前台服务
 * @author shiming
 *
 */
@Service
public class UserForegroundAgentImpl implements IUserForegroundAgent {

	@Autowired
	@Qualifier(AgentRedisCacheConst.REDIS_USER_ONLINE_LIST_CACHE)
	private CacheRedis userOnlineCacheRedis;
	
	@Override
	public boolean existsForegroundUser(long userid) throws Exception {
		return userOnlineCacheRedis.zexists(AgentRedisCacheConst.USER_FOREGROUND_CACHE_KEY, String.valueOf(userid));
	}

	@Override
	public void addForegroundUser(long userid) throws Exception {
		if(userid > 0) {
			long time = System.currentTimeMillis();
			String u = String.valueOf(userid);
			userOnlineCacheRedis.zadd(AgentRedisCacheConst.USER_FOREGROUND_CACHE_KEY, time, u);
		}
	}

	@Override
	public void removeForegroundUser(long userid) throws Exception {
		if(userid > 0) {
			String u = String.valueOf(userid);
			userOnlineCacheRedis.zrem(AgentRedisCacheConst.USER_FOREGROUND_CACHE_KEY, u);
		}
	}
	
	@Override
	public boolean existsForegroundAnchor(long userid) throws Exception {
		return userOnlineCacheRedis.zexists(AgentRedisCacheConst.ANCHOR_FOREGROUND_CACHE_KEY, String.valueOf(userid));
	}

	@Override
	public void addForegroundAnchor(long userid) throws Exception {
		if(userid > 0) {
			long time = System.currentTimeMillis();
			String u = String.valueOf(userid);
			userOnlineCacheRedis.zadd(AgentRedisCacheConst.ANCHOR_FOREGROUND_CACHE_KEY, time, u);
		}
	}

	@Override
	public void removeForegroundAnchor(long userid) throws Exception {
		if(userid > 0) {
			String u = String.valueOf(userid);
			userOnlineCacheRedis.zrem(AgentRedisCacheConst.ANCHOR_FOREGROUND_CACHE_KEY, u);
		}
	}

	@Override
	public void addBackgroundAnchor(long userid) throws Exception {
		if(userid > 0) {
			long time = System.currentTimeMillis();
			String u = String.valueOf(userid);
			userOnlineCacheRedis.zadd(AgentRedisCacheConst.ANCHOR_BACKGROUND_CACHE_KEY, time, u);
		}
	}

	@Override
	public void removeBackgroundAnchor(long userid) throws Exception {
		if(userid > 0) {
			String u = String.valueOf(userid);
			userOnlineCacheRedis.zrem(AgentRedisCacheConst.ANCHOR_BACKGROUND_CACHE_KEY, u);
		}
	}

	@Override
	public int getBackgroundAnchorScore(long userid) throws Exception {
		double res = userOnlineCacheRedis.zscore(AgentRedisCacheConst.ANCHOR_BACKGROUND_CACHE_KEY, userid+"");
		if (res <= 0) {
			return -1;
		} else {
			long current = System.currentTimeMillis();
			int sup = (int) ((current - res) / 1000);
			return sup;
		}
	}

}
