package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.RedFlowerAccountEntity;
import com.tigerjoys.shark.miai.inter.entity.UserChatGiftBoxEntity;
import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  用户礼物盒[t_user_chat_gift_box]表 接口类
 * @author yangjunming
 * @Date 2019-10-30 11:55:38
 *
 */
public interface IUserChatGiftBoxContract extends BaseContract<UserChatGiftBoxEntity> {
	
	/**
	 * 当前礼物盒加锁
	 * @param userId
	 * @return
	 */
	public UserChatGiftBoxEntity lockByUserId(long userId, long giftId);
}
