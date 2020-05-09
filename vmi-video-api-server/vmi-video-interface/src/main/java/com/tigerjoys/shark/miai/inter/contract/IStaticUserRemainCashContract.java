package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.StaticUserRemainCashEntity;
import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  红包现金账户统计[t_static_user_remain_cash]表 接口类
 * @author yangjunming
 * @Date 2017-05-21 19:00:29
 *
 */
public interface IStaticUserRemainCashContract extends BaseContract<StaticUserRemainCashEntity> {
	
	/**
	 * 根据主键获得对象
	 * @param id - long id
	 * @param type - int 类型 1 累计现金  2 剩余现金
	 * return StaticUserRemainCashEntity
	 * @throw Exception
	 *
	 */
	public StaticUserRemainCashEntity getStaticUserRemainCash(long id, int type) throws Exception;
	
}
