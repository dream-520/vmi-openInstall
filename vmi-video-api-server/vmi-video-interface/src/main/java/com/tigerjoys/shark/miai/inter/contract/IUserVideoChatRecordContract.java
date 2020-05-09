package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.UserVideoChatRecordEntity;
import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  用户视频会话记录[t_user_video_chat_record]表 接口类
 * @author mouzhanpeng
 * @Date 2018-01-24 17:57:25
 *
 */
public interface IUserVideoChatRecordContract extends BaseContract<UserVideoChatRecordEntity> {
	
	/**
	 * 查询并使用锁，必须用于事务中
	 * @param userId - 用户ID
	 * @return UserScvcAccountEntity
	 * @throws Exception
	 */
	public UserVideoChatRecordEntity lockById(long id) throws Exception;
}
