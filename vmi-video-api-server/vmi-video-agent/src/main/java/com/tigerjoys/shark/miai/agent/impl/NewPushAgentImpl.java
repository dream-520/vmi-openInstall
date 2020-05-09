package com.tigerjoys.shark.miai.agent.impl;

import java.util.List;

import org.shark.miai.common.enums.AppNameEnum;
import org.shark.miai.common.enums.PlatformEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.agent.INewPushAgent;
import com.tigerjoys.shark.miai.agent.constant.Const;
import com.tigerjoys.shark.miai.agent.constant.PushConstant;
import com.tigerjoys.shark.miai.agent.dto.PushParamDto;
import com.tigerjoys.shark.miai.agent.enums.PushChannel;
import com.tigerjoys.shark.miai.agent.service.IHuaWeiPushService;
import com.tigerjoys.shark.miai.agent.service.impl.AndroidAPushMessageService;
import com.tigerjoys.shark.miai.agent.service.impl.AndroidB2PushMessageService;
import com.tigerjoys.shark.miai.agent.service.impl.AndroidPushMessageService;
import com.tigerjoys.shark.miai.agent.service.impl.AndroidVivoPushMessageService;
import com.tigerjoys.shark.miai.agent.service.impl.IosPushMessageService;
import com.tigerjoys.shark.miai.agent.service.impl.IosPushMessageService17;
import com.tigerjoys.shark.miai.agent.service.impl.IosPushMessageService2;
import com.tigerjoys.shark.miai.agent.service.impl.IosPushMessageService3;
import com.tigerjoys.shark.miai.agent.service.impl.IosPushMessageService4;
import com.tigerjoys.shark.miai.agent.service.impl.IosPushMessageService5;
import com.tigerjoys.shark.miai.agent.service.impl.IosPushMessageService6;
import com.tigerjoys.shark.miai.agent.service.impl.IosPushMessageService7;
import com.tigerjoys.shark.miai.agent.service.impl.IosPushMessageService8;
import com.tigerjoys.shark.miai.agent.service.impl.IosPushMessageService9;


/**
 * 推送服务类实现类
 * @author liuman
 *
 */
@Service
public class NewPushAgentImpl implements INewPushAgent {
	
	/**
	 * B1包
	 */
	@Autowired
	private AndroidPushMessageService androidPushMessageService;
	
	/**
	 * A包
	 */
	@Autowired
	private AndroidAPushMessageService androidAPushMessageService;
	
	/**
	 * B2包
	 */
	@Autowired
	private AndroidB2PushMessageService androidB2PushMessageService;
	
	@Autowired
	private IosPushMessageService iosPushMessageService;
	
	@Autowired
	private IosPushMessageService2 iosPushMessageService2;
	
	@Autowired
	private IosPushMessageService3 iosPushMessageService3;
	
	@Autowired
	private IosPushMessageService4 iosPushMessageService4;
	
	@Autowired
	private IosPushMessageService5 iosPushMessageService5;
	
	@Autowired
	private IosPushMessageService6 iosPushMessageService6;
	
	@Autowired
	private IosPushMessageService7 iosPushMessageService7;
	
	@Autowired
	private IosPushMessageService8 iosPushMessageService8;
	
	@Autowired
	private IosPushMessageService9 iosPushMessageService9;
	
	@Autowired
	private IosPushMessageService17 iosPushMessageService17;
	
	//华为推送网关
	@Autowired
	private IHuaWeiPushService huaWeiPushService;
	
	//华为推送网关
	@Autowired
	private AndroidVivoPushMessageService androidVivoPushMessageService;
	
	@Override
	public void pushMessageToSingleUser(PushParamDto param) throws Exception {
		if(param.getPushChannel() == PushChannel.xiaomiPush.getCode() && Tools.isNotNull(param.getClientId())) {
			//走原先的小米推送服务
			if (PlatformEnum.android == AppNameEnum.getByOsType(param.getPackageName())) {
				//如果没包名默认
				if(Tools.isNotNull(param.getPackageName())){
					androidAPushMessageService.commonPushApp(param,false);
				}
			} else {
				if (PushConstant.ios_xiaomi_accountPackageName5.equals(param.getPackageName())) {
					iosPushMessageService5.commonPushApp(param, false);
				}else if(PushConstant.ios_xiaomi_accountPackageName6.equals(param.getPackageName())){
					iosPushMessageService6.commonPushApp(param, false);
				}else if(PushConstant.ios_xiaomi_accountPackageName7.equals(param.getPackageName())){
					iosPushMessageService7.commonPushApp(param, false);
				}else if(PushConstant.ios_xiaomi_accountPackageName8.equals(param.getPackageName())){
					iosPushMessageService8.commonPushApp(param, false);
				}else if(PushConstant.ios_xiaomi_accountPackageName9.equals(param.getPackageName())){
					iosPushMessageService9.commonPushApp(param, false);
				}else if(PushConstant.ios_xiaomi_accountPackageName17.equals(param.getPackageName())){
					iosPushMessageService17.commonPushApp(param, false);
				}
			}
		} else if(param.getPushChannel() == PushChannel.huaweiPush.getCode()){
			//走华为推送服务
			huaWeiPushService.commonPushApp(param);
		} else if(param.getPushChannel() == PushChannel.vivoPush.getCode()){
			//走vivo推送服务
			androidVivoPushMessageService.commonPushApp(param, false);
		}
	}


	@Override
	public void pushMessageToAllUser(PushParamDto param) throws Exception {
		if (PlatformEnum.android == AppNameEnum.getByOsType(param.getPackageName())) {
			//如果没包名默认
			/*
			if(Tools.isNull(param.getPackageName())){
				androidAPushMessageService.commonPushApp(param,true);
			}
			*/
			//如果是安卓A包
			if (Const.A.equals(param.getPackageName())) {
				androidAPushMessageService.commonPushApp(param,true);
			}
			//如果是安卓B1包
			if (Const.B1.equals(param.getPackageName())) {
				androidPushMessageService.commonPushApp(param,true);
			}
			//如果是安卓B2包
			if (Const.B2.equals(param.getPackageName())) {
				androidB2PushMessageService.commonPushApp(param,true);
			}
		}
		
		if (PlatformEnum.ios == AppNameEnum.getByOsType(param.getPackageName()) ) {
			if (PushConstant.ios_xiaomi_accountPackageName5.equals(param.getPackageName())) {
				iosPushMessageService5.commonPushApp(param, true);
			}else if(PushConstant.ios_xiaomi_accountPackageName6.equals(param.getPackageName())){
				iosPushMessageService6.commonPushApp(param, true);
			}else if(PushConstant.ios_xiaomi_accountPackageName7.equals(param.getPackageName())){
				iosPushMessageService7.commonPushApp(param, true);
			}else if(PushConstant.ios_xiaomi_accountPackageName8.equals(param.getPackageName())){
				iosPushMessageService8.commonPushApp(param, true);
			}else if(PushConstant.ios_xiaomi_accountPackageName9.equals(param.getPackageName())){
				iosPushMessageService9.commonPushApp(param, true);
			}
		}
		
	}


	@Override
	public void subscribeTopicByAlias(int platform,String topic, List<String> aliases) throws Exception {
		/*
		if (platform == PlatformEnum.android.type) {
			androidPushMessageService.commonSubscrineTopic(topic, aliases);
		}
		*/
		if (platform == PlatformEnum.ios.type) {
			/*
			iosPushMessageService.commonSubscrineTopic(topic, aliases);
			iosPushMessageService2.commonSubscrineTopic(topic, aliases);
			iosPushMessageService3.commonSubscrineTopic(topic, aliases);
			iosPushMessageService4.commonSubscrineTopic(topic, aliases);
			iosPushMessageService5.commonSubscrineTopic(topic, aliases);
			*/
		}
	
	}

	@Override
	public void unsubscribeTopicByAlias(int platform, String topic, List<String> aliases) throws Exception {
		/*
		if (platform == PlatformEnum.android.type) {
			androidPushMessageService.commonUnsubscrineTopic(topic, aliases);
		}
		
		if (platform == PlatformEnum.ios.type) {
			iosPushMessageService.commonUnsubscrineTopic(topic, aliases);
			iosPushMessageService2.commonUnsubscrineTopic(topic, aliases);
			iosPushMessageService3.commonUnsubscrineTopic(topic, aliases);
			iosPushMessageService4.commonUnsubscrineTopic(topic, aliases);
			iosPushMessageService5.commonUnsubscrineTopic(topic, aliases);
		}
		*/
	}


	@Override
	public void pushMessageToAndroidUserByTopic(PushParamDto param, String topic) throws Exception {
		//androidPushMessageService.commonPushAppByTopic(topic, param);
	}


	@Override
	public void pushMessageToIosUserByTopic(PushParamDto param, String topic) throws Exception {
		/*
		iosPushMessageService.commonPushAppByTopic(topic, param);
		iosPushMessageService2.commonPushAppByTopic(topic, param);
		iosPushMessageService3.commonPushAppByTopic(topic, param);
		iosPushMessageService4.commonPushAppByTopic(topic, param);
		iosPushMessageService5.commonPushAppByTopic(topic, param);
		*/
	}

	
}
