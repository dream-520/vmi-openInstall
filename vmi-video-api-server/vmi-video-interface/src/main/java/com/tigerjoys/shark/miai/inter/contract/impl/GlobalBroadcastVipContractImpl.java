package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IGlobalBroadcastVipContract;
import com.tigerjoys.shark.miai.inter.entity.GlobalBroadcastVipEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.GlobalBroadcastVipMapper;

/**
 * 数据库中  全局广播[t_global_broadcast_vip]表 接口实现类
 * @author shiming
 * @Date 2019-12-03 18:10:56
 *
 */
@Repository
public class GlobalBroadcastVipContractImpl extends AbstractBaseContract<GlobalBroadcastVipEntity , GlobalBroadcastVipMapper> implements IGlobalBroadcastVipContract {
	
}
