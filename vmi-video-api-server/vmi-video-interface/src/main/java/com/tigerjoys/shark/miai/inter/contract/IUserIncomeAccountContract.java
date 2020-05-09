package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.UserIncomeAccountEntity;

import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  用户收益账户[t_user_income_account]表 接口类
 * @author mouzhanpeng
 * @Date 2017-08-16 15:37:02
 *
 */
public interface IUserIncomeAccountContract extends BaseContract<UserIncomeAccountEntity> {
	/**
	 * 根据用户ID加锁查询获得对象  
	 * @param user - Long
	 * @return UserIncomeAccountEntity
	 */
	public abstract UserIncomeAccountEntity lockByUserId(long userId, int type);
	
	
	/**
	 * 根据用户ID,类型查询获得对象  
	 * @param user - Long
	 * @return UserIncomeAccountEntity
	 */
	public UserIncomeAccountEntity findByUserIdAndType(long userId, int type);
}
