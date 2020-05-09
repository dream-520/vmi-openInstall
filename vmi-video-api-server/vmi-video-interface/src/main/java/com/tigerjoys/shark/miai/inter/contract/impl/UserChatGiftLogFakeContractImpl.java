package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserChatGiftLogFakeContract;
import com.tigerjoys.shark.miai.inter.entity.UserChatGiftLogFakeEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserChatGiftLogFakeMapper;

/**
 * 数据库中  礼物消费记录[t_user_chat_gift_log_fake]表 接口实现类
 * @author lipeng
 * @Date 2019-07-17 16:12:29
 *
 */
@Repository
public class UserChatGiftLogFakeContractImpl extends AbstractBaseContract<UserChatGiftLogFakeEntity , UserChatGiftLogFakeMapper> implements IUserChatGiftLogFakeContract {
	
}
