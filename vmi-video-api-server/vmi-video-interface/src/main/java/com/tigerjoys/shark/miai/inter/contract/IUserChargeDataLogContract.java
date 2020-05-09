package com.tigerjoys.shark.miai.inter.contract;

import java.util.Date;

import com.tigerjoys.nbs.mybatis.core.BaseContract;
import com.tigerjoys.shark.miai.inter.entity.UserChargeDataLogEntity;

/**
 * 数据库中  用户充值数据流水表[t_user_charge_data_log]表 接口类
 * @author chengang
 * @Date 2018-01-26 08:21:25
 *
 */
public interface IUserChargeDataLogContract extends BaseContract<UserChargeDataLogEntity> {
	
	/**
	 * 获得指定日期-至今的充值累计金额
	 * @param userid - 用户ID
	 * @param date - Date
	 * @return long 单位分
	 */
	public long getSumAmount(long userid , Date date);
	
}
