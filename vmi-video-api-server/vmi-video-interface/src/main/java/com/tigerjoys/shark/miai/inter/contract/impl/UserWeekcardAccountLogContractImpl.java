package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserWeekcardAccountLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserWeekcardAccountLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserWeekcardAccountLogMapper;

/**
 * 数据库中  用户周卡账户表[t_user_weekcard_account_log]表 接口实现类
 * @author yangjunming
 * @Date 2019-11-14 11:30:48
 *
 */
@Repository
public class UserWeekcardAccountLogContractImpl extends AbstractBaseContract<UserWeekcardAccountLogEntity , UserWeekcardAccountLogMapper> implements IUserWeekcardAccountLogContract {
	
}
