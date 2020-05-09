package com.tigerjoys.shark.miai.agent.impl;

import java.util.ArrayList;
import java.util.List;

import org.shark.miai.common.constant.CommonConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.IUserMypageActivityAgent;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;
import com.tigerjoys.shark.miai.agent.constant.Const;
import com.tigerjoys.shark.miai.inter.contract.IUserMypageActivityContract;
import com.tigerjoys.shark.miai.inter.entity.UserMypageActivityEntity;

/**
 * 我的页面活动标题实现类
 * @author lipeng
 *
 */
@Service
public class UserMypageActivityAgentImpl implements IUserMypageActivityAgent {
	
	
	@Autowired
	private IUserMypageActivityContract userMypageActivityContract;
	
	@Autowired
	@Qualifier(AgentRedisCacheConst.USER_MYPAGE_ACT_CACHE)
	private CacheRedis userMypageActivityCacheRedis;

	@SuppressWarnings("unchecked")
	@Override
	public List<UserMypageActivityEntity> getMypage() throws Exception {
		String key = AgentRedisCacheConst.USER_MYPAGE_ACTIVITY_KEY;
		List<UserMypageActivityEntity> myList = (List<UserMypageActivityEntity>) userMypageActivityCacheRedis.getObject(key);
		if (Tools.isNotNull(myList)) {
			return myList;
		}
		
		PageModel pageModel = new PageModel();
		pageModel.addQuery(Restrictions.eq("status", 1)); //1启动 0关闭
		pageModel.desc("create_time");
		myList = userMypageActivityContract.load(pageModel);
		
		userMypageActivityCacheRedis.setObject(key, AgentRedisCacheConst.DEFAULT_CACHE_EXPIRE, myList);
		return myList!=null?myList:null;
	}

	@Override
	public void delGetMypageKey() throws Exception {
		String key = AgentRedisCacheConst.USER_MYPAGE_ACTIVITY_KEY;
		userMypageActivityCacheRedis.del(key);
	}
	
	@Override
	public List<Long> getIndexCode(Long userid) throws Exception{
		List<UserMypageActivityEntity> list =  getMypage();
		List<Long> listCode = new ArrayList<Long>();
		if (Tools.isNotNull(list)) {
			for (UserMypageActivityEntity entity : list) {
				String key = CommonConst.myPageKey+entity.getId();
				//获得当前活动是否点击过
				if (Tools.getInt(userMypageActivityCacheRedis.hget(key, String.valueOf(userid)))<=0) {
					listCode.add(entity.getId()+Const.MYPAGE_ACTIVITY_INDEXCODE_INIT);
				}
			}
		}
		return listCode;
	}


	@Override
	public void addActivityKey(Long id) {
		String key = CommonConst.myPageKey+id;
		userMypageActivityCacheRedis.hset(CommonConst.myPageKey, String.valueOf(id), key);
	}

	
	@Override
	public void delActivityKey(Long id){
		String key = CommonConst.myPageKey+id;
		userMypageActivityCacheRedis.del(key);
		userMypageActivityCacheRedis.hdel(CommonConst.myPageKey, String.valueOf(id));
		
	}

	@Override
	public void addActivityViewLog(Long id, Long userid) {
		String key = CommonConst.myPageKey+id;
		userMypageActivityCacheRedis.hincrBy(key, String.valueOf(userid), 1);
	}


}
