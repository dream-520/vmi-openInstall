package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IIosDistributionDetailContract;
import com.tigerjoys.shark.miai.inter.entity.IosDistributionDetailEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.IosDistributionDetailMapper;

/**
 * 数据库中  IOS分发表明细[t_ios_distribution_detail]表 接口实现类
 * @author yangjunming
 * @Date 2019-04-02 15:15:02
 *
 */
@Repository
public class IosDistributionDetailContractImpl extends AbstractBaseContract<IosDistributionDetailEntity , IosDistributionDetailMapper> implements IIosDistributionDetailContract {
	
}
