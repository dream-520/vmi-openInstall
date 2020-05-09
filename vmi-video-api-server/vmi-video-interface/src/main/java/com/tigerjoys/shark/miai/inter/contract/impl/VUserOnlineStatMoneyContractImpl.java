package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IVUserOnlineStatMoneyContract;
import com.tigerjoys.shark.miai.inter.entity.VUserOnlineStatMoneyEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.VUserOnlineStatMoneyMapper;

/**
 * 数据库中  [t_v_user_online_stat_money]表 接口实现类
 * @author shiming
 * @Date 2019-03-06 10:41:07
 *
 */
@Repository
public class VUserOnlineStatMoneyContractImpl extends AbstractBaseContract<VUserOnlineStatMoneyEntity , VUserOnlineStatMoneyMapper> implements IVUserOnlineStatMoneyContract {
	
}
