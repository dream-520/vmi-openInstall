package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.ICopyUserChatGiftLogContract;
import com.tigerjoys.shark.miai.inter.entity.CopyUserChatGiftLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.CopyUserChatGiftLogMapper;

/**
 * 数据库中  礼物消费记录[t_copy_user_chat_gift_log]表 接口实现类
 * @author yangjunming
 * @Date 2019-12-17 19:03:27
 *
 */
@Repository
public class CopyUserChatGiftLogContractImpl extends AbstractBaseContract<CopyUserChatGiftLogEntity , CopyUserChatGiftLogMapper> implements ICopyUserChatGiftLogContract {
	
}
