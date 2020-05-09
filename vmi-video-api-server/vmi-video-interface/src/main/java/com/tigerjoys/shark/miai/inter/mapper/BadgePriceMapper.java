package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.BadgePriceEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  诚信徽章价格列表[t_badge_price]表 dao通用操作接口实现类
 * @author mouzhanpeng
 * @Date 2017-11-13 16:52:00
 *
 */
@Producer(entityType=BadgePriceEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface BadgePriceMapper extends BaseMapper<BadgePriceEntity> {
    
}