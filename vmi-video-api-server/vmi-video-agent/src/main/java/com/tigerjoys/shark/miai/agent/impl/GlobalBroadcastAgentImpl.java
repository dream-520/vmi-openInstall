package com.tigerjoys.shark.miai.agent.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.shark.miai.agent.IGlobalBroadcastAgent;
import com.tigerjoys.shark.miai.agent.dto.result.AgentResult;
import com.tigerjoys.shark.miai.agent.service.IGlobalBroadcastAgentService;

/**
 * 全局广播服务实现类
 * @author lipeng
 *
 */
@Service
public class GlobalBroadcastAgentImpl implements IGlobalBroadcastAgent {
	
	@Autowired
	private IGlobalBroadcastAgentService globalBroadcastService;
	
	@Override
	public int getRecordCount(long userid) throws Exception {
		return globalBroadcastService.getRecordCount(userid);
	}

	@Override
	public AgentResult recordGlobalBroadcast(long userid) {
		return globalBroadcastService.recordGlobalBroadcast(userid);
	}

	@Override
	public void insert(Long userid, long anchorid, long money, int type, long giftId) throws Exception {
		globalBroadcastService.insert(userid, anchorid, money,type,giftId);
	}

}
