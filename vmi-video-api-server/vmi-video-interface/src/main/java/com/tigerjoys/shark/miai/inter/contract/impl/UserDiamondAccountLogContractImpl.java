package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserDiamondAccountLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserDiamondAccountLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserDiamondAccountLogMapper;

/**
 * 数据库中  用户钻石账户流水[t_user_diamond_account_log]表 接口实现类
 * @author mouzhanpeng
 * @Date 2017-05-10 16:39:11
 *
 */
@Repository
public class UserDiamondAccountLogContractImpl extends AbstractBaseContract<UserDiamondAccountLogEntity , UserDiamondAccountLogMapper> implements IUserDiamondAccountLogContract {
	
}
