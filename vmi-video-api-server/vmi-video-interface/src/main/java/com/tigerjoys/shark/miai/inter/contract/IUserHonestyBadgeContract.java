package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.UserHonestyBadgeEntity;
import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  用户诚信徽章表[t_user_honesty_badge]表 接口类
 * @author mouzhanpeng
 * @Date 2017-11-13 16:52:00
 *
 */
public interface IUserHonestyBadgeContract extends BaseContract<UserHonestyBadgeEntity> {
	
	/**
	 * 根据用户ID加锁查询获得对象  
	 * @param user - Long
	 * @return UserHonestyBadgeEntity
	 */
	public abstract UserHonestyBadgeEntity lockByUserId(long userId);
}
