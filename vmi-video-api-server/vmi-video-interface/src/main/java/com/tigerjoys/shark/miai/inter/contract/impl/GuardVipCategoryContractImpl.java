package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IGuardVipCategoryContract;
import com.tigerjoys.shark.miai.inter.entity.GuardVipCategoryEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.GuardVipCategoryMapper;

/**
 * 数据库中  购买守户和VIP[t_guard_vip_category]表 接口实现类
 * @author yangjunming
 * @Date 2019-10-04 20:27:15
 *
 */
@Repository
public class GuardVipCategoryContractImpl extends AbstractBaseContract<GuardVipCategoryEntity , GuardVipCategoryMapper> implements IGuardVipCategoryContract {
	
}
