package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.TalentPriceEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  达人等级价格管理[t_talent_price]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2017-12-19 18:20:42
 *
 */
@Producer(entityType=TalentPriceEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface TalentPriceMapper extends BaseMapper<TalentPriceEntity> {
    
}