package com.tigerjoys.shark.miai.agent.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.shark.miai.agent.IKuaiShouConfirmAgent;
import com.tigerjoys.shark.miai.inter.contract.IKuaiShouAdConfirmContract;
import com.tigerjoys.shark.miai.inter.entity.KuaiShouAdConfirmEntity;

@Service
public class KuaiShouConfirmAgentImpl implements IKuaiShouConfirmAgent {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IKuaiShouAdConfirmContract kuaiShouAdConfirmContract;
	
	@Override
	public void confirm(String clientId, int type) {
		try {
			KuaiShouAdConfirmEntity t = new KuaiShouAdConfirmEntity();
			t.setClientId(clientId);
			t.setState(0);
			t.setType(type);
			t.setCreate_time(new Date());
			t.setUpdate_time(new Date());
			kuaiShouAdConfirmContract.insert(t);
		} catch (Exception e) {
			logger.error("clientId: "+clientId+" type:"+type);
		}
	}

}
