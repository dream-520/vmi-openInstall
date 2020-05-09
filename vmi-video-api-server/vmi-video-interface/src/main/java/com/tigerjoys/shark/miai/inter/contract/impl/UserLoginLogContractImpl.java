package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserLoginLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserLoginLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserLoginLogMapper;

/**
 * 数据库中  用户登录信息变换记录表[t_user_login_log]表 接口实现类
 * @author lipeng
 * @Date 2017-06-08 11:57:22
 *
 */
@Repository
public class UserLoginLogContractImpl extends AbstractBaseContract<UserLoginLogEntity , UserLoginLogMapper> implements IUserLoginLogContract {
	
}
