package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserChatGiftBoxLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserChatGiftBoxLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserChatGiftBoxLogMapper;

/**
 * 数据库中  用户礼物盒流水[t_user_chat_gift_box_log]表 接口实现类
 * @author yangjunming
 * @Date 2019-10-30 11:55:38
 *
 */
@Repository
public class UserChatGiftBoxLogContractImpl extends AbstractBaseContract<UserChatGiftBoxLogEntity , UserChatGiftBoxLogMapper> implements IUserChatGiftBoxLogContract {
	
}
