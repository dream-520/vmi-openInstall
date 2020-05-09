package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAppPackageContract;
import com.tigerjoys.shark.miai.inter.entity.AppPackageEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AppPackageMapper;

/**
 * 数据库中  包名信息表[t_app_package]表 接口实现类
 * @author lipeng
 * @Date 2018-12-28 10:30:02
 *
 */
@Repository
public class AppPackageContractImpl extends AbstractBaseContract<AppPackageEntity , AppPackageMapper> implements IAppPackageContract {
	
}
