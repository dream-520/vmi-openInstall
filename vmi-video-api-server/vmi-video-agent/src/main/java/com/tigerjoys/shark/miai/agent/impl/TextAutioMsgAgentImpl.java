package com.tigerjoys.shark.miai.agent.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.agent.ITextAutioMsgAgent;
import com.tigerjoys.shark.miai.agent.enums.TextAudioMsgEnum;
import com.tigerjoys.shark.miai.agent.enums.TextAudioMsgTypeEnum;
import com.tigerjoys.shark.miai.inter.contract.IUserAudioChatInfoLogContract;
import com.tigerjoys.shark.miai.inter.contract.IUserTextChatHistoryContract;
import com.tigerjoys.shark.miai.inter.contract.IUserTextChatInfoLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserAudioChatInfoLogEntity;
import com.tigerjoys.shark.miai.inter.entity.UserTextChatHistoryEntity;
import com.tigerjoys.shark.miai.inter.entity.UserTextChatInfoLogEntity;

/**
 * 代金券代理接口实现类
 * 
 * @author yangjunming
 *
 */
@Service
public class TextAutioMsgAgentImpl implements ITextAutioMsgAgent {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IUserAudioChatInfoLogContract userAudioChatInfoLogContract;
	
	@Autowired
	private IUserTextChatInfoLogContract userTextChatInfoLogContract;
	
	@Autowired
	private IUserTextChatHistoryContract userTextChatHistoryContract;

	@Override
	public int addAutioMsg(UserAudioChatInfoLogEntity audio) throws Exception {
		userAudioChatInfoLogContract.insert(audio);
		insertChatTextRecord(audio.getId(), audio.getUser_id(), audio.getOther_id(), audio.getAudio_url(), audio.getCheck_taskid(), audio.getFlower(),TextAudioMsgTypeEnum.audio,audio.getCheck_status(),audio.getCheck_type(),audio.getCheck_type_code());
		return 1;
	}

	
	@Override
	public int addPictureMsg(UserAudioChatInfoLogEntity log) throws Exception {
		insertChatTextRecord(log.getId(), log.getUser_id(), log.getOther_id(), log.getAudio_url(), log.getCheck_taskid(), log.getFlower(),TextAudioMsgTypeEnum.photo,log.getCheck_status(),log.getCheck_type(),log.getCheck_type_code());
		return 1;
	}
	
	public int updateAutioMsg(UserAudioChatInfoLogEntity audio) throws Exception{
		if (audio.getId()>0) {
			userAudioChatInfoLogContract.update(audio);
			
			UserTextChatInfoLogEntity log = new UserTextChatInfoLogEntity();
			log.setId(audio.getId());
			log.setCheck_status(audio.getCheck_status());
			log.setCheck_type(audio.getCheck_type());
			log.setCheck_type_code(audio.getCheck_type_code());
			if (Tools.isNotNull(audio.getCheck_text())) {
				log.setChat_text(audio.getCheck_text());
			}
		
			userTextChatInfoLogContract.update(log);
			
			UserTextChatHistoryEntity history = new UserTextChatHistoryEntity();
			history.setId(audio.getId());
			history.setCheck_status(audio.getCheck_status());
			history.setCheck_type(audio.getCheck_type());
			history.setCheck_type_code(audio.getCheck_type_code());
			history.setChat_text(audio.getCheck_text());
			if (Tools.isNotNull(audio.getCheck_text())) {
				history.setChat_text(audio.getCheck_text());
			}
			userTextChatHistoryContract.update(history);
			return 1;
		}else{
			return 0;
		}
	}
	
	
	/**
	 * 
	 * @param orderId	订单号
	 * @param userId	用户
	 * @param toUserId	对方ID
	 * @param audioUrl	音频地址
	 * @param taskId	网易ID
	 * @param checkStatus	核查状态
	 * @param checkType		核查类型
	 * @param flowerPrice	小红花
	 * @throws Exception
	 */
	private void insertChatTextRecord(long orderId,long userId,long toUserId,String pathUrl,String taskId,long flowerPrice,TextAudioMsgTypeEnum type,Integer status,String checkType,Integer checkTypeCode) throws Exception{
		UserTextChatInfoLogEntity log = new UserTextChatInfoLogEntity();
		log.setId(orderId);
		log.setUser_id(userId);
		log.setOther_id(toUserId);
		log.setAudio_falg(type.getCode());
		log.setAudio_url(pathUrl);
		log.setFlower(flowerPrice);
		log.setCheck_taskid(taskId);
		log.setCheck_status(Tools.isNull(status)?TextAudioMsgEnum.unknown.getCode():status);
		log.setCheck_type(Tools.isNull(checkType)?"":checkType);
		log.setCheck_type_code(Tools.isNull(checkTypeCode)? 0:checkTypeCode);
		log.setCreate_time(new Date());
		userTextChatInfoLogContract.insert(log);
		try {
			UserTextChatHistoryEntity history = new UserTextChatHistoryEntity();
			history.setLog_id(log.getId());
			history.setUser_id(log.getUser_id());
			history.setOther_id(log.getOther_id());
			history.setFlower(log.getFlower());
			history.setChat_text(log.getChat_text());
			history.setAudio_falg(type.getCode());
			history.setAudio_url(pathUrl);
			history.setCheck_taskid(log.getCheck_taskid());
			history.setCheck_status(log.getCheck_status());
			history.setCheck_type(log.getCheck_type());
			history.setCheck_type_code(log.getCheck_type_code());
			history.setSend_type(1);
			history.setCreate_time(new Date());
			userTextChatHistoryContract.insert(history);
		} catch (Exception e) {
			logger.info("查入失败",e);
		}
		
	}
	
}
