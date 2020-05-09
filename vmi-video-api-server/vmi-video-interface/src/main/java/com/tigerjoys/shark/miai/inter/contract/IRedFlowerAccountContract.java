package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.RedFlowerAccountEntity;
import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  用户（购买的）红花账户[t_red_flower_account]表 接口类
 * @author mouzhanpeng
 * @Date 2017-12-20 15:29:35
 *
 */
public interface IRedFlowerAccountContract extends BaseContract<RedFlowerAccountEntity> {
	/**
	 * 当前红花账户加锁
	 * @param userId
	 * @return
	 */
	public abstract RedFlowerAccountEntity lockByUserId(long userId);
}
