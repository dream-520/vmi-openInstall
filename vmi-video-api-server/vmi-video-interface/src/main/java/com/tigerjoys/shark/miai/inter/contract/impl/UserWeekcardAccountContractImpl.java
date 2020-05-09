package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.contract.IUserWeekcardAccountContract;
import com.tigerjoys.shark.miai.inter.entity.UserWeekcardAccountEntity;
import com.tigerjoys.shark.miai.inter.mapper.UserWeekcardAccountMapper;

/**
 * 数据库中  用户周卡账户表[t_user_weekcard_account]表 接口实现类
 * @author yangjunming
 * @Date 2019-11-13 14:10:56
 *
 */
@Repository
public class UserWeekcardAccountContractImpl extends AbstractBaseContract<UserWeekcardAccountEntity , UserWeekcardAccountMapper> implements IUserWeekcardAccountContract {
	
	@Override
	public UserWeekcardAccountEntity lockByUserId(long userId, long cardId) {
		return mapper.lockByUserId(userId, cardId);
	}
}
