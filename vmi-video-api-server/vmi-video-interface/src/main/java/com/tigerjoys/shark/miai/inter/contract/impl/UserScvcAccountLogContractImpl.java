package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserScvcAccountLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserScvcAccountLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserScvcAccountLogMapper;

/**
 * 数据库中  用户SCVC账户流水[t_user_scvc_account_log]表 接口实现类
 * @author mouzhanpeng
 * @Date 2018-01-24 17:57:24
 *
 */
@Repository
public class UserScvcAccountLogContractImpl extends AbstractBaseContract<UserScvcAccountLogEntity , UserScvcAccountLogMapper> implements IUserScvcAccountLogContract {
	
}
