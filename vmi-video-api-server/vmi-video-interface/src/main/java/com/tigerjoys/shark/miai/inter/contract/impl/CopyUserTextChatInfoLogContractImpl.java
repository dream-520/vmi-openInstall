package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.ICopyUserTextChatInfoLogContract;
import com.tigerjoys.shark.miai.inter.entity.CopyUserTextChatInfoLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.CopyUserTextChatInfoLogMapper;

/**
 * 数据库中  用户文字聊天详细记录[t_copy_user_text_chat_info_log]表 接口实现类
 * @author yangjunming
 * @Date 2019-12-17 17:04:13
 *
 */
@Repository
public class CopyUserTextChatInfoLogContractImpl extends AbstractBaseContract<CopyUserTextChatInfoLogEntity , CopyUserTextChatInfoLogMapper> implements ICopyUserTextChatInfoLogContract {
	
}
