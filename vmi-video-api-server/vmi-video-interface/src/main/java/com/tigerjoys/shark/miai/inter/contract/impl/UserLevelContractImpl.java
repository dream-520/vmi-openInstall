package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserLevelContract;
import com.tigerjoys.shark.miai.inter.entity.UserLevelEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserLevelMapper;

/**
 * 数据库中  用户等级管理[t_user_level]表 接口实现类
 * @author shiming
 * @Date 2018-10-30 09:02:26
 *
 */
@Repository
public class UserLevelContractImpl extends AbstractBaseContract<UserLevelEntity , UserLevelMapper> implements IUserLevelContract {
	
}
