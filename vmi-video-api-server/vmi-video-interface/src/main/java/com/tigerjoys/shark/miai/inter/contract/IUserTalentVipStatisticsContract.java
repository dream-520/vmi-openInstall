package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.nbs.mybatis.core.BaseContract;
import com.tigerjoys.shark.miai.inter.entity.UserTalentVipStatisticsEntity;

/**
 * 数据库中  用户达人收入统计[t_user_talent_vip_statistics]表 接口类
 * @author yangjunming
 * @Date 2017-11-27 11:31:09
 *
 */
public interface IUserTalentVipStatisticsContract extends BaseContract<UserTalentVipStatisticsEntity> {
	/**
	 * 添加收益分成
	 * @id	
	 * @return
	 * @throws Exception
	 */
	public int addTradeAmount(long userid,int tradeAmount) throws Exception;
}
