package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.CommodityGroupRelationshipEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户商品关系表[t_commodity_group_relationship]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2018-12-10 14:35:58
 *
 */
@Producer(entityType=CommodityGroupRelationshipEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface CommodityGroupRelationshipMapper extends BaseMapper<CommodityGroupRelationshipEntity> {
    
}