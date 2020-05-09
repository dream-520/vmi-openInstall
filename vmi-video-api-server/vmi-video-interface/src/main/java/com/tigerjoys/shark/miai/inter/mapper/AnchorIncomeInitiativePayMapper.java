package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AnchorIncomeInitiativePayEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  主播主动拨打充值分析表[t_anchor_income_initiative_pay]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-06-25 14:42:20
 *
 */
@Producer(entityType=AnchorIncomeInitiativePayEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AnchorIncomeInitiativePayMapper extends BaseMapper<AnchorIncomeInitiativePayEntity> {
    
}