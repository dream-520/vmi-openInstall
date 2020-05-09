package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserRegLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserRegLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserRegLogMapper;

/**
 * 数据库中  用户注册信息记录表[t_user_reg_log]表 接口实现类
 * @author chengang
 * @Date 2018-05-02 18:00:10
 *
 */
@Repository
public class UserRegLogContractImpl extends AbstractBaseContract<UserRegLogEntity , UserRegLogMapper> implements IUserRegLogContract {
	
}
