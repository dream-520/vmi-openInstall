package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IStaticUserRemainCashContract;
import com.tigerjoys.shark.miai.inter.entity.StaticUserRemainCashEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.StaticUserRemainCashMapper;

/**
 * 数据库中  红包现金账户统计[t_static_user_remain_cash]表 接口实现类
 * @author yangjunming
 * @Date 2017-05-21 19:00:29
 *
 */
@Repository
public class StaticUserRemainCashContractImpl extends AbstractBaseContract<StaticUserRemainCashEntity , StaticUserRemainCashMapper> implements IStaticUserRemainCashContract {
	
	@Override
	public StaticUserRemainCashEntity getStaticUserRemainCash(long id, int type) throws Exception {
		return mapper.getStaticUserRemainCash(id, type);
	}
	
}
