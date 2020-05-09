package com.tigerjoys.shark.miai.agent.impl;

import java.util.Date;

import org.shark.miai.common.enums.BussinessMessageTypeEnum;
import org.shark.miai.common.enums.PlatformEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.shark.miai.agent.IMessageRedDotAgent;
import com.tigerjoys.shark.miai.agent.INewPushAgent;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;
import com.tigerjoys.shark.miai.agent.constant.Const;
import com.tigerjoys.shark.miai.agent.dto.PushParamDto;
import com.tigerjoys.shark.miai.agent.enums.NewPushAppTagEnum;
import com.tigerjoys.shark.miai.agent.enums.PushContentTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.PushTypeEnum;
import com.tigerjoys.shark.miai.agent.utils.PushHelper;
import com.tigerjoys.shark.miai.inter.contract.IBussinessMessageContract;
import com.tigerjoys.shark.miai.inter.entity.BussinessMessageEntity;
import com.tigerjoys.shark.miai.inter.entity.MessageTemplateEntity;

@Service
public class MessageRedDotAgentImpl implements IMessageRedDotAgent {
	
	@Autowired
	private IBussinessMessageContract bussinessMessageContract;
	
	@Autowired
	@Qualifier(AgentRedisCacheConst.REDIS_USER_CACHE)
	private CacheRedis redDotCacheRedis;
	
	@Autowired
	private INewPushAgent pushAgent;
	
	@Override
	public void addRedDot(String key, long userId) {
		redDotCacheRedis.hincrBy(key, String.valueOf(userId), 1);
	}

	@Override
	public void reduceRedDot(String key, long userId) {
		redDotCacheRedis.hincrBy(key, String.valueOf(userId), -1);
	}

	@Override
	public void addBussinessMessage(long userId,BussinessMessageTypeEnum bussinessMessageTypeEnum,String param) throws Exception {
		//将业务消息落地到数据库中
		BussinessMessageEntity bussinessMessage = new BussinessMessageEntity();
		
		bussinessMessage.setAndroidAppPage(bussinessMessageTypeEnum.getAndroidAppPage());
		bussinessMessage.setContent("");
		bussinessMessage.setCreate_time(new Date());
		bussinessMessage.setHitType(bussinessMessageTypeEnum.getHitType());
		bussinessMessage.setIntro(bussinessMessageTypeEnum.getIntro());
		bussinessMessage.setIosAppPage(bussinessMessageTypeEnum.getIosAppPage());
		bussinessMessage.setOpentype(bussinessMessageTypeEnum.getOpentype());
		bussinessMessage.setOpenurl(bussinessMessageTypeEnum.getOpenurl());
		bussinessMessage.setParam(param);
		bussinessMessage.setTitle(bussinessMessageTypeEnum.getTitle());
		bussinessMessage.setType(bussinessMessageTypeEnum.getType());
		bussinessMessage.setUserid(userId);
		
		bussinessMessageContract.insert(bussinessMessage);
		
		//业务消息总量增加,要将该用户的业务消息数量存储到redis中的数据中
		//redDotCacheRedis.hincrBy(CommonConst.business_message, String.valueOf(userId), 1);
		
	}

	@Override
	public void sendSystemMessage(MessageTemplateEntity mess) throws Exception {
		//TODO 按照包名推送通知
		PushParamDto androidParam = PushHelper.getAllPushParamDto(PushTypeEnum.type_goto_H5, PushContentTypeEnum.necessary, NewPushAppTagEnum.sysmessage_H5_fragment,PlatformEnum.android);
		androidParam.setContent(mess.getIntro());
		androidParam.setTitle(mess.getTitle());
		androidParam.setH5Title(mess.getTitle());
		androidParam.setPackageName(mess.getPackagename());
		
		/*
		PushParamDto iosParam = PushHelper.getAllPushParamDto(PushTypeEnum.type_goto_H5, PushContentTypeEnum.necessary, NewPushAppTagEnum.sysmessage_H5_fragment,PlatformEnum.ios);
		iosParam.setContent(mess.getIntro());
		iosParam.setTitle(mess.getTitle());
		iosParam.setH5Title(mess.getTitle());
		iosParam.setPackageName(mess.getPackagename());*/

		if(mess.getType()==0){
			String url = Const.getSysMessUrl(mess.getId());
			androidParam.setUrl(url);
//			iosParam.setUrl(url);
		}else if(mess.getType()==1){
			androidParam.setUrl(mess.getOpenurl());
//			iosParam.setUrl(mess.getOpenurl());
		}
		
		pushAgent.pushMessageToAllUser(androidParam);
//		pushAgent.pushMessageToAllUser(iosParam);
	}

	@Override
	public void setZeroRedDot(String key, long userId) {
		redDotCacheRedis.hset(key, String.valueOf(userId), "0");
	}


}
