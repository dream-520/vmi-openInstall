package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.CommodityShipmentsEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  商品发货表[t_commodity_shipments]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2018-12-07 15:21:43
 *
 */
@Producer(entityType=CommodityShipmentsEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface CommodityShipmentsMapper extends BaseMapper<CommodityShipmentsEntity> {
    
}