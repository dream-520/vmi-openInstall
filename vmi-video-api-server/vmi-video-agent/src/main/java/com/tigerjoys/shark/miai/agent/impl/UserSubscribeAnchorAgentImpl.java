package com.tigerjoys.shark.miai.agent.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.ExecutorServiceHelper;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.common.utils.sequence.IdGenerater;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.IUserDiamondAgent;
import com.tigerjoys.shark.miai.agent.IUserSubscribeAnchorAgent;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;
import com.tigerjoys.shark.miai.agent.enums.UserDiamondAccountLogTypeEnum;
import com.tigerjoys.shark.miai.inter.contract.IAnchorSubscribeContract;
import com.tigerjoys.shark.miai.inter.contract.IAnchorSuperSubscribeDetailContract;
import com.tigerjoys.shark.miai.inter.contract.IAnchorSuperSubscribeUserContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorSubscribeEntity;
import com.tigerjoys.shark.miai.inter.entity.AnchorSuperSubscribeDetailEntity;
import com.tigerjoys.shark.miai.inter.entity.AnchorSuperSubscribeUserEntity;

/**
 * 预约业务
 * @author shiming
 */
@Service
public class UserSubscribeAnchorAgentImpl implements IUserSubscribeAnchorAgent {

	@Autowired
	private IAnchorSubscribeContract anchorSubscribeContract;
	
	@Autowired
	private IAnchorSuperSubscribeUserContract anchorSuperSubscribeUserContract;
	
	@Autowired
	private IAnchorSuperSubscribeDetailContract anchorSuperSubscribeDetailContract;
	
	@Autowired
	private IUserDiamondAgent userDiamondAgent;
	
	@Autowired
	@Qualifier(AgentRedisCacheConst.SYS_CONFIG_CACHE)
	private CacheRedis cacheRedis;
	
	/*
	 * 单独一对一预约时的处理业务方法
	@Override
	public boolean checkSubscribe(long userid, long anchorid) throws Exception {
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.eq("userid", userid));
		pageModel.addQuery(Restrictions.eq("anchorid", anchorid));
		pageModel.addQuery(Restrictions.gt("end_time", new Date()));
		long count = anchorSubscribeContract.count(pageModel);
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public long dailCheckSubscribe(long userid, long anchorid, int type) {
		long diamond = 0;
		try {
			String key = AgentRedisCacheConst.REDIS_USER_SUBSCRIBE_ANCHOR_FLAG_PREFIX + type + "_" + userid + "_" + anchorid;
			String data = cacheRedis.get(key);
			if(Tools.isNotNull(data))
				diamond = Long.parseLong(data);
		} catch (Exception e) {
			
		}
		return diamond;
	}

	@Override
	public void checkReturnSubscribeDiamond(long userid, long anchorid, int type) {
		try {
			String key = AgentRedisCacheConst.REDIS_USER_SUBSCRIBE_ANCHOR_FLAG_PREFIX + type + "_" + userid + "_" + anchorid;
			long count = cacheRedis.del(key);
			//检测是否有对应的预约记录被删除
			if(count > 0) {
				//设置预约成功并返回钻石业务
				PageModel pageModel = PageModel.getPageModel();
				pageModel.addQuery(Restrictions.eq("userid", userid));
				pageModel.addQuery(Restrictions.eq("anchorid", anchorid));
				pageModel.addQuery(Restrictions.eq("type", type));
				pageModel.addQuery(Restrictions.ge("end_time", new Date()));
				List<AnchorSubscribeEntity> subscribes = anchorSubscribeContract.load(pageModel);
				if(Tools.isNotNull(subscribes) && subscribes.size() > 0) {
					AnchorSubscribeEntity subscribe = subscribes.get(0);
					if(Tools.isNotNull(subscribe)) {
						//设置预约成功类型
						subscribe.setState(2);
						anchorSubscribeContract.update(subscribe);
						
						//返回对应的钻石值
						if(subscribe.getDiamond() > 0)
							userDiamondAgent.changeDiamondAccount(subscribe.getUserid(), (long)subscribe.getDiamond(), (long)0, UserDiamondAccountLogTypeEnum.user_subscribe_anchor_return.getCode(), 1, null, IdGenerater.generateId()+"", UserDiamondAccountLogTypeEnum.user_subscribe_anchor_return.getDesc());
					}
				}
			}
		} catch (Exception e) {
			
		}
	}
	*/
	
	@Override
	public boolean checkSubscribe(long userid, long anchorid) throws Exception {
		//默认是有预约关系的
		boolean subscribe = true;
		try {
			String key = AgentRedisCacheConst.REDIS_USER_SUBSCRIBE_ANCHOR_FLAG_PREFIX + userid + "_" + anchorid;
			String data = cacheRedis.get(key);
			if(Tools.isNull(data)) {
				subscribe = false;
			}
		} catch (Exception e) {
			
		}
		return subscribe;
	}

	@Override
	public long dailCheckSubscribe(long userid, long anchorid, int type) {
		long diamond = 0;
		try {
			String key = AgentRedisCacheConst.REDIS_USER_SUBSCRIBE_ANCHOR_FLAG_PREFIX + userid + "_" + anchorid;
			String data = cacheRedis.get(key);
			if(Tools.isNotNull(data)) {
				JSONObject json = JsonHelper.toJsonObject(data);
				if(Tools.isNotNull(json)) {
					//检测对应的类型是否匹配
					Integer Type = json.getInteger("type");
					if(Tools.isNotNull(Type) && Type.intValue() == type) {
						if(Tools.isNotNull(json.getInteger("diamond")))
							diamond = json.getIntValue("diamond");
					}
				}
			}
		} catch (Exception e) {
			
		}
		return diamond;
	}

	@Override
	public void checkReturnSubscribeDiamond(long userid, long anchorid, int type) {
		try {
			String key = AgentRedisCacheConst.REDIS_USER_SUBSCRIBE_ANCHOR_FLAG_PREFIX + userid + "_" + anchorid;
			String data = cacheRedis.get(key);
			if(Tools.isNotNull(data)) {
				//根据类型来处理预约
				JSONObject json = JsonHelper.toJsonObject(data);
				if(Tools.isNotNull(json)) {
					//检测对应的类型是否匹配
					Integer Type = json.getInteger("type");
					if(Tools.isNotNull(Type) && Type.intValue() == type) {
						//进一步进行删除操作处理
						long count = cacheRedis.del(key);
						//检测是否有对应的预约记录被删除
						if(count > 0) {
							Integer Flag = json.getInteger("flag");
							Integer Diamond = json.getInteger("diamond");
							if(Tools.isNotNull(Flag) && Tools.isNotNull(Diamond)) {
								if(Flag.intValue() == 0) {
									//设置一对一预约成功并返回钻石业务
									Long id = json.getLong("id");
									if(Tools.isNotNull(id)) {
										AnchorSubscribeEntity subscribe = anchorSubscribeContract.findById(id);
										if(Tools.isNotNull(subscribe)) {
											//设置预约成功类型
											subscribe.setState(2);
											anchorSubscribeContract.update(subscribe);
											//返回对应的钻石值
											if(subscribe.getDiamond() > 0)
												userDiamondAgent.changeDiamondAccount(subscribe.getUserid(), (long)subscribe.getDiamond(), (long)0, UserDiamondAccountLogTypeEnum.user_subscribe_anchor_return.getCode(), 1, null, IdGenerater.generateId()+"", UserDiamondAccountLogTypeEnum.user_subscribe_anchor_return.getDesc());
										}
									}
								} else if(Flag.intValue() == 1){
									//设置群预约成功并返回对应的预约金
									Long id = json.getLong("id");
									if(Tools.isNotNull(id)) {
										AnchorSuperSubscribeUserEntity superSubscribe = anchorSuperSubscribeUserContract.findById(id);
										if(Tools.isNotNull(superSubscribe)) {
											//设置预约成功类型
											superSubscribe.setState(2);
											anchorSuperSubscribeUserContract.update(superSubscribe);
											//返回对应的钻石值
											if(superSubscribe.getDiamond() > 0) {
												userDiamondAgent.changeDiamondAccount(superSubscribe.getUserid(), (long)superSubscribe.getDiamond(), (long)0, UserDiamondAccountLogTypeEnum.user_super_subscribe_anchor_return.getCode(), 1, null, IdGenerater.generateId()+"", UserDiamondAccountLogTypeEnum.user_super_subscribe_anchor_return.getDesc());
												//启动后续的清除对应的预约关系的操作处理
												ExecutorServiceHelper.executor.execute(new UserSuperSubscribeAnchorThread(userid, anchorid, id));
											}
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			
		}
	}
	
	/**
	 * 取消一键预约建立的预约关系
	 * @author shiming
	 *
	 */
	private class UserSuperSubscribeAnchorThread implements Runnable {

		private long userid;
		
		private long anchorid;
		
		private long id;
		
		public UserSuperSubscribeAnchorThread(long userid, long anchorid, long id) {
			this.userid = userid;
			this.anchorid = anchorid;
			this.id = id;
		}
		
		@Override
		public void run() {
			try {
				//解除对应的预约关系
				String key = AgentRedisCacheConst.REDIS_USER_SUBSCRIBE_FLAG_PREFIX + userid;
				cacheRedis.delete(key);
				PageModel pageModel = PageModel.getPageModel();
				pageModel.addQuery(Restrictions.eq("userid", userid));
				pageModel.addQuery(Restrictions.eq("subscribe", id));
				List<AnchorSuperSubscribeDetailEntity> superSubscribes = anchorSuperSubscribeDetailContract.load(pageModel);
				for (AnchorSuperSubscribeDetailEntity superSubscribe : superSubscribes) {
					String keys = AgentRedisCacheConst.REDIS_USER_SUBSCRIBE_ANCHOR_FLAG_PREFIX + userid + "_" + superSubscribe.getAnchorid();
					cacheRedis.delete(keys);
					if(superSubscribe.getAnchorid().longValue() == anchorid) {
						superSubscribe.setState(2);
					} else {
						superSubscribe.setState(1);
					}
					superSubscribe.setUpdate_time(new Date());
					anchorSuperSubscribeDetailContract.update(superSubscribe);
				}
			} catch (Exception e) {
				
			}
		}
	}

}
