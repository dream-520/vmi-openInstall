package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserSpecialRechargeContract;
import com.tigerjoys.shark.miai.inter.entity.UserSpecialRechargeEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserSpecialRechargeMapper;

/**
 * 数据库中  特殊用户充值[t_user_special_recharge]表 接口实现类
 * @author yangjunming
 * @Date 2020-02-26 17:51:20
 *
 */
@Repository
public class UserSpecialRechargeContractImpl extends AbstractBaseContract<UserSpecialRechargeEntity , UserSpecialRechargeMapper> implements IUserSpecialRechargeContract {
	
}
