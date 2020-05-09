package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserPayActionContract;
import com.tigerjoys.shark.miai.inter.entity.UserPayActionEntity;
import com.tigerjoys.nbs.common.utils.sequence.IdGenerater;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserPayActionMapper;

/**
 * 数据库中  用户支付记录[t_user_pay_action]表 接口实现类
 * @author mouzhanpeng
 * @Date 2017-08-14 16:23:30
 *
 */
@Repository
public class UserPayActionContractImpl extends AbstractBaseContract<UserPayActionEntity , UserPayActionMapper> implements IUserPayActionContract {
	
	@Override
	public void insert(UserPayActionEntity t) throws Exception {
		t.setId(IdGenerater.generateId());
		mapper.insert(t);
	}
	
	/**
	 * 插入或更新
	 * @param t
	 */
	public void insertOnDuplicate(UserPayActionEntity t){
		t.setId(IdGenerater.generateId());
		mapper.insertOnDuplicate(t);
	}

	@Override
	public int userRechargeMoney(long userid) {
		return mapper.userRechargeMoney(userid);
	}
}
