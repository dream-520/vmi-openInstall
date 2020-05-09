package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.ICommodityGroupContract;
import com.tigerjoys.shark.miai.inter.entity.CommodityGroupEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.CommodityGroupMapper;

/**
 * 数据库中  商品分组表[t_commodity_group]表 接口实现类
 * @author lipeng
 * @Date 2018-12-07 15:20:02
 *
 */
@Repository
public class CommodityGroupContractImpl extends AbstractBaseContract<CommodityGroupEntity , CommodityGroupMapper> implements ICommodityGroupContract {
	
}
