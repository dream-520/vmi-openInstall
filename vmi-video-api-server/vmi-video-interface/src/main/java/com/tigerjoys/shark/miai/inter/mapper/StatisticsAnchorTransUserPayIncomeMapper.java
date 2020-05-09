package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.StatisticsAnchorTransUserPayIncomeEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  [t_statistics_anchor_trans_user_pay_income]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-07-23 15:32:31
 *
 */
@Producer(entityType=StatisticsAnchorTransUserPayIncomeEntity.class,providerType=DefaultSqlProvider.class,increment=true)
@Mapper
public interface StatisticsAnchorTransUserPayIncomeMapper extends BaseMapper<StatisticsAnchorTransUserPayIncomeEntity> {
    
}