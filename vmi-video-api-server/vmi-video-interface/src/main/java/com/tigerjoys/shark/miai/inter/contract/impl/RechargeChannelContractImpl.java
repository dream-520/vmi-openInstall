package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IRechargeChannelContract;
import com.tigerjoys.shark.miai.inter.entity.RechargeChannelEntity;
import com.tigerjoys.nbs.mybatis.core.contract.RedisCacheContract;
import com.tigerjoys.shark.miai.inter.mapper.RechargeChannelMapper;

/**
 * 数据库中  充值渠道列表[t_recharge_channel]表 接口实现类
 * @author mouzhanpeng
 * @Date 2017-05-10 16:39:11
 *
 */
@Repository
public class RechargeChannelContractImpl extends RedisCacheContract<RechargeChannelEntity , RechargeChannelMapper> implements IRechargeChannelContract {
	
}
