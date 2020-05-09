package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.AnchorIntimateRankingsEntity;
import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  主播亲密排行榜[t_anchor_intimate_rankings]表 接口类
 * @author yangjunming
 * @Date 2018-10-30 10:16:48
 *
 */
public interface IAnchorIntimateRankingsContract extends BaseContract<AnchorIntimateRankingsEntity> {
	/**
	 * 增加累计收益
	 * @param anchorUserId   主播ID
	 * @param uesrid    用户ID
	 * @param price		钻石
	 * @return
	 * @throws Exception
	 */
	public int addIncome(long anchorUserId, Long uesrid,Integer price) throws Exception ;
}
