package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAppVersionContract;
import com.tigerjoys.shark.miai.inter.entity.AppVersionEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AppVersionMapper;

/**
 * 数据库中  版本信息表[t_app_version]表 接口实现类
 * @author lipeng
 * @Date 2017-05-17 16:00:28
 *
 */
@Repository
public class AppVersionContractImpl extends AbstractBaseContract<AppVersionEntity , AppVersionMapper> implements IAppVersionContract {
	
}
