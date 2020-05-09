package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserHonestyBadgeLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserHonestyBadgeLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserHonestyBadgeLogMapper;

/**
 * 数据库中  用户诚信徽章充值记录[t_user_honesty_badge_log]表 接口实现类
 * @author mouzhanpeng
 * @Date 2017-11-13 16:52:00
 *
 */
@Repository
public class UserHonestyBadgeLogContractImpl extends AbstractBaseContract<UserHonestyBadgeLogEntity , UserHonestyBadgeLogMapper> implements IUserHonestyBadgeLogContract {
	
}
