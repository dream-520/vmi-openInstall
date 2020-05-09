package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.nbs.mybatis.core.contract.RedisCacheContract;
import com.tigerjoys.shark.miai.inter.contract.ITalentVipContract;
import com.tigerjoys.shark.miai.inter.entity.TalentVipEntity;
import com.tigerjoys.shark.miai.inter.mapper.TalentVipMapper;

/**
 * 数据库中  达人VIP表[t_talent_vip]表 接口实现类
 * @author chengang
 * @Date 2017-08-21 08:55:32
 *
 */
@Repository
public class TalentVipContractImpl extends RedisCacheContract<TalentVipEntity , TalentVipMapper> implements ITalentVipContract {
	
}
