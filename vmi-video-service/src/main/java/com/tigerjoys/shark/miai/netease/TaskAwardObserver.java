package com.tigerjoys.shark.miai.netease;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.shark.miai.agent.ITaskAgent;
import com.tigerjoys.shark.miai.agent.dto.UserBO;

/** 
  * 完成聊天任务
  * @author mouzhanpeng at [2017年11月30日 上午10:20:03] 
  * @since JDK 1.8.0 
  */
//@Service
public class TaskAwardObserver extends Observer {

	@Autowired
	private ITaskAgent taskAgent;
	
	@Override
	public void deal(UserBO from, UserBO to, JSONObject json) throws Exception {
		taskAgent.finishChatTask(from.getUserid(), to.getUserid());
	}
}
