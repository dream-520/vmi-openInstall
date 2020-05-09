package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.RedFlowerPriceEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  小红花价格列表[t_red_flower_price]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-08-03 14:04:18
 *
 */
@Producer(entityType=RedFlowerPriceEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface RedFlowerPriceMapper extends BaseMapper<RedFlowerPriceEntity> {
    
}