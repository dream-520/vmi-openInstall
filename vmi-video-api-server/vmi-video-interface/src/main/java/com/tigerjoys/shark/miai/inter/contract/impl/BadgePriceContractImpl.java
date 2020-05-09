package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IBadgePriceContract;
import com.tigerjoys.shark.miai.inter.entity.BadgePriceEntity;
import com.tigerjoys.nbs.mybatis.core.contract.RedisCacheContract;
import com.tigerjoys.shark.miai.inter.mapper.BadgePriceMapper;

/**
 * 数据库中  诚信徽章价格列表[t_badge_price]表 接口实现类
 * @author mouzhanpeng
 * @Date 2017-11-13 16:52:00
 *
 */
@Repository
public class BadgePriceContractImpl extends RedisCacheContract<BadgePriceEntity , BadgePriceMapper> implements IBadgePriceContract {
	
}
