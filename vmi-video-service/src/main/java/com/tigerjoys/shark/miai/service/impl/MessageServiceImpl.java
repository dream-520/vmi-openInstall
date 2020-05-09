package com.tigerjoys.shark.miai.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.shark.miai.common.constant.CommonConst;
import org.shark.miai.common.enums.BussinessMessageTypeEnum;
import org.shark.miai.common.enums.UserTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.RedisCacheConst;
import com.tigerjoys.shark.miai.agent.IAnchorOnlineStateAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserMypageActivityAgent;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.dto.task.JudgeMessageUpdateDto;
import com.tigerjoys.shark.miai.dto.task.RedDotDto;
import com.tigerjoys.shark.miai.es.dto.EsUserOnlineReordDto;
import com.tigerjoys.shark.miai.es.enums.EsUserOnlineUserEventEnum;
import com.tigerjoys.shark.miai.es.service.IEsUserOnlineRecordService;
import com.tigerjoys.shark.miai.inter.contract.IBussinessMessageContract;
import com.tigerjoys.shark.miai.inter.contract.IMessageTemplateContract;
import com.tigerjoys.shark.miai.inter.contract.IMessageUserReadContract;
import com.tigerjoys.shark.miai.inter.contract.IPayUserRecordContract;
import com.tigerjoys.shark.miai.inter.entity.BussinessMessageEntity;
import com.tigerjoys.shark.miai.inter.entity.MessageTemplateEntity;
import com.tigerjoys.shark.miai.inter.entity.MessageUserReadEntity;
import com.tigerjoys.shark.miai.inter.entity.PayUserRecordEntity;
import com.tigerjoys.shark.miai.service.IChannelCheckService;
import com.tigerjoys.shark.miai.service.IMessageService;

/**
 * 消息服务类
 * @author liuman
 *
 */
@Service
public class MessageServiceImpl implements IMessageService{
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier(RedisCacheConst.REDIS_PUBLIC_CACHE)
	private CacheRedis cacheRedis;
	
	@Autowired
	private IBussinessMessageContract bussinessMessageContract;
	
	@Autowired
	private IMessageTemplateContract messageTemplateContract;
	
	@Autowired
	private IUserMypageActivityAgent userMypageActivityAgent;
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private IEsUserOnlineRecordService esUserOnlineRecordService;
	
	@Autowired
	private IAnchorOnlineStateAgent anchorOnlineStateAgent;
	
	@Autowired
	private IPayUserRecordContract payUserRecordContract;
	
	@Autowired
	private IMessageUserReadContract messageUserReadContract;
	
	@Autowired
	private IChannelCheckService channelCheckService;
	
	/**
	 * 根据登录的用户id获得是否更新的消息
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Override
	public ActionResult getMessageUpdateDtos(long userId,String stamp,String packageName) throws Exception {
		
		//添加处理在线状态更新的处理
		anchorOnlineStateAgent.refreshOnlineAnchor(userId);
		
		
		
		
		//如果登录了
		if (userId > 0L) {
			
			
			//首先检查本用户是否为主播
			UserBO bo = userAgent.findById(userId);
			if(Tools.isNotNull(bo)){
				try{
					esUserOnlineRecordService.saveEntity(EsUserOnlineReordDto.preDto(userId, bo.isWaiter()? UserTypeEnum.servicer:UserTypeEnum.ordinary, EsUserOnlineUserEventEnum.refresh, new Date()));
				}catch(Exception e){
					logger.info("保存刷新日志失败,userid:"+userId,e);
				}
			}
			
			List<Integer> types = new ArrayList<>();
			//系统消息
			types.add(CommonConst.MSG_TYPE_SYS_NOTICE);
			//业务消息
			types.add(CommonConst.MSG_TYPE_SYS_INFO);
			
			//我购买的付费约会(属于业务消息)
			//types.add(CommonConst.MSG_TYPE_PAY_DATE_BUY);
			//我发布的付费约会(属于业务消息)
			//types.add(CommonConst.MSG_TYPE_PAY_DATE_SELL);
			//我购买的普通约会(属于业务消息)
			//types.add(CommonConst.MSG_TYPE_EGT_DATE_BUY);
			//我发布的普通约会(属于业务消息)
			//types.add(CommonConst.MSG_TYPE_EGT_DATE_SELL);
			//派单约会的达人接单
			//types.add(CommonConst.MSG_TYPE_PAY_DATE_SELL_ORDERS);
			//临时活动消息
			types.add(CommonConst.MSG_TYPE_MY_TEMP);
			//根据用户id和业务消息类型查询业务消息
			Map<Integer,BussinessMessageEntity> dataMap = this.getDataMap(userId);
			List<JudgeMessageUpdateDto> updateDtos = new ArrayList<>(types.size());
			String unreadSysCount = this.addSysMessageUpdateDtos(updateDtos,userId,stamp,packageName);
			//比对dataMap中所有的最新业务消息
			this.addBusinessMessageUpdateDto(updateDtos,userId,dataMap);
			
			//this.addMyPurchaseUpdateDto(updateDtos,userId,dataMap);
			//this.addMyPublishUpdateDto(updateDtos,userId,dataMap);
			//this.addMyPurchaseFreeUpdateDto(updateDtos,userId,dataMap);
			//this.addMyPublishFreeUpdateDto(updateDtos,userId,dataMap);
			//this.addMyReceiveDipatchUpdateDto(updateDtos,userId,dataMap);
			
			this.addTempUpdateDto(updateDtos, userId);
			//获得所有未读消息的总数
			int unreadCount = Tools.getInt(cacheRedis.hget(CommonConst.business_message, String.valueOf(userId))) + Tools.getInt(unreadSysCount);
			RedDotDto redDotDto = new RedDotDto();
			redDotDto.setUnreadCount(unreadCount);
			redDotDto.setUpdateMessages(updateDtos);
			return ActionResult.success(redDotDto, unreadSysCount, false);
		} else {
			return ActionResult.success();
		}
		
	}
	
	/**
	 * 获得某个用户最新的业务消息数据集合
	 * @param userId
	 * @return
	 */
	private Map<Integer,BussinessMessageEntity> getDataMap(long userId) {
		List<BussinessMessageEntity> messages = bussinessMessageContract.loadByUseridAndType(BussinessMessageTypeEnum.getTypes(), userId);
		Map<Integer,BussinessMessageEntity> dataMap = null;
		if (CollectionUtils.isNotEmpty(messages)) {
			dataMap = new HashMap<>();
			//根据创建时间倒序排序
			Collections.sort(messages, new Comparator<BussinessMessageEntity>() {

				@Override
				public int compare(BussinessMessageEntity o1, BussinessMessageEntity o2) {
					//flag = -1是按照时间的降序排序
					int flag = o2.getCreate_time().compareTo(o1.getCreate_time());
					return flag;
				}
			});
			
			dataMap.put(BussinessMessageTypeEnum.my_latest_message.getType(), messages.get(0));
			for (BussinessMessageEntity message : messages) {
				dataMap.put(message.getType(), message);
			}
		}

		return dataMap;
	}
	
	/**
	 * 系统消息
	 * @param updateDtos
	 * @throws Exception 
	 */
	private String addSysMessageUpdateDtos(List<JudgeMessageUpdateDto> updateDtos,long userId,String stamp,String packageName) throws Exception {
		JudgeMessageUpdateDto judgeMessageUpdate = new JudgeMessageUpdateDto();
		judgeMessageUpdate.setMsgType(CommonConst.MSG_TYPE_SYS_NOTICE);
		//TODO 获得所有包名
		/*
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addProjection(Projections.groupProperty("packagename"));
		List<Map<String , Object>> datas = messageTemplateContract.loadGroupBy(pageModel);
		List<String> keys = new ArrayList<>();
		for (Map<String , Object> data : datas) {
			keys.add(data.get("packagename").toString());
		}*/
		//发布消息个数
		String publishCount = cacheRedis.get(CommonConst.sys_message + packageName);
		//用户是否点击进入系统消息列表
		String readCount = cacheRedis.hget(CommonConst.sys_user_message + packageName, String.valueOf(userId));
		int unreadCount = 0;
		UserBO user = userAgent.findById(userId);
		if(user == null) {
			return "0";
		}
		
		PageModel pageModel = PageModel.getLimitModel(0, 1);
		pageModel.addQuery(Restrictions.eq("status", 1));
		pageModel.addQuery(Restrictions.eq("type", 0));
		//在系统消息发布时间之后注册的用户,之前的系统消息不应该在系统消息列表中展示
		pageModel.addQuery(Restrictions.gt("publish_time", user.getCreateTime()));
		//已经推送过的消息要展示,未推送的不能展示
		pageModel.addQuery(Restrictions.eq("send", 1));
		pageModel.addQuery(Restrictions.le("publish_time", new Date(Tools.getMinuteTime(5))));//发布的时间不能超过当前的时间
		pageModel.desc("publish_time");

		List<MessageTemplateEntity> sysMessages = messageTemplateContract.load(pageModel);
		
		if (Tools.isNotNull(publishCount)) {
			//比对系统消息个数
			if (Tools.parseInt(publishCount) > Tools.parseInt(readCount) && CollectionUtils.isNotEmpty(sysMessages)) {
				judgeMessageUpdate.setMsgTag(1);
				unreadCount = Tools.parseInt(publishCount) - Tools.parseInt(readCount);
			} else {
				judgeMessageUpdate.setMsgTag(0);
			}

			if (CollectionUtils.isNotEmpty(sysMessages)) {
				MessageTemplateEntity newSysMessage = sysMessages.get(0);
				judgeMessageUpdate.setCreateDate(newSysMessage.getCreate_time().getTime());
				judgeMessageUpdate.setInfo(newSysMessage.getIntro());
				judgeMessageUpdate.setTitle(newSysMessage.getTitle());
			}
		}
		//添加系统消息更新数据信息
		updateDtos.add(judgeMessageUpdate);
		
		//将未读数量返回
		return String.valueOf(unreadCount);
	}
	
	/**
	 * 是否有新的业务消息
	 * @param updateDtos
	 * @throws Exception 
	 * 
	 */
	private void addBusinessMessageUpdateDto(List<JudgeMessageUpdateDto> updateDtos,long userId,Map<Integer,BussinessMessageEntity> dataMap) throws Exception {
		String count = cacheRedis.hget(CommonConst.business_message, String.valueOf(userId));
		JudgeMessageUpdateDto judgeMessageUpdate = new JudgeMessageUpdateDto();
		judgeMessageUpdate.setMsgType(CommonConst.MSG_TYPE_SYS_INFO);
		//如果需求中的业务有发生,则将该业务更新进去,数据结构暂定hash表
		if (Tools.parseInt(count) > 0) {
			judgeMessageUpdate.setMsgTag(1);

		} else {
			judgeMessageUpdate.setMsgTag(0);
		}
		
		this.organizedJudgeMessageUpdateDto(judgeMessageUpdate, BussinessMessageTypeEnum.my_latest_message.getType(), dataMap);
		//添加系统消息更新数据信息
		updateDtos.add(judgeMessageUpdate);
	}
	
	/**
	 * 是否有新的'我购买的'业务消息
	 * @param updateDtos
	 * @throws Exception 
	 * 
	 */
	private void addMyPurchaseUpdateDto(List<JudgeMessageUpdateDto> updateDtos,long userId,Map<Integer,BussinessMessageEntity> dataMap) throws Exception {
		String count = cacheRedis.hget(CommonConst.myPurchase, String.valueOf(userId));
		JudgeMessageUpdateDto judgeMessageUpdate = new JudgeMessageUpdateDto();
		judgeMessageUpdate.setMsgType(CommonConst.MSG_TYPE_PAY_DATE_BUY);
		//如果需求中的业务有发生,则将该业务更新进去,数据结构暂定hash表
		if (Tools.parseInt(count) > 0) {
			judgeMessageUpdate.setMsgTag(1);

		} else {
			judgeMessageUpdate.setMsgTag(0);
		}
		
		this.organizedJudgeMessageUpdateDto(judgeMessageUpdate, BussinessMessageTypeEnum.my_purchase.getType(), dataMap);

		//添加我购买的消息更新数据信息
		updateDtos.add(judgeMessageUpdate);
	}
	
	
	/**
	 * 是否有新的'我发布的'业务消息
	 * @param updateDtos
	 * @throws Exception 
	 * 
	 */
	private void addMyPublishUpdateDto(List<JudgeMessageUpdateDto> updateDtos,long userId,Map<Integer,BussinessMessageEntity> dataMap) throws Exception {
		String count = cacheRedis.hget(CommonConst.myPublish, String.valueOf(userId));
		JudgeMessageUpdateDto judgeMessageUpdate = new JudgeMessageUpdateDto();
		judgeMessageUpdate.setMsgType(CommonConst.MSG_TYPE_PAY_DATE_SELL);
		//如果需求中的业务有发生,则将该业务更新进去,数据结构暂定hash表
		if (Tools.parseInt(count) > 0) {
			judgeMessageUpdate.setMsgTag(1);

		} else {
			judgeMessageUpdate.setMsgTag(0);
		}
		
		this.organizedJudgeMessageUpdateDto(judgeMessageUpdate, BussinessMessageTypeEnum.my_publish.getType(), dataMap);

		//添加'我发布的'业务消息
		updateDtos.add(judgeMessageUpdate);
	}
	
	private void organizedJudgeMessageUpdateDto(JudgeMessageUpdateDto judgeMessageUpdate,int type,Map<Integer,BussinessMessageEntity> dataMap) {
		if (Tools.isNotNull(dataMap)) {
			BussinessMessageEntity newBusinessMessage = dataMap.get(type);
			if (newBusinessMessage != null) {
				judgeMessageUpdate.setCreateDate(newBusinessMessage.getCreate_time().getTime());
				judgeMessageUpdate.setInfo(newBusinessMessage.getIntro());
				judgeMessageUpdate.setTitle(newBusinessMessage.getTitle());
			}
		}
	}
	
	/**
	 * 是否有新的'临时活动'消息
	 * @param updateDtos
	 * @throws Exception 
	 * 
	 */
	private void addTempUpdateDto(List<JudgeMessageUpdateDto> updateDtos,long userId) throws Exception {
		List<Long> tempActivities = userMypageActivityAgent.getIndexCode(userId);
		if (CollectionUtils.isNotEmpty(tempActivities)) {
			for (Long tempActivity : tempActivities) {
				JudgeMessageUpdateDto judgeMessageUpdate = new JudgeMessageUpdateDto();
				judgeMessageUpdate.setMsgType(tempActivity.intValue());
				judgeMessageUpdate.setMsgTag(1);
				//添加'临时活动'业务消息
				updateDtos.add(judgeMessageUpdate);
			}
		}

	}
	
	/**
	 * 是否有新的'我购买的'业务消息(普通约)
	 * @param updateDtos
	 * @throws Exception 
	 * 
	 */
	private void addMyPurchaseFreeUpdateDto(List<JudgeMessageUpdateDto> updateDtos,long userId,Map<Integer,BussinessMessageEntity> dataMap) throws Exception {
		String count = cacheRedis.hget(CommonConst.myPurchaseFree, String.valueOf(userId));
		JudgeMessageUpdateDto judgeMessageUpdate = new JudgeMessageUpdateDto();
		judgeMessageUpdate.setMsgType(CommonConst.MSG_TYPE_EGT_DATE_BUY);
		//如果需求中的业务有发生,则将该业务更新进去,数据结构暂定hash表
		if (Tools.parseInt(count) > 0) {
			judgeMessageUpdate.setMsgTag(1);

		} else {
			judgeMessageUpdate.setMsgTag(0);
		}
		this.organizedJudgeMessageUpdateDto(judgeMessageUpdate, BussinessMessageTypeEnum.my_purchase_free.getType(), dataMap);

		//添加我购买的消息更新数据信息
		updateDtos.add(judgeMessageUpdate);
	}
	
	
	/**
	 * 是否有新的'我发布的'业务消息(普通约)
	 * @param updateDtos
	 * @throws Exception 
	 * 
	 */
	private void addMyPublishFreeUpdateDto(List<JudgeMessageUpdateDto> updateDtos,long userId,Map<Integer,BussinessMessageEntity> dataMap) throws Exception {
		String count = cacheRedis.hget(CommonConst.myPublishFree, String.valueOf(userId));
		JudgeMessageUpdateDto judgeMessageUpdate = new JudgeMessageUpdateDto();
		judgeMessageUpdate.setMsgType(CommonConst.MSG_TYPE_EGT_DATE_SELL);
		//如果需求中的业务有发生,则将该业务更新进去,数据结构暂定hash表
		if (Tools.parseInt(count) > 0) {
			judgeMessageUpdate.setMsgTag(1);

		} else {
			judgeMessageUpdate.setMsgTag(0);
		}
		
		this.organizedJudgeMessageUpdateDto(judgeMessageUpdate, BussinessMessageTypeEnum.my_publish_free.getType(), dataMap);

		//添加'我发布的'业务消息
		updateDtos.add(judgeMessageUpdate);
	}
	
	/**
	 * 是否有新的'接单管理'业务消息
	 * @param updateDtos
	 * @throws Exception 
	 * 
	 */
	private void addMyReceiveDipatchUpdateDto(List<JudgeMessageUpdateDto> updateDtos,long userId,Map<Integer,BussinessMessageEntity> dataMap) throws Exception {
		String count = cacheRedis.hget(CommonConst.masterReceiveDispatch, String.valueOf(userId));
		JudgeMessageUpdateDto judgeMessageUpdate = new JudgeMessageUpdateDto();
		judgeMessageUpdate.setMsgType(CommonConst.MSG_TYPE_PAY_DATE_SELL_ORDERS);
		//如果需求中的业务有发生,则将该业务更新进去,数据结构暂定hash表
		if (Tools.parseInt(count) > 0) {
			judgeMessageUpdate.setMsgTag(1);

		} else {
			judgeMessageUpdate.setMsgTag(0);
		}
		
		this.organizedJudgeMessageUpdateDto(judgeMessageUpdate, BussinessMessageTypeEnum.master_receive_dispatch.getType(), dataMap);

		//添加'接单管理'业务消息
		updateDtos.add(judgeMessageUpdate);
	}

	@Override
	public ActionResult getMessageCount(long userId) throws Exception {
		RedDotDto redDotDto = new RedDotDto();
		redDotDto.setUnreadCount(0);
		List<JudgeMessageUpdateDto> updateDtos = null;
		JudgeMessageUpdateDto dto = null;
		//如果登录了
		if (userId > 0L) {
			//首先检查本用户是否为主播
			UserBO bo = userAgent.findById(userId);
			if(Tools.isNotNull(bo)){
				try{
					esUserOnlineRecordService.saveEntity(EsUserOnlineReordDto.preDto(userId, bo.isWaiter()? UserTypeEnum.servicer:UserTypeEnum.ordinary, EsUserOnlineUserEventEnum.refresh, new Date()));
				}catch(Exception e){
					logger.info("保存刷新日志失败,userid:"+userId,e);
				}
				if (!bo.isWaiter()) {
					try{
						userAgent.saveUserCanCallLog(userId);
					}catch(Exception e){
						logger.info("保存可拨打用户数失败,userid:"+userId,e);
					}
				}
				try{
					PayUserRecordEntity payUser = payUserRecordContract.findByProperty("userid", userId);
					if (payUser!=null) {
						PayUserRecordEntity entity = new PayUserRecordEntity();
						entity.setId(payUser.getId());
						entity.setLast_login_time(new Date());
						payUserRecordContract.update(entity);
					}
				} catch (Exception e){
					logger.info("更新用户活跃时间",e);
				}
				
				//添加处理在线状态更新的处理
				anchorOnlineStateAgent.refreshOnlineAnchor(userId);
				// 检测是否有未读的小红点消息
				PageModel pageModel = PageModel.getPageModel();
				pageModel.addQuery(Restrictions.in("userid", "10010", userId));
				List<MessageUserReadEntity> userReads = messageUserReadContract.load(pageModel);
				MessageUserReadEntity total = null;
				MessageUserReadEntity user = null;
				if(Tools.isNotNull(userReads) && userReads.size() > 0) {
					//分析对应的用户和特殊总数值
					for (MessageUserReadEntity userRead : userReads) {
						if(userRead.getUserid().longValue() == 10010) {
							total = userRead;
						} else if(userRead.getUserid().longValue() == userId) {
							user = userRead;
						}
					}
				}
				
				if(Tools.isNotNull(total)) {
					if(Tools.isNotNull(user)) {
						if(bo.isWaiter()) {
							//主播
							if(total.getAll_num() > user.getAll_num() || total.getAnchor_num() > user.getAnchor_num()) {
								updateDtos = new ArrayList<>();
								dto = new JudgeMessageUpdateDto();
								dto.setMsgType(1);
								dto.setMsgTag(1);
								updateDtos.add(dto);
							}
								
						} else {
							//用户
							if(total.getAll_num() > user.getAll_num() || total.getUser_num() > user.getUser_num()) {
								updateDtos = new ArrayList<>();
								dto = new JudgeMessageUpdateDto();
								dto.setMsgType(1);
								dto.setMsgTag(1);
								updateDtos.add(dto);
							}
						}
					} else {
						MessageUserReadEntity t = new MessageUserReadEntity();
						t.setUserid(userId);
						t.setAll_num(total.getAll_num());
						t.setUser_num(total.getUser_num());
						t.setAnchor_num(total.getAnchor_num());
						t.setCreate_time(new Date());
						t.setUpdate_time(new Date());
						messageUserReadContract.insert(t);
					}
				}
			}
			
			//单独处理主播端的预约小红点
			if(Tools.isNotNull(bo) && bo.isWaiter()) {
				//检测是否有没有读取的预约消息
				String unread = cacheRedis.hget(AgentRedisCacheConst.REDIS_USER_SUBSCRIBE_ANCHOR_UNREAD, bo.getUserid()+"");
				if(Tools.isNotNull(unread)) {
					int unreadCount = Integer.parseInt(unread);
					int readCount = 0;
					String read = cacheRedis.hget(AgentRedisCacheConst.REDIS_USER_SUBSCRIBE_ANCHOR_READ, bo.getUserid()+"");
					if(Tools.isNotNull(read)) {
						readCount = Integer.parseInt(read);
					}
					if(unreadCount > readCount) {
						//标识有未读的预约消息
						if(Tools.isNull(updateDtos))
							updateDtos = new ArrayList<>();
						dto = new JudgeMessageUpdateDto();
						dto.setMsgType(3);
						dto.setMsgTag(1);
						updateDtos.add(dto);
					}
				}
			}
			
		}
		
		boolean check = channelCheckService.checkChannel();
		if(!check)
			redDotDto.setUpdateMessages(updateDtos);
		return ActionResult.success(redDotDto, "", false);
	}
	
}
