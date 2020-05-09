package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.CommodityEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  领取商品表[t_commodity]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2018-12-07 15:16:38
 *
 */
@Producer(entityType=CommodityEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface CommodityMapper extends BaseMapper<CommodityEntity> {
    
}