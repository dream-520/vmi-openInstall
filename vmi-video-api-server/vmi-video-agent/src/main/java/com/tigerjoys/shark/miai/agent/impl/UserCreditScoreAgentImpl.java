package com.tigerjoys.shark.miai.agent.impl;

import java.util.List;
import java.util.Map;

import org.shark.miai.common.enums.UserCreditScoreAccountResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserCreditScoreAgent;
import com.tigerjoys.shark.miai.agent.dto.CreditRecordBO;
import com.tigerjoys.shark.miai.agent.dto.CreditScoreConfigureBO;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.service.IUserCreditScoreService;

@Service
public class UserCreditScoreAgentImpl implements IUserCreditScoreAgent {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IUserCreditScoreService userCreditScoreService;
	
	@Autowired
	private IUserAgent userAgent;
	
	@Override
	public List<CreditScoreConfigureBO> findPurchaseCreditScoreList(long userid) throws Exception {
		return userCreditScoreService.findPurchaseCreditScoreList(userid);
	}

	@Override
	public List<CreditRecordBO> findCreditScoreRecordList(long userid) throws Exception {
		return userCreditScoreService.findCreditScoreRecordList(userid);
	}
	
	@Override
	public List<CreditRecordBO> findCreditScoreRecordListByPaging(long userid,long recordId , int pagesize) throws Exception {
		return userCreditScoreService.findCreditScoreRecordListByPaging(userid,recordId , pagesize);
	}

	@Override
	public Map<String, Object> changeCreditScore(Long userId, boolean isAdd, long changeScore, String transactionFlow, int type, String memo) throws Exception {
		if(changeScore <= 0) {
			throw new RuntimeException("changeScore="+changeScore+" can not be less than or equal to zero");
		}
		
		Map<String , Object> result = userCreditScoreService.changeCreditScore(userId, isAdd, changeScore, transactionFlow, type, memo);
		
		if(String.valueOf(result.get(UserCreditScoreAccountResultEnum.success.getKey())).equals("0")) {
			try {
				UserBO user = userAgent.findById(userId);
				if(user != null && user.isWaiter()) {
					try {
						logger.info("取消订单的时候：检查服务者["+userId+"]的信用是否低于某个阈值，如果是的话，将付费约下架！");
					} catch (Exception e) {
						logger.error(e.getMessage() , e);
					}
				}
			} catch (Exception e) {
				logger.error(e.getMessage() , e);
			}
		}
		
		return result;
	}

	@Override
	public void changeUserCreditStatus(long userid, int status, String memo) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public long getUserCreditBalance(long userid) throws Exception {
		return userCreditScoreService.getUserCreditBalance(userid);
	}

	@Override
	public void initUserCreaditAccount(long userid,int type) throws Exception {
		userCreditScoreService.initUserCreaditAccount(userid, type);
	}

}
