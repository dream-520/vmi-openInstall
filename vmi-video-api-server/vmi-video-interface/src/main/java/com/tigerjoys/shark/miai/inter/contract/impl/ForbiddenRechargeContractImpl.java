package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IForbiddenRechargeContract;
import com.tigerjoys.shark.miai.inter.entity.ForbiddenRechargeEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.ForbiddenRechargeMapper;

/**
 * 数据库中  用户禁用充值入口[t_forbidden_recharge]表 接口实现类
 * @author lipeng
 * @Date 2020-03-14 15:08:39
 *
 */
@Repository
public class ForbiddenRechargeContractImpl extends AbstractBaseContract<ForbiddenRechargeEntity , ForbiddenRechargeMapper> implements IForbiddenRechargeContract {
	
}
