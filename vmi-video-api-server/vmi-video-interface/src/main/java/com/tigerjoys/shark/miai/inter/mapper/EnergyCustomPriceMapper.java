package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.EnergyCustomPriceEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  充能量自定义价格列表[t_energy_custom_price]表 dao通用操作接口实现类
 * @author mouzhanpeng
 * @Date 2018-08-16 10:41:55
 *
 */
@Producer(entityType=EnergyCustomPriceEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface EnergyCustomPriceMapper extends BaseMapper<EnergyCustomPriceEntity> {
    
}