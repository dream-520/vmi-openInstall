package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.RechargeCustomCategoryEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  充钻自定义分类表[t_recharge_custom_category]表 dao通用操作接口实现类
 * @author mouzhanpeng
 * @Date 2018-08-03 14:04:38
 *
 */
@Producer(entityType=RechargeCustomCategoryEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface RechargeCustomCategoryMapper extends BaseMapper<RechargeCustomCategoryEntity> {
    
}