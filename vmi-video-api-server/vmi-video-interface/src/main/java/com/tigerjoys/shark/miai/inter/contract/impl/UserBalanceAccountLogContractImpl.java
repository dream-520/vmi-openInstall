package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserBalanceAccountLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserBalanceAccountLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserBalanceAccountLogMapper;

/**
 * 数据库中  用户余额账户流水[t_user_balance_account_log]表 接口实现类
 * @author chengang
 * @Date 2017-08-10 11:02:06
 *
 */
@Repository
public class UserBalanceAccountLogContractImpl extends AbstractBaseContract<UserBalanceAccountLogEntity , UserBalanceAccountLogMapper> implements IUserBalanceAccountLogContract {
	
}
