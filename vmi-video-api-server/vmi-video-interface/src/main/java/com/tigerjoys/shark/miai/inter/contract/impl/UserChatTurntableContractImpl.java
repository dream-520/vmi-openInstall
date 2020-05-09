package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserChatTurntableContract;
import com.tigerjoys.shark.miai.inter.entity.UserChatTurntableEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserChatTurntableMapper;

/**
 * 数据库中  转盘项目列表[t_user_chat_turntable]表 接口实现类
 * @author yangjunming
 * @Date 2019-07-26 18:11:17
 *
 */
@Repository
public class UserChatTurntableContractImpl extends AbstractBaseContract<UserChatTurntableEntity , UserChatTurntableMapper> implements IUserChatTurntableContract {
	
}
