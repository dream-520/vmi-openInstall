package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IRechargeOrderContract;
import com.tigerjoys.shark.miai.inter.entity.RechargeOrderEntity;
import com.tigerjoys.nbs.common.utils.sequence.IdGenerater;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.RechargeOrderMapper;

/**
 * 数据库中  充值订单表[t_recharge_order]表 接口实现类
 * @author mouzhanpeng
 * @Date 2017-08-14 17:31:08
 *
 */
@Repository
public class RechargeOrderContractImpl extends AbstractBaseContract<RechargeOrderEntity , RechargeOrderMapper> implements IRechargeOrderContract {
	
	@Override
	public void insert(RechargeOrderEntity t) throws Exception {
		t.setOrder_id(IdGenerater.generateId());
		mapper.insert(t);
	};
}
