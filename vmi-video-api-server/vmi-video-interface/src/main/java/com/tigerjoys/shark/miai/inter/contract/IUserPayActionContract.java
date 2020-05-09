package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.UserPayActionEntity;

import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  用户支付记录[t_user_pay_action]表 接口类
 * @author mouzhanpeng
 * @Date 2017-08-14 16:23:30
 *
 */
public interface IUserPayActionContract extends BaseContract<UserPayActionEntity> {
	
	/**
	 * 获取用户总的充值
	 * @param userid
	 * @return
	 */
	public int userRechargeMoney(long userid);
}
