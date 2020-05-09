package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.contract.IUserAudioChatInfoLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserAudioChatInfoLogEntity;
import com.tigerjoys.shark.miai.inter.mapper.UserAudioChatInfoLogMapper;

/**
 * 数据库中  用户语音聊天详细记录[t_user_audio_chat_info_log]表 接口实现类
 * @author yangjunming
 * @Date 2019-12-04 20:21:31
 *
 */
@Repository
public class UserAudioChatInfoLogContractImpl extends AbstractBaseContract<UserAudioChatInfoLogEntity , UserAudioChatInfoLogMapper> implements IUserAudioChatInfoLogContract {
	
	/**
	 * 根据用户ID加锁查询获得对象  
	 * @param id - Long
	 * @return UserAudioChatInfoLogEntity
	 */
	public  UserAudioChatInfoLogEntity lockById(long id) throws Exception{
		return mapper.lockById(id);
	}

	@Override
	public int updateAudioRecvWatchFalg(long id) throws Exception {
		return mapper.updateByStatement("audio_recv_watch_falg=1", "id="+id+" and audio_recv_watch_falg=0");
	}
	
	
}
