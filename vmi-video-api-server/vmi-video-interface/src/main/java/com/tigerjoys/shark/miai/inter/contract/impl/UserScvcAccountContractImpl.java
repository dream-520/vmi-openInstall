package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserScvcAccountContract;
import com.tigerjoys.shark.miai.inter.entity.UserScvcAccountEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserScvcAccountMapper;

/**
 * 数据库中  用户SCVC账户[t_user_scvc_account]表 接口实现类
 * @author mouzhanpeng
 * @Date 2018-01-24 17:57:24
 *
 */
@Repository
public class UserScvcAccountContractImpl extends AbstractBaseContract<UserScvcAccountEntity , UserScvcAccountMapper> implements IUserScvcAccountContract {

	@Override
	public UserScvcAccountEntity lockByUserId(long userId) throws Exception {
		return mapper.lockByUserId(userId);
	}
	
	
}
