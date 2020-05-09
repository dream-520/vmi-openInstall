package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.UserBuyWatchPopLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  用户购买弹窗记录[t_user_buy_watch_pop_log]表 接口类
 * @author yangjunming
 * @Date 2019-12-04 20:21:31
 *
 */
public interface IUserBuyWatchPopLogContract extends BaseContract<UserBuyWatchPopLogEntity> {
	/**
	 * 
	 * @param userId
	 * @param type 参照：RechargeCategoryPriceEnum
	 * @return
	 */
	public UserBuyWatchPopLogEntity getUserBuyWatchPopLog(long userId,int type) throws Exception;
	
}
