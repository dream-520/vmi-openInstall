package com.tigerjoys.shark.miai.inter.contract.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserDynamicContract;
import com.tigerjoys.shark.miai.inter.entity.UserDynamicEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserDynamicMapper;

/**
 * 数据库中  动态表[t_user_dynamic]表 接口实现类
 * @author shiming
 * @Date 2017-08-14 14:42:23
 *
 */
@Repository
public class UserDynamicContractImpl extends AbstractBaseContract<UserDynamicEntity , UserDynamicMapper> implements IUserDynamicContract {

	@Override
	public List<UserDynamicEntity> findAttentionDynamic(long userid, String stamp, long limit) {
		return mapper.findAttentionDynamic(userid, stamp, limit);
	}
	
}
