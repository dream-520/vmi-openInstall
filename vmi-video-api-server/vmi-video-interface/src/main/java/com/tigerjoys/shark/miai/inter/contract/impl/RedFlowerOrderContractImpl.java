package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IRedFlowerOrderContract;
import com.tigerjoys.shark.miai.inter.entity.RechargeOrderEntity;
import com.tigerjoys.shark.miai.inter.entity.RedFlowerOrderEntity;
import com.tigerjoys.nbs.common.utils.sequence.IdGenerater;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.RedFlowerOrderMapper;

/**
 * 数据库中  小红花订单表[t_red_flower_order]表 接口实现类
 * @author yangjunming
 * @Date 2019-08-16 12:39:09
 *
 */
@Repository
public class RedFlowerOrderContractImpl extends AbstractBaseContract<RedFlowerOrderEntity , RedFlowerOrderMapper> implements IRedFlowerOrderContract {
	@Override
	public void insert(RedFlowerOrderEntity t) throws Exception {
		t.setOrder_id(IdGenerater.generateId());
		mapper.insert(t);
	};
}
