package com.tigerjoys.shark.miai.agent.utils;

import org.shark.miai.common.enums.PlatformEnum;

import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.shark.miai.agent.dto.PushMessageDto;
import com.tigerjoys.shark.miai.agent.dto.PushMessageParamDto;
import com.tigerjoys.shark.miai.agent.dto.PushParamDto;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.enums.AndroidAppEnum;
import com.tigerjoys.shark.miai.agent.enums.IosAppEnum;
import com.tigerjoys.shark.miai.agent.enums.NewPushAppTagEnum;
import com.tigerjoys.shark.miai.agent.enums.PushContentTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.PushTypeEnum;

/**
 * 关于推送组织信息的辅助类,可以减少重复代码
 * @author liuman
 *
 */
public class PushHelper {
	
	/**
	 * 获取群推送消息实体,群推消息目前只有系统消息,是H5页面
	 * @param param 参数实体,需要具体业务调用进行组装
	 * @return
	 */
	public static PushMessageDto getAllPushMessageDto(PushParamDto param) {
		PushMessageDto dto = new PushMessageDto();
		PushMessageParamDto pushMessageParam = new PushMessageParamDto();
		NewPushAppTagEnum pushAppTagEnum = param.getPushAppTagEnum();
		dto.setMsgType(param.getMsgType());
		dto.setTitle(param.getTitle());
		dto.setUser(0);//全局推送userId为零
		dto.setNotifyId(pushAppTagEnum.getType());//notifyId值得就是枚举类中不同的type值
		if (param.getContentType() == PushContentTypeEnum.necessary.getCode()) {
			dto.setContent(param.getContent());
		} else {
			dto.setContent(pushAppTagEnum.getMessageContent());
		}
		
		pushMessageParam.addParam(1, param.getUrl());
		pushMessageParam.addParam(2, param.getTitle());
		dto.setParam(JsonHelper.toJson(pushMessageParam));
		
		return dto;
	}
	
	/**
	 * 获取推送消息实体
	 * @param param 参数实体,需要具体业务调用进行组装
	 * @return
	 */
	public static PushMessageDto getPushMessageDto(PushParamDto param) {
		PushMessageDto dto = new PushMessageDto();
		PushMessageParamDto pushMessageParam = new PushMessageParamDto();
		NewPushAppTagEnum pushAppTagEnum = param.getPushAppTagEnum();
		dto.setMsgType(param.getMsgType());
		if (param.getPlatformType() == PlatformEnum.android.type) {
			dto.setAppTag(pushAppTagEnum.getAndroidCode());
		} else {
			dto.setAppTag(pushAppTagEnum.getIosCode());
		}
		dto.setTitle(param.getTitle());
		dto.setUser(0);//全局推送userId为零
		dto.setNotifyId(pushAppTagEnum.getType());//notifyId值得就是枚举类中不同的type值
		if (param.getContentType() == PushContentTypeEnum.necessary.getCode()) {
			dto.setContent(param.getContent());
		} else {
			dto.setContent(pushAppTagEnum.getMessageContent());
		}
		if (param.getMsgType() == PushTypeEnum.type_goto_H5.getCode()) {
			pushMessageParam.addParam(1, param.getUrl());
			if (param.getShowH5TitleFalg() == 0) {
				pushMessageParam.addParam(2, param.getH5Title());
			}
		}
		if (pushAppTagEnum.getType() == NewPushAppTagEnum.chat.getType()) {
			pushMessageParam.addParam(1, param.getUserId());
			pushMessageParam.addParam(2, param.getUserName());
			pushMessageParam.addParam(3, param.getUserHead());
		}
		
		if (pushAppTagEnum.getType() == NewPushAppTagEnum.personal_page.getType()) {
			pushMessageParam.addParam(1, param.getExtend());
		}
		
		if(pushAppTagEnum.getType() == NewPushAppTagEnum.recharge_user.getType()) {
			//设置对应的充值信息参数
			pushMessageParam.addParam(1, param.getExtend());
		}
		
		if(pushAppTagEnum.getType() == NewPushAppTagEnum.user_wakeup_anchor.getType()) {
			//设置对应的充值信息参数
			pushMessageParam.addParam(1, param.getExtend());
		}
		//设置对应的外部链接
		dto.setUrl(param.getUrl());
		
		dto.setParam(JsonHelper.toJson(pushMessageParam.getParamMap()));
		dto.setPackageName(param.getPackageName());
		dto.setIos_control(param.getIos_control());
		return dto;
	}
	
	/**
	 * 获得推送所有用户的参数实体
	 * @param pushTypeEnum 推送类型枚举
	 * @param pushContentTypeEnum 推送内容是否需要调用方自行设置
	 * @param pushAppTagEnum 跳转页面定义枚举类
	 * @param PlatformEnum 推送平台类型
	 * @return
	 */
	public static PushParamDto getAllPushParamDto(PushTypeEnum pushTypeEnum,PushContentTypeEnum pushContentTypeEnum,NewPushAppTagEnum pushAppTagEnum,PlatformEnum pushPlatformTypeEnum) {
		PushParamDto param = new PushParamDto();
		param.setContent(pushAppTagEnum.getMessageContent());
		param.setContentType(pushContentTypeEnum.getCode());
		param.setMsgType(pushTypeEnum.getCode());
		param.setPushAppTagEnum(pushAppTagEnum);
		param.setTitle(pushAppTagEnum.getMessageTitle());
		param.setPlatformType(pushPlatformTypeEnum.type);
		return param;
	}
	
	/**
	 * 获得单个用户推送参数实体
	 * @param userBO 用户信息实体
	 * @param pushTypeEnum 推送类型枚举
	 * @param pushContentTypeEnum 推送内容是否需要调用方自行设置
	 * @param pushAppTagEnum 跳转页面定义枚举类
	 * @return
	 */
	public static PushParamDto getPushParamDto(UserBO userBO,PushTypeEnum pushTypeEnum,PushContentTypeEnum pushContentTypeEnum,NewPushAppTagEnum pushAppTagEnum) {
		PushParamDto param = new PushParamDto();
		param.setClientId(userBO.getClientid());
		param.setContent(pushAppTagEnum.getMessageContent());
		param.setContentType(pushContentTypeEnum.getCode());
		param.setMsgType(pushTypeEnum.getCode());
		param.setPlatformType(userBO.getPlatform());
		param.setPushAppTagEnum(pushAppTagEnum);
		param.setTitle(pushAppTagEnum.getMessageTitle());
		param.setPackageName(userBO.getPackageName());
		param.setIos_control(pushAppTagEnum.getIos_control());
		
		//设置对应的推送方式和华为推送token
		param.setPushChannel(userBO.getPushchannel());
		param.setHuaweiToken(userBO.getHuaweiToken());
		return param;
	}
	
	/**
	 * 获得订单超时'我发布的'跳转枚举类
	 * @return
	 */
	public static NewPushAppTagEnum getMyPublishOfOrderTimeout() {
		NewPushAppTagEnum.order_timeout.setAndroidCode(AndroidAppEnum.my_publish.getCode());
		NewPushAppTagEnum.order_timeout.setIosCode(IosAppEnum.my_publish.getCode());
		return NewPushAppTagEnum.order_timeout;
	}
	/**
	 * 获得订单超时'我购买的'跳转枚举类
	 * @return
	 */
	public static NewPushAppTagEnum getMyPurchaseOfOrderTimeout() {
		NewPushAppTagEnum.order_timeout.setAndroidCode(AndroidAppEnum.my_purchase.getCode());
		NewPushAppTagEnum.order_timeout.setIosCode(IosAppEnum.my_purchase.getCode());
		return NewPushAppTagEnum.order_timeout;
	}
	
	/**
	 * 获得约会时间将到的'我发布的'跳转枚举类
	 * @return
	 */
	public static NewPushAppTagEnum getMyPublishOfAppointmentBeginSoon() {
		NewPushAppTagEnum.appointment_begin_soon.setAndroidCode(AndroidAppEnum.my_publish.getCode());
		NewPushAppTagEnum.appointment_begin_soon.setIosCode(IosAppEnum.my_publish.getCode());
		return NewPushAppTagEnum.appointment_begin_soon;
	}
	/**
	 * 获得约会时间将到的'我购买的'跳转枚举类
	 * @return
	 */
	public static NewPushAppTagEnum getMyPurchaseOfAppointmentBeginSoon() {
		NewPushAppTagEnum.appointment_begin_soon.setAndroidCode(AndroidAppEnum.my_purchase.getCode());
		NewPushAppTagEnum.appointment_begin_soon.setIosCode(IosAppEnum.my_purchase.getCode());
		return NewPushAppTagEnum.appointment_begin_soon;
	}
}
