package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.CashFlowEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  现金流表[t_cash_flow]表 dao通用操作接口实现类
 * @author chengang
 * @Date 2017-05-11 15:39:46
 *
 */
@Producer(entityType=CashFlowEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface CashFlowMapper extends BaseMapper<CashFlowEntity> {
    
}