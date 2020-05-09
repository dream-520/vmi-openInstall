package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import org.apache.ibatis.annotations.Select;

import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserAudioChatInfoLogEntity;

/**
 * 数据库  用户语音聊天详细记录[t_user_audio_chat_info_log]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-12-04 20:21:31
 *
 */
@Producer(entityType=UserAudioChatInfoLogEntity.class,providerType=DefaultSqlProvider.class,increment=false)
@Mapper
public interface UserAudioChatInfoLogMapper extends BaseMapper<UserAudioChatInfoLogEntity> {
    
	/**
	 * 根据用户ID加锁查询获得对象  
	 * @param id - Long
	 * @return UserAudioChatInfoLogEntity
	 */
	@Select("select * from t_user_audio_chat_info_log where id = #{id} for update")
	public abstract UserAudioChatInfoLogEntity lockById(long id);
}