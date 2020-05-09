package com.tigerjoys.shark.miai.agent.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.shark.miai.agent.INewPushAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.dto.PushMessageDto;
import com.tigerjoys.shark.miai.agent.dto.PushParamDto;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.enums.NewPushAppTagEnum;
import com.tigerjoys.shark.miai.agent.enums.PushChannel;
import com.tigerjoys.shark.miai.agent.enums.PushContentTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.PushTopicEnum;
import com.tigerjoys.shark.miai.agent.enums.PushTypeEnum;
import com.tigerjoys.shark.miai.agent.service.impl.AndroidVivoPushMessageService;
import com.tigerjoys.shark.miai.agent.service.impl.HuaWeiMYPushMessageServiceImpl;
import com.tigerjoys.shark.miai.agent.service.impl.HuaWeiVMPushMessageServiceImpl;
import com.tigerjoys.shark.miai.agent.utils.PushHelper;
import com.tigerjoys.shark.miai.dto.service.PushRichUserDto;
import com.tigerjoys.shark.miai.dto.service.UserBaseInfo;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

public class VivoPushMessageTest extends BaseTestConfig {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	// 华为推送网关
	@Autowired
	private AndroidVivoPushMessageService androidVivoPushMessageService;

	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private INewPushAgent newPushAgent;
	
	
	@Test
	public void pushMessageVMSendTest() throws Exception {
		
		
		PushParamDto param = new PushParamDto();
		param.setClientId("630b5c8d48b05faeb5cae967872c64f4");
		param.setContent(NewPushAppTagEnum.user_head_apply_fail.getMessageContent());
		param.setContentType(PushContentTypeEnum.necessary.getCode());
		param.setMsgType(PushTypeEnum.type_web_H5.getCode());
		param.setPlatformType(1);
		param.setPushAppTagEnum(NewPushAppTagEnum.user_head_apply_fail);
		param.setTitle("测试vivo");
		param.setPackageName("com.ydwx.yoyo");
		param.setIos_control(NewPushAppTagEnum.user_head_apply_fail.getIos_control());
		param.setUrl("www.baidu.com");
		param.setPushChannel(PushChannel.vivoPush.getCode());
		
		newPushAgent.pushMessageToSingleUser(param);
	}
	
	
	@Test
	public void testPushRechargeMessage() throws Exception {
		//long userId = 67244811045896448L;
		//long userId = 36775801156337920L;
		//long userId = 66138512797270272L;
		long userId = 145328275512688896L;
		UserBO user = userAgent.findById(userId);
		System.out.println("user:"+JsonHelper.toJson(user));
		System.err.println(user.getClientid());
		PushParamDto paramC = PushHelper.getPushParamDto(user, PushTypeEnum.type_goto_app, PushContentTypeEnum.necessary, NewPushAppTagEnum.recharge_user);
		paramC.setTitle("我充值了  来和我聊");
		paramC.setContent("一块来聊要");
		paramC.setUserId(userId);
		PushRichUserDto dto = new PushRichUserDto();
		UserBaseInfo otherUserData = new UserBaseInfo();
		otherUserData.setNickName(user.getNickname());
		otherUserData.setBalance("余额: "+20);
		dto.setOtherUserData(otherUserData);
		dto.setInfo("多聊多赚钱");
		
		paramC.setExtend(JsonHelper.toJson(dto));
		newPushAgent.pushMessageToSingleUser(paramC);
	}
	
	
	@Test
	public void testPushRecharge() throws Exception {
		long userId = 158009672480129280L;
		UserBO user = userAgent.findById(userId);
		//外部页面
		PushParamDto paramC = PushHelper.getPushParamDto(user, PushTypeEnum.type_web_H5, PushContentTypeEnum.necessary, NewPushAppTagEnum.wakeup_user);
		paramC.setTitle("我爱大北京");
		paramC.setContent("我爱大北京");
		paramC.setUserId(user.getUserid());
		paramC.setUrl("http://www.baidu.com");
		newPushAgent.pushMessageToSingleUser(paramC);
	}
	
	
	
	
	
	
}
