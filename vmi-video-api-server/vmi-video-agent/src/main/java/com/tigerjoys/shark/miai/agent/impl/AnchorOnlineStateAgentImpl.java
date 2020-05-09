package com.tigerjoys.shark.miai.agent.impl;

import java.util.ArrayList;
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
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.IAnchorOnlineStateAgent;
import com.tigerjoys.shark.miai.agent.INeteaseAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserForegroundAgent;
import com.tigerjoys.shark.miai.agent.IUserOrdinaryOnlineListAgent;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;
import com.tigerjoys.shark.miai.agent.constant.Const;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.enums.AnchorOnlineStateEnum;
import com.tigerjoys.shark.miai.inter.contract.IAnchorOnlineContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorOnlineEntity;

import redis.clients.jedis.Tuple;

@Service
public class AnchorOnlineStateAgentImpl implements IAnchorOnlineStateAgent {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier(AgentRedisCacheConst.REDIS_USER_ONLINE_LIST_CACHE)
	private CacheRedis anchorOnlineCacheRedis;
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private IAnchorOnlineContract anchorOnlineContract;
	
	@Autowired
	private IUserForegroundAgent userForegroundAgent;
	
	@Autowired
	private IUserOrdinaryOnlineListAgent userOrdinaryOnlineListAgent;
	
	@Autowired
	private INeteaseAgent neteaseAgent;
	
	@Override
	public boolean existsOnline(long userid) throws Exception {
		//首先检查本用户是否为主播
//		UserBO bo = userAgent.findById(userid);
//		if(Tools.isNotNull(bo) && bo.isWaiter()) {
//			return anchorOnlineCacheRedis.zexists(AgentRedisCacheConst.ANCHOR_ONLINE_CACHE_KEY, String.valueOf(userid));
//		}
//		return false;
		
		//这个地方不进行判断 直接返回就可以了
		return anchorOnlineCacheRedis.zexists(AgentRedisCacheConst.ANCHOR_ONLINE_CACHE_KEY, String.valueOf(userid));
	}
	
	@Override
	public boolean existsTalking(long userid) throws Exception {
		return anchorOnlineCacheRedis.zexists(AgentRedisCacheConst.VCHAT_USER_VIDEO_ONLINE, String.valueOf(userid));
	}

	@Override
	public void refreshOnlineAnchor(long userid) throws Exception {
		//首先检查本用户是否为主播
		UserBO bo = userAgent.findById(userid);
		
		
		if(Tools.isNotNull(bo) && !bo.isWaiter()){
			userOrdinaryOnlineListAgent.addOnlineUser(userid);
		}

		if(Tools.isNotNull(bo) && bo.isWaiter()) {
			//检测对应的主播是否切换到后台超过3分钟
			int sup = userForegroundAgent.getBackgroundAnchorScore(userid);
			logger.error("主播切换到后台剩余时间值:"+sup);
			if(sup > 3*60) {
				//超过3分钟 将在线状态的主播 切换成 离线状态的主播
				AnchorOnlineEntity anchor = anchorOnlineContract.findByProperty("userid", userid);
				if(Tools.isNotNull(anchor) && anchor.getOnline() == AnchorOnlineStateEnum.online.getCode()) {
					anchor.setOnline(AnchorOnlineStateEnum.offline.getCode());
					anchorOnlineContract.update(anchor);
				}
			} else {
				//没有超过对应的时间  按照正常流程变动
				AnchorOnlineEntity anchor = anchorOnlineContract.findByProperty("userid", userid);
				if(Tools.isNotNull(anchor) && anchor.getOnline() == AnchorOnlineStateEnum.offline.getCode()) {
					anchor.setOnline(AnchorOnlineStateEnum.online.getCode());
					anchorOnlineContract.update(anchor);
				}
			}
			anchorOnlineCacheRedis.zadd(AgentRedisCacheConst.ANCHOR_ONLINE_CACHE_KEY, System.currentTimeMillis(), userid+"");
		}	
	}

	@Override
	public long removeExpireAnchorOnlineList() throws Exception {
		logger.info("开启更新主播离线状态变化任务~~~~~~~~~~~");
		long startTime = System.currentTimeMillis();
		//计算对应的超时时间   监控不到5分钟就设置为下线操作处理
		long expireTime = System.currentTimeMillis() - 3*60*1000;
		List<Long> userids = new ArrayList<>();
		//查询到过期的在聊状态
		Set<Tuple> returnSet = anchorOnlineCacheRedis.zrangeByScoreWithScores(AgentRedisCacheConst.ANCHOR_ONLINE_CACHE_KEY, 0, expireTime);
		//删除对应的过期在聊状态
		anchorOnlineCacheRedis.zremrangeByScore(AgentRedisCacheConst.ANCHOR_ONLINE_CACHE_KEY, 0, expireTime);
		if(Tools.isNotNull(returnSet)) {
			if(Tools.isNotNull(returnSet) && returnSet.size() > 0) {
				logger.info("需要删除的主播离线状态的数量为:"+returnSet.size());
				for (Tuple data : returnSet) {
					userids.add(Long.valueOf(data.getElement()));
				}
			}
		}
		//进行批量更新主播在聊状态的信息
		if(Tools.isNotNull(userids) && userids.size() > 0) {
			PageModel pageModel = PageModel.getPageModel();
			pageModel.addQuery(Restrictions.in("userid", userids));
			//此处需要处理屏蔽更新勿扰模式的用户数据
			pageModel.addQuery(Restrictions.eq("disturb", 0));
			Map<String, Object> updateStatement = new HashMap<>();
			updateStatement.put("online", 0);
			updateStatement.put("update_time", Tools.getDateTime());
			int count = anchorOnlineContract.updateByCondition(updateStatement, pageModel);
			logger.info("实际进行表更新离线操作处理的数据量为:"+count);
			if(Const.is_test) {
				for (Long userid : userids) {
					logger.info("长期没有在线处理进行下线的主播id:"+userid);
				}
			}
		}
		
		long endTime = System.currentTimeMillis();
		logger.info("更新主播离线状态变化信息完成：" + (endTime - startTime) + "毫秒");
		return 0;
	}

	@Override
	public void waiterActionOnline(long userid) {
		try {
			anchorOnlineCacheRedis.zadd(AgentRedisCacheConst.ANCHOR_ACTION_ONLINE_CACHE_KEY, System.currentTimeMillis(), userid+"");
		} catch (Exception e) {
			
		}
	}

	@Override
	public long removeExpireAnchorActionOnlineList() throws Exception {
		logger.info("开启检测主播长时间没有动作行为的任务~~~~~~~~~~~");
		long startTime = System.currentTimeMillis();
		//计算对应的超时时间   监控不到5分钟就设置为下线操作处理
		long expireTime = System.currentTimeMillis() - 30*60*1000;
		/*
		if(Const.is_test) {
			expireTime = System.currentTimeMillis() - 5*60*1000;
		}
		*/
		List<Long> userids = new ArrayList<>();
		//查询到过期的在聊状态
		Set<Tuple> returnSet = anchorOnlineCacheRedis.zrangeByScoreWithScores(AgentRedisCacheConst.ANCHOR_ACTION_ONLINE_CACHE_KEY, 0, expireTime);
		//删除对应的过期在聊状态
		anchorOnlineCacheRedis.zremrangeByScore(AgentRedisCacheConst.ANCHOR_ACTION_ONLINE_CACHE_KEY, 0, expireTime);
		if(Tools.isNotNull(returnSet)) {
			if(Tools.isNotNull(returnSet) && returnSet.size() > 0) {
				for (Tuple data : returnSet) {
					userids.add(Long.valueOf(data.getElement()));
				}
			}
		}
		//进行批量更新主播在聊状态的信息
		if(Tools.isNotNull(userids) && userids.size() > 0) {
			PageModel pageModel = PageModel.getPageModel();
			pageModel.addQuery(Restrictions.in("userid", userids));
			pageModel.addQuery(Restrictions.eq("online", 3));
			List<AnchorOnlineEntity> anchors = anchorOnlineContract.load(pageModel);
			if(Tools.isNotNull(anchors)) {
				//修改主播的状态为 勿扰
				for (AnchorOnlineEntity anchor : anchors) {
					Map<String, Object> updateStatement = new HashMap<>();
					updateStatement.put("online", 1);
					updateStatement.put("disturb", 1);
					updateStatement.put("update_time", Tools.getDateTime());
					anchorOnlineContract.updateByProperty(updateStatement, "userid", anchor.getUserid());
					//处理发送提示消息
					if(Const.is_test) {
						neteaseAgent.pushOneMessage(131879189602173184L, anchor.getUserid(), "您已经半小时没有活跃了，自动变为勿扰状态，请到设置中心关闭勿扰。", true);
					} else {
						neteaseAgent.pushOneMessage(65418719477628672L, anchor.getUserid(), "您已经半小时没有活跃了，自动变为勿扰状态，请到设置中心关闭勿扰。", true);
					}
				}
			}
		}
		long endTime = System.currentTimeMillis();
		logger.info("更新检测主播长时间没有动作行为的完成：" + (endTime - startTime) + "毫秒");
		return 0;
	}

}
