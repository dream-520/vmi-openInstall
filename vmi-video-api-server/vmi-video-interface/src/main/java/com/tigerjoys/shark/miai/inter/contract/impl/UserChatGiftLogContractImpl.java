package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserChatGiftLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserChatGiftLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserChatGiftLogMapper;

/**
 * 数据库中  礼物消费记录[t_user_chat_gift_log]表 接口实现类
 * @author mouzhanpeng
 * @Date 2018-07-24 12:01:43
 *
 */
@Repository
public class UserChatGiftLogContractImpl extends AbstractBaseContract<UserChatGiftLogEntity , UserChatGiftLogMapper> implements IUserChatGiftLogContract {
	
}
