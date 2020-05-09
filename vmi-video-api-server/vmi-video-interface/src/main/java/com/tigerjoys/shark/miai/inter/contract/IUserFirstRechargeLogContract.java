package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.UserFirstRechargeLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  用户首次充值记录[t_user_first_recharge_log]表 接口类
 * @author yangjunming
 * @Date 2019-07-31 15:13:37
 *
 */
public interface IUserFirstRechargeLogContract extends BaseContract<UserFirstRechargeLogEntity> {
	
	public boolean checkFirstRecharge(long userid, int type) throws Exception;
}
