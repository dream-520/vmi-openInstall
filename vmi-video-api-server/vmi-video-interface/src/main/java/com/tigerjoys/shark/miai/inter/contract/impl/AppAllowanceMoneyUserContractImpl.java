package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAppAllowanceMoneyUserContract;
import com.tigerjoys.shark.miai.inter.entity.AppAllowanceMoneyUserEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AppAllowanceMoneyUserMapper;

/**
 * 数据库中  app补助用户记录表[t_app_allowance_money_user]表 接口实现类
 * @author shiming
 * @Date 2019-07-11 17:47:23
 *
 */
@Repository
public class AppAllowanceMoneyUserContractImpl extends AbstractBaseContract<AppAllowanceMoneyUserEntity , AppAllowanceMoneyUserMapper> implements IAppAllowanceMoneyUserContract {
	
}
