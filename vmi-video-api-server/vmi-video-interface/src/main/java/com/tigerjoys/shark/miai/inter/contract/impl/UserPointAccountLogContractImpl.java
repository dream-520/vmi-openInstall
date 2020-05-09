package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserPointAccountLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserPointAccountLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserPointAccountLogMapper;

/**
 * 数据库中  用户积分账户流水[t_user_point_account_log]表 接口实现类
 * @author lipeng
 * @Date 2019-09-05 11:35:37
 *
 */
@Repository
public class UserPointAccountLogContractImpl extends AbstractBaseContract<UserPointAccountLogEntity , UserPointAccountLogMapper> implements IUserPointAccountLogContract {
	
}
