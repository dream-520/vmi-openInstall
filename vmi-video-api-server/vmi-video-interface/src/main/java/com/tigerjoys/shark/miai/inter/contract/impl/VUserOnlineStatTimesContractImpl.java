package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IVUserOnlineStatTimesContract;
import com.tigerjoys.shark.miai.inter.entity.VUserOnlineStatTimesEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.VUserOnlineStatTimesMapper;

/**
 * 数据库中  [t_v_user_online_stat_times]表 接口实现类
 * @author shiming
 * @Date 2019-03-06 10:41:07
 *
 */
@Repository
public class VUserOnlineStatTimesContractImpl extends AbstractBaseContract<VUserOnlineStatTimesEntity , VUserOnlineStatTimesMapper> implements IVUserOnlineStatTimesContract {
	
}
