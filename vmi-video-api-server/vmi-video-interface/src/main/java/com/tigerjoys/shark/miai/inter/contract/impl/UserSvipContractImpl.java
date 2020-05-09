package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserSvipContract;
import com.tigerjoys.shark.miai.inter.entity.UserSvipEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserSvipMapper;

/**
 * 数据库中  用户充值数据[t_user_svip]表 接口实现类
 * @author chengang
 * @Date 2018-01-26 08:21:25
 *
 */
@Repository
public class UserSvipContractImpl extends AbstractBaseContract<UserSvipEntity , UserSvipMapper> implements IUserSvipContract {
	
}
