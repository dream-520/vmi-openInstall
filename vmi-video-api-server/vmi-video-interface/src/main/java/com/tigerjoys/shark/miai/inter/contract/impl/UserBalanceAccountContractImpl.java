package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.contract.IUserBalanceAccountContract;
import com.tigerjoys.shark.miai.inter.entity.UserBalanceAccountEntity;
import com.tigerjoys.shark.miai.inter.mapper.UserBalanceAccountMapper;

/**
 * 数据库中  用户余额账户[t_user_balance_account]表 接口实现类
 * @author chengang
 * @Date 2017-08-10 11:02:06
 *
 */
@Repository
public class UserBalanceAccountContractImpl extends AbstractBaseContract<UserBalanceAccountEntity , UserBalanceAccountMapper> implements IUserBalanceAccountContract {
	
	@Override
	public UserBalanceAccountEntity lockByUserId(long userId) throws Exception {
		return mapper.lockByUserId(userId);
	}
	
}
