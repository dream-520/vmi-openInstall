package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.CouponTemplateEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  代金券类别[t_coupon_template]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2017-09-08 15:30:38
 *
 */
@Producer(entityType=CouponTemplateEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface CouponTemplateMapper extends BaseMapper<CouponTemplateEntity> {
    
}