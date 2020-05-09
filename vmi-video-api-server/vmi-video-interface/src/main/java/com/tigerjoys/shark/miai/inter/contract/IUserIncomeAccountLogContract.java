package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.UserIncomeAccountLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  用户收益流水[t_user_income_account_log]表 接口类
 * @author mouzhanpeng
 * @Date 2017-08-16 15:37:02
 *
 */
public interface IUserIncomeAccountLogContract extends BaseContract<UserIncomeAccountLogEntity> {
	
	public Double getAnchorOneDayIncome(long userid, String start, String end);
}
