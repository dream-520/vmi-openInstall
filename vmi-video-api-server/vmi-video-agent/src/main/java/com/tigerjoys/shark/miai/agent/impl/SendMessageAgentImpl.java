package com.tigerjoys.shark.miai.agent.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.utils.StringUtils;
import com.tigerjoys.shark.miai.agent.ISendMessageAgent;
import com.tigerjoys.shark.miai.agent.enums.SendSmsTypeEnum;
import com.tigerjoys.shark.miai.agent.service.ISendMessageService;
import com.tigerjoys.shark.miai.agent.utils.SmsParam;
import com.tigerjoys.shark.miai.inter.contract.ISmsDelayQueueContract;
import com.tigerjoys.shark.miai.inter.entity.SmsDelayQueueEntity;

/**
 * 短信接口代理实现类
 * @author chengang
 *
 */
@Service
public class SendMessageAgentImpl implements ISendMessageAgent {
	
	@Autowired
	private ISendMessageService sendMessageService;
	
	@Autowired
	private ISmsDelayQueueContract smsDelayQueueContract;

	@Override
	public ActionResult sendSms(SmsParam param, Long userid, SendSmsTypeEnum type , String... mobiles) throws Exception {
		return sendMessageService.sendSms(mobiles, param, userid, type);
	}

	@Override
	public ActionResult sendMobileValidCode(String mobile, SendSmsTypeEnum type) throws Exception {
		return sendMessageService.sendMobileValidCode(mobile, type);
	}

	@Override
	public long todaySendSmsCount(String mobile) throws Exception {
		return sendMessageService.todaySendSmsCount(mobile);
	}

	@Override
	public boolean checkCode(String mobile, String validCode) {
		return sendMessageService.checkCode(mobile, validCode);
	}

	@Override
	public long addSmsDelayQueue(SmsParam param, Long userid, SendSmsTypeEnum type, Date dealyTime, String... mobiles) throws Exception {
		Date currDate = new Date();
		
		SmsDelayQueueEntity s = new SmsDelayQueueEntity();
		s.setCreate_time(currDate);
		s.setDealy_time(dealyTime != null ? dealyTime : currDate);
		s.setMobile(StringUtils.join(mobiles , ','));
		s.setParams(param != null ? param.toJson() : "");
		s.setSms_type(type.getCode());
		s.setUserid(userid);
		s.setErrors(0);
		smsDelayQueueContract.insert(s);
		
		return s.getId();
	}

	@Override
	public boolean delSmsDelayQueue(long id) throws Exception {
		if(id <= 0) {
			return false;
		}
		
		return smsDelayQueueContract.deleteById(id) > 0;
	}

}
