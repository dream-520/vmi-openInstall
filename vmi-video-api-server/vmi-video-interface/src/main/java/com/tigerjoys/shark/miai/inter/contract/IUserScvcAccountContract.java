package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.UserScvcAccountEntity;
import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  用户SCVC账户[t_user_scvc_account]表 接口类
 * @author mouzhanpeng
 * @Date 2018-01-24 17:57:24
 *
 */
public interface IUserScvcAccountContract extends BaseContract<UserScvcAccountEntity> {
	
	/**
	 * 查询并使用锁，必须用于事务中
	 * @param userId - 用户ID
	 * @return UserScvcAccountEntity
	 * @throws Exception
	 */
	public UserScvcAccountEntity lockByUserId(long userId) throws Exception;
}
