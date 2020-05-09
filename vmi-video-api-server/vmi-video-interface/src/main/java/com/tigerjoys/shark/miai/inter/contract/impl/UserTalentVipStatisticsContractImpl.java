package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserTalentVipStatisticsContract;
import com.tigerjoys.shark.miai.inter.entity.UserTalentVipStatisticsEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserTalentVipStatisticsMapper;

/**
 * 数据库中  用户达人收入统计[t_user_talent_vip_statistics]表 接口实现类
 * @author yangjunming
 * @Date 2017-11-27 11:31:09
 *
 */
@Repository
public class UserTalentVipStatisticsContractImpl extends AbstractBaseContract<UserTalentVipStatisticsEntity , UserTalentVipStatisticsMapper> implements IUserTalentVipStatisticsContract {

	@Override
	public int addTradeAmount(long userid, int tradeAmount) throws Exception {
		return mapper.updateByStatement("trade_num=trade_num+1,trade_amount=trade_amount+"+tradeAmount, "userid="+userid);
	}
	
}
