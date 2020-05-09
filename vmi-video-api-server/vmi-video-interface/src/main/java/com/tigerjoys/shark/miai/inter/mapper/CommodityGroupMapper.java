package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.CommodityGroupEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  商品分组表[t_commodity_group]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2018-12-07 15:20:02
 *
 */
@Producer(entityType=CommodityGroupEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface CommodityGroupMapper extends BaseMapper<CommodityGroupEntity> {
    
}