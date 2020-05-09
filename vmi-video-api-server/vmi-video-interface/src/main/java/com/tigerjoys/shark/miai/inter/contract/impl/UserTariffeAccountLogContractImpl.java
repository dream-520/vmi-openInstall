package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserTariffeAccountLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserTariffeAccountLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserTariffeAccountLogMapper;

/**
 * 数据库中  用户话费账户流水[t_user_tariffe_account_log]表 接口实现类
 * @author lipeng
 * @Date 2019-12-10 14:00:50
 *
 */
@Repository
public class UserTariffeAccountLogContractImpl extends AbstractBaseContract<UserTariffeAccountLogEntity , UserTariffeAccountLogMapper> implements IUserTariffeAccountLogContract {
	
}
