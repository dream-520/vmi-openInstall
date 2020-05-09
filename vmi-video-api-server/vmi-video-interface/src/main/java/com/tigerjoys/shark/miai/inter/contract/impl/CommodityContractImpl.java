package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.ICommodityContract;
import com.tigerjoys.shark.miai.inter.entity.CommodityEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.CommodityMapper;

/**
 * 数据库中  领取商品表[t_commodity]表 接口实现类
 * @author lipeng
 * @Date 2018-12-07 15:16:38
 *
 */
@Repository
public class CommodityContractImpl extends AbstractBaseContract<CommodityEntity , CommodityMapper> implements ICommodityContract {
	
}
