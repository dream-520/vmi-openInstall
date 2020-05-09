package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.RechargeOrderEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  充值订单表[t_recharge_order]表 dao通用操作接口实现类
 * @author mouzhanpeng
 * @Date 2017-08-14 17:31:08
 *
 */
@Producer(entityType=RechargeOrderEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface RechargeOrderMapper extends BaseMapper<RechargeOrderEntity> {
    
}