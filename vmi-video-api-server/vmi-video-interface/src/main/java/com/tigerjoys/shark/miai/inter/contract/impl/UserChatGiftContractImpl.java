package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserChatGiftContract;
import com.tigerjoys.shark.miai.inter.entity.UserChatGiftEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserChatGiftMapper;

/**
 * 数据库中  礼物列表[t_user_chat_gift]表 接口实现类
 * @author mouzhanpeng
 * @Date 2018-07-24 12:01:43
 *
 */
@Repository
public class UserChatGiftContractImpl extends AbstractBaseContract<UserChatGiftEntity , UserChatGiftMapper> implements IUserChatGiftContract {
	
}
