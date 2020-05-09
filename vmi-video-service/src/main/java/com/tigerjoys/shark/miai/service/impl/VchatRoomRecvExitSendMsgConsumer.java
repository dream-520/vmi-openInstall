package com.tigerjoys.shark.miai.service.impl;

import java.util.concurrent.DelayQueue;

import javax.annotation.PostConstruct;

import org.shark.miai.common.delayqueue.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.utils.ExecutorServiceHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.agent.INeteaseAgent;
import com.tigerjoys.shark.miai.inter.contract.IVchatRoomContract;
import com.tigerjoys.shark.miai.inter.entity.VchatRoomEntity;

/**
 * 
 * @author yangjunming
 *
 */
@Service
public class VchatRoomRecvExitSendMsgConsumer {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private static DelayQueue<Message> queue = new DelayQueue<Message>();

	@Autowired
	private INeteaseAgent neteaseAgent;
	

	@Autowired
	private IVchatRoomContract vchatRoomContract;
	
	public void put(Message msg){
		queue.offer(msg);
	}
	
	
	@PostConstruct
	public void init() throws Exception {

		ExecutorServiceHelper.executor.execute(new VchatRoomRecvExitSendMsgConsumerThread(queue));

	}

	public  class VchatRoomRecvExitSendMsgConsumerThread implements Runnable {
		private DelayQueue<Message> queue;

		public VchatRoomRecvExitSendMsgConsumerThread(DelayQueue<Message> queue) {
			this.queue = queue;
		}

		@Override
		public void run() {
			while (true) {
				try {
					Message take = queue.take();
					logger.info("VchatRoomRecvExitSendMsgConsumer:消费消息id：" + take.getId() + " 消息体：" + take.getBody());
					String []body = Tools.split(take.getBody());
					VchatRoomEntity vchatRoom = vchatRoomContract.findById(Long.parseLong(take.getId()));
					if(Tools.isNotNull(vchatRoom)){
						if (Tools.isNotNull(vchatRoom.getPay_create()) && Tools.isNotNull(vchatRoom.getFinish_time())) {
							Long recvUser = vchatRoom.getSponsor_user().equals(vchatRoom.getUserid())?vchatRoom.getAnchorid():vchatRoom.getUserid();
							String hms = "";
							if(vchatRoom.getSys_duration()>0){
				    			if(vchatRoom.getSys_duration()<vchatRoom.getVchat_duration()*60){
				    				int sysShowDuration = vchatRoom.getSys_duration()/60;
				    				int mod = vchatRoom.getSys_duration()%60;
				    				if(mod >0){
				    					sysShowDuration = vchatRoom.getSys_duration()/60+1;
				    				}
				    				if(sysShowDuration == vchatRoom.getVchat_duration()){
				    					hms = Tools.formatMillisToDuration(vchatRoom.getSys_duration()*1000);
				    				}else{
				    					hms = Tools.formatMillisToDuration(vchatRoom.getVchat_duration()*60*1000);
				    				}
				    			}else{
				    				hms = Tools.formatMillisToDuration(vchatRoom.getVchat_duration()*60*1000);
				    			}
				    		}else{
				    			hms = Tools.formatMillisToDuration(vchatRoom.getVchat_duration()*60*1000);
				    		}
							//String msg = "通话时长为" + hms;
							String msg = "通话结束";
							neteaseAgent.pushOneMessage(vchatRoom.getSponsor_user(), recvUser,msg,true);
							
							Long otherId = Long.valueOf(body[0]);
							Long oldOtherId = Long.valueOf(body[1]);
							Long userId = Long.valueOf(body[2]);
							if (!otherId.equals(oldOtherId)) {
								neteaseAgent.pushOneMessage(userId, otherId, msg,false);
							}
							
						}
					}
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	}
}
