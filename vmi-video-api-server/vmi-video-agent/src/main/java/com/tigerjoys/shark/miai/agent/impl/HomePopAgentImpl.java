package com.tigerjoys.shark.miai.agent.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.shark.miai.common.constant.CommonConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.IHomePopAgent;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;
import com.tigerjoys.shark.miai.inter.contract.IHomePopContract;
import com.tigerjoys.shark.miai.inter.contract.IHomePopLogContract;
import com.tigerjoys.shark.miai.inter.entity.HomePopEntity;
import com.tigerjoys.shark.miai.inter.entity.HomePopLogEntity;

@Service
public class HomePopAgentImpl implements IHomePopAgent {
	
	@Autowired
	@Qualifier(AgentRedisCacheConst.REDIS_USER_CACHE)
	private CacheRedis userCacheRedis;
	
	@Autowired
	private IHomePopContract homePopContract;
	
	@Autowired
	private IHomePopLogContract homePopLogContract;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<HomePopEntity> getHomePopList(int platform) throws Exception {
		String key = CommonConst.HOMEPOPKEY + platform;
		List<HomePopEntity> list = (List<HomePopEntity>)userCacheRedis.getObject(key);
		if(list == null) {
			PageModel pageModel = PageModel.getLimitModel(0, 10);//最多取10个。
			pageModel.addQuery(Restrictions.eq("status", 1));
			pageModel.addQuery(Restrictions.eq("platform", platform));
			pageModel.desc("create_time");
			
			list = homePopContract.load(pageModel);
			if(list == null) list = new ArrayList<>();
			
			//缓存3分钟
			userCacheRedis.setObject(key, ((int)Tools.MINUTE_MILLIS*3)/1000, list);
		}
		
		return list;
	}

	@Override
	public List<HomePopLogEntity> getHomePopLogList(String clientId, long popId) throws Exception {
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.eq("client_id", clientId));
		pageModel.addQuery(Restrictions.eq("popid", popId));
		
		return homePopLogContract.load(pageModel);
	}
	
	@Override
	public List<HomePopLogEntity> getHomePopLogByToday(String clientId, long popId) throws Exception {
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.eq("client_id", clientId));
		pageModel.addQuery(Restrictions.eq("popid", popId));
		pageModel.addQuery(Restrictions.ge("create_time", Tools.getDayTime()));
		return homePopLogContract.load(pageModel);
	}

	@Override
	public void addHomePopLog(String clientId, long popId, long userId) throws Exception {
		HomePopLogEntity popLog = new HomePopLogEntity();
		Date currDate = new Date();
		popLog.setClient_id(clientId);
		popLog.setPopid(popId);
		popLog.setCreate_time(currDate);
		popLog.setUpdate_time(currDate);
		popLog.setCount(1);
		popLog.setUserid(userId);
		homePopLogContract.insert(popLog);
	}

	@Override
	public void updateHomePopLog(String clientId, long popLogId, int count,Date currDate) throws Exception {
		HomePopLogEntity temp = new HomePopLogEntity();
		temp.setId(popLogId);
		temp.setCount(count+1);
		temp.setUpdate_time(currDate);
		homePopLogContract.update(temp);
	}
	
}
