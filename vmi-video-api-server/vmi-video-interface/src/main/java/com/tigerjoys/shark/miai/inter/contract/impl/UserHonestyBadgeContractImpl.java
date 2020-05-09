package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserHonestyBadgeContract;
import com.tigerjoys.shark.miai.inter.entity.UserHonestyBadgeEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserHonestyBadgeMapper;

/**
 * 数据库中  用户诚信徽章表[t_user_honesty_badge]表 接口实现类
 * @author mouzhanpeng
 * @Date 2017-11-13 16:52:00
 *
 */
@Repository
public class UserHonestyBadgeContractImpl extends AbstractBaseContract<UserHonestyBadgeEntity , UserHonestyBadgeMapper> implements IUserHonestyBadgeContract {
	
	@Override
	public UserHonestyBadgeEntity lockByUserId(long userId) {
		return mapper.lockByUserId(userId);
	}
}
