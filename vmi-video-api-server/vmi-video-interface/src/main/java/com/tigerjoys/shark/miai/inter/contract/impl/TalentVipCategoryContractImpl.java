package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.nbs.mybatis.core.contract.RedisCacheContract;
import com.tigerjoys.shark.miai.inter.contract.ITalentVipCategoryContract;
import com.tigerjoys.shark.miai.inter.entity.TalentVipCategoryEntity;
import com.tigerjoys.shark.miai.inter.mapper.TalentVipCategoryMapper;

/**
 * 数据库中  达人VIP字典表[t_talent_vip_category]表 接口实现类
 * @author chengang
 * @Date 2017-08-21 08:55:32
 *
 */
@Repository
public class TalentVipCategoryContractImpl extends RedisCacheContract<TalentVipCategoryEntity , TalentVipCategoryMapper> implements ITalentVipCategoryContract {
	
}
