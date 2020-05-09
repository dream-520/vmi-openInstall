package com.tigerjoys.shark.miai.agent.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.ExecutorServiceHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.INewPushAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserDiamondAgent;
import com.tigerjoys.shark.miai.agent.IUserForegroundAgent;
import com.tigerjoys.shark.miai.agent.IUserWakeUpMessageAgent;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;
import com.tigerjoys.shark.miai.agent.dto.PushParamDto;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.enums.NewPushAppTagEnum;
import com.tigerjoys.shark.miai.agent.enums.PushContentTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.PushTypeEnum;
import com.tigerjoys.shark.miai.agent.utils.PushHelper;
import com.tigerjoys.shark.miai.inter.contract.IAnchorOnlineContract;
import com.tigerjoys.shark.miai.inter.contract.IAnchorRecvUserContract;
import com.tigerjoys.shark.miai.inter.contract.IUserFriendsContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorOnlineEntity;
import com.tigerjoys.shark.miai.inter.entity.AnchorRecvUserEntity;
import com.tigerjoys.shark.miai.inter.entity.UserFriendsEntity;

@Service
public class UserWakeUpMessageAgentImpl implements IUserWakeUpMessageAgent {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier(AgentRedisCacheConst.REDIS_USER_ONLINE_LIST_CACHE)
	private CacheRedis userOnlineCacheRedis;
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private IUserDiamondAgent userDiamondAgent;
	
	@Autowired
	private INewPushAgent newPushAgent;
	
	@Autowired
	private IAnchorRecvUserContract anchorRecvUserContract;
	
	@Autowired
	private IUserForegroundAgent userForegroundAgent;
	
	@Autowired
	private IUserFriendsContract userFriendsContract;
	
	@Autowired
	private IAnchorOnlineContract anchorOnlineContract;
	
	@Override
	public void wakeUpMessage(long userid) {
		if(userid > 0) {
			try {
				UserBO bo = userAgent.findById(userid);
				if(Tools.isNotNull(bo)) {
					//根据用户类型来进行区别对待
					if(bo.isWaiter()) {
						//主播登录上线
						logger.error("发送主播上线推送消息  启动的用户id为:"+userid);
						ExecutorServiceHelper.executor.execute(new UserPushMessageThread(bo.getNickname(), userid));
					} else {
						//用户登录上线
						//检测对应的用户是否有对应的余额   余额充足 发起对应的发送通知栏的业务
						long  diamond = userDiamondAgent.getDiamondBalance(userid);
						logger.error("发送用户上线推送消息  启动的用户id为:"+userid + "  用户对应的钻石值为:"+ diamond);
						if(diamond > 200) {
							//触发对应的线程进行业务处理
							ExecutorServiceHelper.executor.execute(new AnchorPushMessageThread(bo.getNickname(), userid));
						}
					}
				}
			} catch (Exception e) {
				logger.error("发送对应的唤醒消息出现了错误  启动的用户id为:"+userid);
			}
		}
	}
	
	public int getRandomNumber(int min, int max) {
		Random random = new Random();  
		int randomNumber =  random.nextInt(max)%(max-min+1) + min; 
		return randomNumber;
	}
	
	private class AnchorPushMessageThread implements Runnable {

		private String nickname;
		
		private long userid;
		
		public AnchorPushMessageThread(String nickname, long userid) {
			this.nickname = nickname;
			this.userid = userid;
		}
		
		@Override
		public void run() {
			//根据对应的用户id与他聊过的主播
			try {
				PageModel pageModel = PageModel.getPageModel();
				pageModel.addQuery(Restrictions.eq("userid", userid));
				pageModel.addQuery(Restrictions.eq("video", 1));
				List<AnchorRecvUserEntity> anchors = anchorRecvUserContract.load(pageModel);
				if(Tools.isNotNull(anchors) && anchors.size() > 0) {
					logger.error("当前获取的需要发送的主播数量为:"+anchors.size());
					//根据对应的随机值 来确定是否发送通知栏消息
					for (AnchorRecvUserEntity anchor : anchors) {
						//添加一个在勿扰模式的时候不给主播发送消息的处理
						AnchorOnlineEntity an = anchorOnlineContract.findByProperty("userid", anchor.getAnchorId());
						if(Tools.isNotNull(an) && an.getOnline() != 1) {
							//检测是否处于后端或者已经被杀死了
							boolean ex = userForegroundAgent.existsForegroundAnchor(anchor.getAnchorId());
							if(!ex) {
								int rand = getRandomNumber(0,9);
								logger.error("当前用户处于非前端  随机到的数量值为:"+rand);
								//即有30%的概率发送给对应的主播
								if(rand >= 6) {
									//触发对应的发送通知栏消息的业务
									UserBO userBO = userAgent.findById(anchor.getAnchorId());
									if(Tools.isNotNull(userBO) && Tools.isNotNull(userBO.getClientid())) {
										PushParamDto paramC = PushHelper.getPushParamDto(userBO, PushTypeEnum.type_goto_app, PushContentTypeEnum.necessary, NewPushAppTagEnum.user_wakeup_anchor);
										paramC.setTitle("曾经和你嗨聊的"+nickname+"上线了！");
										paramC.setContent("赶紧找他聊天吧！");
										paramC.setUserId(userBO.getUserid());
										//设置聊过的参数
										paramC.setExtend("1");
										newPushAgent.pushMessageToSingleUser(paramC);
									}
								}
							}
						}
					}
				}
			} catch (Exception e) {
				logger.error("给对应的主播发送唤醒消息出现了错误  启动的用户id为:"+userid);
			}
		}
	}

	private class UserPushMessageThread implements Runnable {
		
		private String nickname;
		
		private long userid;
		
		public UserPushMessageThread(String nickname, long userid) {
			this.nickname = nickname;
			this.userid = userid;
		}
		
		public boolean SendMessage(long userid) {
			//获取当前时段对应的 本用户的发送数量值
			Calendar cal = Calendar.getInstance();
			int hour = cal.get(Calendar.HOUR_OF_DAY);
			//发送标记
			boolean flag = false;
			if(hour < 20) {
				//检测是否已经发送了 3条消息
				String key = AgentRedisCacheConst.USER_SEND_PUSH_MESSAGE_CACHE_KEY+Tools.getDate()+"_"+ 0;
				long num = userOnlineCacheRedis.hincrBy(key, userid +"", 1);
				userOnlineCacheRedis.expire(key, AgentRedisCacheConst.DEFAULT_CACHE_EXPIRE_DAY);
				logger.error("当前用户对应的id:"+userid + "  已经发送的数量为:"+ num +" 当前对应的时间key值为"+key);
				if(num <= 3) {
					flag = true;
				}
			} else {
				//检测是否已经发送了 2条消息
				String key = AgentRedisCacheConst.USER_SEND_PUSH_MESSAGE_CACHE_KEY+Tools.getDate()+"_"+ 20;
				long num = userOnlineCacheRedis.hincrBy(key, userid +"", 1);
				userOnlineCacheRedis.expire(key, AgentRedisCacheConst.DEFAULT_CACHE_EXPIRE_DAY);
				logger.error("当前用户对应的id:"+userid + "  已经发送的数量为:"+ num +" 当前对应的时间key值为"+key);
				if(num <= 2) {
					flag = true;
				}
			}
			return flag;
		}
		
		@Override
		public void run() {
			//根据对应的主播id与他聊过的用户
			try {
				Map<Long, Long> userids = new HashMap<>();
				PageModel pageModel = PageModel.getPageModel();
				pageModel.addQuery(Restrictions.eq("anchorId", userid));
				pageModel.addQuery(Restrictions.eq("video", 1));
				List<AnchorRecvUserEntity> users = anchorRecvUserContract.load(pageModel);
				//检测是否有对应的聊天用户
				if(Tools.isNotNull(users) && users.size() > 0) {
					//直接发送对应的 上线通知      给用户发送通知栏
					logger.error("当前获取的需要发送的用户数量为:"+users.size());
					for (AnchorRecvUserEntity user : users) {
						userids.put(user.getUserid(), user.getUserid());
						boolean ex = userForegroundAgent.existsForegroundUser(user.getUserid());
						if(!ex) {
							int rand = getRandomNumber(0,9);
							logger.error("当前用户处于非前端  随机到的数量值为:"+rand);
							//即有50%的概率发送给对应的主播
							if(rand >= 5) {
								UserBO userBO = userAgent.findById(user.getUserid());
								if(Tools.isNotNull(userBO) && Tools.isNotNull(userBO.getClientid())) {
									//当前用户是否是七日活跃用户
									long before7 = Tools.getDayTime(7);
									long update = Tools.getDayTime(userBO.getUpdateTime());
									//确定是否是七天活跃
									if(update > before7) {
										logger.error("本用户属于七天内的用户");
										boolean flag = SendMessage(user.getUserid());
										if(flag) {
											//触发对应的发送通知栏消息的业务
											PushParamDto paramC = PushHelper.getPushParamDto(userBO, PushTypeEnum.type_goto_app, PushContentTypeEnum.necessary, NewPushAppTagEnum.personal_page);
											paramC.setTitle("曾经和你嗨聊的大V "+nickname+" 上线了！");
											paramC.setContent("赶紧找他聊天吧！");
											paramC.setUserId(userBO.getUserid());
											//设置进入的个人主页用户id值
											paramC.setExtend(""+userid);
											newPushAgent.pushMessageToSingleUser(paramC);
										}
									}
								}
							}
						}
					}
				}
				//给对应的关注的人用户发送通知栏消息  暂时不做处理
				pageModel.clearAll();
				pageModel.addQuery(Restrictions.eq("friendid", userid));
				List<UserFriendsEntity> friends = userFriendsContract.load(pageModel);
				if(Tools.isNotNull(friends) && friends.size() > 0) {
					logger.error("当前获取的需要发送的关注用户数量为:"+friends.size());
					for (UserFriendsEntity friend : friends) {
						boolean contains = userids.containsKey(friend.getUserid());
						if(!contains) {
							boolean ex = userForegroundAgent.existsForegroundUser(friend.getUserid());
							if(!ex) {
								int rand = getRandomNumber(0,9);
								//即有50%的概率发送给对应的主播
								logger.error("当前用户处于非前端  随机到的数量值为:"+rand);
								if(rand >= 5) {
									UserBO userBO = userAgent.findById(friend.getUserid());
									if(Tools.isNotNull(userBO) && Tools.isNotNull(userBO.getClientid())) {
										//当前用户是否是七日活跃用户
										long before7 = Tools.getDayTime(7);
										long update = Tools.getDayTime(userBO.getUpdateTime());
										//确定是否是七天活跃
										if(update > before7) {
											logger.error("本用户属于七天内的用户");
											boolean flag = SendMessage(friend.getUserid());
											if(flag) {
												//触发对应的发送通知栏消息的业务
												PushParamDto paramC = PushHelper.getPushParamDto(userBO, PushTypeEnum.type_goto_app, PushContentTypeEnum.necessary, NewPushAppTagEnum.personal_page);
												paramC.setTitle("你关注的大V "+nickname+" 上线了！");
												paramC.setContent("赶紧找他聊天吧！");
												paramC.setUserId(userBO.getUserid());
												//设置进入的个人主页用户id值
												paramC.setExtend(""+userid);
												newPushAgent.pushMessageToSingleUser(paramC);
											}
										}
									}
								}
							}
						}
					}
				}
			} catch (Exception e) {
				logger.error("给对应的用户发送唤醒消息出现了错误  启动的主播id为:"+userid);
			}
		}
	}
}
