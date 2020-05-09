package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserChatTurntableLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserChatTurntableLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserChatTurntableLogMapper;

/**
 * 数据库中  转盘消费记录[t_user_chat_turntable_log]表 接口实现类
 * @author yangjunming
 * @Date 2019-07-27 16:13:40
 *
 */
@Repository
public class UserChatTurntableLogContractImpl extends AbstractBaseContract<UserChatTurntableLogEntity , UserChatTurntableLogMapper> implements IUserChatTurntableLogContract {
	
}
