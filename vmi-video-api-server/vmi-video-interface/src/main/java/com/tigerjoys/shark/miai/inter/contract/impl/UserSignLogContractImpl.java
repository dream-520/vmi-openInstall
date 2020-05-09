package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserSignLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserSignLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserSignLogMapper;

/**
 * 数据库中  用户签到记录表[t_user_sign_log]表 接口实现类
 * @author lipeng
 * @Date 2018-12-06 11:52:43
 *
 */
@Repository
public class UserSignLogContractImpl extends AbstractBaseContract<UserSignLogEntity , UserSignLogMapper> implements IUserSignLogContract {
	
}
