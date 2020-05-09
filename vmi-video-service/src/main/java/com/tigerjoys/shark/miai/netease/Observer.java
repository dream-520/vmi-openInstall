package com.tigerjoys.shark.miai.netease;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.mybatis.core.utils.SpringBeanApplicationContext;
import com.tigerjoys.shark.miai.agent.dto.UserBO;

/** 
  * 网易消息接收者
  * @author mouzhanpeng at [2017年11月29日 下午6:21:38] 
  * @since JDK 1.8.0 
  */
public abstract class Observer {

	{
		SpringBeanApplicationContext.getBean(AttachMessageObservable.class).attachObserver(this);
	}
	
	/**
	 * 处理抄送消息
	 * @param from
	 * @param to
	 * @param json
	 */
	public abstract void deal(UserBO from, UserBO to, JSONObject json) throws Exception;
}
