package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserEnergyAccountLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserEnergyAccountLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserEnergyAccountLogMapper;

/**
 * 数据库中  用户能量账户流水[t_user_energy_account_log]表 接口实现类
 * @author mouzhanpeng
 * @Date 2018-08-17 14:31:33
 *
 */
@Repository
public class UserEnergyAccountLogContractImpl extends AbstractBaseContract<UserEnergyAccountLogEntity , UserEnergyAccountLogMapper> implements IUserEnergyAccountLogContract {
	
}
