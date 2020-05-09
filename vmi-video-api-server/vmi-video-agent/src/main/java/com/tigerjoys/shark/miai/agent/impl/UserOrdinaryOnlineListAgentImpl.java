package com.tigerjoys.shark.miai.agent.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.nbs.web.context.RequestHeader;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserOnlineListAgent;
import com.tigerjoys.shark.miai.agent.IUserOrdinaryOnlineListAgent;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.inter.contract.IAnchorOnlineContract;
import com.tigerjoys.shark.miai.inter.contract.IAppControllContract;

import redis.clients.jedis.Tuple;


@Service
public class UserOrdinaryOnlineListAgentImpl implements IUserOrdinaryOnlineListAgent {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier(AgentRedisCacheConst.REDIS_PUBLIC_CACHE)
	private CacheRedis cacheRedis;
	
	@Autowired
	private IUserOnlineListAgent userOnlineListAgent;
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private IAnchorOnlineContract anchorOnlineContract;
	
	@Autowired
	private IAppControllContract appControllContract;
	
	
	
	private final long userOnlineTimeOut =  1000*60*3;
	private final int anchorDialingTimeOut =  60*60*24;

	@Override
	public void addOnlineUser(long userid) throws Exception {
	
		try {
			// 首先检查本用户是否为主播
			UserBO bo = userAgent.findById(userid);
			RequestHeader header = RequestUtils.getCurrent().getHeader();
			if (Tools.isNotNull(bo) && !bo.isWaiter() && Tools.isNotNull(header)) {
				boolean falg = true;
				int versioncode = header.getVersioncode();
				String channel = header.getChannel();
				PageModel pageModel = PageModel.getPageModel();
				pageModel.addQuery(Restrictions.eq("channel", channel));
				pageModel.addQuery(Restrictions.eq("version", versioncode));
				pageModel.addQuery(Restrictions.eq("state", 1));
				long count = appControllContract.count(pageModel);
				if(count > 0) {
					falg =  false;
				}
				
				/*
				if (header.getChannel().equals("Huawei_yoyo3")) {
					// 处理特殊的区域 北京 天津 广东省
					if (Tools.isNotNull(bo)) {
						if (bo.getProvinceid() == 3696 || bo.getProvinceid() == 3958 || bo.getProvinceid() == 3593)
							falg = false;
					}
				}
				*/
				if ( falg){
					cacheRedis.zadd(AgentRedisCacheConst.VCHAT_USER_ORDINARY_ONLINE, System.currentTimeMillis(), "" + userid);
				}
			}
		} catch (Exception e) {
			logger.info("禁用大V助手", e);
		}
		
	}

	@Override
	public Set<String> getOnlineUser(int count) throws Exception {
		return cacheRedis.zrevrangeByScore(AgentRedisCacheConst.VCHAT_USER_ORDINARY_ONLINE, System.currentTimeMillis(), 0, 0, count);
	}
	

	@Override
	public Set<String> getOnlineUser(int score, int count) throws Exception {
		return cacheRedis.zrevrangeByScore(AgentRedisCacheConst.VCHAT_USER_ORDINARY_ONLINE, score, 0, 0, count);
	}

	
	@Override
	public Set<Tuple> getOnlineUserWithScores(double score, int count) throws Exception {
		return cacheRedis.zrevrangeByScoreWithScores(AgentRedisCacheConst.VCHAT_USER_ORDINARY_ONLINE, score, 0, 0, count);
	}

	
	@Override
	public void removeOnlineUser() throws Exception {
		cacheRedis.zremrangeByScore(AgentRedisCacheConst.VCHAT_USER_ORDINARY_ONLINE, 0, System.currentTimeMillis()-userOnlineTimeOut);
		
	}
	
	@Override
	public void addAnchorDialingUser(long anchorId,long userid) throws Exception {
		String key = AgentRedisCacheConst.VCHAT_ANCHOR_DIALING_TODAY+Tools.getFormatDate(new Date(), "yyyyMMdd")+"_"+anchorId;
		cacheRedis.sadd(key, ""+userid);
		long anchoRexpire =  cacheRedis.ttl(key);
		if(anchoRexpire == -1){
			cacheRedis.expire(key, anchorDialingTimeOut);
		}
	}
	
	@Override
	public Set<String> getAnchorDialingUser(long anchorId) throws Exception {
		String key = AgentRedisCacheConst.VCHAT_ANCHOR_DIALING_TODAY+Tools.getFormatDate(new Date(), "yyyyMMdd")+"_"+anchorId;
		return cacheRedis.smembers(key);
	}
	
	@Override
	public long getAnchorDialingUserNum(long anchorId) throws Exception {
		String key = AgentRedisCacheConst.VCHAT_ANCHOR_DIALING_TODAY+Tools.getFormatDate(new Date(), "yyyyMMdd")+"_"+anchorId;
		return cacheRedis.scard(key);
	}
	
	@Override
	public List<String> getAnchorDialingRandomUser(long anchorId,int count) throws Exception {
		String key = AgentRedisCacheConst.VCHAT_ANCHOR_DIALING_TODAY+Tools.getFormatDate(new Date(), "yyyyMMdd")+"_"+anchorId;
		return cacheRedis.srandmember(key,count);
	}

	@Override
	public void addAnchorDialingRecvUser(long anchorId, long userid) throws Exception {
		String key = AgentRedisCacheConst.VCHAT_ANCHOR_DIALING_RECV_TODAY+Tools.getFormatDate(new Date(), "yyyyMMdd")+"_"+anchorId;
		cacheRedis.sadd(key, ""+userid);
		long anchoRexpire =  cacheRedis.ttl(key);
		if(anchoRexpire == -1){
			cacheRedis.expire(key, anchorDialingTimeOut);
		}
	}

	@Override
	public long getAnchorDialingRecvUserTotal(long anchorId) throws Exception {
		String key = AgentRedisCacheConst.VCHAT_ANCHOR_DIALING_RECV_TODAY+Tools.getFormatDate(new Date(), "yyyyMMdd")+"_"+anchorId;
		return cacheRedis.scard(key);
	}
	
	
	
	@Override
	public void setAnchorForbidDialFlag(long userId,int expire) throws Exception {
		String key = AgentRedisCacheConst.Anchor_Forbid_Dial_REDFLOWER+userId;
		cacheRedis.set(key, expire);
		cacheRedis.expire(key, expire);
	}
	
	@Override
	public boolean getAnchorForbidDialFlag(long userId) throws Exception {
		String key = AgentRedisCacheConst.Anchor_Forbid_Dial_REDFLOWER+userId;
		return cacheRedis.exists(key);
	}

}
