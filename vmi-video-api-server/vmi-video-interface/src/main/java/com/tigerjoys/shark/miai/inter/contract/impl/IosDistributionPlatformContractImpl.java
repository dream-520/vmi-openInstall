package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IIosDistributionPlatformContract;
import com.tigerjoys.shark.miai.inter.entity.IosDistributionPlatformEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.IosDistributionPlatformMapper;

/**
 * 数据库中  IOS分发平台[t_ios_distribution_platform]表 接口实现类
 * @author yangjunming
 * @Date 2019-04-02 15:15:02
 *
 */
@Repository
public class IosDistributionPlatformContractImpl extends AbstractBaseContract<IosDistributionPlatformEntity , IosDistributionPlatformMapper> implements IIosDistributionPlatformContract {
	
}
