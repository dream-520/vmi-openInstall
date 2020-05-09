package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IRedFlowerAccountContract;
import com.tigerjoys.shark.miai.inter.entity.RedFlowerAccountEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.RedFlowerAccountMapper;

/**
 * 数据库中  用户（购买的）红花账户[t_red_flower_account]表 接口实现类
 * @author mouzhanpeng
 * @Date 2017-12-20 15:29:35
 *
 */
@Repository
public class RedFlowerAccountContractImpl extends AbstractBaseContract<RedFlowerAccountEntity , RedFlowerAccountMapper> implements IRedFlowerAccountContract {

	@Override
	public RedFlowerAccountEntity lockByUserId(long userId) {
		return mapper.lockByUserId(userId);
	}
}
