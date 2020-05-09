package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserChatGiftStatisticsContract;
import com.tigerjoys.shark.miai.inter.entity.UserChatGiftStatisticsEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserChatGiftStatisticsMapper;

/**
 * 数据库中  用户聊天送礼物数量统计表[t_user_chat_gift_statistics]表 接口实现类
 * @author lipeng
 * @Date 2019-07-09 13:53:47
 *
 */
@Repository
public class UserChatGiftStatisticsContractImpl extends AbstractBaseContract<UserChatGiftStatisticsEntity , UserChatGiftStatisticsMapper> implements IUserChatGiftStatisticsContract {
	
}
