package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.CouponEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  [t_coupon]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2017-08-18 19:27:26
 *
 */
@Producer(entityType=CouponEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface CouponMapper extends BaseMapper<CouponEntity> {
    
}