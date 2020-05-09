package com.tigerjoys.shark.miai.agent.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.utils.ExecutorServiceHelper;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.utils.SpringBeanApplicationContext;
import com.tigerjoys.shark.miai.agent.constant.Const;
import com.tigerjoys.shark.miai.agent.dto.PushMessageDto;
import com.tigerjoys.shark.miai.agent.dto.PushParamDto;
import com.tigerjoys.shark.miai.agent.enums.VivoPushMessageEnum;
import com.tigerjoys.shark.miai.agent.service.INewPushService;
import com.tigerjoys.shark.miai.agent.utils.PushHelper;

/**
 * 推送服务业务抽象类
 * @author liuman
 *
 */

@Service
public  class AndroidVivoPushMessageService {
	
	private static final Logger logger = LoggerFactory.getLogger(AndroidVivoPushMessageService.class);
	
	
	public  INewPushService getNewPushService(String packageName) {
		return SpringBeanApplicationContext.getBean(VivoPushMessageEnum.getByCode(packageName).name(), INewPushService.class);
	}
	
	/**
	 * 消息的推送
	 * @param param
	 */
	public void commonPushApp(PushParamDto param,boolean isGroup) {
		PushMessageDto dto = PushHelper.getPushMessageDto(param);
		if (isGroup) {
			ExecutorServiceHelper.executor.execute(new PushMessageThread(isGroup, dto, Tools.HOUR_MILLIS*8, dto.getNotifyId()));
		} else {
			if(Tools.isNotNull(param.getClientId())) {
				ExecutorServiceHelper.executor.execute(new PushMessageThread(param.getClientId(), dto, Tools.HOUR_MILLIS*8,dto.getNotifyId()));
			}
		}
	}
	
	
	
	/**
	 * 发送消息线程类
	 * @author liuman
	 *
	 */
	private class PushMessageThread implements Runnable {
		
		/**
		 * 设备clientId
		 */
		private String clientId;
		
		/**
		 * 推送消息体对象
		 */
		private PushMessageDto message;
		
		/**
		 * 推送消息体
		 */
		private String msg;
		
		/**
		 * 离线消息过期时间
		 */
		private long expireTime;
		
		/**
		 * 是否是群推
		 */
		private boolean isgroup = false;
		
		/**
		 * 安卓平台可以显示 通知栏要显示多条推送消息 notifyid的值要不同
		 */
		private int notifyid;
		
		/**
		 * 
		 * @param isgroup - 是否群推
		 * @param message - 消息体
		 * @param expireTime - 离线消息过期时间，单位毫秒
		 * @param notifyid - 安卓端可以显示多个通知栏消息
		 */
		public PushMessageThread(boolean isgroup , PushMessageDto message , long expireTime,int notifyid) {
			this.isgroup = isgroup;
			this.message = message;
			this.msg = JsonHelper.toJson(message);
			this.expireTime = expireTime;
			this.notifyid = notifyid;
		}
		
		/**
		 * 
		 * @param clientId - 客户端ID
		 * @param message - 消息体
		 * @param expireTime - 离线消息过期时间，单位毫秒
		 * @param notifyid - 通知栏消息类别
		 */
		public PushMessageThread(String clientId , PushMessageDto message , long expireTime,int notifyid) {
			this.clientId = clientId;
			this.message = message;
			this.msg = JsonHelper.toJson(message);
			this.expireTime = expireTime;
			this.notifyid = notifyid;
		}

		@Override
		public void run() {
			//测试环境只给该手机发送推送通知
			if (Const.is_test) {
				String testClientId = "0a05e078192dfad2214f4bd109d18993";
				if(Tools.isNotNull(clientId ) && clientId.length() > 0) {
					int result = getNewPushService(message.getPackageName()).sendMessage(clientId, msg , expireTime,"2",notifyid);
					logger.info("vivoPushLog:本次推送：["+(result==-1?"失败":"成功")+"]");
				} else {
					int result = getNewPushService(message.getPackageName()).sendMessage(testClientId, msg , expireTime,"2",notifyid);
					logger.info("vivoPushLog:本次推送：["+(result==-1?"失败":"成功")+"]");
				}
			} else {//非测试环境要按照包名区分
				if(isgroup) {
					boolean b = getNewPushService(message.getPackageName()).groupPushMessageToApp(msg,expireTime,"0",notifyid);
					logger.info("vivoPushLog:本次群推送：["+(b?"失败":"成功")+"]");
					
				} else {
					int result = getNewPushService(message.getPackageName()).sendMessage(clientId, msg , expireTime,"2",notifyid);
					logger.info("vivoPushLog:本次推送：["+(result==-1?"失败":"成功")+"]");
				}
			}
			/*
			if(isgroup) {
				boolean b = getNewPushService().groupPushMessageToApp(msg,expireTime,"0",notifyid);
				logger.info("本次群推送：["+(b?"失败":"成功")+"]");
				
			} else {
				int result = getNewPushService().sendMessage(clientId, msg , expireTime,"2",notifyid);
				logger.info("本次推送：["+(result==-1?"失败":"成功")+"]");
			}
			*/
		}
		
	}
}
