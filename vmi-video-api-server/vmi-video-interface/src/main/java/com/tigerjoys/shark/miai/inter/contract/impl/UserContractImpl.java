package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.contract.IUserContract;
import com.tigerjoys.shark.miai.inter.entity.UserEntity;
import com.tigerjoys.shark.miai.inter.mapper.UserMapper;

/**
 * 数据库中  用户基础表[t_user]表 接口实现类
 * @author chengang
 * @Date 2017-04-12 11:26:55
 *
 */
@Repository
public class UserContractImpl extends AbstractBaseContract<UserEntity , UserMapper> implements IUserContract {

	@Override
	public UserEntity findByUsername(String username) throws Exception {
		if(Tools.isNull(username)) return null;
		
		return mapper.findByProperty("username", username);
	}

	@Override
	public UserEntity findByNickname(String nickname) throws Exception {
		if(Tools.isNull(nickname)) return null;
		
		return mapper.findByProperty("nickname", nickname);
	}

	@Override
	public UserEntity findByUniqueKey(String uniqueKey) throws Exception {
		if(Tools.isNull(uniqueKey)) return null;
		
		return mapper.findByProperty("unique_key", uniqueKey);
	}

	@Override
	public UserEntity findByMobile(String mobile) throws Exception {
		if(mobile == null) return null;
		
		return mapper.findByProperty("mobile", mobile);
	}
	
}
