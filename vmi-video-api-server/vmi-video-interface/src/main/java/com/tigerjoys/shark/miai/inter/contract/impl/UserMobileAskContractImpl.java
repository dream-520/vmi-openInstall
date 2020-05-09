package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserMobileAskContract;
import com.tigerjoys.shark.miai.inter.entity.UserMobileAskEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserMobileAskMapper;

/**
 * 数据库中  [t_user_mobile_ask]表 接口实现类
 * @author yangjunming
 * @Date 2019-05-16 15:34:24
 *
 */
@Repository
public class UserMobileAskContractImpl extends AbstractBaseContract<UserMobileAskEntity , UserMobileAskMapper> implements IUserMobileAskContract {
	
}
