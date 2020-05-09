package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserSignCardContract;
import com.tigerjoys.shark.miai.inter.entity.UserSignCardEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserSignCardMapper;

/**
 * 数据库中  用户补签卡账户[t_user_sign_card]表 接口实现类
 * @author lipeng
 * @Date 2018-12-06 11:49:35
 *
 */
@Repository
public class UserSignCardContractImpl extends AbstractBaseContract<UserSignCardEntity , UserSignCardMapper> implements IUserSignCardContract {
	
}
