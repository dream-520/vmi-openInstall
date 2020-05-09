package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserTextChatHistoryContract;
import com.tigerjoys.shark.miai.inter.entity.UserTextChatHistoryEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserTextChatHistoryMapper;

/**
 * 数据库中  文字聊天所有记录[t_user_text_chat_history]表 接口实现类
 * @author yangjunming
 * @Date 2019-10-08 19:47:06
 *
 */
@Repository
public class UserTextChatHistoryContractImpl extends AbstractBaseContract<UserTextChatHistoryEntity , UserTextChatHistoryMapper> implements IUserTextChatHistoryContract {
	
}
