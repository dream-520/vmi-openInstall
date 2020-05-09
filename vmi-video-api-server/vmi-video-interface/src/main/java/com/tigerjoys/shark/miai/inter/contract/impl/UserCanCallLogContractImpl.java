package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserCanCallLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserCanCallLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserCanCallLogMapper;

/**
 * 数据库中  可拨打用户记录表[t_user_can_call_log]表 接口实现类
 * @author lipeng
 * @Date 2019-10-14 18:25:51
 *
 */
@Repository
public class UserCanCallLogContractImpl extends AbstractBaseContract<UserCanCallLogEntity , UserCanCallLogMapper> implements IUserCanCallLogContract {
	
}
