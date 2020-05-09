package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserSignControlContract;
import com.tigerjoys.shark.miai.inter.entity.UserSignControlEntity;
import com.tigerjoys.nbs.mybatis.core.contract.RedisCacheContract;
import com.tigerjoys.shark.miai.inter.mapper.UserSignControlMapper;

/**
 * 数据库中  用户签到控制[t_user_sign_control]表 接口实现类
 * @author lipeng
 * @Date 2019-09-05 12:01:58
 *
 */
@Repository
public class UserSignControlContractImpl extends RedisCacheContract<UserSignControlEntity , UserSignControlMapper> implements IUserSignControlContract {
	
}
