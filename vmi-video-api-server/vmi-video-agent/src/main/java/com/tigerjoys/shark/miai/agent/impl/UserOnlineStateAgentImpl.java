package com.tigerjoys.shark.miai.agent.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.agent.IAnchorOnlineStateAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserOnlineListAgent;
import com.tigerjoys.shark.miai.agent.IUserOnlineStateAgent;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.enums.AnchorOnlineStateEnum;
import com.tigerjoys.shark.miai.inter.contract.IAnchorOnlineContract;

@Service
public class UserOnlineStateAgentImpl implements IUserOnlineStateAgent {

	@Autowired
	private IAnchorOnlineStateAgent anchorOnlineStateAgent;
	
	@Autowired
	private IUserOnlineListAgent userOnlineListAgent;
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private IAnchorOnlineContract anchorOnlineContract;
	
	@Override
	public int userOnlineState(long userid) throws Exception {
		//首先检测是否处于在聊状态
		boolean anchor = anchorOnlineStateAgent.existsTalking(userid);
		if(anchor)
			return AnchorOnlineStateEnum.talking.getCode();
		//然后根据用户id找到对应的用户
		UserBO user = userAgent.findById(userid);
		if(Tools.isNull(user))
			return 0;
		//根据是否是主播进行获取状态处理
		if(user.isWaiter()) {
			return anchorOnlineContract.onlineState(userid);
		} else {
			boolean online = userOnlineListAgent.existsOnline(userid);
			if(online)
				return AnchorOnlineStateEnum.online.getCode();
		}
		return 0;
	}

	@Override
	public int onlineState(long userid) throws Exception {
		//首先检测是否处于在聊状态
		boolean anchor = anchorOnlineStateAgent.existsTalking(userid);
		if(anchor)
			return AnchorOnlineStateEnum.talking.getCode();
		boolean online = userOnlineListAgent.existsOnline(userid);
		if(online)
			return AnchorOnlineStateEnum.online.getCode();
		return 0;
	}

}
