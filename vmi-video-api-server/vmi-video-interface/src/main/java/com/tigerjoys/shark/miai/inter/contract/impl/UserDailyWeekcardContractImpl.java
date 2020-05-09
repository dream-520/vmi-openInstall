package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserDailyWeekcardContract;
import com.tigerjoys.shark.miai.inter.entity.UserDailyWeekcardEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserDailyWeekcardMapper;

/**
 * 数据库中  用户每日周卡[t_user_daily_weekcard]表 接口实现类
 * @author yangjunming
 * @Date 2019-11-16 11:01:30
 *
 */
@Repository
public class UserDailyWeekcardContractImpl extends AbstractBaseContract<UserDailyWeekcardEntity , UserDailyWeekcardMapper> implements IUserDailyWeekcardContract {
	
}
