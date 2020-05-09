package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IRechargePriceContract;
import com.tigerjoys.shark.miai.inter.entity.RechargePriceEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.nbs.mybatis.core.contract.RedisCacheContract;
import com.tigerjoys.shark.miai.inter.mapper.RechargePriceMapper;

/**
 * 数据库中  充值价格列表[t_recharge_price]表 接口实现类
 * @author mouzhanpeng
 * @Date 2017-05-10 16:39:11
 *
 */
@Repository
public class RechargePriceContractImpl extends AbstractBaseContract<RechargePriceEntity , RechargePriceMapper> implements IRechargePriceContract {
	
}
