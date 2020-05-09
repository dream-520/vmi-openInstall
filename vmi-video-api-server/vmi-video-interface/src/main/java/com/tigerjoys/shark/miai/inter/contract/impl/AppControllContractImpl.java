package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAppControllContract;
import com.tigerjoys.shark.miai.inter.entity.AppControllEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AppControllMapper;

/**
 * 数据库中  审核数据控制[t_app_controll]表 接口实现类
 * @author shiming
 * @Date 2019-06-22 15:12:52
 *
 */
@Repository
public class AppControllContractImpl extends AbstractBaseContract<AppControllEntity , AppControllMapper> implements IAppControllContract {
	
}
