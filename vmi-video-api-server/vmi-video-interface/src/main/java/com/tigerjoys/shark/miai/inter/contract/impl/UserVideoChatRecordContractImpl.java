package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserVideoChatRecordContract;
import com.tigerjoys.shark.miai.inter.entity.UserVideoChatRecordEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserVideoChatRecordMapper;

/**
 * 数据库中  用户视频会话记录[t_user_video_chat_record]表 接口实现类
 * @author mouzhanpeng
 * @Date 2018-01-24 17:57:25
 *
 */
@Repository
public class UserVideoChatRecordContractImpl extends AbstractBaseContract<UserVideoChatRecordEntity , UserVideoChatRecordMapper> implements IUserVideoChatRecordContract {

	@Override
	public UserVideoChatRecordEntity lockById(long id) throws Exception {
		return mapper.lockById(id);
	}
	
	
}
