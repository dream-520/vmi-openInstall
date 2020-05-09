package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserCharmContract;
import com.tigerjoys.shark.miai.inter.entity.UserCharmEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserCharmMapper;

/**
 * 数据库中  用户魅力值表[t_user_charm]表 接口实现类
 * @author lipeng
 * @Date 2017-08-24 15:42:34
 *
 */
@Repository
public class UserCharmContractImpl extends AbstractBaseContract<UserCharmEntity , UserCharmMapper> implements IUserCharmContract {
	
}
