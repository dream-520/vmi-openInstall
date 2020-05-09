package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserDiamondAccountContract;
import com.tigerjoys.shark.miai.inter.entity.UserDiamondAccountEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserDiamondAccountMapper;

/**
 * 数据库中  用户钻石账户[t_user_diamond_account]表 接口实现类
 * @author mouzhanpeng
 * @Date 2017-05-10 16:39:11
 *
 */
@Repository
public class UserDiamondAccountContractImpl extends AbstractBaseContract<UserDiamondAccountEntity , UserDiamondAccountMapper> implements IUserDiamondAccountContract {
	
}
