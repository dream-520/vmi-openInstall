package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserVideoChatRecordLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserVideoChatRecordLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserVideoChatRecordLogMapper;

/**
 * 数据库中  用户视频会话记录流水（每分钟计一次）[t_user_video_chat_record_log]表 接口实现类
 * @author mouzhanpeng
 * @Date 2018-01-24 17:57:25
 *
 */
@Repository
public class UserVideoChatRecordLogContractImpl extends AbstractBaseContract<UserVideoChatRecordLogEntity , UserVideoChatRecordLogMapper> implements IUserVideoChatRecordLogContract {
	
}
