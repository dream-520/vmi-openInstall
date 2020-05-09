package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.nbs.mybatis.core.BaseContract;
import com.tigerjoys.shark.miai.inter.entity.UserWeekcardAccountEntity;

/**
 * 数据库中  用户周卡账户表[t_user_weekcard_account]表 接口类
 * @author yangjunming
 * @Date 2019-11-13 14:10:56
 *
 */
public interface IUserWeekcardAccountContract extends BaseContract<UserWeekcardAccountEntity> {
	
	/**
	 * 当前周卡加锁
	 * @param userId
	 * @return
	 */
	public UserWeekcardAccountEntity lockByUserId(long userId, long cardId);
}
