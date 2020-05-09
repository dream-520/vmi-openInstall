package com.tigerjoys.shark.miai.agent.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.common.utils.sequence.IdGenerater;
import com.tigerjoys.shark.miai.agent.IUserOnlineListAgent;
import com.tigerjoys.shark.miai.agent.IUserScvcAgent;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;
import com.tigerjoys.shark.miai.agent.enums.ScvcAwardCategoryEnum;
import com.tigerjoys.shark.miai.inter.contract.IUserOnlineLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserOnlineLogEntity;

import redis.clients.jedis.Tuple;

/**
 * 用户在线列表服务代理接口实现类
 * @author chengang
 *
 */
@Service
public class UserOnlineListAgentImpl implements IUserOnlineListAgent {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IUserScvcAgent userScvcAgent;
	
	@Autowired
	@Qualifier(AgentRedisCacheConst.REDIS_USER_ONLINE_LIST_CACHE)
	private CacheRedis userOnlineCacheRedis;
	
	@Autowired
	private IUserOnlineLogContract userOnlineLogContract;
	
	@Override
	public boolean existsOnline(long userid) throws Exception {
		return userOnlineCacheRedis.zexists(AgentRedisCacheConst.USER_ONLINE_CACHE_KEY, String.valueOf(userid));
	}

	@Override
	public void addOnlineUser(long userid , String clientId) throws Exception {
		long time = System.currentTimeMillis();
		String u = String.valueOf(userid);
		String t = String.valueOf(time);
		
		Map<String , String> dataMap = new HashMap<>();
		dataMap.put(AgentRedisCacheConst.USER_ONLINE_CLIENT_KEY, clientId);
		dataMap.put(AgentRedisCacheConst.USER_ONLINE_USERID_KEY, u);
		dataMap.put(AgentRedisCacheConst.USER_ONLINE_LOGINTIME_KEY, t);
		dataMap.put(AgentRedisCacheConst.USER_ONLIN_LASTTIME_KEY, t);
		
		//添加用户在线列表
		userOnlineCacheRedis.transaction(tx -> {
			String detailKey = AgentRedisCacheConst.USER_ONLINE_DETAIL_PREFIX + userid;
			//将用户详情存储到redis中
			tx.hmset(detailKey , dataMap);
			tx.expire(detailKey, AgentRedisCacheConst.USER_ONLINE_EXPIRE_TIME_SEC);
			//存放到zset中，socre为当前添加时间，将来做过期用
			tx.zadd(AgentRedisCacheConst.USER_ONLINE_CACHE_KEY, time, u);
			
			//存储信息最后在线时间[PS:删除在线信息的时候，不要删除用户的登录信息列表，否则可能在刷新的时候会出现异常，让其自动过期掉即可]
			String heartKey = AgentRedisCacheConst.USER_ONLINE_HEART_PREFIX + userid;
			
			//更新登录时间和刷新时间和
			tx.hsetnx(heartKey, AgentRedisCacheConst.USER_ONLINE_LOGINTIME_KEY, t);
			tx.hset(heartKey, AgentRedisCacheConst.USER_ONLINE_CLIENT_KEY, clientId);
			tx.hset(heartKey, AgentRedisCacheConst.USER_ONLINE_USERID_KEY, u);
			tx.hset(heartKey, AgentRedisCacheConst.USER_ONLIN_LASTTIME_KEY, t);
			tx.expire(heartKey, AgentRedisCacheConst.USER_ONLINE_HEART_EXPIRE_TIME_SEC);
			
			//将在线信息存放到zset中
			tx.zadd(AgentRedisCacheConst.USER_ONLINE_HEART_LIST_KEY, time, u);
		});
	}
	
	@Override
	public String getOnlineUserClientId(long userid) throws Exception {
		return userOnlineCacheRedis.hget(AgentRedisCacheConst.USER_ONLINE_DETAIL_PREFIX + userid, AgentRedisCacheConst.USER_ONLINE_CLIENT_KEY);
	}

	@Override
	public void refreshOnlineUser(long userid , String clientId) throws Exception {
		long time = System.currentTimeMillis();
		String uid = String.valueOf(userid);
		String t = String.valueOf(time);
		
		userOnlineCacheRedis.pipelined(pipeline -> {
			//刷新详情过期缓存
			pipeline.expire(AgentRedisCacheConst.USER_ONLINE_DETAIL_PREFIX + userid, AgentRedisCacheConst.USER_ONLINE_EXPIRE_TIME_SEC);
			//刷新过期列表缓存
			pipeline.zadd(AgentRedisCacheConst.USER_ONLINE_CACHE_KEY, System.currentTimeMillis(), uid);
			
			//刷新最后在线时间列表
			String heartKey = AgentRedisCacheConst.USER_ONLINE_HEART_PREFIX + userid;
			
			//更新登录时间和刷新时间
			pipeline.hsetnx(heartKey, AgentRedisCacheConst.USER_ONLINE_LOGINTIME_KEY, t);
			pipeline.hset(heartKey, AgentRedisCacheConst.USER_ONLIN_LASTTIME_KEY, t);
			pipeline.hset(heartKey, AgentRedisCacheConst.USER_ONLINE_CLIENT_KEY, clientId);
			pipeline.hset(heartKey, AgentRedisCacheConst.USER_ONLINE_USERID_KEY, uid);
			pipeline.expire(heartKey, AgentRedisCacheConst.USER_ONLINE_HEART_EXPIRE_TIME_SEC);
			
			//将在线信息存放到zset中
			pipeline.zadd(AgentRedisCacheConst.USER_ONLINE_HEART_LIST_KEY, time, uid);
		});
	}

	@Override
	public void removeOnlineUser(long userid) throws Exception {
		String u = String.valueOf(userid);
		userOnlineCacheRedis.transaction(tx -> {
			//删除详情数据
			tx.del(AgentRedisCacheConst.USER_ONLINE_DETAIL_PREFIX + userid);
			//删除在线列表中的数据
			tx.zrem(AgentRedisCacheConst.USER_ONLINE_CACHE_KEY, u);
		});
	}
	
	@Override
	public long removeExpireUserOnlineList() throws Exception {
		logger.info("删除过期在线列表...");
		
		//将5分钟前登录的用户删除掉
		long expireTime = System.currentTimeMillis() - AgentRedisCacheConst.USER_ONLINE_EXPIRE_TIME_SEC*1000;
		long c = userOnlineCacheRedis.zremrangeByScore(AgentRedisCacheConst.USER_ONLINE_CACHE_KEY, 0, expireTime);
		
		logger.info("删除了过期用户列表数："+c+"个!");
		
		return c;
	}
	
	@Override
	public void saveUserOnlineTimeLog() throws Exception {
		logger.info("开始持久化用户的在线时长...");
		
		int pagesize = 200 , c = 0 , actual = 0;//一次性获取200个
		//查询区间
		long start = 0 , end = System.currentTimeMillis();
		while(true) {
			Set<Tuple> returnSet = userOnlineCacheRedis.zrangeByScoreWithScores(AgentRedisCacheConst.USER_ONLINE_HEART_LIST_KEY, start, end, 0, pagesize);
			if(Tools.isNotNull(returnSet)) {
				List<String> uidList = new ArrayList<>();
				for(Tuple t : returnSet) {
					uidList.add(t.getElement());
					start = (long)t.getScore();
					
					c++;
				}
				
				//此处批量获取用户的信息
				List<Object> returnList = userOnlineCacheRedis.pipelinedAndReturnAll(pipelined -> {
					uidList.stream().forEach(userid -> pipelined.hgetAll(AgentRedisCacheConst.USER_ONLINE_HEART_PREFIX + userid));
				});
				
				//此处批量删除掉用户的在线时长列表
				userOnlineCacheRedis.zremrangeByScore(AgentRedisCacheConst.USER_ONLINE_HEART_LIST_KEY, 0, start);
				
				if(Tools.isNotNull(returnList)) {
					logger.info("获取用户在线记录 , key size=" + returnList.size()+"，获取到了用户的在线详情!");
					//添加在线日志
					actual += addUserOnlineLogs(returnList);
				}
			} else {
				break;
			}
		}
		
		logger.info("本次需要持久化用户在线时长的数量："+c+",实际插入数据：" + actual);
	}
	
	/**
	 * 添加用户的在线列表记录
	 * @param returnList - List<Object>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private int addUserOnlineLogs(List<Object> returnList) throws Exception {
		if(Tools.isNull(returnList)) return 0;
		
		List<UserOnlineLogEntity> addList = new ArrayList<>(returnList.size());
		for(Object o : returnList) {
			if(o instanceof Map) {
				Map<String , String> userLogMap = (Map<String , String>)o;
				
				String clientId = userLogMap.get(AgentRedisCacheConst.USER_ONLINE_CLIENT_KEY);
				String uid = userLogMap.get(AgentRedisCacheConst.USER_ONLINE_USERID_KEY);
				String loginTime = userLogMap.get(AgentRedisCacheConst.USER_ONLINE_LOGINTIME_KEY);
				String refreshTime = userLogMap.get(AgentRedisCacheConst.USER_ONLIN_LASTTIME_KEY);
				
				if(Tools.isNotNull(clientId) && Tools.isNumber(uid) && Tools.isNumber(loginTime) && Tools.isNumber(refreshTime)) {
					Date loginDate = new Date(Long.parseLong(loginTime));
					Date refreshDate = new Date(Long.parseLong(refreshTime));
					long times = refreshDate.getTime()-loginDate.getTime();
					if(times < 0) times = 0;
					times = times/1000;//记录秒数
					
					UserOnlineLogEntity log = new UserOnlineLogEntity();
					log.setClientId(clientId);
					log.setCreate_time(new Date());
					log.setLogin_time(loginDate);
					log.setRefresh_time(refreshDate);
					log.setUserid(Long.parseLong(uid));
					log.setTimes((int)times);
					addList.add(log);
					try {
						// 在线时长超过10分钟，奖励scvc
						if (600 <= userOnlineCacheRedis.hincrBy(AgentRedisCacheConst.USER_ONLIN_SCVC_AWARD_KEY, uid, times)) {
							userScvcAgent.gainScvcVia(log.getUserid(), ScvcAwardCategoryEnum.ONLINE, String.valueOf(IdGenerater.generateId()), ScvcAwardCategoryEnum.ONLINE.getTitle());
							// 保证每天只送一次
							userOnlineCacheRedis.hincrBy(AgentRedisCacheConst.USER_ONLIN_SCVC_AWARD_KEY, uid, -Tools.DAY_MILLIS / 1000);
						}
					} catch (Exception e) {
						logger.info("累计时长，并奖励scvc出错！", e);
					}
				}
			}
		}
		
		//每100个插入批量插入一次
		int batchLimit = 100;
		List<UserOnlineLogEntity> batchList = new ArrayList<>(batchLimit);
		List<String> redisKeyList = new ArrayList<>(batchLimit);
		for(int i=0,size=addList.size();i<size;i++) {
			UserOnlineLogEntity log = addList.get(i);
			
			batchList.add(log);
			redisKeyList.add(AgentRedisCacheConst.USER_ONLINE_HEART_PREFIX + log.getUserid());
			
			if(i>0 && i%100 == 0) {
				//批量插入数据
				userOnlineLogContract.insertAll(batchList);
				//批量删除redis中的详情数据
				userOnlineCacheRedis.del(redisKeyList);
				
				batchList.clear();
				redisKeyList.clear();
			}
		}
		
		//如果执行完毕后，batchList还有，则一次性再插入
		if(Tools.isNotNull(batchList)) {
			userOnlineLogContract.insertAll(batchList);
			//批量删除redis中的详情数据
			userOnlineCacheRedis.del(redisKeyList);
		}
		
		return addList.size();
	}

}
