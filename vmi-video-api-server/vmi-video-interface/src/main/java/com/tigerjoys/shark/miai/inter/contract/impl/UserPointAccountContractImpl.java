package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserPointAccountContract;
import com.tigerjoys.shark.miai.inter.entity.UserPointAccountEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserPointAccountMapper;

/**
 * 数据库中  用户积分账户[t_user_point_account]表 接口实现类
 * @author lipeng
 * @Date 2019-09-05 11:23:23
 *
 */
@Repository
public class UserPointAccountContractImpl extends AbstractBaseContract<UserPointAccountEntity , UserPointAccountMapper> implements IUserPointAccountContract {
	
}
