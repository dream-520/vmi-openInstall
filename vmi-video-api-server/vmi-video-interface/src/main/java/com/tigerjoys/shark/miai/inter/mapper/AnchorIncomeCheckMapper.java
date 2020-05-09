package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AnchorIncomeCheckEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  主播收益考核表[t_anchor_income_check]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-04-18 17:07:09
 *
 */
@Producer(entityType=AnchorIncomeCheckEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AnchorIncomeCheckMapper extends BaseMapper<AnchorIncomeCheckEntity> {
    
}