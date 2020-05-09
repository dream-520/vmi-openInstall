package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAppTabContract;
import com.tigerjoys.shark.miai.inter.entity.AppTabEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AppTabMapper;

/**
 * 数据库中  [t_app_tab]表 接口实现类
 * @author shiming
 * @Date 2018-12-25 10:49:36
 *
 */
@Repository
public class AppTabContractImpl extends AbstractBaseContract<AppTabEntity , AppTabMapper> implements IAppTabContract {
	
}
