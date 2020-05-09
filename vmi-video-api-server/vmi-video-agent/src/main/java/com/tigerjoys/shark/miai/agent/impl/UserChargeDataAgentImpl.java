package com.tigerjoys.shark.miai.agent.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserChargeDataAgent;
import com.tigerjoys.shark.miai.agent.dto.result.AgentResult;
import com.tigerjoys.shark.miai.agent.enums.UserChargeDataLogTypeEnum;
import com.tigerjoys.shark.miai.inter.contract.IUserChargeDataLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserChargeDataLogEntity;

/**
 * 用户充值数据代理接口实现类
 * @author chengang
 *
 */
@Service
public class UserChargeDataAgentImpl implements IUserChargeDataAgent {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserChargeDataAgentImpl.class);
	
	
	@Autowired
	private IUserChargeDataLogContract userChargeDataLogContract;
	
	@Autowired
	private IUserAgent userAgent;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public AgentResult changeChargeAmount(long userid, long amount, UserChargeDataLogTypeEnum logType, String memo) throws Exception {
		LOGGER.info("添加充值数据--userid="+userid+",amount="+amount+",logtype="+logType.getDesc()+",memo="+memo);
		
		if (memo == null) {
			memo = "";
		} else if (memo.length() > 100) {
			memo = memo.substring(0, 100);
		}
		
		UserChargeDataLogEntity log = new UserChargeDataLogEntity();
		log.setAmount(amount);
		log.setCreate_time(new Date());
		log.setMemo(memo);
		log.setType(logType.getCode());
		log.setUserid(userid);
		userChargeDataLogContract.insert(log);
		
		//检查用户等级
		userAgent.updateSvip(userid);
		
		return AgentResult.success();
	}

	@Override
	public long getTotalChargeAmount(long userId) {
		return userChargeDataLogContract.getSumAmount(userId, Tools.getDate("1988-01-01"));
	}

}
