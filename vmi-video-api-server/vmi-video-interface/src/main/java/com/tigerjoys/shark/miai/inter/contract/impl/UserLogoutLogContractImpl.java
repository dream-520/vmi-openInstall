package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserLogoutLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserLogoutLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserLogoutLogMapper;

/**
 * 数据库中  用户账号注销记录表[t_user_logout_log]表 接口实现类
 * @author lipeng
 * @Date 2020-01-06 16:15:35
 *
 */
@Repository
public class UserLogoutLogContractImpl extends AbstractBaseContract<UserLogoutLogEntity , UserLogoutLogMapper> implements IUserLogoutLogContract {
	
}
