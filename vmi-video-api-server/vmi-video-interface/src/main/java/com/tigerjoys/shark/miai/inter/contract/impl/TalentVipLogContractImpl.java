package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.ITalentVipLogContract;
import com.tigerjoys.shark.miai.inter.entity.TalentVipLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.TalentVipLogMapper;

/**
 * 数据库中  达人VIP充值记录[t_talent_vip_log]表 接口实现类
 * @author chengang
 * @Date 2017-08-21 08:55:32
 *
 */
@Repository
public class TalentVipLogContractImpl extends AbstractBaseContract<TalentVipLogEntity , TalentVipLogMapper> implements ITalentVipLogContract {
	
}
