package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.UserIncomeAccountEntity;
import com.tigerjoys.shark.miai.inter.entity.UserNoWithdrawalBoundsEntity;
import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  用户不可提现收益限额[t_user_no_withdrawal_bounds]表 接口类
 * @author yangjunming
 * @Date 2019-08-05 14:15:28
 *
 */
public interface IUserNoWithdrawalBoundsContract extends BaseContract<UserNoWithdrawalBoundsEntity> {
	/**
	 * 根据用户ID加锁查询获得对象  
	 * @param user - Long
	 * @return UserIncomeAccountEntity
	 */
	public abstract UserNoWithdrawalBoundsEntity lockByUserId(long userId, int type);
	
	
	/**
	 * 根据用户ID,类型查询获得对象  
	 * @param user - Long
	 * @return UserIncomeAccountEntity
	 */
	public UserNoWithdrawalBoundsEntity findByUserIdAndType(long userId, int type);
	
	/**
	 * 更新不可提限余额
	 * @param userId     
	 * @param type   账户类型
	 * @param io    0 支出 1  收入   
	 * @param amount   金额
	 * @return
	 */
	public int updateBalance(long userId, int type,int io,long amount);
	
}
