package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IGuardVipOrderContract;
import com.tigerjoys.shark.miai.inter.entity.GuardVipOrderEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.GuardVipOrderMapper;

/**
 * 数据库中  购买守户和VIP订单[t_guard_vip_order]表 接口实现类
 * @author yangjunming
 * @Date 2019-10-04 20:27:15
 *
 */
@Repository
public class GuardVipOrderContractImpl extends AbstractBaseContract<GuardVipOrderEntity , GuardVipOrderMapper> implements IGuardVipOrderContract {
	
}
