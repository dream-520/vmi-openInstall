package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserTextChatInfoLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserTextChatInfoLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserTextChatInfoLogMapper;

/**
 * 数据库中  用户文字聊天详细记录[t_user_text_chat_info_log]表 接口实现类
 * @author yangjunming
 * @Date 2019-08-26 11:23:17
 *
 */
@Repository
public class UserTextChatInfoLogContractImpl extends AbstractBaseContract<UserTextChatInfoLogEntity , UserTextChatInfoLogMapper> implements IUserTextChatInfoLogContract {
	
}
