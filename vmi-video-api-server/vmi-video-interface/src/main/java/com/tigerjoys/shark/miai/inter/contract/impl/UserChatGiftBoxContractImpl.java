package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserChatGiftBoxContract;
import com.tigerjoys.shark.miai.inter.entity.RedFlowerAccountEntity;
import com.tigerjoys.shark.miai.inter.entity.UserChatGiftBoxEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserChatGiftBoxMapper;

/**
 * 数据库中  用户礼物盒[t_user_chat_gift_box]表 接口实现类
 * @author yangjunming
 * @Date 2019-10-30 11:55:38
 *
 */
@Repository
public class UserChatGiftBoxContractImpl extends AbstractBaseContract<UserChatGiftBoxEntity , UserChatGiftBoxMapper> implements IUserChatGiftBoxContract {

	@Override
	public UserChatGiftBoxEntity lockByUserId(long userId, long giftId) {
		return mapper.lockByUserId(userId, giftId);
	}
	
	
}
