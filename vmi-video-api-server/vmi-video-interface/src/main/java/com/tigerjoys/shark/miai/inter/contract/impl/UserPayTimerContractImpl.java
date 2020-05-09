package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserPayTimerContract;
import com.tigerjoys.shark.miai.inter.entity.UserPayTimerEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserPayTimerMapper;

/**
 * 数据库中  [t_user_pay_timer]表 接口实现类
 * @author shiming
 * @Date 2019-01-02 16:25:51
 *
 */
@Repository
public class UserPayTimerContractImpl extends AbstractBaseContract<UserPayTimerEntity , UserPayTimerMapper> implements IUserPayTimerContract {
	
}
