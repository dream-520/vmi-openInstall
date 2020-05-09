package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserIncomeAccountLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserIncomeAccountLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserIncomeAccountLogMapper;

/**
 * 数据库中  用户收益流水[t_user_income_account_log]表 接口实现类
 * @author mouzhanpeng
 * @Date 2017-08-16 15:37:02
 *
 */
@Repository
public class UserIncomeAccountLogContractImpl extends AbstractBaseContract<UserIncomeAccountLogEntity , UserIncomeAccountLogMapper> implements IUserIncomeAccountLogContract {

	@Override
	public Double getAnchorOneDayIncome(long userid, String start, String end) {
		return mapper.getAnchorOneDayIncome(userid, start, end);
	}
	
}
