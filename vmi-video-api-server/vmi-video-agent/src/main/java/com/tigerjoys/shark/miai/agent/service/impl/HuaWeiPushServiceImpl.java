package com.tigerjoys.shark.miai.agent.service.impl;

import java.io.IOException;

import org.shark.miai.common.enums.AppNameEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.utils.ExecutorServiceHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.agent.dto.PushMessageDto;
import com.tigerjoys.shark.miai.agent.dto.PushParamDto;
import com.tigerjoys.shark.miai.agent.service.IHuaWeiPushService;
import com.tigerjoys.shark.miai.agent.utils.PushHelper;

/**
 * 华为推送网关
 * @author shiming
 *
 */
@Service
public class HuaWeiPushServiceImpl implements IHuaWeiPushService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private HuaWeiVMPushMessageServiceImpl huaWeiVMPushMessageService;
	
	@Autowired
	private HuaWeiMYPushMessageServiceImpl huaWeiMYPushMessageService;
	
	@Autowired
	private HuaWeiMPushMessageServiceImpl huaWeiMPushMessageService;
	
	@Override
	public void commonPushApp(PushParamDto param) {
		//首先检测对应的token值是否存在
		if(Tools.isNotNull(param.getHuaweiToken())) {
			if(Tools.isNotNull(param.getPackageName())) {
				PushMessageDto dto = PushHelper.getPushMessageDto(param);
				if(Tools.isNotNull(dto)) {
					ExecutorServiceHelper.executor.execute(new PushHuaWeiMessageThread(param.getHuaweiToken(), dto));
				}
			}
		} else {
			logger.info("需要发送的华为推送信息的token值为空");
		}
	}
	
	private class PushHuaWeiMessageThread implements Runnable {

		private String token;
		
		private PushMessageDto dto;
		
		public PushHuaWeiMessageThread(String token, PushMessageDto dto) {
			this.token = token;
			this.dto = dto;
		}
		@Override
		public void run() {
			//进一步根据对应的包名来确定需要使用的华为推送服务
			try {
				if(dto.getPackageName().equals(AppNameEnum.andriod_com_ydwx_yoyo.getPackageName())) {
					huaWeiVMPushMessageService.sendPushMessage(token, dto);
				} else if(dto.getPackageName().equals(AppNameEnum.andriod_com_ydwx_yoyo3.getPackageName())) {
					huaWeiMYPushMessageService.sendPushMessage(token, dto);	
				} else if(dto.getPackageName().equals(AppNameEnum.andriod_com_ydwx_yoyo2.getPackageName())) {
					huaWeiMPushMessageService.sendPushMessage(token, dto);
				}
			} catch (IOException e) {
				
			}
		}
	}
}
