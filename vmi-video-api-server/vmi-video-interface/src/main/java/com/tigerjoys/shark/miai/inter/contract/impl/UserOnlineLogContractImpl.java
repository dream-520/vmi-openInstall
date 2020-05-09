package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserOnlineLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserOnlineLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserOnlineLogMapper;

/**
 * 数据库中  用户在线记录表[t_user_online_log]表 接口实现类
 * @author chengang
 * @Date 2017-05-10 18:20:19
 *
 */
@Repository
public class UserOnlineLogContractImpl extends AbstractBaseContract<UserOnlineLogEntity , UserOnlineLogMapper> implements IUserOnlineLogContract {
	
}
