package com.tigerjoys.shark.miai.inter.contract.impl;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserChargeDataLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserChargeDataLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserChargeDataLogMapper;

/**
 * 数据库中  用户充值数据流水表[t_user_charge_data_log]表 接口实现类
 * @author chengang
 * @Date 2018-01-26 08:21:25
 *
 */
@Repository
public class UserChargeDataLogContractImpl extends AbstractBaseContract<UserChargeDataLogEntity , UserChargeDataLogMapper> implements IUserChargeDataLogContract {

	@Override
	public long getSumAmount(long userid , Date date) {
		Long total = mapper.getSumAmount(userid, date);
		
		return total == null ? 0L : total.longValue();
	}
	
}
