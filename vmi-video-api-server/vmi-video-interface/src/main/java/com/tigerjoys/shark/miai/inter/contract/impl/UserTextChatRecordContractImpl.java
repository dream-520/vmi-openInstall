package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserTextChatRecordContract;
import com.tigerjoys.shark.miai.inter.entity.UserTextChatRecordEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserTextChatRecordMapper;

/**
 * 数据库中  用户图文聊天记录[t_user_text_chat_record]表 接口实现类
 * @author mouzhanpeng
 * @Date 2018-08-21 15:03:18
 *
 */
@Repository
public class UserTextChatRecordContractImpl extends AbstractBaseContract<UserTextChatRecordEntity , UserTextChatRecordMapper> implements IUserTextChatRecordContract {
	
}
