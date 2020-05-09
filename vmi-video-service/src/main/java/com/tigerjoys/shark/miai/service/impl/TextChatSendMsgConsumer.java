package com.tigerjoys.shark.miai.service.impl;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.shark.miai.common.delayqueue.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.tigerjoys.nbs.common.utils.ExecutorServiceHelper;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.agent.INeteaseAgent;
import com.tigerjoys.shark.miai.inter.contract.IAnchorOnlineContract;
import com.tigerjoys.shark.miai.inter.contract.IVchatRoomContract;
import com.tigerjoys.shark.miai.inter.entity.VchatRoomEntity;

/**
 * 
 * @author yangjunming
 *
 */
@Service
public class TextChatSendMsgConsumer {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private static DelayQueue<Message> queue = new DelayQueue<Message>();

	@Autowired
	private INeteaseAgent neteaseAgent;
	
	public void put(Message msg){
		queue.offer(msg);
	}
	
	
	@PostConstruct
	public void init() throws Exception {

		ExecutorServiceHelper.executor.execute(new SendMsgConsumerThread(queue));

	}

	public  class SendMsgConsumerThread implements Runnable {
		private DelayQueue<Message> queue;

		public SendMsgConsumerThread(DelayQueue<Message> queue) {
			this.queue = queue;
		}

		@Override
		public void run() {
			while (true) {
				try {
					Message take = queue.take();
					logger.info("SendMsgConsumerThread:消费消息id：" + take.getId() + " 消息体：" + take.getBody());
					JSONObject json = JsonHelper.toJsonObject(take.getBody());
					long userId = json.getLongValue("userId");
					long toUserId = json.getLongValue("toUserId");
					String msg = json.getString("msg");
					neteaseAgent.pushOneMessage(userId, toUserId,msg,false);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	}
}
