package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.nbs.mybatis.core.BaseContract;
import com.tigerjoys.shark.miai.inter.entity.UserBalanceAccountEntity;

/**
 * 数据库中  用户余额账户[t_user_balance_account]表 接口类
 * @author chengang
 * @Date 2017-08-10 11:02:06
 *
 */
public interface IUserBalanceAccountContract extends BaseContract<UserBalanceAccountEntity> {
	
	/**
	 * 查询并使用锁，必须用于事务中
	 * @param userId - 用户ID
	 * @return UserBalanceAccountEntity
	 * @throws Exception
	 */
	public UserBalanceAccountEntity lockByUserId(long userId) throws Exception;
	
}
