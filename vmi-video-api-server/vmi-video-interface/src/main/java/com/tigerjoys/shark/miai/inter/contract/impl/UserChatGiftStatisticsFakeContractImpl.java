package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserChatGiftStatisticsFakeContract;
import com.tigerjoys.shark.miai.inter.entity.UserChatGiftStatisticsFakeEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserChatGiftStatisticsFakeMapper;

/**
 * 数据库中  用户聊天送礼物数量统计表[t_user_chat_gift_statistics_fake]表 接口实现类
 * @author lipeng
 * @Date 2019-07-17 16:50:03
 *
 */
@Repository
public class UserChatGiftStatisticsFakeContractImpl extends AbstractBaseContract<UserChatGiftStatisticsFakeEntity , UserChatGiftStatisticsFakeMapper> implements IUserChatGiftStatisticsFakeContract {
	
}
