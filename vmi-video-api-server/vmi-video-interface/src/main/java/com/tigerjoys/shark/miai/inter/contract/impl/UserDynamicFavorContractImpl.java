package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserDynamicFavorContract;
import com.tigerjoys.shark.miai.inter.entity.UserDynamicFavorEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserDynamicFavorMapper;

/**
 * 数据库中  [t_user_dynamic_favor]表 接口实现类
 * @author shiming
 * @Date 2017-08-14 14:42:23
 *
 */
@Repository
public class UserDynamicFavorContractImpl extends AbstractBaseContract<UserDynamicFavorEntity , UserDynamicFavorMapper> implements IUserDynamicFavorContract {
	
}
