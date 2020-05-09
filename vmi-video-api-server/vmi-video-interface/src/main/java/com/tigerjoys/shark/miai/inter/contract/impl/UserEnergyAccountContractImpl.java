package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserEnergyAccountContract;
import com.tigerjoys.shark.miai.inter.entity.UserEnergyAccountEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserEnergyAccountMapper;

/**
 * 数据库中  用户能量账户[t_user_energy_account]表 接口实现类
 * @author mouzhanpeng
 * @Date 2018-08-17 14:31:33
 *
 */
@Repository
public class UserEnergyAccountContractImpl extends AbstractBaseContract<UserEnergyAccountEntity , UserEnergyAccountMapper> implements IUserEnergyAccountContract {
	
}
