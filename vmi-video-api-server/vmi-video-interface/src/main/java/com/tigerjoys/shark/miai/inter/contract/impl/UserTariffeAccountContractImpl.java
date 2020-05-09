package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserTariffeAccountContract;
import com.tigerjoys.shark.miai.inter.entity.UserTariffeAccountEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserTariffeAccountMapper;

/**
 * 数据库中  用户话费账户[t_user_tariffe_account]表 接口实现类
 * @author lipeng
 * @Date 2019-12-10 13:55:12
 *
 */
@Repository
public class UserTariffeAccountContractImpl extends AbstractBaseContract<UserTariffeAccountEntity , UserTariffeAccountMapper> implements IUserTariffeAccountContract {
	
}
