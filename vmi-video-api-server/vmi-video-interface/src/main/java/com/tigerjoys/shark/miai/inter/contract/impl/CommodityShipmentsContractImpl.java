package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.ICommodityShipmentsContract;
import com.tigerjoys.shark.miai.inter.entity.CommodityShipmentsEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.CommodityShipmentsMapper;

/**
 * 数据库中  商品发货表[t_commodity_shipments]表 接口实现类
 * @author lipeng
 * @Date 2018-12-07 15:21:43
 *
 */
@Repository
public class CommodityShipmentsContractImpl extends AbstractBaseContract<CommodityShipmentsEntity , CommodityShipmentsMapper> implements ICommodityShipmentsContract {
	
}
