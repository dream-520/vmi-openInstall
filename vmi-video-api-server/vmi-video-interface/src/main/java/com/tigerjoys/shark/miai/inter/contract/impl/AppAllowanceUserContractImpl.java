package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAppAllowanceUserContract;
import com.tigerjoys.shark.miai.inter.entity.AppAllowanceUserEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AppAllowanceUserMapper;

/**
 * 数据库中  app补助用户记录表[t_app_allowance_user]表 接口实现类
 * @author shiming
 * @Date 2019-07-10 17:41:27
 *
 */
@Repository
public class AppAllowanceUserContractImpl extends AbstractBaseContract<AppAllowanceUserEntity , AppAllowanceUserMapper> implements IAppAllowanceUserContract {
	
}
