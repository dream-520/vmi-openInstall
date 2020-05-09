package com.tigerjoys.shark.miai.agent.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.shark.miai.agent.IFreeVideoChatExperienceAgent;
import com.tigerjoys.shark.miai.inter.contract.IFreeVideoChatExperienceContract;
import com.tigerjoys.shark.miai.inter.contract.IFreeVideoChatExperienceLogContract;
import com.tigerjoys.shark.miai.inter.entity.FreeVideoChatExperienceLogEntity;

@Service
public class FreeVideoChatExperienceAgentImpl implements IFreeVideoChatExperienceAgent {

	@Autowired
	private IFreeVideoChatExperienceContract freeVideoChatExperienceContract;
	
	@Autowired
	private IFreeVideoChatExperienceLogContract freeVideoChatExperienceLogContract;
	
	@Override
	public int insertRecord(long userid, String channel, int second,int buyType) throws Exception {
		FreeVideoChatExperienceLogEntity entity = new FreeVideoChatExperienceLogEntity();
		entity.setUserid(userid);
		entity.setType(buyType);
		entity.setCreate_time(new Date());
		freeVideoChatExperienceLogContract.insertIgnore(entity);
		
		freeVideoChatExperienceContract.insertRecord(userid, channel, second);
		return 1;
	}



}
