package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserSignRechargeContract;
import com.tigerjoys.shark.miai.inter.entity.UserSignRechargeEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserSignRechargeMapper;

/**
 * 数据库中  用户签到送话费表[t_user_sign_recharge]表 接口实现类
 * @author lipeng
 * @Date 2019-12-07 15:46:04
 *
 */
@Repository
public class UserSignRechargeContractImpl extends AbstractBaseContract<UserSignRechargeEntity , UserSignRechargeMapper> implements IUserSignRechargeContract {
	
}
