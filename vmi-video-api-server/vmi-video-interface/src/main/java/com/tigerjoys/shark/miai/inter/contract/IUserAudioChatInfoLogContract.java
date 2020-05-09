package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.nbs.mybatis.core.BaseContract;
import com.tigerjoys.shark.miai.inter.entity.UserAudioChatInfoLogEntity;

/**
 * 数据库中  用户语音聊天详细记录[t_user_audio_chat_info_log]表 接口类
 * @author yangjunming
 * @Date 2019-12-04 20:21:31
 *
 */
public interface IUserAudioChatInfoLogContract extends BaseContract<UserAudioChatInfoLogEntity> {
	
	/**
	 * 根据用户ID加锁查询获得对象  
	 * @param userId - Long
	 * @return UserAudioChatInfoLogEntity
	 */
	public  UserAudioChatInfoLogEntity lockById(long id) throws Exception;
	
	
	/**
	 * 更新用户的查看状态  
	 * @param userId - Long
	 * @return UserAudioChatInfoLogEntity
	 */
	public  int  updateAudioRecvWatchFalg(long id) throws Exception;
}
